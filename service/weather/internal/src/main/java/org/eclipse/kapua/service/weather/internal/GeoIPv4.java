package org.eclipse.kapua.service.weather.internal;

import com.maxmind.geoip.LookupService;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URL;

public class GeoIPv4 {

    private static LookupService lookUp;
   
    static {
        try {
        	
        	URL url=GeoIPv4.class.getClassLoader().getResource("GeoLiteCity.dat");
        	
            lookUp = new LookupService(url.getFile(),
                    
                    LookupService.GEOIP_MEMORY_CACHE);
            
            System.out.println("GeoIP Database loaded: " + lookUp.getDatabaseInfo());
        } catch (Exception e) {
            System.out.println("Could not load geo ip database: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static GeoLocation getLocation(String ipAddress) {
    	
        return GeoLocation.map(lookUp.getLocation(ipAddress));
    }

    public static GeoLocation getLocation(long ipAddress){
        return GeoLocation.map(lookUp.getLocation(ipAddress));
    }

    public static GeoLocation getLocation(InetAddress ipAddress){
        return GeoLocation.map(lookUp.getLocation(ipAddress));
    }
}
