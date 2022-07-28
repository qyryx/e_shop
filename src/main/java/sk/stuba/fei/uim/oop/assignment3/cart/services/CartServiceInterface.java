package sk.stuba.fei.uim.oop.assignment3.cart.services;

import sk.stuba.fei.uim.oop.assignment3.cart.Cart;
import sk.stuba.fei.uim.oop.assignment3.cart.requests.CartRequest;
import sk.stuba.fei.uim.oop.assignment3.exceptions.BadRequestException;

import java.util.List;

public interface CartServiceInterface {
    List<Cart> getAll();
    Cart create();
    Cart findById(Long id);
    void deleteCart(Long id);
    Cart addProductToCart(Long id, CartRequest request) throws BadRequestException;
    String pay(Long id) throws BadRequestException;
}
