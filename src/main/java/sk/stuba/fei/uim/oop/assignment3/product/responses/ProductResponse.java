package sk.stuba.fei.uim.oop.assignment3.product.responses;

import lombok.Getter;
import sk.stuba.fei.uim.oop.assignment3.product.Product;

@Getter
public class ProductResponse {
    final private Long id;
    final private String name;
    final private String description;
    final private int amount;
    final private String unit;
    final private double price;

    public ProductResponse(Product p) {
        this.id = p.getId();
        this.name = p.getName();
        this.description = p.getDescription();
        this.amount = p.getAmount();
        this.unit = p.getUnit();
        this.price = p.getPrice();
    }
}
