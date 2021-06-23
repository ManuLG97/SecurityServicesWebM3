package securityservices.core.order.domain.services;

/*import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;*/
/*
DTO inmutable generado a traves del constructor para transferirlo a otra capa
sin permitir cambios en su contenido
 */
public class OrderDTO {

    private final String code, type, status, additonalInfo, beginDate, finishDate, paymentType, paymentDate, orderId;
    private final double value, surcharges;
    private final int creator;

    public OrderDTO(String code, Integer creator, Double value, Double surcharges, String type,
            String status, String additonalInfo, String beginDate, String finishDate,
            String paymentType, String paymentDate, String orderId) {
        this.code = code;
        this.creator = creator;
        this.value = value;
        this.surcharges = surcharges;
        this.type = type;
        this.status = status;
        this.additonalInfo = additonalInfo;
        this.beginDate = beginDate;
        this.finishDate = finishDate;
        this.paymentType = paymentType;
        this.paymentDate = paymentDate;
        this.orderId = orderId;
    }

    public String getCode() {
        return code;
    }

    public int getCreator() {
        return creator;
    }
    
    public double getValue() {
        return value;
    }

    public double getSurcharges() {
        return surcharges;
    }

    public String getType() {
        return type;
    }

    public String getStatus() {
        return status;
    }
    
    public String getAdditonalInfo() {
        return additonalInfo;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public String getFinishDate() {
        return finishDate;
    }

    public String getPaymentType() {
        return paymentType;
    }
    public String getPaymentDate() {
        return paymentDate;
    }
    
    public String getOrderId() {
        return orderId;
    }
}
