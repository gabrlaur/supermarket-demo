package com.springapp.supermarket.controller;

import com.springapp.supermarket.entity.Coin;
import com.springapp.supermarket.entity.Wallet;
import com.springapp.supermarket.service.SupermarketService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/supermarket")
@AllArgsConstructor
public class SupermarketController {
    private final SupermarketService supermarketService;

    @PostMapping("/buy/{id}")
    public Iterable<Coin> buyProduct(@PathVariable Long id, @Valid @RequestBody Wallet clientCash) {
        return supermarketService.buyProduct(id, clientCash);
    }
}
