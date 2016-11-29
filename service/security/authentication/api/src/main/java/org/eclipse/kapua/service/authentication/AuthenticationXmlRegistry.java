package org.eclipse.kapua.service.authentication;

import org.eclipse.kapua.locator.KapuaLocator;

public class AuthenticationXmlRegistry {

    private final KapuaLocator locator = KapuaLocator.getInstance();
    private final CredentialsFactory credentialsFactory = locator.getFactory(CredentialsFactory.class);

    /**
     * Creates a new {@link UsernamePasswordCredentials} instance
     * 
     * @return
     */
    public UsernamePasswordCredentials newUsernamePasswordCredentials() {
        return credentialsFactory.newUsernamePasswordCredentials(null, null);
    }

    /**
     * Creates a new {@link ApiKeyCredentials} instance
     * 
     * @return
     */
    public ApiKeyCredentials newApiKeyCredentials() {
        return credentialsFactory.newApiKeyCredentials(null);
    }

    /**
     * Creates a new {@link JwtCredentials} instance
     * 
     * @return
     */
    public JwtCredentials newJwtCredentials() {
        return credentialsFactory.newJwtCredentials(null);
    }

    /**
     * Creates a new {@link AccessTokenCredentials} instance
     * 
     * @return
     */
    public AccessTokenCredentials newAccessTokenCredentials() {
        return credentialsFactory.newAccessTokenCredentials(null);
    }
}