package com.desafio.desafio.service;

import com.desafio.desafio.model.TransactionTypeEnum;
import com.desafio.desafio.model.User;
import com.desafio.desafio.repository.UserRepository;
import com.desafio.desafio.service.exceptions.SellerNotFoundException;
import com.desafio.desafio.service.exceptions.SellerNotPermisionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerService {

    @Autowired
    private UserRepository repository;

    public User sellerValidation(Long id_seller, Double amount){
        User seller = repository.findById(id_seller).orElseThrow(() -> new SellerNotFoundException("Seller not found"));

        if (seller.getType() == TransactionTypeEnum.CUSTOMER) {
            throw new SellerNotPermisionException("Seller can't receive money");
        }

        seller.setBalance(seller.getBalance() + amount);

        repository.save(seller);
        return seller;
    }
}