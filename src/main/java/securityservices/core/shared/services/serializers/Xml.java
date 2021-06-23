package securityservices.core.shared.services.serializers;

import java.util.ArrayList;

public interface Xml {
    public void createDocument();
    public void set (String xmlDoc);
    public void setRootNode(String node);
    public void setNode(String node, String value);
    public void setSubNode(String node, String subnode, String value);
    public void setArrayNodes (String node, ArrayList<String> subnodelist, ArrayList<String> nodeValueslist);
    public void setAtributes(String node, String atribs);
    public String getRootNode();
    public String getValueNode (String node);
    public String[] getSubNodes(String node);
    public String[] getAtributes(String node);
}
