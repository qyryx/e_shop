package sk.stuba.fei.uim.oop.assignment3.cart.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartRequest {
    private Long productId;
    private int amount;
}
