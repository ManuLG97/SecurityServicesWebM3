package securityservices.managment.catalogs.persistence;

//Classe que subministra el servei amb persistència que necessitem, amb una connexió fitxada
import securityservices.core.equipment.infraestructure.EquipmentRepository;
import securityservices.infrastructure.db.postgreadapters.EquipmentPostgreRepository;

public class EquipmentPersistenceCatalog {

    public static EquipmentRepository getInstance() {
        return new EquipmentPostgreRepository(PersistenceConnectionFactory.getDataConnection());
    }
}
