package com.emporganizer.api.XML;
import java.io.File;

/**
 * Created by tomas on 6/4/2016.
 */
public class XmlModify extends XmlBase {
    public XmlModify() {
    }

    public XmlModify(File xmlFile) {
        super(xmlFile);
    }

    public XmlModify(String path) {
        super(path);
    }

    public boolean changeElementValue(String elementName){
        return false;
    }

    public boolean changeElementValue(String elementName, String parentName){
        return false;
    }

    public boolean changeElementValue(String elementName, String attributeName, String attributeValue){
        return false;
    }

    public boolean changeElementValue(String elementName, String attributeName, String attributeValue, String parentName){
        return false;
    }

    public boolean changeElementValue(String elementName, String attributeName, String attributeValue, String parentName, String paAttrName, String paAttrVal){
        return false;
    }
}
