package org.eclipse.kapua.service.weather.internal;

import org.eclipse.kapua.KapuaException;
import org.eclipse.kapua.commons.service.internal.AbstractKapuaService;
import org.eclipse.kapua.locator.KapuaLocator;
import org.eclipse.kapua.locator.KapuaProvider;
import org.eclipse.kapua.model.id.KapuaId;
import org.eclipse.kapua.model.id.KapuaIdStatic;
import org.eclipse.kapua.service.authorization.AuthorizationService;
import org.eclipse.kapua.service.weather.WeatherCode;
import org.eclipse.kapua.service.weather.WeatherCodeService;
import org.eclipse.kapua.service.authorization.permission.Actions;
import org.eclipse.kapua.service.authorization.permission.PermissionFactory;
import org.eclipse.kapua.service.authorization.domain.Domain;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.List;





/**
 * {@link weatherCodeService} implementation.
 * 
 * @since 1.0
 *
 */
@KapuaProvider
public class WeatherCodeServiceImpl extends AbstractKapuaService implements WeatherCodeService {

    private static final Domain WEATHERCODE_DOMAIN = new WeatherCodeDomain();

    public WeatherCodeServiceImpl() {
        super(WeatherCodeEntityManagerFactory.getInstance());
    }

  
 
    @SuppressWarnings("unchecked")
	@Override
    public WeatherCode findByCname(String cname) throws KapuaException {
      
     
      //checkAccess
      KapuaLocator locator = KapuaLocator.getInstance();
      AuthorizationService authorizationService = locator.getService(AuthorizationService.class);
      PermissionFactory permissionFactory = locator.getFactory(PermissionFactory.class);
      authorizationService.checkPermission(permissionFactory.newPermission(WEATHERCODE_DOMAIN, 
          Actions.read, KapuaId.ANY));
    
     return entityManagerSession.onResult(em -> {
    	  
    	  Query q;
    	  q = em.createNativeQuery("select c.* from weather_code c where c.cname=?1");
    	  q.setParameter(1, cname);
          List list=q.getResultList();
          WeatherCode weatherCode=null;
          for(int i=0;i<list.size();i++){
				Object[] obj = (Object[]) list.get(i);
				weatherCode=new WeatherCodeImpl();
				weatherCode.setId(new KapuaIdStatic(new BigInteger(obj[0].toString())));
				weatherCode.setCode(obj[1].toString());
			    weatherCode.setExplain(obj[2].toString());
				weatherCode.setCname(obj[3].toString());
			    weatherCode.setEname(obj[4].toString());
				weatherCode.setDayImage(obj[5].toString());
				weatherCode.setNightImage(obj[6].toString());
				
		  }
          
          return weatherCode;
      });
    }


 
}
