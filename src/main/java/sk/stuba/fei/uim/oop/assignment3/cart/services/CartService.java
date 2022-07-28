package sk.stuba.fei.uim.oop.assignment3.cart.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.assignment3.cart.Cart;
import sk.stuba.fei.uim.oop.assignment3.cart.data.CartRepository;
import sk.stuba.fei.uim.oop.assignment3.cart.requests.CartRequest;
import sk.stuba.fei.uim.oop.assignment3.exceptions.BadRequestException;
import sk.stuba.fei.uim.oop.assignment3.exceptions.NotFoundException;
import sk.stuba.fei.uim.oop.assignment3.notAnOrder.NotAnOrder;
import sk.stuba.fei.uim.oop.assignment3.notAnOrder.NotAnOrderRepository;
import sk.stuba.fei.uim.oop.assignment3.product.Product;
import sk.stuba.fei.uim.oop.assignment3.product.data.ProductRepository;

import java.util.List;

@Service
public class CartService implements CartServiceInterface {

    final private CartRepository cartRepo;
    final private ProductRepository productRepo;
    final private NotAnOrderRepository notAnOrderRepository;


    @Autowired
    public CartService(CartRepository cartRepo, ProductRepository productRepo, NotAnOrderRepository notAnOrderRepository) {
        this.cartRepo = cartRepo;
        this.productRepo = productRepo;
        this.notAnOrderRepository = notAnOrderRepository;
    }

    @Override
    public List<Cart> getAll() {
        return this.cartRepo.findAll();
    }

    @Override
    public Cart create() {
        Cart cart = new Cart();
        return this.cartRepo.save(cart);
    }

    @Override
    public Cart findById(Long id) {
        return this.cartRepo.findById(id).orElseThrow();
    }

    @Override
    public void deleteCart(Long id) {
        this.cartRepo.findById(id).orElseThrow();
        this.cartRepo.deleteById(id);
    }

    @Override
    public Cart addProductToCart(Long id, CartRequest request) throws BadRequestException {
        Cart cart = this.cartRepo.findById(id).orElseThrow(NotFoundException::new);
        Product product = this.productRepo.findById(request.getProductId()).orElseThrow(NotFoundException::new);

        if (product.getAmount() >= request.getAmount() && !cart.isPayed()) {

            for (NotAnOrder i : cart.getShoppingList()) {
                if (i.getProductId().equals(request.getProductId())) {
                    i.setAmount(i.getAmount() + request.getAmount());
                    product.setAmount(product.getAmount() - request.getAmount());
                    this.productRepo.save(product);
                    return this.cartRepo.save(cart);
                }
            }
            NotAnOrder notAnOrder = new NotAnOrder(request.getProductId(), request.getAmount());
            this.notAnOrderRepository.save(notAnOrder);
            cart.getShoppingList().add(notAnOrder);
            product.setAmount(product.getAmount() - request.getAmount());
            this.productRepo.save(product);
            return this.cartRepo.save(cart);

        } else {
            throw new BadRequestException();
        }
    }

    @Override
    public String pay(Long id) throws BadRequestException {
        double toPay = 0;
        Product product;
        Cart cart = this.cartRepo.findById(id).orElseThrow(NotFoundException::new);
        if (!cart.isPayed()) {
            for (NotAnOrder i : cart.getShoppingList()) {
                product = this.productRepo.findById(i.getProductId()).orElseThrow();
                toPay += product.getPrice() * i.getAmount();
            }
            cart.setPayed(true);
            this.cartRepo.save(cart);
            return String.valueOf(toPay);
        } else {
            throw new BadRequestException();
        }
    }
}
