package com.springapp.supermarket.service;

import com.springapp.supermarket.entity.Product;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

public interface ProductService {
    Iterable<Product> getAllProducts();

    Optional<Product> getProductById(@PathVariable long id);

    Product saveProduct(Product newProduct);

    Product updateProduct(Product newProduct, long id);
}
