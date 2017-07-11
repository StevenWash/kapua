package org.eclipse.kapua.service.appversion.internal;

import org.eclipse.kapua.commons.model.AbstractKapuaEntityCreator;
import org.eclipse.kapua.commons.model.AbstractKapuaNamedEntityCreator;
import org.eclipse.kapua.model.id.KapuaId;
import org.eclipse.kapua.service.appversion.AppVersion;
import org.eclipse.kapua.service.appversion.AppVersionCreator;

public class AppVersionCreatorImpl extends AbstractKapuaEntityCreator<AppVersion> 
             implements AppVersionCreator {
  
  /**
   * .
   */
  private static final long serialVersionUID = -2932375603481463037L;

  private String packagename;
   
  private String code;
   
  private String url;
   
  private String md5;
   
  private Integer size;
   
  private String types;
   
  private String revision;
   
  private String forversion;
   
  private String version;
   
   
   
  /**
   * Constructor
   * 
   * @param appVersionCreator.
   */
  @SuppressWarnings({ "rawtypes", "unchecked" })
  public AppVersionCreatorImpl(AppVersionCreator appVersionCreator) {
      super((AbstractKapuaEntityCreator) appVersionCreator);

    setPackagename(appVersionCreator.getPackagename());
    setUrl(appVersionCreator.getUrl());
    setMd5(appVersionCreator.getMd5());
    setCode(appVersionCreator.getCode());
    setSize(appVersionCreator.getSize());
    setRevision(appVersionCreator.getRevision());
    setVersion(appVersionCreator.getVersion());
    setForversion(appVersionCreator.getForversion());
    setTypes(appVersionCreator.getTypes());
      
  }

  /**
   * Constructor
   * 
   * @param scopeId.
   */
  public AppVersionCreatorImpl(KapuaId scopeId) {
      super(scopeId);
  }

  public String getPackagename() {
    return packagename;
  }

  public void setPackagename(String packagename) {
    this.packagename = packagename;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getMd5() {
    return md5;
  }

  public void setMd5(String md5) {
    this.md5 = md5;
  }

  public Integer getSize() {
    return size;
  }

  public void setSize(Integer size) {
    this.size = size;
  }

  public String getTypes() {
    return types;
  }

  public void setTypes(String types) {
    this.types = types;
  }

  public String getRevision() {
    return revision;
  }

  public void setRevision(String revision) {
    this.revision = revision;
  }

  public String getForversion() {
    return forversion;
  }

  public void setForversion(String forversion) {
    this.forversion = forversion;
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }


   
   
   
   
   
   

}
