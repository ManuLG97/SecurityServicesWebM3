package securityservices.core.shared.products;

import securityservices.core.shared.data.PhysicalData;
import securityservices.shared.responses.ResultRequest;
import securityservices.shared.responses.ResultResponses;

public abstract class PhysicalProduct extends Product implements Storable {

    protected PhysicalData physics;

    public ResultRequest setHigh(Double high) {
        if (high == null || high < 0 || high == 0) {
            return ResultRequest.fails("\"Error\":\"invalid high\"");
        }
        this.physics.setHigh(high);
        return ResultRequest.done(ResultResponses.SUCCESS);
    }

    public ResultRequest setWide(Double wide) {
        if (wide == null || wide < 0 || wide == 0) {
            return ResultRequest.fails("\"Error\":\"invalid wide\"");
        }
        this.physics.setWide(wide);
        return ResultRequest.done(ResultResponses.SUCCESS);
    }

    public ResultRequest setDeep(Double deep) {
        if (deep == null || deep < 0 || deep == 0) {
            return ResultRequest.fails("\"Error\":\"invalid deep\"");
        }
        this.physics.setDeep(deep);
        return ResultRequest.done(ResultResponses.SUCCESS);
    }

    public ResultRequest setWeight(Double weight) {
        if (weight == null || weight < 0 || weight == 0) {
            return ResultRequest.fails("\"Error\":\"invalid weight\"");
        }
        this.physics.setWeight(weight);
        return ResultRequest.done(ResultResponses.SUCCESS);
    }

    public ResultRequest isFragile(Boolean fragile) {
        if (fragile == null) {
            return ResultRequest.fails("\"Error\":\"invalid fragile\"");
        }
        this.physics.isFragile(fragile);
        return ResultRequest.done(ResultResponses.SUCCESS);
    }

    public void setPhysics(PhysicalData physics) {
        this.physics = physics;
    }

    public Double getHigh() {
        return this.physics.getHigh();
    }

    public Double getWide() {
        return this.physics.getWide();
    }

    public Double getDeep() {
        return this.physics.getDeep();
    }

    public Double getWeight() {
        return this.physics.getWeight();
    }

    public Boolean isFragile() {
        return this.physics.getFragile();
    }

}
