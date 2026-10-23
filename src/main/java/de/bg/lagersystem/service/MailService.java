package de.bg.lagersystem.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;

@Service
public class MailService {
    private final JavaMailSender mailSender;

    @Value("${mail.sender-address:mail@test.de}")
    private String from;

    @Value("${mail.recipient:matteo.grado.23@gmail.com}")
    private String recipient;

    public MailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendMail(String productName, int stock, String useFor, String firstName, String lastName) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(recipient);
        message.setSubject("Lager aktuallisierung");
        message.setText("Produkt: " + productName + "\n"
                + "Aktueller Bestand: " + stock + "\n"
                + "Verwendungszweck: " + useFor + "\n"
                + "Mitarbeiter: " + firstName + " " + lastName);
        mailSender.send(message);
    }

    public void sendHtmlMail(String productName, BigDecimal price, int removedQuantity,
                             int stock, String manufacturer) {
        try {
            String html = new String(
                    getClass().getResourceAsStream("/email/template.html").readAllBytes(),
                    StandardCharsets.UTF_8
            );
            html = html.replace("{{PRODUCT_NAME}}", String.valueOf(productName))
                    .replace("{{PRICE}}", String.valueOf(price))
                    .replace("{{ENTNOMMENE_MENGE}}", String.valueOf(removedQuantity))
                    .replace("{{STOCK}}", String.valueOf(stock))
                    .replace("{{HERSTELLER}}", String.valueOf(manufacturer));

            MimeMessage mime = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mime, false, StandardCharsets.UTF_8.name());
            helper.setFrom(from);
            helper.setTo(recipient);
            helper.setSubject("Lager akutallisierung");
            helper.setText(html, true);
            mailSender.send(mime);
        } catch (IOException | MessagingException e) {
            throw new IllegalStateException(e);
        }
    }

    public void sendHtmlMail(String productName, BigDecimal price, int removedQuantity,
                             int stock, String manufacturer, String firstName,
                             String lastName, String action) {
        try {
            String html = new String(
                    getClass().getResourceAsStream("/email/template.html").readAllBytes(),
                    StandardCharsets.UTF_8
            );
            html = html.replace("{{PRODUCT_NAME}}", String.valueOf(productName))
                    .replace("{{PRICE}}", String.valueOf(price))
                    .replace("{{ENTNOMMENE_MENGE}}", String.valueOf(removedQuantity))
                    .replace("{{STOCK}}", String.valueOf(stock))
                    .replace("{{HERSTELLER}}", String.valueOf(manufacturer))
                    .replace("{{FIRST_NAME}}", String.valueOf(firstName))
                    .replace("{{LAST_NAME}}", String.valueOf(lastName))
                    .replace("{{ACTION}}", String.valueOf(action));

            MimeMessage mime = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mime, false, StandardCharsets.UTF_8.name());
            helper.setFrom(from);
            helper.setTo(recipient);
            helper.setSubject("Lager akutallisierung");
            helper.setText(html, true);
            mailSender.send(mime);
        } catch (IOException | MessagingException e) {
            throw new IllegalStateException(e);
        }
    }


    public void sendMail(String productName, int itemStock)
    {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(recipient);
        message.setSubject("Lagerbestand kritisch");
        message.setText("Für das Produkt " + productName
                + " sind nur noch " + itemStock + " Einheiten verfügbar!");

        mailSender.send(message);
    }
}
