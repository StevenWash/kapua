package org.eclipse.kapua.service.processrecovery.internal;

import org.eclipse.kapua.commons.model.AbstractKapuaEntityCreator;
import org.eclipse.kapua.model.id.KapuaId;
import org.eclipse.kapua.service.processrecovery.ProcessRecovery;
import org.eclipse.kapua.service.processrecovery.ProcessRecoveryCreator;

public class ProcessRecoveryCreatorImpl extends AbstractKapuaEntityCreator<ProcessRecovery> 
             implements ProcessRecoveryCreator {

  /**
   * .
   */
  private static final long serialVersionUID = 2749497529598658680L;

  private String dataContent;
  
  private String dataTitle;
  
  private Integer parentId;
  
  private Integer nextId;
  


  /**
   * Constructor
   * 
   * @param scopeId.
   */
  public ProcessRecoveryCreatorImpl(KapuaId scopeId) {
      super(scopeId);
  }



  /**.
   * @return the dataContent.
   */
  public String getDataContent() {
    return dataContent;
  }



  /**.
   * @param dataContent the dataContent to set.
   */
  public void setDataContent(String dataContent) {
    this.dataContent = dataContent;
  }



  /**.
   * @return the dataTitle.
   */
  public String getDataTitle() {
    return dataTitle;
  }



  /**.
   * @param dataTitle the dataTitle to set.
   */
  public void setDataTitle(String dataTitle) {
    this.dataTitle = dataTitle;
  }



  /**.
   * @return the parentId.
   */
  public Integer getParentId() {
    return parentId;
  }



  /**.
   * @param parentId the parentId to set.
   */
  public void setParentId(Integer parentId) {
    this.parentId = parentId;
  }



  /**.
   * @return the nextId.
   */
  public Integer getNextId() {
    return nextId;
  }



  /**.
   * @param nextId the nextId to set.
   */
  public void setNextId(Integer nextId) {
    this.nextId = nextId;
  }



 

 

}
