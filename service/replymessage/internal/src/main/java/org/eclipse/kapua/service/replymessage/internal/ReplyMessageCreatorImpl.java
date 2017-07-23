package org.eclipse.kapua.service.replymessage.internal;

import org.eclipse.kapua.commons.model.AbstractKapuaEntityCreator;
import org.eclipse.kapua.model.id.KapuaId;
import org.eclipse.kapua.service.replymessage.ReplyMessageCreator;
import org.eclipse.kapua.service.replymessage.ReplyMessage;

public class ReplyMessageCreatorImpl extends AbstractKapuaEntityCreator<ReplyMessage> 
             implements ReplyMessageCreator {

  /**
   * .
   */
  private static final long serialVersionUID = 2749497529598658680L;

  private String content;
  
  private String title;
  
  private String url;
  
  private String picurl;
  
  private String description;
  
  private String keywords;
  
  private String types;
  
  private KapuaId processrecoveryId;
  


  /**
   * Constructor
   * 
   * @param scopeId.
   */
  public ReplyMessageCreatorImpl(KapuaId scopeId) {
      super(scopeId);
  }



  /**.
   * @return the content.
   */
  public String getContent() {
    return content;
  }



  /**.
   * @param content the content to set.
   */
  public void setContent(String content) {
    this.content = content;
  }



  /**.
   * @return the title.
   */
  public String getTitle() {
    return title;
  }



  /**.
   * @param title the title to set.
   */
  public void setTitle(String title) {
    this.title = title;
  }



  /**.
   * @return the url.
   */
  public String getUrl() {
    return url;
  }



  /**.
   * @param url the url to set.
   */
  public void setUrl(String url) {
    this.url = url;
  }



  /**.
   * @return the picurl.
   */
  public String getPicurl() {
    return picurl;
  }



  /**.
   * @param picurl the picurl to set.
   */
  public void setPicurl(String picurl) {
    this.picurl = picurl;
  }



  /**.
   * @return the description.
   */
  public String getDescription() {
    return description;
  }



  /**.
   * @param description the description to set.
   */
  public void setDescription(String description) {
    this.description = description;
  }



  /**.
   * @return the keywords.
   */
  public String getKeywords() {
    return keywords;
  }



  /**.
   * @param keywords the keywords to set.
   */
  public void setKeywords(String keywords) {
    this.keywords = keywords;
  }



  /**.
   * @return the types.
   */
  public String getTypes() {
    return types;
  }



  /**.
   * @param types the types to set.
   */
  public void setTypes(String types) {
    this.types = types;
  }



  /**.
   * @return the processrecoveryId.
   */
  public KapuaId getProcessrecoveryId() {
    return processrecoveryId;
  }



  /**.
   * @param processrecoveryId the processrecoveryId to set.
   */
  public void setProcessrecoveryId(KapuaId processrecoveryId) {
    this.processrecoveryId = processrecoveryId;
  }



}
