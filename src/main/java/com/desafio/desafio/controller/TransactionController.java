package com.desafio.desafio.controller;

import com.desafio.desafio.model.User;
import com.desafio.desafio.service.CustomerService;
import com.desafio.desafio.service.EmailService;
import com.desafio.desafio.service.SellerService;
import com.desafio.desafio.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private SellerService sellerService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private EmailService emailService;

    @PostMapping()
    public ResponseEntity<String> sendMoney(
            @RequestParam(name = "id_seller") Long id_seller,
            @RequestParam(name = "id_customer") Long id_customer,
            @RequestParam(name = "amount") Double amount) {

       User resultSeller = sellerService.sellerValidation(id_seller, amount);
       User resultCustomer = customerService.customerValidation(id_customer, amount);
       transactionService.transaction(resultSeller, resultCustomer, amount);


        return new ResponseEntity<>("Transação enviada com sucesso!", HttpStatus.OK);
    }

}
