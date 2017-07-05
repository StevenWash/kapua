package org.eclipse.kapua.service.weather.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.kapua.service.weather.Weather;
import org.eclipse.kapua.service.weather.WeatherListResult;

public class WeatherListResultImpl implements WeatherListResult{
	
	
   
    private ArrayList<String> items;

    /**
     * Constructor
     */
    public WeatherListResultImpl() {
      
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
