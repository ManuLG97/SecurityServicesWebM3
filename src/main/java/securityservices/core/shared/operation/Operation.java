package securityservices.core.shared.operation;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import securityservices.shared.responses.ResultRequest;
import securityservices.shared.responses.ResultResponses;

public abstract class Operation {

    protected int creator;
    protected double value, surcharges;
    protected String code, type, status, additonalInfo;
    protected LocalDateTime initDate, finishDate;
    protected DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd'/'MM'/'yyyy'-'HH:mm:ss");

    protected Operation() {
    }

    public String getCode() {
        return code;
    }

   public ResultRequest setCode(String code) {
        if (code == null || code.trim().equals("")) {
            return ResultRequest.fails("\"Error\":\"invalid code\"");
        }
        this.code = code;
        return ResultRequest.done(ResultResponses.SUCCESS);
    }

    public int getCreator() {
        return creator;
    }
    
    public ResultRequest setCreator(Integer creator) {
        if (creator == null || creator <0 || creator == 0) {
            return ResultRequest.fails("\"Error\":\"invalid creator\"");
        }
        this.creator = creator;
        return ResultRequest.done(ResultResponses.SUCCESS);
    }

    public double getValue() {
        return value;
    }

    public ResultRequest setValue(Double value) {
        if (value == null || value <0 || value == 0) {
            return ResultRequest.fails("\"Error\":\"invalid value\"");
        }
        this.value = value;
        return ResultRequest.done(ResultResponses.SUCCESS);
    }

    public double getSurcharges() {
        return surcharges;
    }

    public ResultRequest setSurcharges(Double surcharges) {
         if (surcharges == null || surcharges <0 || surcharges == 0) {
            return ResultRequest.fails("\"Error\":\"invalid surcharges\"");
        }
        this.surcharges = surcharges;
        return ResultRequest.done(ResultResponses.SUCCESS);
    }

    public String getType() {
        return type;
    }

     public ResultRequest setType(String type) {
        if (type == null || type.trim().equals("")) {
            return ResultRequest.fails("\"Error\":\"invalid type\"");
        }
        this.type = type;
        return ResultRequest.done(ResultResponses.SUCCESS);
    }

    public String getStatus() {
        return status;
    }

    public ResultRequest setStatus(String status) {
        if (status == null || status.trim().equals("")) {
            return ResultRequest.fails("\"Error\":\"invalid status\"");
        }
        this.status = status;
        return ResultRequest.done(ResultResponses.SUCCESS);
    }

    public String getAdditonalInfo() {
        return additonalInfo;
    }

   public ResultRequest setAdditonalInfo(String additonalInfo) {
        if (additonalInfo == null || additonalInfo.trim().equals("")) {
            return ResultRequest.fails("\"Error\":\"invalid additonalInfo\"");
        }
        this.additonalInfo = additonalInfo;
        return ResultRequest.done(ResultResponses.SUCCESS);
    }

    public String getBeginDate() {
        if (this.initDate != null) {
            return this.initDate.format(this.dateTimeFormatter);
        } 
        return "";
    }

     public ResultRequest setBeginDate(String beginDate) {
        try {
            this.initDate = LocalDateTime.parse(beginDate, dateTimeFormatter);
            return ResultRequest.done(ResultResponses.SUCCESS);
        } catch (Exception e) {
            return ResultRequest.fails("\"Error\":\"invalid beginDate: " + e.getMessage() + "\"");
        }
    }

    public String getFinishDate() {
        if (this.finishDate != null) {
            return this.finishDate.format(this.dateTimeFormatter);
        } 
        return "";
    }

   public ResultRequest setFinishDate(String finishDate) {
        try {
            this.finishDate = LocalDateTime.parse(finishDate, dateTimeFormatter);
            return ResultRequest.done(ResultResponses.SUCCESS);
        } catch (Exception e) {
            return ResultRequest.fails("\"Error\":\"invalid finishDate: " + e.getMessage() + "\"");
        }
    }
    
}
