package securityservices.core.shared.services.serializers.xmlapis;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import org.jdom2.Document; //imports de JDOM
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;
import org.jdom2.output.Format;
import org.xml.sax.InputSource;
import securityservices.core.shared.services.serializers.Xml;

public class JDom implements Xml {

    protected SAXBuilder builder; //Classe per crear un document JDOM
    protected Document document; // classe que representa el document
    protected Element rootNode, node; // node arrel
    protected List subnodes; // llista de subnodes
    protected XMLOutputter jdomToXML;
    protected Boolean iscreated, isseted;

    public JDom() {
        try {
            builder = new SAXBuilder();
            iscreated = true;
        } catch (Exception ex) {
            iscreated = false;
        }
    }

    public Boolean getIscreated() {
        return iscreated;
    }

    @Override
    public String toString() {
        try {
            
            jdomToXML = new XMLOutputter(); //creamos un objecto, este realizará la transformación
            jdomToXML.setFormat(Format.getPrettyFormat()); 
            StringWriter writer = new StringWriter();
            jdomToXML.output(document, writer);
            return writer.getBuffer().toString(); 

        } catch (IOException ex) {
            
            return "IOException"+ ex.getMessage();
            
        }
    }

    public void set (String xmlDoc) {
        
        try {
            createDocument();
            InputSource is = new InputSource();
            is.setCharacterStream(new StringReader(xmlDoc));
            document = (Document)builder.build(is);
            isseted = true;
        
        } catch (Exception ex) {
            isseted = false;
            
        }
        rootNode = document.getRootElement();
        subnodes = rootNode.getChildren(); 
        
    }
    
    @Override
    public void createDocument() {
       document = new Document();  //crear nuevo documento
    }

    @Override
    public void setRootNode(String node) {
        rootNode = new Element(node); 
        document.setRootElement(rootNode);
    }

    @Override
    public void setNode(String node, String value) {
        rootNode.addContent(new Element(node).setText(value));
    }

    @Override
    public String getRootNode() {
        return String.valueOf(document.getRootElement());
    }

    @Override
    public String getValueNode(String node) {
        for (int i = 0; i < subnodes.size(); i++) {
            this.node = (Element) subnodes.get(i);
            if (this.node.getName().equals(node)) {
                return this.node.getText();
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
