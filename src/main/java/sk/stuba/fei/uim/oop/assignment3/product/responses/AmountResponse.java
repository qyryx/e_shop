package sk.stuba.fei.uim.oop.assignment3.product.responses;

import lombok.Getter;
import sk.stuba.fei.uim.oop.assignment3.product.Product;

@Getter

public class AmountResponse {

    final private int amount;

    public AmountResponse(Product p) {
        this.amount = p.getAmount();
    }
}
