package org.eclipse.kapua.service.appversion.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.kapua.service.appversion.AppVersionListResult;


public class AppVersionListResultImpl implements AppVersionListResult {
  
  private static final long serialVersionUID = 5607964544036693639L;
  private ArrayList<String> items;

  /**
   * Constructor.
   */
  public AppVersionListResultImpl() {
      
    items = new ArrayList<>();
  }
    
  @Override
  public int getSize() {
    return this.items.size();
  }

   

  @Override
  public List<String> getItems() {
    return Collections.unmodifiableList(items);
  }

  @Override
  public void addItems(List<String> item) {
    this.items.addAll(item);
  }

  @Override
  public String getItem(int index) {
    // TODO Auto-generated method stub
    return this.items.get(index);
  }

  
}
