package securityservices.core.order.appservices;

import securityservices.shared.responses.ResultRequest;
import securityservices.core.shared.services.serializers.Json;
import securityservices.core.shared.services.serializers.JsonObjectFactory;
import securityservices.core.shared.services.serializers.Serializer;
import securityservices.core.order.domain.services.OrderDTO;

public class JsonOrderSerializer implements Serializer {

    private Json jOrder = JsonObjectFactory.getInstance();

    public JsonOrderSerializer() {
    }

    @Override
    public ResultRequest<OrderDTO> unserialize(String data) {
        jOrder.set(data);
        try {
            OrderDTO order = new OrderDTO(
                    jOrder.get("code"),
                    Integer.valueOf(jOrder.get("creator")),
                    Double.valueOf(jOrder.get("value")),
                    Double.valueOf(jOrder.get("surcharges")),
                    jOrder.get("type"),
                    jOrder.get("status"),
                    jOrder.get("additonalInfo"),
                    jOrder.get("beginDate"),
                    jOrder.get("finishDate"),
                    jOrder.get("paymentType"),
                    jOrder.get("paymentDate"),
                    jOrder.get("orderId")
            );
            return ResultRequest.done(order);

        } catch (Exception e) {
            return ResultRequest.fails("{\"Error\":\"OrderDTO unserialized Exception\","
                    + "\"Details\":\"" + e.toString() + "\"}");
        }
    }

    @Override
    public ResultRequest<String> serialize(Object order) {
        jOrder.removeAll();
        jOrder.set("code", ((OrderDTO) order).getCode());
        jOrder.set("creator", String.valueOf(((OrderDTO) order).getCreator()));
        jOrder.set("value", String.valueOf(((OrderDTO) order).getValue()));
        jOrder.set("surcharges", String.valueOf(((OrderDTO) order).getSurcharges()));
        jOrder.set("type", ((OrderDTO) order).getType());
        jOrder.set("status", ((OrderDTO) order).getStatus());
        jOrder.set("additonalInfo", ((OrderDTO) order).getAdditonalInfo());
        jOrder.set("beginDate", ((OrderDTO) order).getBeginDate());
        jOrder.set("finishDate", ((OrderDTO) order).getFinishDate());
        jOrder.set("paymentType", ((OrderDTO) order).getPaymentType());
        jOrder.set("paymentDate", ((OrderDTO) order).getPaymentDate());
        jOrder.set("orderId", ((OrderDTO) order).getOrderId());
        
        return ResultRequest.done(jOrder.toString());
    }

}
