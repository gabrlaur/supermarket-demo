package com.springapp.supermarket.service;

import com.springapp.supermarket.dao.ProductRepository;
import com.springapp.supermarket.entity.Product;
import com.springapp.supermarket.exceptions.SoldOutException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    ProductRepository productRepository;

    @Override
    public Iterable<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> getProductById(long id) {
        if (productRepository.existsById(id)) {
            return productRepository.findById(id);
        } else {
            throw new SoldOutException();
        }
    }

    @Override
    public Product saveProduct(Product newProduct) {
        return this.productRepository.save(newProduct);
    }

    @Override
    public Product updateProduct(Product newProduct, long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) {
            throw new SoldOutException();
        }
        Product productToUpdate = product.get();
        productToUpdate.setPrice(newProduct.getPrice());
        productToUpdate.setName(newProduct.getName());
        productToUpdate.setQuantity(newProduct.getQuantity());

        return this.productRepository.save(productToUpdate);
    }
}
