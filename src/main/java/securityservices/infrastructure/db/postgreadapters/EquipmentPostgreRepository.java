package securityservices.infrastructure.db.postgreadapters;

import java.util.ArrayList;
import java.util.List;
import securityservices.core.equipment.appservices.JsonEquipmentSerializer;
import securityservices.core.equipment.domain.services.EquipmentDTO;
import securityservices.core.equipment.infraestructure.EquipmentRepository;
import securityservices.core.shared.services.serializers.Json;
import securityservices.core.shared.services.serializers.JsonObjectFactory;
import securityservices.infrastructure.db.connectors.JdbcConnector;
import securityservices.infrastructure.db.connectors.PersistenceConnector;
import securityservices.shared.responses.ResultRequest;

public class EquipmentPostgreRepository implements EquipmentRepository {

    private PersistenceConnector connection;
    private ResultRequest<Json> resul;
    private Boolean exist = true;
    JsonEquipmentSerializer jSerializer = new JsonEquipmentSerializer();
    //constructor on rebem les dades de la connexió, per defecte fem servir un connector jdbc

    public EquipmentPostgreRepository(Json dataconnex) {
        this.connection = new JdbcConnector(dataconnex);
    }
    //constructor on rebem l’objecte per la connexió fent servir Inversió de Dependències

    public EquipmentPostgreRepository(PersistenceConnector connection) {
        this.connection = connection;
    }

    //espai lliure per implementar els serveis de la interfície i els interns necessaris
    @Override
    public ResultRequest<List<EquipmentDTO>> getAll() {
        try {
            resul = this.connection.readQuery("SELECT * FROM equipments;");
            Json equipment = resul.getValue();
            ArrayList<EquipmentDTO> equipments = new ArrayList();
            for (int i = 0; i < resul.getValue().getArraySize("content"); i++) {

                String newJsonEquipment = (equipment.getArrayObj("content", i)).toString();
                String physic = (equipment.getArrayObj("content", i)).get("physicdata");
                Json jphysic = JsonObjectFactory.getInstance();
                jphysic.set(physic);
                Json jobj = JsonObjectFactory.getInstance();
                jobj.set(newJsonEquipment);
                String all = "{\"code\":\"" + jobj.get("code") + "\",\"name\":\"" + jobj.get("name") + "\",\"type\":\""
                        + jobj.get("type") + "\",\"maker\":\"" + jobj.get("maker") + "\",\"description\":\"" + jobj.get("description")
                        + "\",\"price\":\"" + jobj.get("price") + "\",\"high\":\"" + jphysic.get("high") + "\",\"wide\":\"" + jphysic.get("wide")
                        + "\",\"deep\":\"" + jphysic.get("deep") + "\",\"weight\":\"" + jphysic.get("weigth") + "\",\"fragile\":\"" + jphysic.get("fragile")
                        + "\",\"function\":\"" + jobj.get("function") + "\",\"components\":\"" + jobj.get("components") + "\",\"power\":\"" + jobj.get("power")
                        + "\",\"equipmentid\":\"" + jobj.get("equipmentid") + "\"}";
                ResultRequest<EquipmentDTO> equipmentUnserialized = jSerializer.unserialize(all);
                System.out.println(equipmentUnserialized.getValue());
                equipments.add(equipmentUnserialized.getValue());
            }
            return ResultRequest.done(equipments);
        } catch (Exception ex) {
            return ResultRequest.fails("\"Error\":" + ex.toString());
        }
    }

    @Override
    public ResultRequest<EquipmentDTO> getByID(String id) {
        try {
            resul = this.connection.readQuery("SELECT * FROM equipments WHERE equipmentId = '" + id + "'");
            Json equipment = resul.getValue();
            String res = equipment.get("content");
            ResultRequest<EquipmentDTO> equipmentUnserialized = jSerializer.unserialize(res);
            return ResultRequest.done(equipmentUnserialized.getValue());

        } catch (Exception ex) {
            return ResultRequest.fails("\"Error\":" + ex.toString());
        }
    }

    @Override
    public ResultRequest<String> add(EquipmentDTO equipment) {
        try {
            String values = "INSERT INTO equipments VALUES ('" + equipment.getCode() + "','" + equipment.getName()
                    + "','" + equipment.getType() + "','" + equipment.getMaker() + "','" + equipment.getDescription()
                    + "'," + equipment.getPrice() + ",'" + equipment.getEquipmentId() + "','" + equipment.getFunction()
                    + "','" + equipment.getComponents() + "'," + equipment.getPower() + ",'{\"high\":" + equipment.getHigh()
                    + ",\"wide\":" + equipment.getWide() + ",\"deep\":" + equipment.getDeep()
                    + ",\"weigth\":" + equipment.getWeight() + ",\"fragile\":" + equipment.isFragile() + "}')";

            System.out.println(values);
            this.resul = this.connection.writeQuery(values);

            if (this.resul.failed()) {
                return ResultRequest.fails("\"Error\":" + resul.getError());
            } else {
                return ResultRequest.done("Successful insert into");
            }

        } catch (Exception ex) {
            return ResultRequest.fails("\"Error\":" + ex.toString());
        }
    }

    @Override
    public ResultRequest<String> update(EquipmentDTO equipment) {
        try {
            String up = "UPDATE equipments SET code='" + equipment.getCode() + "', name='" + equipment.getName() + "', type='"
                    + equipment.getType() + "', maker='" + equipment.getMaker() + "', description='" + equipment.getDescription()
                    + "', price=" + equipment.getPrice() + ", equipmentid='" + equipment.getEquipmentId() + "', function='"
                    + equipment.getFunction() + "', components='" + equipment.getComponents() + "', power="
                    + equipment.getPower() + ", physicdata='{\"high\":\"" + equipment.getHigh() + "\",\"wide\":\"" + equipment.getWide()
                    + "\",\"deep\":\"" + equipment.getDeep() + "\",\"weigth\":\"" + equipment.getWeight() + "\",\"fragile\":\"" + equipment.isFragile() + "\"}' WHERE code='" + equipment.getCode() + "'";
            System.out.println(up);
            ResultRequest<Json> result = this.connection.writeQuery(up);
            return ResultRequest.done("\"Updated\"");
        } catch (Exception ex) {
            return ResultRequest.fails("\"Error\"");
        }
    }

    @Override
    public ResultRequest<String> deleteByID(String id) {
        try {
            this.resul = this.connection.writeQuery("DELETE FROM equipments WHERE equipmentId='" + id + "'");

            if (this.resul.failed()) {
                return ResultRequest.fails("\"Error\"");
            } else {
                return ResultRequest.done("\"Deleted\"");
            }

        } catch (Exception ex) {
            return ResultRequest.fails("\"Error\":" + resul.getError());
        }
    }

    @Override
    public Boolean exists(String id) {
        return exist;
    }
}
