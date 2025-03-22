package com.desafio.desafio.service;

import com.desafio.desafio.model.Transaction;
import com.desafio.desafio.model.User;
import com.desafio.desafio.repository.TransactionRepository;
import com.desafio.desafio.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private EmailService emailService;

    @Transactional
    public void sendMoney(Long id_receiver, Long id_shipper, Double amount) {
        Optional<User> receiver = userRepository.findById(id_receiver);
        User shipper = userRepository.getReferenceById(id_shipper);
        Transaction transaction = new Transaction();

        if (receiver.get().getCnpj() == null) {
            throw new RuntimeException("O lojista nao pode receber dinheiro");
        }

        if (shipper.getBalance() <= amount) {
            throw new RuntimeException("Saldo insuficiente");
        }

        receiver.get().setBalance(receiver.get().getBalance() + amount);
        shipper.setBalance(shipper.getBalance() - amount);

        userRepository.save(shipper);

        userRepository.save(receiver.get());

        transaction.setAmount(amount);
        transaction.setClient(receiver.get());

        transactionRepository.save(transaction);

        emailService.enviarNotificacaoTransacao(
                shipper.getEmail(),
                receiver.get().getEmail(),
                transaction.getAmount()
                );
    }
}
