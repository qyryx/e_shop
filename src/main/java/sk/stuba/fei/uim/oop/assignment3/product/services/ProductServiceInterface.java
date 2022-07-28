package sk.stuba.fei.uim.oop.assignment3.product.services;

import sk.stuba.fei.uim.oop.assignment3.product.Product;
import sk.stuba.fei.uim.oop.assignment3.product.requests.ProductEditAmountRequest;
import sk.stuba.fei.uim.oop.assignment3.product.requests.ProductEditRequest;
import sk.stuba.fei.uim.oop.assignment3.product.requests.ProductRequest;

import java.util.List;

public interface ProductServiceInterface {

    List<Product> getAll();
    Product create(ProductRequest request);
    Product getById(Long id);
    Product edit(Long id, ProductEditRequest request);
    Product editAmount(Long id, ProductEditAmountRequest request);
    void deleteProduct(Long id);
}
