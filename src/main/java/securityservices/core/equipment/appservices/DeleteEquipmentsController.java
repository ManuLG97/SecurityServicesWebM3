package securityservices.core.equipment.appservices;

import securityservices.core.service.appservices.*;
import securityservices.shared.responses.ResultRequest;

//Controlador per al cas d’us específic, molt senzill doncs no cal cap tractament ni comprovació
public class DeleteEquipmentsController extends EquipmentAPIController {

    public ResultRequest<String> deleteByID(String id) {
        return this.equipmentRepository.deleteByID(id);
    }
}
