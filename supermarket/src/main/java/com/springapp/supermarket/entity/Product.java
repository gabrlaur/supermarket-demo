package com.springapp.supermarket.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "product")
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator="seq")
    @GenericGenerator(name = "seq", strategy="increment")
    @Column(name = "id")
    private Long id;

    @ToString.Include
    @Column(name = "name")
    @NotNull
    private String name;

    @ToString.Include
    @Column(name = "quantity")
    @NotNull
    private Integer quantity;

    @ToString.Include
    @Column(name = "price")
    @NotNull
    private Double price;

    public Product() {
    }

    public Product(String name, Integer quantity, Double price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }
}