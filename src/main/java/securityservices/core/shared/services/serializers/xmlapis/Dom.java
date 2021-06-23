package securityservices.core.shared.services.serializers.xmlapis;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import securityservices.core.shared.services.serializers.Xml;

public class Dom implements Xml{
    protected DocumentBuilder builder;
    protected Document doc;
    protected Element elem;
    protected Node rootNode, node;
    protected NodeList nodeList, attribList;
    protected Boolean iscreated;

    public Dom() {
        try {
            builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            iscreated = true;
        } catch (ParserConfigurationException ex) {
            iscreated = false;
        }
    }

    public Boolean getIscreated() {
        return iscreated;
    }
          
    @Override    
    public void set (String xmlDoc) {
        try {
            doc = builder.newDocument();
            InputSource is = new InputSource();
            is.setCharacterStream(new StringReader(xmlDoc));
            doc = builder.parse(is); 
        } catch (SAXException | IOException ex) {
            iscreated = false;
        }
        rootNode = doc.getFirstChild(); // retorna el node arrel del document XML.
        nodeList = rootNode.getChildNodes(); //agafem la llista de nodes de cada client
    }   
    
    @Override
    public String toString(){            
        String result="";
        try {
            TransformerFactory transFabric = TransformerFactory.newInstance();
            Transformer transformer = transFabric.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
                     
            StringWriter writer = new StringWriter();
            transformer.transform(new DOMSource(doc), new StreamResult(writer));
            result = writer.getBuffer().toString();  
        } catch (TransformerConfigurationException ex) {
            return result; 
        } catch (TransformerException ex) {
            return result; 
        }
        return result; 
    }
   
    @Override
    public void createDocument() {
        doc = builder.newDocument();             
    }
    
    @Override
    public void setRootNode(String node) {
        rootNode = doc.createElement(node); //creació del node arrel del document XML
        doc.appendChild(rootNode );  
    }

    @Override
    public void setNode(String node, String value) {
        elem = doc.createElement(node);
        elem.setTextContent(value);
        rootNode.appendChild(elem);    
    }
    
    @Override
    public String getRootNode() {
        return rootNode.getNodeName();
    }

    @Override
    public String getValueNode(String node) {
        for (int i=0; i < this.nodeList.getLength(); i++){
            this.node = nodeList.item(i);
            if (this.node.getNodeName().equals(node)){
                return this.node.getTextContent();
            }
        }
        return null;
    }

    @Override
    public String[] getSubNodes(String node) {
        return null;
    }

    @Override
    public void setAtributes(String node, String atribs) {

    }
    
    @Override
    public String[] getAtributes(String node) {
        return null;
    }
    
        @Override
    public void setSubNode(String node, String subnode, String value) {

    }

    @Override
    public void setArrayNodes(String node, ArrayList<String> subnodelist, ArrayList<String> nodeValueslist) {

    }
}