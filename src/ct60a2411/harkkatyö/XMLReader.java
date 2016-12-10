/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ct60a2411.harkkatyö;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author Petri
 */
public class XMLReader {
    
    private Document doc;
    public SmartPost SP;
    public ArrayList<String> city = new ArrayList<>();
    public ArrayList<SmartPost> SPList = new ArrayList<>();
    
    public XMLReader() throws MalformedURLException, IOException, ParserConfigurationException, SAXException {
        
        URL url = new URL("http://smartpost.ee/fi_apt.xml");
        
        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
        String content = "";
        String line;
        
        while ((line = br.readLine()) != null) {
            content +=line + "\n";
        }
        
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        
        doc = db.parse(new InputSource(new StringReader(content)));
        
        doc.getDocumentElement().normalize();
        
        NodeList nodes = doc.getElementsByTagName("place");
        
        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            Element e = (Element) node;
            
            SP = new SmartPost(getValue("code", e), getValue("city", e), 
                    getValue("address", e), getValue("availability", e), 
                    getValue("postoffice", e), getValue("lat", e), 
                    getValue("lng", e));
            SPList.add(SP);
            String name = getValue("city", e);
            name = name.toUpperCase();
            if (!city.contains(name)) {
                city.add(name);
            }               
        }
        Collections.sort(city);
    }
    
    public ArrayList<SmartPost> getList() {
        return SPList;
    }
    
    
    private String getValue(String tag, Element e) {
        return e.getElementsByTagName(tag).item(0).getTextContent();
    }
}
