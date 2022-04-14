package com.springapp.supermarket.controller;

import com.springapp.supermarket.entity.Product;
import com.springapp.supermarket.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/product")
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping()
    public Iterable<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Optional<Product> getProductById(@PathVariable long id) {
        return productService.getProductById(id);
    }

    @PostMapping
    public Product createProduct(@RequestBody Product newProduct) {
        return this.productService.saveProduct(newProduct);
    }

    @PutMapping("{id}")
    public Product updateProduct(@Valid @RequestBody Product newProduct, @PathVariable long id) {
        return this.productService.updateProduct(newProduct, id);
    }
}
