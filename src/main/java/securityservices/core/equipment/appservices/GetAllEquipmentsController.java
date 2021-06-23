package securityservices.core.equipment.appservices;

import securityservices.core.service.appservices.*;
import java.util.List;
import securityservices.core.equipment.domain.services.EquipmentDTO;
import securityservices.shared.responses.ResultRequest;

//Controlador per al cas d’us específic, molt senzill doncs no cal cap tractament ni comprovació
public class GetAllEquipmentsController extends EquipmentAPIController {

    public ResultRequest<List<EquipmentDTO>> getAll() {
        return this.equipmentRepository.getAll();
    }
}
