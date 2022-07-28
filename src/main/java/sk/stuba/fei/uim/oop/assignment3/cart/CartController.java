package sk.stuba.fei.uim.oop.assignment3.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.stuba.fei.uim.oop.assignment3.cart.requests.CartRequest;
import sk.stuba.fei.uim.oop.assignment3.cart.responses.CartResponse;
import sk.stuba.fei.uim.oop.assignment3.cart.services.CartServiceInterface;
import sk.stuba.fei.uim.oop.assignment3.exceptions.BadRequestException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartServiceInterface service;

    @GetMapping()
    public List<CartResponse> getAll() {
        return this.service.getAll().stream().map(CartResponse::new).collect(Collectors.toList());
    }

    @PostMapping()
    public ResponseEntity<CartResponse> addCart() {
        return new ResponseEntity<>(new CartResponse(this.service.create()), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public CartResponse getCartById(@PathVariable("id") Long id) {
        return new CartResponse(this.service.findById(id));
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Long id) {
        this.service.deleteCart(id);
    }

    @PostMapping("{id}/add")
    public CartResponse addProductToCart(@PathVariable("id") Long id, @RequestBody CartRequest request) throws BadRequestException {
        return new CartResponse(this.service.addProductToCart(id, request));
    }

    @GetMapping("{id}/pay")
    public String payForCart(@PathVariable("id") Long id) throws BadRequestException {
        return this.service.pay(id);
    }
}
