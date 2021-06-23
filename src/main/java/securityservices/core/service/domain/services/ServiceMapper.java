package securityservices.core.service.domain.services;

import securityservices.core.service.domain.model.Service;
import securityservices.shared.responses.ResultRequest;

public class ServiceMapper {

    public static ResultRequest<Service> componentFromDTO(ServiceDTO sdto) {
        return Service.getInstance(
                sdto.getName(),
                sdto.getCode(),
                sdto.getType(),
                sdto.getMaker(),
                sdto.getDescription(),
                sdto.getPrice(),
                sdto.getPeriodicity(),
                sdto.getConditions(),
                sdto.getBeginDate(),
                sdto.getFinishDate()
        );
    }

    public static ServiceDTO dtoFromComponent(Service s) {
        return new ServiceDTO(
                s.getName(),
                s.getCode(),
                s.getType(),
                s.getDescription(),
                s.getMaker(),
                s.getPrice(),
                s.getPeriodicity(),
                s.getConditions(),
                s.getBeginDate(),
                s.getFinishDate(),
                s.getServiceId()
        );
    }
}