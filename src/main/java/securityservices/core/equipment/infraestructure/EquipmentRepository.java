/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package securityservices.core.equipment.infraestructure;

import securityservices.core.equipment.infraestructure.*;
import java.util.List;
import securityservices.core.equipment.domain.services.EquipmentDTO;
import securityservices.shared.responses.ResultRequest;

/**
 *
 * @author sandr
 */
public interface EquipmentRepository {
 public ResultRequest<List<EquipmentDTO>> getAll();
 public ResultRequest<EquipmentDTO> getByID (String id);
 public ResultRequest<String> add(EquipmentDTO equipment);
 public ResultRequest<String> update(EquipmentDTO equipment);
 public ResultRequest<String> deleteByID(String id);
 public Boolean exists(String id);
}
