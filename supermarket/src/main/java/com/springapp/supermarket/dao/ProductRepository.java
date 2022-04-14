package com.springapp.supermarket.dao;

import com.springapp.supermarket.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}