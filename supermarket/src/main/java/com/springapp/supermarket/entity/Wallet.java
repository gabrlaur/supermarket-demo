package com.springapp.supermarket.entity;

import com.springapp.supermarket.exceptions.CannotAffordException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Wallet {
    private List<Coin> listOfCoins;

    //Method to get total cash in wallet
    public double getTotalCash() {
        try {
            return this.listOfCoins.stream().mapToDouble(coin -> coin.getValue() * coin.getQuantity()).sum();
        } catch (Exception e) {
            throw new CannotAffordException("Customer cannot afford the product!");
        }
    }

    //Method to calculate change after buying a product
    public double getChange(Double price) {
        return this.getTotalCash() - price;
    }

    //Method to check if clients cash can be accepted (available: 0.1, 0.5, 1, 2)
    public boolean isCashValid() {
        if (listOfCoins == null) {
            throw new CannotAffordException("Customer cannot afford the product!");
        }
        for (Coin c : listOfCoins) {
            if (c.getValue() == null) return false;
            if (c.getValue() != 0.1 && c.getValue() != 0.5 && c.getValue() != 1 && c.getValue() != 2)
                return false;
        }
        return true;
    }
}