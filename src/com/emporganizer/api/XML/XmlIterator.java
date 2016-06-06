package com.emporganizer.api.XML;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.emporganizer.api.XML.errors.XmlParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tomas on 6/5/2016.
 */
public class XmlIterator extends XmlBase{
    public XmlIterator() {

    }

    private Element currentElement;
    private NodeList searchResult;

    public void createElement(String elementName){
        currentElement = doc.createElement(elementName);
        doc.appendChild(currentElement);
    }

    public void findElement(String elementName){
        if(currentElement == null){
            searchResult = doc.getElementsByTagName(elementName);
        }
        else{
            searchResult = currentElement.getElementsByTagName(elementName);
        }
        if(searchResult != null && searchResult.getLength() == 1){
            currentElement = (Element) searchResult.item(0);
        }
        else{
            currentElement = null;
        }
    }

    public boolean append(String elementName){
        if(currentElement != null){
            Element element = doc.createElement(elementName);
            currentElement.appendChild(element);
            currentElement = element;
            return true;
        }
        else if(searchResult != null){
            boolean success = false;
            for(int i = 0; i < searchResult.getLength(); i++){
                if(searchResult.item(i).getNodeType() == Node.ELEMENT_NODE){
                    ((Element)searchResult.item(i)).appendChild(doc.createElement(elementName));
                    success = true;
                }
            }
            return success;
        }

        return false;
    }

    public boolean appendText(String value){
        if(currentElement != null){
            currentElement.appendChild(doc.createTextNode(value));
            return true;
        }
        return false;
    }

    public void setAttributes(String attrName, String attrVal){
        if(currentElement != null){
            currentElement.setAttribute(attrName,attrVal);
        }
    }

    public void findElement(String elementName, String attrName, String attrVal){
        List<Element> result = new ArrayList<>();
        if(currentElement == null){
            NodeList nodeList = doc.getElementsByTagName(elementName);
            for(int i = 0; i < nodeList.getLength(); i++){
                if(nodeList.item(i).getNodeType() == Node.ELEMENT_NODE){
                    Element element = (Element) nodeList.item(i);
                    if(element.getAttribute(attrName) != null && element.getAttribute(attrName).equals(attrVal)){
                        result.add(element);
                    }
                }
            }
        }
        else{
            NodeList nodeList = currentElement.getElementsByTagName(elementName);
            for(int i = 0; i < nodeList.getLength(); i++){
                if(nodeList.item(i).getNodeType() == Node.ELEMENT_NODE){
                    Element element = (Element) nodeList.item(i);
                    if(element.getAttribute(attrName) != null && element.getAttribute(attrName).equals(attrVal)){
                        result.add(element);
                    }
                }
            }
        }

        if(result.size() == 1){
            currentElement = result.get(0);
        }
        else {
            currentElement = null;
        }
    }
    
    public boolean isNull(){
    	return currentElement == null ? true : false;
    }
    
    public void root(){
        currentElement = doc.getDocumentElement();
    }

    public void parent(){
        if(currentElement != null){
            currentElement = (Element) currentElement.getParentNode();
        }
    }

    public void nextSibling(){
        if(currentElement != null){
            currentElement = (Element) currentElement.getNextSibling();
        }
    }

    public void prevSibling(){
        if(currentElement != null){
            currentElement = (Element) currentElement.getPreviousSibling();
        }
    }
    
    public void firstChild(){
    	if(currentElement != null){
    		currentElement = (Element) currentElement.getChildNodes().item(0);
    	}    	
    }
    
    public String readValue(){
    	if(currentElement != null){
    		return currentElement.getTextContent();
    	}
    	
    	return "ERROR";
    }
    
    public void setElement(Element element){
    	currentElement = element;
    }
    
    public boolean build(){
        try {
            transform();
            return true;
        } catch (XmlParserException e) {
            e.printStackTrace();
            return false;
        }
    }
}
