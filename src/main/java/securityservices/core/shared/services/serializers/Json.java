package securityservices.core.shared.services.serializers;
//interficie que permet a la resta del projecte esser independent de la llibreria
//per al tractament d'informació en format json que ens interessi incloure

public interface Json {
    public void setXmlData(String data);                //build a new json object from String data    
    public String toXml();                              //return the json object in a String Xml format
    public boolean has(String attrib);                  //has a specific attrib
    public void set(String data);
    public void set (String attrib, String value);      //set value in a specific attrib
    public void set (String attrib, Json jdata);
    public String get(String attrib);                   //get value for specific attrib
    public int getArraySize (String attrib);            //if attrib is a Array return yours number of elements
    public String getArrayValue (String data, int pos);
    public Json getArrayObj (String data, int pos);  //get a specific Json form the Array
    public Json getJResult (String data);            //get a Json value
    public void remove( String attrib);                     //remove a especific attrib        
    public void removeAll();
}