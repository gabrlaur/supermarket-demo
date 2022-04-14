package com.springapp.supermarket.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "cash_register")
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
public class Coin {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator="seq")
    @GenericGenerator(name = "seq", strategy="increment")
    @Column(name = "id")
    private Long id;

    @ToString.Include
    @Column(name = "value")
    @NotNull(message = "value may not be null")
    private Double value;

    @ToString.Include
    @NotNull(message = "quantity may not be quantity")
    @Column(name = "quantity")
    private Integer quantity;

    public Coin() {
    }

    public Coin(Double value, Integer quantity) {
        this.value = value;
        this.quantity = quantity;
    }
}