package com.springapp.supermarket.service;

import com.springapp.supermarket.dao.ProductRepository;
import com.springapp.supermarket.entity.Coin;
import com.springapp.supermarket.entity.Product;
import com.springapp.supermarket.entity.Wallet;
import com.springapp.supermarket.exceptions.CannotAffordException;
import com.springapp.supermarket.exceptions.PayNotAcceptedException;
import com.springapp.supermarket.exceptions.SoldOutException;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Optional;

@Service
@Scope(value = "prototype")
@AllArgsConstructor
public class SupermarketServiceImpl implements SupermarketService {

    private static final DecimalFormat df = new DecimalFormat("0.00");

    private final ProductRepository productRepository;

    private final CashRegisterService cashRegisterService;

    @Override
    @Transactional
    public Iterable<Coin> buyProduct(Long id, @Valid @RequestBody Wallet customersCash) {
        // get product by id
        Optional<Product> product = productRepository.findById(id);

        //check if product is found
        if (product.isEmpty()) {
            throw new SoldOutException();
        }

        //check quantity, if 0, throw not found
        if (product.get().getQuantity() == 0)
            throw new SoldOutException();
        else {
            product.get().setQuantity(product.get().getQuantity() - 1);
        }

        //validate if customers coins can be accepted
        if (!customersCash.isCashValid()) {
            throw new PayNotAcceptedException();
        }

        //validate if customer can afford the product
        if (customersCash.getTotalCash() < product.get().getPrice()) {
            Double costDifference = product.get().getPrice() - customersCash.getTotalCash();
            throw new CannotAffordException("Customer cannot afford the product! " + df.format(costDifference) + " short!");
        }

        //add coins to the cash register
        cashRegisterService.addCoinsToCashRegister(customersCash.getListOfCoins());

        //calculate how much change needs to be given
        double change = customersCash.getChange(product.get().getPrice());

        List<Coin> changeCoinsList = (List<Coin>) cashRegisterService.giveChangeToCustomer(change);

        //if bought, return product
        Product p = new Product(product.get().getName(), 1, product.get().getPrice());
        System.out.println("Here's your product: " + p);
        return changeCoinsList;
    }
}
