package com.desafio.desafio.controller;

import com.desafio.desafio.service.EmailService;
import com.desafio.desafio.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/transaction")
public class TransactionController {

    @Autowired
    private TransactionService service;

    @Autowired
    private EmailService emailService;

    @PostMapping()
    public ResponseEntity<String> sendMoney(
            @RequestParam(name = "id_receiver") Long id_receiver,
            @RequestParam(name = "id_shipper") Long id_shipper,
            @RequestParam(name = "amount") Double amount) {
        service.sendMoney(id_receiver, id_shipper, amount);


        return new ResponseEntity<>("Transação enviada com sucesso!", HttpStatus.OK);
    }

}
