package com.springapp.supermarket.controller;

import com.springapp.supermarket.entity.Coin;
import com.springapp.supermarket.service.CashRegisterService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;


@RestController
@RequestMapping("/cash")
@AllArgsConstructor
public class CashRegisterController {
    private final CashRegisterService cashRegisterService;

    @GetMapping()
    public Iterable<Coin> getAllCash() {
        return cashRegisterService.getListOfCoins();
    }

    @GetMapping("/{id}")
    public Optional<Coin> getCashById(@PathVariable long id) {
        return cashRegisterService.getCoinById(id);
    }

    @PutMapping("/{id}")
    public Coin updateCoin(@Valid @RequestBody Coin newCoin, @PathVariable long id) {
        return this.cashRegisterService.updateCoin(newCoin, id);
    }
}
