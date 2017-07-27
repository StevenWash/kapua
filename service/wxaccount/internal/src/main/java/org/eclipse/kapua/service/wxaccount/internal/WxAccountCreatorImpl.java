package org.eclipse.kapua.service.wxaccount.internal;

import org.eclipse.kapua.commons.model.AbstractKapuaNamedEntityCreator;
import org.eclipse.kapua.model.id.KapuaId;
import org.eclipse.kapua.service.wxaccount.WxAccount;
import org.eclipse.kapua.service.wxaccount.WxAccountCreator;

public class WxAccountCreatorImpl extends AbstractKapuaNamedEntityCreator<WxAccount> 
             implements WxAccountCreator {
  


  /**
	 * 
	 */
	private static final long serialVersionUID = 6219532418165935377L;

  private String wxAppid;
   
  private String wxToken;
   
  private String wxAeskey;
   
  private String urlName;
  
  private String wxAppsecret;
   
  
   
   
   


  /**
   * Constructor
   * 
   * @param scopeId.
   */
  public WxAccountCreatorImpl(KapuaId scopeId) {
      super(scopeId);
  }







/**
 * @return the wxAppid
 */
public String getWxAppid() {
	return wxAppid;
}







/**
 * @param wxAppid the wxAppid to set
 */
public void setWxAppid(String wxAppid) {
	this.wxAppid = wxAppid;
}







/**
 * @return the wxToken
 */
public String getWxToken() {
	return wxToken;
}







/**
 * @param wxToken the wxToken to set
 */
public void setWxToken(String wxToken) {
	this.wxToken = wxToken;
}







/**
 * @return the wxAeskey
 */
public String getWxAeskey() {
	return wxAeskey;
}







/**
 * @param wxAeskey the wxAeskey to set
 */
public void setWxAeskey(String wxAeskey) {
	this.wxAeskey = wxAeskey;
}







/**
 * @return the urlName
 */
public String getUrlName() {
	return urlName;
}







/**
 * @param urlName the urlName to set
 */
public void setUrlName(String urlName) {
	this.urlName = urlName;
}







/**
 * @return the wxAppsecret
 */
public String getWxAppsecret() {
	return wxAppsecret;
}







/**
 * @param wxAppsecret the wxAppsecret to set
 */
public void setWxAppsecret(String wxAppsecret) {
	this.wxAppsecret = wxAppsecret;
}









  

   
   
   
   
   
   

}
