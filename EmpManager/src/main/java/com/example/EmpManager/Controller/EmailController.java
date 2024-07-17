package com.example.EmpManager.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.EmpManager.Entity.Email;
import com.example.EmpManager.Entity.Vendor;
import com.example.EmpManager.Repository.EmailRepository;
import com.example.EmpManager.Service.EmailService;

@RestController
@RequestMapping("/emails")
public class EmailController {

    @Autowired
    private EmailRepository emailRepository;

    @Autowired
    private EmailService emailService;

    @GetMapping
    public List<Email> getAllEmails() {
        return emailRepository.findAll();
    }

    @PostMapping("/send")
    public void sendEmailsToVendors(@RequestBody Vendor vendors) {
        emailService.sendEmailToVendors(vendors);
    }
}
