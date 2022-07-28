package sk.stuba.fei.uim.oop.assignment3.notAnOrder;

import lombok.Getter;

@Getter
public class NotAnOrderResponse {

    final private Long productId;
    final private int amount;

    public NotAnOrderResponse(NotAnOrder notAnOrder) {
        this.productId = notAnOrder.getProductId();
        this.amount = notAnOrder.getAmount();
    }
}
