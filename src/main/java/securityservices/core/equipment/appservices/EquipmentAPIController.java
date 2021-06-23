package securityservices.core.equipment.appservices;

//Controlador genèric que adquireix la classe que ens connecta amb persistència
import securityservices.core.equipment.appservices.*;
import securityservices.core.equipment.infraestructure.EquipmentRepository;
import securityservices.managment.catalogs.persistence.EquipmentPersistenceCatalog;

public abstract class EquipmentAPIController {

    protected EquipmentRepository equipmentRepository;
    //Constructor que rep l’objecte per injecció de dependències que ens connecta a persistència

    public EquipmentAPIController(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }

    //Constructor que demana a la capa de managment l’objecte que ens connecta a persistència
    public EquipmentAPIController() {
        this.equipmentRepository = EquipmentPersistenceCatalog.getInstance();
    }

}
