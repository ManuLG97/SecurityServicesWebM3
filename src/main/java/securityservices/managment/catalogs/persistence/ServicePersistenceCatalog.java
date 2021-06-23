package securityservices.managment.catalogs.persistence;

//Classe que subministra el servei amb persistència que necessitem, amb una connexió fitxada

import securityservices.core.service.infraestructure.ServiceRepository;
import securityservices.infrastructure.db.postgreadapters.ServicePostgreRepository;

public class ServicePersistenceCatalog {
 public static ServiceRepository getInstance() {
 return new ServicePostgreRepository(PersistenceConnectionFactory.getDataConnection());
 }
}