/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package securityservices.core.service.infraestructure;

import securityservices.core.equipment.infraestructure.*;
import securityservices.core.service.infraestructure.*;
import java.util.List;
import securityservices.core.service.domain.services.ServiceDTO;
import securityservices.shared.responses.ResultRequest;

/**
 *
 * @author sandr
 */
public interface ServiceRepository {
 public ResultRequest<List<ServiceDTO>> getAll();
 public ResultRequest<ServiceDTO> getByID (String id);
 public ResultRequest<String> add(ServiceDTO service);
 public ResultRequest<String> update(ServiceDTO service);
 public ResultRequest<String> deleteByID(String id);
 public Boolean exists(String id);
}
