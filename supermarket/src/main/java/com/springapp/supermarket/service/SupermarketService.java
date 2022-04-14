package com.springapp.supermarket.service;

import com.springapp.supermarket.entity.Coin;
import com.springapp.supermarket.entity.Wallet;


public interface SupermarketService {
    Iterable<Coin> buyProduct(Long id, Wallet customersCash);
}

