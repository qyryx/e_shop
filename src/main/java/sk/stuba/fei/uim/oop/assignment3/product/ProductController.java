package sk.stuba.fei.uim.oop.assignment3.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.stuba.fei.uim.oop.assignment3.product.requests.ProductEditAmountRequest;
import sk.stuba.fei.uim.oop.assignment3.product.requests.ProductEditRequest;
import sk.stuba.fei.uim.oop.assignment3.product.requests.ProductRequest;
import sk.stuba.fei.uim.oop.assignment3.product.responses.AmountResponse;
import sk.stuba.fei.uim.oop.assignment3.product.responses.ProductResponse;
import sk.stuba.fei.uim.oop.assignment3.product.services.ProductServiceInterface;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductServiceInterface service;

    @GetMapping()
    public List<ProductResponse> getAllProducts() {
        return this.service.getAll().stream().map(ProductResponse::new).collect(Collectors.toList());
    }

    @PostMapping()
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest request) {
        return new ResponseEntity<>(new ProductResponse(this.service.create(request)), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ProductResponse getProductById(@PathVariable("id") Long id) {
        return new ProductResponse(this.service.getById(id));
    }

    @PutMapping("/{id}")
    public ProductResponse editProduct(@PathVariable("id") Long id, @RequestBody ProductEditRequest request) {
        return new ProductResponse(this.service.edit(id, request));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        this.service.deleteProduct(id);
    }

    @GetMapping("/{id}/amount")
    public AmountResponse getProductAmount(@PathVariable("id") Long id) {
        return new AmountResponse(this.service.getById(id));
    }

    @PostMapping("/{id}/amount")
    public AmountResponse editProductAmount(@PathVariable("id") Long id, @RequestBody ProductEditAmountRequest request) {
        return new AmountResponse(this.service.editAmount(id, request));
    }
}
