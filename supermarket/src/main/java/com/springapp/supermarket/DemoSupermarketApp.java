package com.springapp.supermarket;

import com.springapp.supermarket.entity.Coin;
import com.springapp.supermarket.entity.Product;
import com.springapp.supermarket.entity.Wallet;
import com.springapp.supermarket.exceptions.CustomerLeftException;
import com.springapp.supermarket.exceptions.PayNotAcceptedException;
import com.springapp.supermarket.exceptions.SoldOutException;
import com.springapp.supermarket.service.CashRegisterService;
import com.springapp.supermarket.service.ProductService;
import com.springapp.supermarket.service.SupermarketService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class DemoSupermarketApp {
    public static void main(String[] args) throws IOException {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(DemoSupermarketApp.class, args);

        DecimalFormat df = new DecimalFormat("0.00");
        ProductService productService = applicationContext.getBean(ProductService.class);
        CashRegisterService cashRegisterService = applicationContext.getBean(CashRegisterService.class);
        SupermarketService supermarketService = applicationContext.getBean(SupermarketService.class);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("\n\nWelcome to the supermarket!");
        System.out.println("If you want to leave, type \"leave\"");
        printInventories(productService, cashRegisterService);

        System.out.println("What would you like to buy? Type the name of the product:");
        String product = reader.readLine();
        checkIfCustomerIsStillThere(product, productService, cashRegisterService);
        Product productInShoppingCart = null;
        for (Product p : productService.getAllProducts()) {
            if (p.getName().equalsIgnoreCase(product)) {
                productInShoppingCart = p;
                System.out.println("You are trying to buy: " + productInShoppingCart.getName());
                break;
            }
        }
        if (productInShoppingCart != null) {
            System.out.println("You need to pay: " + df.format(productInShoppingCart.getPrice()));
            System.out.println("Accepted values: 0.1, 0.5, 1, 2");
            List<Coin> listOfCoins = new ArrayList<>();
            double sumOfCoins = 0;
            double remainingPrice = productInShoppingCart.getPrice();
            while (sumOfCoins < productInShoppingCart.getPrice()) {
                double coin;
                String line = reader.readLine();
                checkIfCustomerIsStillThere(line, productService, cashRegisterService);
                try {
                    coin = Double.parseDouble(line);
                } catch (Exception e) {
                    throw new PayNotAcceptedException();
                }
                if(coin == 0)
                    throw new PayNotAcceptedException();
                listOfCoins.add(new Coin(coin, 1));
                sumOfCoins += coin;
                remainingPrice -= coin;
                if (sumOfCoins < productInShoppingCart.getPrice())
                    System.out.println("You paid " + df.format(sumOfCoins) + " in total. You still need to pay: " + df.format(remainingPrice));
            }
            Wallet customersWallet = new Wallet(listOfCoins);
            if (customersWallet.isCashValid()) {
                System.out.println("You paid " + sumOfCoins + " in total. Your change will be: " + df.format(customersWallet.getChange(productInShoppingCart.getPrice())));
                List<Coin> change = (List<Coin>) supermarketService.buyProduct(productInShoppingCart.getId(), customersWallet);
                System.out.println("Here's your change: ");
                System.out.println(change);

                printInventories(productService, cashRegisterService);
            } else throw new PayNotAcceptedException();
        } else throw new SoldOutException();
    }

    public static void checkIfCustomerIsStillThere(String customersResponse, ProductService productService, CashRegisterService cashRegisterService) {
        if (customersResponse != null && customersResponse.equalsIgnoreCase("leave")) {
            System.out.println("\nGoodbye!");
            printInventories(productService, cashRegisterService);
            throw new CustomerLeftException();
        }
    }

    public static void printInventories(ProductService productService, CashRegisterService cashRegisterService) {
        System.out.println("\n\n--------------------------");
        System.out.println("Product Inventory: ");
        System.out.println(productService.getAllProducts());
        System.out.println("Cash Inventory: ");
        System.out.println(cashRegisterService.getListOfCoins());
        System.out.println("--------------------------\n");
    }
}
