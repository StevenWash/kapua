package org.eclipse.kapua.service.weather.internal;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;


/**
 * @author "Federico De Faveri defaveri@gmail.com"
 *
 */
public class RSSParser {

	private Unmarshaller unmarshaller;

	public RSSParser() throws JAXBException
	{
		JAXBContext context = JAXBContext.newInstance(Rss.class);
		unmarshaller = context.createUnmarshaller();
	}

	public Rss parse(String xml) throws JAXBException
	{
		return (Rss)unmarshaller.unmarshal(new StringReader(xml));
	}
	
}
