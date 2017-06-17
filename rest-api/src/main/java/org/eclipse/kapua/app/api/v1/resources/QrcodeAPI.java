package org.eclipse.kapua.app.api.v1.resources;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.StreamingOutput;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.Consumes;
import javax.ws.rs.QueryParam;

import org.eclipse.kapua.app.common.util.EncodeHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api("QrcodeAPI")
@Path("/qrcode")  
public class QrcodeAPI {
	
	@ApiOperation(value = "packageUrl", //
	            notes = "Queries the packageUrl with the given pName parameter returning all ", //
	            response = String.class, //
	            responseContainer = "String") //
	@GET
	public String getPackageUrl/*(@QueryParam("package") String pName)*/
	(@ApiParam(value = "The ScopeId in which to create the Account", required = true) @QueryParam("package") String pName
    ){
	
		System.out.println("pName::: "+pName);
		       String url="";
		        if(pName=="")
		        {
		        	
		        }
		        return url;
	}
	 
	 
/*	@ApiOperation(value = "create the qrcode", 
	            notes = "create the Qrcode with content,width parameter returning ",
	            response = InputStream.class, 
	            responseContainer = "InputStream")
	@GET
	@Path(value = "/getQRCode/{content}/{width}")  
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces("image/png")
	public InputStream  getQRCode(
	        @ApiParam(value = "The content in which to create the QRcode", required = true) @PathParam("content") String content, 
            @ApiParam(value = "The width in which to create the QRcode", required = true) @PathParam("width") int width)  { 
		System.out.println("getQRCode-----start");
		System.out.println("getQRCode-----content"+content); //throws IOException
		System.out.println("getQRCode-----width"+width);
		byte[] by = null;
		String PNG="image/png;charset=GB2312";
		String qrcode=content;
		if(content=="DONGLEAPP")
		{
			qrcode="https://github.com/miricy/release/temp/ContralDongle.apk";
		}
		else if(content=="wifispeaker")
		{
			qrcode="https://github.com/miricy/release/blob/master/net-speaker/BoxController.apk?raw=true";
		}
		else if(content=="speakerurl")
		{
			qrcode="http://192.168.49.1:8080";
		}
		try {
			System.out.println("qrcode: "+qrcode);
			String _content =  URLDecoder.decode(qrcode, "utf-8");
			by = EncodeHelper.getQRCode(_content, width);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ByteArrayInputStream(by);
	} */
	 
	 

	@ApiOperation(value = "Queries the QrcodeAPI", 
            notes = "Queries the QrcodeAPI with the given content,width  parameter returning all matching QrcodeAPI", 
            response = InputStream.class, 
            responseContainer = "InputStream")
	@GET
	@Path(value = "/getpic")  
	@Produces("image/png")
	public InputStream  getCodePic(
			@ApiParam(value = "The content in which to create the QrcodeAPI", required = true) @QueryParam("content") String content,
		
			@ApiParam(value = "Provides the widths for the new QrcodeAPI to be created", required = true) @QueryParam("width") int width) throws IOException {  
		byte[] b = null;
		String PNG="image/png;charset=GB2312";
		String qrcode=content;		
		try {
			String _content =  URLDecoder.decode(qrcode, "utf-8");
			b = EncodeHelper.getQRCode(_content, width);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
		}
		return new ByteArrayInputStream(b);
	} 
}
