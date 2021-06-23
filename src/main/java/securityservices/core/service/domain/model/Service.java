package securityservices.core.service.domain.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.UUID;
import securityservices.core.shared.products.Product;
import securityservices.shared.responses.ResultResponses;
import securityservices.shared.responses.ResultRequest;

public class Service extends Product {
    protected UUID serviceId;
    protected String periodicity, conditions;
    protected LocalDate startDate, finishDate;
    protected DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy'-'MM'-'dd");

    public Service() {
        this.setServiceId();
    }
     public static Service getInstance() {
        return new Service();
    }
    
     public static ResultRequest<Service> getInstance(String code, String name, String type, String maker, String description, double price,
            String periodicity, String conditions, String startDate, String finishDate) {

        Service service = new Service();
        
        ResultRequest result = service.setCode(code);
        if (result.failed()) {
            return result;
        }
        result = service.setName(name);
        if (result.failed()) {
            return result;
        }
        result = service.setType(type);
        if (result.failed()) {
            return result;
        }
        result = service.setMaker(maker);
        if (result.failed()) {
            return result;
        }
        
        result = service.setDescription(description);
        if (result.failed()) {
            return result;
        }
        
        result = service.setPrice(price);
        if (result.failed()) {
            return result;
        }
        
        result = service.setPeriodicity(periodicity);
        if (result.failed()) {
            return result;
        }
        
        result = service.setConditions(conditions);        
        if (result.failed()) {
            return result;
        }
        
        result = service.setBeginDate(startDate);
        if (result.failed()) {
            return result;
        }
         result = service.setFinishDate(finishDate);
        if (result.failed()) {
            return result;
        }        

        return ResultRequest.done(service);
    }

    public String getServiceId() {
        return serviceId.toString();
    }

    protected void setServiceId() {
        this.serviceId = UUID.randomUUID();
    }

    public String getBeginDate() {
        if (this.startDate != null) {
            return startDate.format(this.dateTimeFormat);
        }
        return "";
    }

    public ResultRequest setBeginDate(String beginDate) {
        try {
            this.startDate = LocalDate.parse(beginDate, dateTimeFormat);
            return ResultRequest.done(ResultResponses.SUCCESS);
        } catch (Exception e) {
            return ResultRequest.fails("\"Error\":\"invalid beginDate: " + e.getMessage() + "\"");
        }
    }
    

    public String getFinishDate() {
        if (this.finishDate != null) {
            return this.finishDate.format(this.dateTimeFormat);
        } 
        return "";
    }

    public ResultRequest setFinishDate(String finishDate) {
        try {
            this.finishDate = LocalDate.parse(finishDate, dateTimeFormat);
            return ResultRequest.done(ResultResponses.SUCCESS);
        } catch (Exception e) {
            return ResultRequest.fails("\"Error\":\"invalid finishDate: " + e.getMessage() + "\"");
        }
    }

    public String getPeriodicity() {
        return periodicity;
    }

    public ResultRequest setPeriodicity(String periodicity) {
        if (periodicity == null || periodicity.trim().equals("")) {
            return ResultRequest.fails("\"Error\":\"invalid periodicity\"");
        }
        this.periodicity = periodicity;
        return ResultRequest.done(ResultResponses.SUCCESS);
    }

    public String getConditions() {
        return conditions;
    }

    public ResultRequest setConditions(String conditions) {
        if (conditions == null || conditions.trim().equals("")) {
            return ResultRequest.fails("\"Error\":\"invalid conditions\"");
        }
        this.conditions = conditions;
        return ResultRequest.done(ResultResponses.SUCCESS);
    }

    @Override
    public String getDetails() {
        return "Periodicity:" + this.periodicity
                + ";Conditions:" + this.conditions;
    }

 
}