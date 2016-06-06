package com.emporganizer.api.XML;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.emporganizer.api.XML.errors.XmlParserException;

import java.io.File;

/**
 * Created by tomas on 6/4/2016.
 */
public class XmlOut extends XmlBase{
    public XmlOut() {
    }

    public XmlOut(File xmlFile) {
        super(xmlFile);
    }

    public XmlOut(String path) {
        super(path);
    }

    public void createElement(String elementName) throws XmlParserException {
        doc.appendChild(doc.createElement(elementName));
        transform();
    }

    public void writeElement(String elementName, String parentName) throws XmlParserException {
        Element element = doc.createElement(elementName);
        Element parent = (Element) doc.getElementsByTagName(parentName).item(0);

        if(parent != null){
            parent.appendChild(element);
        }
        else{
            throw new XmlParserException("Element with name " + parentName + " was not found");
        }

        transform();
    }

    public void writeElement(String elementName, String attributeName, String attributeValue, String parentName) throws XmlParserException {
        Element element = doc.createElement(elementName);
        element.setAttribute(attributeName,attributeValue);
        Element parent = (Element) doc.getElementsByTagName(parentName).item(0);

        if(parent != null){
            parent.appendChild(element);
        }
        else{
            throw new XmlParserException("Element with name " + parentName + " was not found.");
        }

        transform();
    }

    public void writeElement(String elementName, String attributeName, String attributeValue, String parentName, boolean parentAttr) throws XmlParserException {
        if(parentAttr){
            NodeList nodeList = doc.getElementsByTagName(parentName);
            if(nodeList == null || nodeList.getLength() == 0){
                throw new XmlParserException("Elements with name " + elementName + " were not found.");
            }
            boolean success = false;
            for(int i = 0 ; i < nodeList.getLength(); i++){
                Node tmpNode = nodeList.item(i);
                if(tmpNode.getNodeType() == Node.ELEMENT_NODE){
                    Element parent = (Element) tmpNode;

                    if(parent.getAttribute(attributeName) != null && parent.getAttribute(attributeName).equals(attributeValue)){
                        Element element = doc.createElement(elementName);
                        parent.appendChild(element);
                        success = true;
                        break;
                    }
                }
            }
            if(!success){
                throw new XmlParserException("Elements with name " + parentName + " and attribute " + attributeName + " with value " + attributeValue + " was not found.");
            }

            transform();
        }
        else{
            writeElement(elementName,attributeName,attributeValue,parentName);
        }
    }

    public void writeMulElement(String elementName, String attributeName, String attributeValue, String parentName, boolean multiple) throws XmlParserException {
        NodeList nodeList = doc.getElementsByTagName(parentName);
        if(nodeList == null || nodeList.getLength() == 0){
            throw new XmlParserException("Elements with name " + elementName + " were not found.");
        }
        boolean success = false;

        for(int i = 0 ; i < nodeList.getLength(); i++){
            Node tmpNode = nodeList.item(i);
            if(tmpNode.getNodeType() == Node.ELEMENT_NODE){
                Element parent = (Element) tmpNode;
                Element element = doc.createElement(elementName);
                element.setAttribute(attributeName,attributeValue);
                parent.appendChild(element);
                success = true;
                if(!multiple){
                    break;
                }

            }
        }

        if(!success){
            throw new XmlParserException("Element with name " + elementName + " and attribute " + attributeName + " with value " + attributeValue + " was not found.");
        }

        transform();
    }

    public void writeDataToElement(String data, String elementName) throws XmlParserException {
        Element element = (Element) doc.getElementsByTagName(elementName).item(0);

        if(element != null){
            element.appendChild(doc.createTextNode(data));
        }
        else{
            throw new XmlParserException("Element with name " + elementName + " was not found.");
        }

        transform();
    }

    public void writeDataToElement(String data, String elementName, String attributeName, String attributeValue, boolean multiple) throws XmlParserException {
        NodeList nodeList = doc.getElementsByTagName(elementName);
        if(nodeList == null || nodeList.getLength() == 0){

        }
        boolean success = false;

        for(int i = 0; i < nodeList.getLength(); i++){
            Node tmpNode = nodeList.item(i);
            if(tmpNode.getNodeType() == Node.ELEMENT_NODE){
                Element element = (Element) tmpNode;

                if(element.getAttribute(attributeName) != null && element.getAttribute(attributeName).equals(attributeValue)){
                    element.appendChild(doc.createTextNode(data));
                    success = true;
                    if(!multiple){
                        break;
                    }
                }
            }
        }

        if(!success){
            throw new XmlParserException("Element with name " + elementName + " and attribute " + attributeName + " with value " + attributeValue + " was not found.");
        }

        transform();
    }

