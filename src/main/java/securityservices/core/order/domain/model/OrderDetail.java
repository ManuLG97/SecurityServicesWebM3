package securityservices.core.order.domain.model;

import java.time.LocalDateTime;
import securityservices.shared.responses.ResultRequest;
import securityservices.shared.responses.ResultResponses;

public class OrderDetail {

    protected int amount;
    protected double price;
    protected String ref;

    public OrderDetail() {
    }

    public OrderDetail(String ref, int amount, double price) {
        this.ref = ref;
        this.amount = amount;
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public ResultRequest setAmount(Integer amount) {
        if (amount == null || amount <0 || amount == 0) {
            return ResultRequest.fails("\"Error\":\"invalid amount\"");
        }
        this.amount = amount;
        return ResultRequest.done(ResultResponses.SUCCESS);
    }


    public double getPrice() {
        return price;
    }

    public ResultRequest setPrice(Double price) {
        if (price == null || price <0 || price == 0) {
            return ResultRequest.fails("\"Error\":\"invalid price\"");
        }
        this.price = price;
        return ResultRequest.done(ResultResponses.SUCCESS);
    }

    public String getRef() {
        return ref;
    }

    public ResultRequest setRef(String ref) {
        if (ref == null || ref.trim().equals("")) {
            return ResultRequest.fails("\"Error\":\"invalid ref\"");
        }
        this.ref = ref;
        return ResultRequest.done(ResultResponses.SUCCESS);
    }
}
