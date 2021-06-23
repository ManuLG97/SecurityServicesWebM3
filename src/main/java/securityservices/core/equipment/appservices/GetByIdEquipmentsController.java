package securityservices.core.equipment.appservices;

import securityservices.core.equipment.appservices.*;
import java.util.List;
import securityservices.core.equipment.domain.services.EquipmentDTO;
import securityservices.shared.responses.ResultRequest;

//Controlador per al cas d’us específic, molt senzill doncs no cal cap tractament ni comprovació
public class GetByIdEquipmentsController extends EquipmentAPIController {

    public ResultRequest<EquipmentDTO> getByID(String id) {
        return this.equipmentRepository.getByID(id);
    }
}
