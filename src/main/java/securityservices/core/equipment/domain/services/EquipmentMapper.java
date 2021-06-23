package securityservices.core.equipment.domain.services;

import securityservices.core.equipment.domain.model.Equipment;
import securityservices.shared.responses.ResultRequest;

public class EquipmentMapper {

    public static ResultRequest<Equipment> componentFromDTO(EquipmentDTO edto) {
        return Equipment.getInstance(
                edto.getCode(),
                edto.getName(),
                edto.getType(),
                edto.getMaker(),
                edto.getDescription(),
                edto.getPrice(),
                edto.getHigh(),
                edto.getWide(),
                edto.getDeep(),
                edto.getWeight(),
                edto.isFragile(),
                edto.getFunction(),
                edto.getComponents(),
                edto.getPower()
        );
    }

    public static EquipmentDTO dtoFromComponent(Equipment e) {
        return new EquipmentDTO(
                e.getCode(),
                e.getName(),
                e.getType(),
                e.getMaker(),
                e.getDescription(),
                e.getPrice(),
                e.getHigh(),
                e.getWide(),
                e.getDeep(),
                e.getWeight(),
                e.isFragile(),
                e.getFunction(),
                e.getComponents(),
                e.getPower(),
                e.getEquipmentId()
        );
    }
}