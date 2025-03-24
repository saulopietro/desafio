package com.desafio.desafio.service;


import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.mail.*;
import org.springframework.beans.factory.annotation.Autowired;


@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;
    public void enviarEmail(String destinatario, String assunto, String corpo) {
        try {
            MimeMessage mail = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mail, true);
            helper.setTo(destinatario);
            helper.setSubject(assunto);
            helper.setText(corpo);
            mailSender.send(mail);
        } catch (MessagingException | MailException e) {
            e.printStackTrace();
        }
    }

    public void enviarNotificacaoTransacao(String clienteEmail, String vendedorEmail, double valor) {
        String assunto = "Notificação de Transação";
        String corpo = String.format(
                """
                        Uma nova transação foi realizada!
                        
                        Valor: R$%.2f
                       
                        
                        Obrigado por usar nossos serviços!""",
                valor
        );

        // Enviar para o cliente
        enviarEmail(clienteEmail, assunto, corpo);

        // Enviar para o vendedor
        enviarEmail(vendedorEmail, assunto, corpo);


    }

}
