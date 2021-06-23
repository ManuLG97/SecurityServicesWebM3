package securityservices.core.service.appservices;

import java.util.List;
import securityservices.core.service.domain.services.ServiceDTO;
import securityservices.shared.responses.ResultRequest;

//Controlador per al cas d’us específic, molt senzill doncs no cal cap tractament ni comprovació
public class UpdateServicesController extends ServiceAPIController {

    public ResultRequest<String> update(ServiceDTO service) {
        return this.serviceRepository.update(service);
    }
}
