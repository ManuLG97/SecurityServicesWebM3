package securityservices.core.equipment.appservices;

import securityservices.core.equipment.domain.services.EquipmentDTO;
import securityservices.core.service.appservices.*;
import securityservices.shared.responses.ResultRequest;

//Controlador per al cas d’us específic, molt senzill doncs no cal cap tractament ni comprovació
public class AddEquipmentsController extends EquipmentAPIController {

    public ResultRequest<String> add(EquipmentDTO equipment) {
        return this.equipmentRepository.add(equipment);
    }
}
