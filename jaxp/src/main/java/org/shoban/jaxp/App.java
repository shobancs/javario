package org.shoban.jaxp;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws ParserConfigurationException, SAXException, IOException
    {


        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        dbf.setNamespaceAware(true);
        dbf.setValidating(false);
        
        DocumentBuilder db = dbf.newDocumentBuilder(); 
        File file = new File("C:\\shoban\\artidr1_configdescriptor.xml");
        Document doc = db.parse(file);
        doc.getElementsByTagName("config");
        //doc.
    }
    private static void usage() {
        // ...
    }
}
