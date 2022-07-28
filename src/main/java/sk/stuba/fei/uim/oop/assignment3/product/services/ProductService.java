package sk.stuba.fei.uim.oop.assignment3.product.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.assignment3.product.Product;
import sk.stuba.fei.uim.oop.assignment3.product.requests.ProductEditAmountRequest;
import sk.stuba.fei.uim.oop.assignment3.product.requests.ProductEditRequest;
import sk.stuba.fei.uim.oop.assignment3.product.requests.ProductRequest;
import sk.stuba.fei.uim.oop.assignment3.product.data.ProductRepository;

import java.util.List;

@Service
public class ProductService implements ProductServiceInterface {

    final private ProductRepository repo;

    @Autowired
    public ProductService(ProductRepository repo) {
        this.repo = repo;

        Product p1 = new Product();
        p1.setName("Mercedes");
        p1.setDescription("car");
        p1.setAmount(5);
        p1.setUnit("eur");
        p1.setPrice(10);
        this.repo.save(p1);

        Product p2 = new Product();
        p2.setName("BMW");
        p2.setDescription("car");
        p2.setAmount(5);
        p2.setUnit("eur");
        p2.setPrice(20);
        this.repo.save(p2);

        Product p3 = new Product();
        p3.setName("Audi");
        p3.setDescription("car");
        p3.setAmount(5);
        p3.setUnit("eur");
        p3.setPrice(30);
        this.repo.save(p3);
    }

    @Override
    public List<Product> getAll() {
        return this.repo.findAll();
    }

    @Override
    public Product create(ProductRequest request) {
        Product newProduct = new Product();
        newProduct.setName(request.getName());
        newProduct.setDescription(request.getDescription());
        newProduct.setAmount(request.getAmount());
        newProduct.setUnit(request.getUnit());
        newProduct.setPrice(request.getPrice());
        return this.repo.save(newProduct);
    }

    @Override
    public Product getById(Long id) {
        return this.repo.findById(id).orElseThrow();
    }

    @Override
    public Product edit(Long id, ProductEditRequest request) {
        Product editProduct = this.repo.findById(id).orElseThrow();
        if (request.getName() != null) {
            editProduct.setName(request.getName());
        }
        if (request.getDescription() != null) {
            editProduct.setDescription(request.getDescription());
        }
        return this.repo.save(editProduct);
    }

    @Override
    public Product editAmount(Long id, ProductEditAmountRequest request) {
        Product editProduct = this.repo.findById(id).orElseThrow();
        editProduct.setAmount(request.getAmount() + editProduct.getAmount());
        return this.repo.save(editProduct);
    }

    @Override
    public void deleteProduct(Long id) {
        this.repo.findById(id).orElseThrow();
        this.repo.deleteById(id);
    }
}
