/*******************************************************************************
 * Copyright (c) 2017 Red Hat Inc and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Red Hat Inc - initial API and implementation
 *******************************************************************************/
package org.eclipse.kapua.service.authentication.shiro.realm;

import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonString;
import javax.json.JsonValue;

import org.eclipse.kapua.KapuaException;
import org.eclipse.kapua.KapuaRuntimeException;
import org.eclipse.kapua.service.authentication.shiro.setting.KapuaAuthenticationSetting;
import org.eclipse.kapua.service.authentication.shiro.setting.KapuaAuthenticationSettingKeys;
import org.jose4j.jwk.HttpsJwks;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.jwt.consumer.JwtContext;
import org.jose4j.keys.resolvers.HttpsJwksVerificationKeyResolver;

public final class JwtHelper {

    // FIXME: Add a time-to-live cache???
    private static final Map<String, URI> ISSUER_JWKSURI_CACHE = new HashMap<>();

    private static final String OPEN_ID_CONFIGURATION_WELL_KNOWN_PATH = "/.well-known/openid-configuration";
    private static final String JWKS_URI_WELL_KNOWN_KEY = "jwks_uri";

    private JwtHelper() {
    }
    

    public static JwtContext processJwt(String jwt) throws InvalidJwtException {
        URI jwksUri = resolveJwksUri(jwt);
        
        HttpsJwks httpsJkws = new HttpsJwks(jwksUri.toString());
        HttpsJwksVerificationKeyResolver httpsJwksKeyResolver = new HttpsJwksVerificationKeyResolver(httpsJkws);

        //
        // Set validator
        KapuaAuthenticationSetting setting = KapuaAuthenticationSetting.getInstance();
        List<String> audiences = setting.getList(String.class, KapuaAuthenticationSettingKeys.AUTHENTICATION_CREDENTIAL_AUDIENCE_ALLOWED);
        List<String> expectedIssuers = setting.getList(String.class, KapuaAuthenticationSettingKeys.AUTHENTICATION_CREDENTIAL_ISSUER_ALLOWED);
        
        JwtConsumer jwtConsumer = new JwtConsumerBuilder()
                .setVerificationKeyResolver(httpsJwksKeyResolver) // Set resolver key
                .setRequireIssuedAt() // Set require reserved claim: iat
                .setRequireExpirationTime() // Set require reserved claim: exp
                .setRequireSubject() // // Set require reserved claim: sub
                .setExpectedIssuers(true, expectedIssuers.toArray(new String[expectedIssuers.size()]))
                .setExpectedAudience(audiences.toArray(new String[audiences.size()]))
                .build();
        return jwtConsumer.process(jwt);
    }

    private static URI resolveJwksUri(final String jwt) {
        try {
            //
            // Parse JWT without validation
            final JwtConsumer jwtConsumer = new JwtConsumerBuilder()
                    .setSkipAllValidators()
                    .setDisableRequireSignature()
                    .setSkipSignatureVerification()
                    .build();
            
            final JwtContext jwtContext = jwtConsumer.process(jwt);
            
            //
            // Resolve Json Web Key Set URI by the issuer
            String issuer = jwtContext.getJwtClaims().getIssuer();
            if (issuer.endsWith("/")) {
                issuer = issuer.substring(0, issuer.length() - 1);
            }

            // Check against cache
            synchronized (ISSUER_JWKSURI_CACHE) {
                if (ISSUER_JWKSURI_CACHE.containsKey(issuer)) {
                    return ISSUER_JWKSURI_CACHE.get(issuer);
                }
            }

            // Read .well-known file
            final JsonObject jsonObject;
            try (final InputStream stream = new URL(issuer + OPEN_ID_CONFIGURATION_WELL_KNOWN_PATH).openStream()) {
                // Parse json response
                jsonObject = Json.createReader(stream).readObject();
            }

            // Get and clean jwks_uri property
            final JsonValue jwksUriJsonValue = jsonObject.get(JWKS_URI_WELL_KNOWN_KEY);
            
            if (jwksUriJsonValue instanceof JsonString) {
                final String jwksUriString = ((JsonString) jwksUriJsonValue).getString();
                final URI uri = new URI(jwksUriString);
                synchronized (ISSUER_JWKSURI_CACHE) {
                    if (ISSUER_JWKSURI_CACHE.containsKey(issuer)) {
                        // re-check
                        return ISSUER_JWKSURI_CACHE.get(issuer);
                    }
                    ISSUER_JWKSURI_CACHE.put(issuer, uri);
                }
                return uri;
            } else {
                throw KapuaException.internalError(String.format("Cannot get the 'jwks_key' property from the .well-known file: %s", jsonObject));
            }
        } catch (Exception e) {
            throw KapuaRuntimeException.internalError(e, "Cannot get Json Web Key Set URI");
        }
    }

}
