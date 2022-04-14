package com.springapp.supermarket.service;

import com.springapp.supermarket.dao.CashRegisterRepository;
import com.springapp.supermarket.entity.Coin;
import com.springapp.supermarket.exceptions.NotEnoughChangeException;
import com.springapp.supermarket.exceptions.PayNotAcceptedException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CashRegisterServiceImpl implements CashRegisterService {
    private final CashRegisterRepository cashRegisterRepository;

    @Override
    public void addCoinsToCashRegister(Iterable<Coin> customerCoins) {
        List<Coin> cashRegisterCoins = cashRegisterRepository.findAll();

        for (Coin customerCoin : customerCoins) {
            for (Coin cashRegisterCoin : cashRegisterCoins) {
                if (customerCoin.getValue().equals(cashRegisterCoin.getValue()))
                    cashRegisterCoin.setQuantity(cashRegisterCoin.getQuantity() + 1);
            }
        }
    }

    @Override
    public List<Coin> getListOfCoins() {
        return cashRegisterRepository.findAll();
    }

    @Override
    public Optional<Coin> getCoinById(Long id) {
        return cashRegisterRepository.findById(id);
    }

    @Override
    public List<Coin> giveChangeToCustomer(double change) {
        List<Coin> changeCoinsList = new ArrayList<>();
        for (Coin cashRegisterCoin : this.getListOfCoins()) {
            while (cashRegisterCoin.getValue() <= change && change - cashRegisterCoin.getValue() >= 0) {
                    //Take the coin from the cash register
                    if (cashRegisterCoin.getQuantity() > 0) {
                        cashRegisterCoin.setQuantity(cashRegisterCoin.getQuantity() - 1);
                    } else throw new NotEnoughChangeException();
                    //Pass the coin to customer
                    boolean sameCoin = false;
                    Coin customersCoin = new Coin(cashRegisterCoin.getValue(), 1);
                    for (Coin c : changeCoinsList) {
                        if (c.getValue().equals(customersCoin.getValue())) {
                            c.setQuantity(c.getQuantity() + 1);
                            sameCoin = true;
                        }
                    }
                    if (!sameCoin) {
                        changeCoinsList.add(customersCoin);
                    }
                    change -= cashRegisterCoin.getValue();
            }
        }
        return changeCoinsList;
    }

    @Override
    public Coin updateCoin(Coin newCoin, long id) {
        Optional<Coin> coin = cashRegisterRepository.findById(id);
        if(coin.isEmpty()) {
            throw new PayNotAcceptedException();
        }

        Coin coinToUpdate = coin.get();
        coinToUpdate.setQuantity(newCoin.getQuantity());
        coinToUpdate.setValue(newCoin.getValue());

        return this.cashRegisterRepository.save(coinToUpdate);
    }
}
