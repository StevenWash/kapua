package org.eclipse.kapua.service.weather.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class XmlUtil {
	private Map<String, String> map;
	//存储xml元素信息的容器 
//	private static List elemList = new ArrayList();
	
	private String srcXml;


//	public static void main(String args[]) throws DocumentException { 
//	  XmlParser test = new XmlParser(); 
//	  Element root = test.getRootElement(); 
//	  test.getElementList(root); 
//	  String x = test.getListString(elemList); 
//	
//	  System.out.println("-----------原xml内容------------"); 
//	  System.out.println(srcXml); 
//	  System.out.println("-----------解析结果------------"); 
//	  System.out.println(x); 
//	
//	} 
	public XmlUtil(String srcXml){
		this.srcXml = srcXml;
		this.map = new HashMap<String, String>();
	}
	
	public Map<String, String> getContents(){
		Element root;
		try {
			root = getRootElement();
			getElementList(root); 
		} catch (DocumentException e) {
			e.printStackTrace();
		} 
		  
		return map;  
	}

	/** 
	* 获取根元素 
	* 
	* @return 
	* @throws DocumentException 
	*/ 
	private Element getRootElement() throws DocumentException { 
		Document srcdoc = DocumentHelper.parseText(srcXml); 
		Element elem = srcdoc.getRootElement(); 
		return elem; 
	} 

	/** 
	* 递归遍历方法 
	* 
	* @param element 
	*/ 
	private void getElementList(Element element) { 
		List elements = element.elements(); 
		if (elements.size() == 0) { 
			//没有子元素 
			String xpath = element.getPath().substring(element.getPath().lastIndexOf("/")+1); 
			String value = element.getTextTrim(); 
//			elemList.add(new Leaf(xpath, value)); 
			map.put(xpath, value);
			System.out.println(xpath+","+value);
		} else { 
			//有子元素 
			for (Iterator it = elements.iterator(); it.hasNext();) { 
				Element elem = (Element) it.next(); 
				//递归遍历 
				getElementList(elem); 
			} 
		} 
	} 
	
//	public String getListString(List elemList) { 
//		StringBuffer sb = new StringBuffer(); 
//		for (Iterator it = elemList.iterator(); it.hasNext();) { 
//			Leaf leaf = it.next(); 
//			sb.append(leaf.getXpath()).append(" = ").append(leaf.getValue()).append("\n"); 
//		} 
//		return sb.toString(); 
//	} 
} 
