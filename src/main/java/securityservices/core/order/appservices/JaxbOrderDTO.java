package securityservices.core.order.appservices;

import securityservices.core.order.appservices.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
/*
    DTO generado para adaptarnos a la magia de JAXB
 */
@XmlRootElement(name = "order")
public class JaxbOrderDTO {

    private String code, type, status, additonalInfo, beginDate, finishDate, paymentType, paymentDate, orderId;
    private double value, surcharges;
    private int creator;
    
    public JaxbOrderDTO(){}
    
    public JaxbOrderDTO(String code, Integer creator, Double value, Double surcharges, String type,
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

    @XmlElement(name = "code")
    public String getCode() {
        return code;
    }

    @XmlElement(name = "creator")
    public int getCreator() {
        return creator;
    }

    @XmlElement(name = "value")
    public double getValue() {
        return value;
    }

    @XmlElement(name = "surcharges")
    public double getSurcharges() {
        return surcharges;
    }

    @XmlElement(name = "type")
    public String getType() {
        return type;
    }

    @XmlElement(name = "status")
    public String getStatus() {
        return status;
    }
    
    @XmlElement(name = "additonalInfo")
    public String getAdditonalInfo() {
        return additonalInfo;
    }

    @XmlElement(name = "beginDate")
    public String getBeginDate() {
        return beginDate;
    }

    @XmlElement(name = "finishDate")
    public String getFinishDate() {
        return finishDate;
    }
    @XmlElement(name = "paymentType")
    public String getPaymentType() {
        return paymentType;
    }
    @XmlElement(name = "paymentDate")
    public String getPaymentDate() {
        return paymentDate;
    }
    @XmlElement(name = "orderId")
    public String getOrderId() {
        return orderId;
    }

    //@XmlTransient

    public void setCode(String code) {
        this.code = code;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setAdditonalInfo(String additonalInfo) {
        this.additonalInfo = additonalInfo;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public void setFinishDate(String finishDate) {
        this.finishDate = finishDate;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void setSurcharges(double surcharges) {
        this.surcharges = surcharges;
    }

    public void setCreator(int creator) {
        this.creator = creator;
    }
   
    
}
