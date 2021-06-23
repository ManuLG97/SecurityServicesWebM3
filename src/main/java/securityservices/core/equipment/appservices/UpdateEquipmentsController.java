package securityservices.core.equipment.appservices;

import securityservices.core.equipment.appservices.*;
import java.util.List;
import securityservices.core.equipment.domain.services.EquipmentDTO;
import securityservices.shared.responses.ResultRequest;

//Controlador per al cas d’us específic, molt senzill doncs no cal cap tractament ni comprovació
public class UpdateEquipmentsController extends EquipmentAPIController {

    public ResultRequest<String> update(EquipmentDTO equipment) {
        return this.equipmentRepository.update(equipment);
    }
}
