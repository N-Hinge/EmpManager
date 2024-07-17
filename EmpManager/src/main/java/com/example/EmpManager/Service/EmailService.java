package com.example.EmpManager.Service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.EmpManager.Entity.Email;
import com.example.EmpManager.Entity.Vendor;
import com.example.EmpManager.Repository.EmailRepository;
import com.example.EmpManager.configuration.EmailConfig;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    @Value("$(spring.mail.username)")
    private String FromEmailId;
    
    @Autowired
    private EmailRepository emailRepository;

    public void sendEmailToVendors(Vendor vendors) {
            String message = "Sending payments to vendor " + vendors.getName() + " at upi " + vendors.getUpi();
            sendSimpleMessage(vendors, "Vendor Payment", message);
    }

    public void sendSimpleMessage(Vendor vendor, String subject, String text) {
        Email email = new Email();
        email.setRecipient(vendor.getEmail());
        email.setSubject(subject);
        email.setBody(text);
        email.setSentAt(LocalDateTime.now());

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(FromEmailId);
            message.setTo(email.getRecipient());
            message.setSubject(email.getSubject());
            message.setText(email.getBody());
            emailSender.send(message);
            email.setStatus("SENT");
        } catch (Exception e) {
            email.setStatus("FAILED");
        }

        emailRepository.save(email);
    }
}