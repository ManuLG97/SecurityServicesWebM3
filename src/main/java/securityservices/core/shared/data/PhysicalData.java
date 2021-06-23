package securityservices.core.shared.data;

import securityservices.shared.responses.ResultRequest;
import securityservices.shared.responses.ResultResponses;

public class PhysicalData {

    protected Double high, wide, deep, weight;
    protected Boolean fragile;

    public PhysicalData() {
    }

    public PhysicalData getInstance() {
        return new PhysicalData();
    }

    public static ResultRequest<PhysicalData> getInstance(Double high, Double wide, Double deep, Double weight, Boolean fragile) {
        PhysicalData physicalData = new PhysicalData();

       ResultRequest result = physicalData.setHigh(high);
        if (result.failed()) {
            return result;
        } 
        result = physicalData.setWide(wide);
        if (result.failed()) {
            return result;
        } 
        result = physicalData.setDeep(deep);
        if (result.failed()) {
            return result;
        } 
        result = physicalData.setWeight(weight);
        if (result.failed()) {
            return result;
        } 
        result = physicalData.isFragile(fragile);
        if (result.failed()) {
            return result;
        } 
        
        return ResultRequest.done(physicalData);

    }

    public Double getHigh() {
        return high;
    }

    public ResultRequest setHigh(Double high) {
        if (high == null || high<0 || high == 0) {
            return ResultRequest.fails("\"Error\":\"invalid high\"");
        }
        this.high = high;
        return ResultRequest.done(ResultResponses.SUCCESS);
    }

    public Double getWide() {
        return wide;
    }

    public ResultRequest setWide(Double wide) {
        if (wide == null || wide<0 || wide == 0) {
            return ResultRequest.fails("\"Error\":\"invalid wide\"");
        }
        this.wide = wide;
        return ResultRequest.done(ResultResponses.SUCCESS);
    }

    public Double getDeep() {
        return deep;
    }

    public ResultRequest setDeep(Double deep) {
        if (deep == null || deep<0 || deep == 0) {
            return ResultRequest.fails("\"Error\":\"invalid deep\"");
        }
        this.deep = deep;
        return ResultRequest.done(ResultResponses.SUCCESS);
    }

    public Double getWeight() {
        return weight;
    }

    public ResultRequest setWeight(Double weight) {
        if (weight == null || weight<0 || weight == 0) {
            return ResultRequest.fails("\"Error\":\"invalid weight\"");
        }
        this.weight = weight;
        return ResultRequest.done(ResultResponses.SUCCESS);
    }

    public Boolean getFragile() {
        return fragile;
    }

    public ResultRequest isFragile(Boolean fragile) {
        if (fragile == null) {
            return ResultRequest.fails("\"Error\":\"invalid fragile\"");
        }
        this.fragile = fragile;
        return ResultRequest.done(ResultResponses.SUCCESS);
    }

}
