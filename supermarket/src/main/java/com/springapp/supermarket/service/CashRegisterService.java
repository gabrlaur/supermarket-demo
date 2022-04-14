package com.springapp.supermarket.service;

import com.springapp.supermarket.entity.Coin;

import java.util.Optional;

public interface CashRegisterService {
    void addCoinsToCashRegister(Iterable<Coin> listOfCoins);

    Iterable<Coin> getListOfCoins();

    Optional<Coin> getCoinById(Long id);

    Iterable<Coin> giveChangeToCustomer(double change);

    Coin updateCoin(Coin newCoin, long id);
}
