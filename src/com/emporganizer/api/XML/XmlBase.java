package com.emporganizer.api.XML;

import org.w3c.dom.Document;

import com.emporganizer.api.XML.errors.XmlParserException;

import javax.management.modelmbean.XMLParseException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

/**
 * Created by tomas on 6/4/2016.
 */
public abstract class XmlBase {

    protected File xmlFile;

    protected DocumentBuilderFactory dbFactory;
    protected DocumentBuilder dBuilder;
    protected Document doc;

    private boolean buildersReady;
    public XmlBase(){
        setupBuilders();
    }

    public XmlBase(File xmlFile) {
        this.xmlFile = xmlFile;
        if(!xmlFile.isDirectory() && !xmlFile.exists()){
            try {
                xmlFile.createNewFile();
                setupBuilders();
                doc.getDocumentElement().normalize();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            setupBuilders();
            doc.getDocumentElement().normalize();
        }
    }

    public XmlBase(String path){
        xmlFile = new File(path);
        if(!xmlFile.isDirectory() && !xmlFile.exists()){
            try {
                xmlFile.createNewFile();
                setupBuilders();
                doc.getDocumentElement().normalize();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            setupBuilders();
            doc.getDocumentElement().normalize();
        }
    }

    public XmlBase(File xmlFile, boolean store) {
        this.xmlFile = xmlFile;
        if(store){
            if(!xmlFile.isDirectory() && !xmlFile.exists()){
                try {
                    xmlFile.createNewFile();
                    setupBuilders();
                    doc.getDocumentElement().normalize();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else{
                setupBuilders();
                doc.getDocumentElement().normalize();
            }
        }
        else{
            setupBuilders();
        }
    }

    public XmlBase(String path, boolean store) {
        xmlFile = new File(path);
        if(store){
            if(!xmlFile.isDirectory() && !xmlFile.exists()){
                try {
                    xmlFile.createNewFile();
                    setupBuilders();
                    doc.getDocumentElement().normalize();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else{
                setupBuilders();
                doc.getDocumentElement().normalize();
            }
        }
        else{
            setupBuilders();
        }
    }

    public void setFile(File file){
        xmlFile = file;
        setupBuilders();
    }

    public void setFile(String path){
        xmlFile = new File(path);
        setupBuilders();
    }

    public void saveFile() throws XmlParserException {
        if(xmlFile == null){
            throw new XmlParserException("File not defined.");
        }
        if(!xmlFile.isDirectory() && !xmlFile.exists()){
            try {
                xmlFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void setupBuilders(){
        if(!buildersReady){
            try {
                dbFactory = DocumentBuilderFactory.newInstance();
                dBuilder = dbFactory.newDocumentBuilder();
                doc = dBuilder.newDocument();
                buildersReady = true;
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            }
        }
    }

    protected void transform() throws XmlParserException {
        if(xmlFile == null){
            throw new XmlParserException("File not defined.");
        }
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(xmlFile);
            transformer.transform(source, result);
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    protected void transform(boolean save) throws XmlParserException, IOException {
        if(xmlFile == null){
            throw new XmlParserException("File not defined.");
        }
        if(save && !xmlFile.exists()){
            File dir = new File("exports");
            dir.mkdir();
            xmlFile = new File("exports/"+xmlFile.getName());
            xmlFile.createNewFile();
        }
        else{
            transform();
            return;
        }

        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(xmlFile);
            transformer.transform(source, result);
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }


    public Document getDoc(){
        return doc;
    }
}
