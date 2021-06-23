package securityservices.core.service.appservices;

import java.util.List;
import securityservices.core.service.domain.services.ServiceDTO;
import securityservices.shared.responses.ResultRequest;

//Controlador per al cas d’us específic, molt senzill doncs no cal cap tractament ni comprovació
public class AddServicesController extends ServiceAPIController {

    public ResultRequest<String> add(ServiceDTO service) {
        return this.serviceRepository.add(service);
    }
}
