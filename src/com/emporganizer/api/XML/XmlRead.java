package com.emporganizer.api.XML;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.emporganizer.api.XML.errors.XmlParserException;

import java.io.File;

/**
 * Created by tomas on 6/4/2016.
 */
public class XmlRead extends XmlBase{
    public XmlRead() {
    }

    public XmlRead(File xmlFile) {
        super(xmlFile);
    }

    public XmlRead(String path) {
        super(path);
    }

    public String getElementValue(String elementName) throws XmlParserException {
        Element element = (Element) doc.getElementsByTagName(elementName).item(0);
        if(element == null){
            throw new XmlParserException("Element with name " + elementName + " was not found.");
        }
        return element.getTextContent();
    }

    public String getElementValue(String elementName, String attributeName, String attributeValue) throws XmlParserException {
        NodeList nodeList = doc.getElementsByTagName(elementName);
        if(nodeList == null || nodeList.getLength() == 0){
            throw new XmlParserException("Elements with name " + elementName + " were not found.");
        }
        for(int i = 0; i < nodeList.getLength(); i++){
            Node tmpNode = nodeList.item(i);
            if(tmpNode.getNodeType() == Node.ELEMENT_NODE){
                Element element = (Element) tmpNode;
                if(element.getAttribute(attributeName) != null && element.getAttribute(attributeName).equals(attributeValue)){
                    return element.getTextContent();
                }
            }
        }

        throw new XmlParserException("Element with name " + elementName + " and attribute " + attributeName + " with value " + attributeValue + " was not found.");
    }

    public String getElementValue(String elementName, String parentName) throws XmlParserException {
        Element parent = (Element) doc.getElementsByTagName(parentName).item(0);
        if(parent == null){
            throw new XmlParserException("Element with name " + parentName + " was not found.");
        }
        Element element = (Element) parent.getElementsByTagName(elementName);
        if(element == null){
            throw new XmlParserException("Element with name " + elementName + " was not found.");
        }
        return element.getTextContent();
    }

    public String getElementValue(String elementName, String attributeName, String attributeValue, String parentName) throws XmlParserException {
        Element parent = (Element) doc.getElementsByTagName(parentName).item(0);
        if(parent == null){
            throw new XmlParserException("Element with name " + parentName + " was not found.");
        }
        NodeList nodeList = parent.getElementsByTagName(elementName);
        if(nodeList == null || nodeList.getLength() == 0){
            throw new XmlParserException("Elements with name " + elementName + " were not found.");
        }
        for(int i = 0; i < nodeList.getLength(); i++){
            Node tmpNode = nodeList.item(i);
            if(tmpNode.getNodeType() == Node.ELEMENT_NODE){
                Element element = (Element) tmpNode;
                if(element.getAttribute(attributeName) != null && element.getAttribute(attributeName).equals(attributeValue)){
                    return element.getTextContent();
                }
            }
        }

        throw new XmlParserException("Elements with name " + elementName + " and attribute " + attributeName + " with value " + attributeValue + " were not found.");
    }

    public String getElementValue(String elementName, String attributeName, String attributeValue, String parentName, String paAttrName, String paAttrVal) throws XmlParserException {
        NodeList nodeList = doc.getElementsByTagName(parentName);
        if(nodeList == null || nodeList.getLength() == 0){
            throw new XmlParserException("Elements with name " + parentName + " were not found.");
        }
        for(int i = 0; i < nodeList.getLength(); i++){
            Node tmpNode = nodeList.item(i);
            if(tmpNode.getNodeType() == Node.ELEMENT_NODE){
                Element element = (Element) tmpNode;
                if(element.getAttribute(paAttrName) != null && element.getAttribute(paAttrName).equals(paAttrVal)){
                    String value = getElementValue(elementName,attributeName,attributeValue,element);
                    if(value != null){
                        return value;
                    }
                }
            }
        }

        throw new XmlParserException("Elements with name " + parentName+ " and attribute " + paAttrName + " with value " + paAttrVal + " were not found.");
    }

    public String getElementValue(Element element) throws XmlParserException {
        return getElementValue(element.getTagName());
    }

    public String getElementValue(String elementName, Element parent) throws XmlParserException {
        return getElementValue(elementName,parent.getTagName());
    }

    public String getElementValue(Element element, String parentName) throws XmlParserException {
        return getElementValue(element.getTagName(),parentName);
    }

    public String getElementValue(Element element, Element parent) throws XmlParserException {
        return getElementValue(element.getTagName(),parent.getTagName());
    }

    public String getElementValue(Element element, String attributeName, String attributeValue) throws XmlParserException {
        return getElementValue(element.getTagName(),attributeName,attributeValue);
    }

    public String getElementValue(String elementName, String attributeName, String attributeValue, Element parent) throws XmlParserException {
        return getElementValue(elementName,attributeName,attributeValue,parent.getTagName());
    }

    public String getElementValue(Element element, String attributeName, String attributeValue, String parentName) throws XmlParserException {
        return getElementValue(element.getTagName(),attributeName,attributeValue,parentName);
    }

    public String getElementValue(Element element, String attributeName, String attributeValue, Element parent) throws XmlParserException {
        return getElementValue(element.getTagName(),attributeName,attributeValue,parent.getTagName());
    }

    public String getElementValue(String elementName, String attributeName, String attributeValue, Element parent, String paAttrName, String paAttrVal) throws XmlParserException {
        return getElementValue(elementName,attributeName,attributeValue,parent.getTagName(),paAttrName,paAttrVal);
    }

    public String getElementValue(Element element, String attributeName, String attributeValue, String parentName, String paAttrName, String paAttrVal) throws XmlParserException {
        return getElementValue(element.getTagName(),attributeName,attributeValue,parentName,paAttrName,paAttrVal);
    }

    public String getElementValue(Element element, String attributeName, String attributeValue, Element parent, String paAttrName, String paAttrVal) throws XmlParserException {
        return getElementValue(element.getTagName(),attributeName,attributeValue,parent.getTagName(),paAttrName,paAttrVal);
    }

}
