package securityservices.core.service.appservices;

//Controlador genèric que adquireix la classe que ens connecta amb persistència
import securityservices.core.service.infraestructure.ServiceRepository;
import securityservices.managment.catalogs.persistence.ServicePersistenceCatalog;

public abstract class ServiceAPIController {

    protected ServiceRepository serviceRepository;
    //Constructor que rep l’objecte per injecció de dependències que ens connecta a persistència

    public ServiceAPIController(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    //Constructor que demana a la capa de managment l’objecte que ens connecta a persistència
    public ServiceAPIController() {
        this.serviceRepository = ServicePersistenceCatalog.getInstance();
    }

}
