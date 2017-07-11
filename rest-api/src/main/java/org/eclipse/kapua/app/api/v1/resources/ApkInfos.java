package org.eclipse.kapua.app.api.v1.resources;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import org.eclipse.kapua.app.api.v1.resources.model.ScopeId;
import org.eclipse.kapua.app.common.util.EncodeHelper;
import org.eclipse.kapua.locator.KapuaLocator;
import org.eclipse.kapua.service.apkinfo.ApkInfo;
import org.eclipse.kapua.service.apkinfo.ApkInfoService;


@Api("ApkInfos")
@Path("{scopeId}/apkinfos") 
public class ApkInfos extends AbstractKapuaResource {
	
	private final KapuaLocator locator = KapuaLocator.getInstance();
    private final ApkInfoService apiInfoService = locator.getService(ApkInfoService.class);
    
    @Context HttpServletResponse response;
    
    
/*    @ApiOperation(value = "Get an apkInfo",  //
            notes = "Returns the ApiInfo specified by the \"apiInfoId\" path parameter.",  //
            response = ApkInfo.class)
    @GET
    @Path("{apkInfoId}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public ApkInfo find(
            @ApiParam(value = "The ScopeId of the requested ApiInfo.", required = true, defaultValue = DEFAULT_SCOPE_ID) @PathParam("scopeId") ScopeId scopeId, //
            @ApiParam(value = "The id of the requested ApiInfo", required = true) @PathParam("apkInfoId") EntityId apkInfoId) {
    	System.out.println("scopeId:"+scopeId);
    	System.out.println("apkInfoId:"+apkInfoId);
    	ApkInfo apkInfo = null;
        try {
        	
        	apkInfo = apiInfoService.findById(scopeId, apkInfoId);
        } catch (Throwable t) {
            handleException(t);
        }
        return returnNotNullEntity(apkInfo);
    }*/
    
    
    
/*    @ApiOperation(value = "Get an apkUrl",  //
            notes = "Returns the url by the \"apiInfoId\" path parameter.",  //
            response = String.class)
    @GET
    @Path("apk")
    @Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public String getUrl(
            @ApiParam(value = "The ScopeId of the requested url.", required = true, defaultValue = DEFAULT_SCOPE_ID) @PathParam("scopeId") ScopeId scopeId,
            @ApiParam(value = "The package of the requested url.")@QueryParam("package") String pName,//
            @ApiParam(value = "The type of the requested url") @QueryParam("type") String type) {
    	
    	  String url="";
	        if(pName.equalsIgnoreCase("com.example.goliath")){
	        
	        	url="https://raw.githubusercontent.com/miricy/release/master/switch/Goliath.apk";
	        	 if(type!=null && type.equalsIgnoreCase("version")){
	        	 
	        		 url="https://raw.githubusercontent.com/miricy/release/master/switch/version.json";
	        	 }
	        }
	        try {
	        	System.out.println("url--"+url);
	        	if(url!="")  response.sendRedirect(url);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        return url;
	        
	        
	        
	        
    }*/
    
    
    
  
   
    
    @ApiOperation(value = "Get an ApiInfo",  //
            notes = "Returns the ApiInfo specified by the \"apiInfoId\" path parameter.",  //
            response = ApkInfo.class)
    @GET
    @Path("update")
    @Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public ApkInfo getPackageInfo(
            @ApiParam(value = "The ScopeId of the requested ApiInfo.", required = true, defaultValue = DEFAULT_SCOPE_ID) @PathParam("scopeId") ScopeId scopeId, //
            @ApiParam(value = "The pName of the requested ApiInfo")@QueryParam("package") String pName,
            @ApiParam(value = "The version of the requested ApiInfo")@QueryParam("version") String version
            ) {
    	
    	ApkInfo apkinfo = null;
   
		if(version==null){
			version="";
		}
        try {
        	
        	apkinfo = apiInfoService.findByPackagename(scopeId, pName);
        	
        	if(apkinfo==null || apkinfo.getVersion().compareTo(version)<=0){
        	   
        		apkinfo=apiInfoService.findByDistinct(scopeId,pName, version);
        	}
			
        } catch (Exception e) {
        	apkinfo=null;
        	System.out.println("apkinfo is null");
        }
        return apkinfo;
    }
    
    
    
    
    
    @ApiOperation(value = "Get an qrcode",  //
            notes = "Returns the qrcode by the \"apiInfoId\" path parameter.",  //
            response = InputStream.class)
    @GET
    @Path("packagename/qrcode")
    @Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Produces("image/png")
    public InputStream getQrcode(
            @ApiParam(value = "The ScopeId of the requested Qrcode.", required = true, defaultValue = DEFAULT_SCOPE_ID) @PathParam("scopeId") ScopeId scopeId,
            @ApiParam(value = "The package of the requested Qrcode.")@QueryParam("package") String pName,//
            @ApiParam(value = "The width of the requested Qrcode")@QueryParam("width") int width) {
    	
			byte[] b = null;
			ApkInfo apkinfo = null;
		        
	 try{   
			apkinfo= apiInfoService.findByPackagename(scopeId, pName);
		 } catch (Throwable t) {
		        handleException(t);
		 }
	 
	  if(apkinfo==null) return null;

	  try {
			String _content =  URLDecoder.decode(apkinfo.getUrl(), "utf-8");
			b = EncodeHelper.getQRCode(_content, width<50?50:width);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
		}
		return new ByteArrayInputStream(b);
    }
    
    
    
    
    
/*   @ApiOperation(value = "Get an ApiInfo",  //
            notes = "Returns the ApiInfo specified by the \"packageName\" path parameter.",  //
            response = ApkInfo.class)
    @GET
    @Path("scopeId/packagename/{packageName}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public ApkInfo findByPackageName(
            @ApiParam(value = "The ScopeId of the requested ApiInfo.", required = true, defaultValue = DEFAULT_SCOPE_ID) @PathParam("scopeId") ScopeId scopeId, //
            @ApiParam(value = "The packageName of the requested ApiInfo", required = true) @PathParam("packageName") String packageName) {
    	ApkInfo apiInfo = null;
        try {
        	System.out.println("scopeId:"+scopeId);
        	apiInfo = apiInfoService.findByPackagename(scopeId, packageName);
        } catch (Throwable t) {
            handleException(t);
        }
        return returnNotNullEntity(apiInfo);
    }*/
}
