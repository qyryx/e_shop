package sk.stuba.fei.uim.oop.assignment3.cart.responses;

import lombok.Getter;
import sk.stuba.fei.uim.oop.assignment3.cart.Cart;
import sk.stuba.fei.uim.oop.assignment3.notAnOrder.NotAnOrderResponse;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class CartResponse {

    final private Long id;
    final private List<NotAnOrderResponse> shoppingList;
    final private boolean payed;

    public CartResponse(Cart cart) {
        this.id = cart.getId();
        this.shoppingList = cart.getShoppingList().stream().map(NotAnOrderResponse::new).collect(Collectors.toList());
        this.payed = cart.isPayed();
    }
}
