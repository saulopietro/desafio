package com.desafio.desafio.service;

import com.desafio.desafio.model.User;
import com.desafio.desafio.repository.UserRepository;
import com.desafio.desafio.service.exceptions.InsufificientBalanceException;
import com.desafio.desafio.service.exceptions.SellerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private UserRepository repository;

        public User customerValidation(Long id_customer, Double amount) {
        User customer = repository.findById(id_customer).orElseThrow(() -> new SellerNotFoundException("Customer not found"));

        if (customer.getBalance() <= amount) {
            throw new InsufificientBalanceException("Insuficcient Balance");
        }
        customer.setBalance(customer.getBalance() - amount);

        repository.save(customer);
        return customer;
    }
}
