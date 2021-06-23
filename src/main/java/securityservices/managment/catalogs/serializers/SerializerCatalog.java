package securityservices.managment.catalogs.serializers;

import java.util.TreeMap;
import securityservices.core.component.client.appservices.JaxbClientSerializer;
import securityservices.core.component.client.appservices.JsonClientSerializer;
import securityservices.core.component.client.appservices.XmlClientSerializer;
import securityservices.core.equipment.appservices.JsonEquipmentSerializer;
import securityservices.core.equipment.appservices.XmlEquipmentSerializer;
import securityservices.core.order.appservices.JaxbOrderSerializer;
import securityservices.core.order.appservices.JsonOrderSerializer;
import securityservices.core.service.appservices.JsonServiceSerializer;
import securityservices.core.service.appservices.XmlServiceSerializer;
import securityservices.core.shared.services.serializers.Serializer;
import securityservices.core.shared.services.serializers.xmlapis.Dom;
import securityservices.core.shared.services.serializers.xmlapis.JDom;

public class SerializerCatalog {
    
    private static TreeMap<SerializerType, Serializer> catalog = new TreeMap<>();
    
    private static void loadCatalog(){
        //CLIENT
        catalog.put(SerializerType.XmlClient, new XmlClientSerializer(new Dom()));
        catalog.put(SerializerType.JaxbClient, JaxbClientSerializer.getInstance().getValue());
        catalog.put(SerializerType.JsonClient, new JsonClientSerializer());
        //SERVICE
        catalog.put(SerializerType.XmlService, new XmlServiceSerializer(new Dom()));
        catalog.put(SerializerType.JsonService, new JsonServiceSerializer());
        //EQUIPMENT
        catalog.put(SerializerType.XmlEquipment, new XmlEquipmentSerializer(new JDom()));
        catalog.put(SerializerType.JsonEquipment, new JsonEquipmentSerializer());
        //ORDER
        catalog.put(SerializerType.JaxbOrder, JaxbOrderSerializer.getInstance().getValue());
        catalog.put(SerializerType.JsonOrder, new JsonOrderSerializer());
    }
    
    public static Serializer getInstance(SerializerType type){
        if (catalog.isEmpty()){
            loadCatalog();
        }
        return catalog.get(type);
    }
}
