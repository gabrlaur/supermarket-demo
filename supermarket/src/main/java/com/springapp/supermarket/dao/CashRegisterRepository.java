package com.springapp.supermarket.dao;

import com.springapp.supermarket.entity.Coin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CashRegisterRepository extends JpaRepository<Coin, Long> {
}