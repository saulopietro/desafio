package com.desafio.desafio.service;

import com.desafio.desafio.model.Transaction;
import com.desafio.desafio.model.TransactionStatusEnum;
import com.desafio.desafio.model.User;
import com.desafio.desafio.repository.TransactionRepository;
import com.desafio.desafio.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class TransactionService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private EmailService emailService;

    @Transactional
    public void transaction(User seller, User customer, Double amount) {
        try {
            Transaction result = copyToTransaction(seller, customer, amount);

            transactionRepository.save(result);

            emailService.enviarNotificacaoTransacao(
                    seller.getEmail(),
                    customer.getEmail(),
                    result.getAmount()
            );
        } catch (Exception e) {
            Transaction result = copyToTransaction(seller, customer, amount);
            result.setStatus(TransactionStatusEnum.DENIED);
            transactionRepository.save(result);
        }
}

    public Transaction copyToTransaction(User seller, User customer, Double amount) {
        Transaction transaction = new Transaction();

        LocalDate dateNow = LocalDate.now();

        transaction.setDate(dateNow);
        transaction.setAmount(amount);
        transaction.setSeller(seller);
        transaction.setCustomer(customer);
        transaction.setStatus(TransactionStatusEnum.APPROVED);

        return transaction;
        }
}