    public void writeDataToElement(String data, String elementName, String parentName, String attributeName, String attributeValue) throws XmlParserException {
        NodeList nodeList = doc.getElementsByTagName(parentName);
        if(nodeList == null || nodeList.getLength() == 0){

        }
        boolean success = false;

        for(int i = 0; i < nodeList.getLength(); i++){
            Node tmpNode = nodeList.item(i);
            if(tmpNode.getNodeType() == Node.ELEMENT_NODE){
                Element parent = (Element) tmpNode;

                if(parent.getAttribute(attributeName) != null && parent.getAttribute(attributeName).equals(attributeValue)){
                    Element element = (Element) parent.getElementsByTagName(elementName).item(0);
                    element.appendChild(doc.createTextNode(data));
                    success = true;
                    break;
                }
            }
        }

        if(!success){
            throw new XmlParserException("Element with name " + elementName + " and attribute " + attributeName + " with value " + attributeValue + " was not found.");
        }

        transform();
    }

    public void addAttribute(String elementName, String attributeName, String attributeValue) throws XmlParserException {
        Element element = (Element) doc.getElementsByTagName(elementName);
        if(element == null){
            throw new XmlParserException("Element with name " + elementName + " was not found.");
        }

        element.setAttribute(attributeName,attributeValue);
        transform();
    }

    public void addAttribute(String elementName, String attributeName, String attributeValue, boolean multiple) throws XmlParserException {
        if(!multiple){
            addAttribute(elementName,attributeName,attributeValue);
            return;
        }
        else{
            NodeList nodeList = doc.getElementsByTagName(elementName);
            if(nodeList == null || nodeList.getLength() == 0){
                throw new XmlParserException("Elements with name " + elementName + " were not found.");
            }
            for(int i = 0; i < nodeList.getLength(); i++){
                Node tmpNode = nodeList.item(i);
                if(tmpNode.getNodeType() == Node.ELEMENT_NODE){
                    Element element = (Element) tmpNode;
                    element.setAttribute(attributeName,attributeValue);
                }
            }
        }

        transform();
    }

    public void addAttribute(String elementName,String elAttrName, String elAttrValue, String attributeName, String attributeValue) throws XmlParserException {
        Element element = (Element) doc.getElementsByTagName(elementName);
        if(element == null){
            throw new XmlParserException("Element with name " + elementName + " was not found.");
        }

        if(element.getAttribute(elAttrName) != null && element.getAttribute(elAttrName).equals(elAttrValue)){
            element.setAttribute(attributeName,attributeValue);
        }
        else{
            throw new XmlParserException("Element with name " + elementName + " and attribute " + elAttrName + " with value " + elAttrValue + " was not found.");
        }

        transform();
    }

    public void addAttribute(String elementName,String elAttrName, String elAttrValue, String attributeName, String attributeValue, boolean multiple) throws XmlParserException {
        if(!multiple){
            addAttribute(elementName,elAttrName,elAttrValue,attributeName,attributeValue);
            return;
        }
        else{
            NodeList nodeList = doc.getElementsByTagName(elementName);
            boolean success = false;
            if(nodeList == null || nodeList.getLength() == 0){
                throw new XmlParserException("Elements with name " + elementName + " were not found.");
            }
            for(int i = 0; i < nodeList.getLength(); i++){
                Node tmpNode = nodeList.item(i);
                if(tmpNode.getNodeType() == Node.ELEMENT_NODE){
                    Element element = (Element) tmpNode;
                    if(element.getAttribute(elAttrName) != null && element.getAttribute(elAttrName).equals(elAttrValue)){
                        element.setAttribute(attributeName,attributeValue);
                        success = true;
                    }
                }
            }
            if(!success){
                throw new XmlParserException("Element with name " + elementName + " and attribute " + elAttrName + " with value " + elAttrValue + " was not found.");
            }
        }

        transform();
    }

}
