package sk.stuba.fei.uim.oop.assignment3.product.requests;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class ProductEditRequest {
    private String name;
    private String description;
}