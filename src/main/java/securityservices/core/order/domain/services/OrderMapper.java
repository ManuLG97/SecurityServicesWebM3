package securityservices.core.order.domain.services;


import securityservices.core.order.domain.model.Order;
import securityservices.shared.responses.ResultRequest;

public class OrderMapper {

    public static ResultRequest<Order> componentFromDTO(OrderDTO odto) {
        return Order.getInstance(
                odto.getCode(),
                odto.getCreator(),
                odto.getValue(),
                odto.getSurcharges(),
                odto.getType(),
                odto.getStatus(),
                odto.getAdditonalInfo(),
                odto.getBeginDate(),
                odto.getFinishDate(),
                odto.getPaymentType(),
                odto.getPaymentDate()
        );
    }

    public static OrderDTO dtoFromComponent(Order o) {
        return new OrderDTO(
                o.getCode(),
                o.getCreator(),
                o.getValue(),
                o.getSurcharges(),
                o.getType(),
                o.getStatus(),
                o.getAdditonalInfo(),
                o.getBeginDate(),
                o.getFinishDate(),
                o.getPaymentType(),
                o.getPaymentDate(),
                o.getOrderId()
        );
    }
}