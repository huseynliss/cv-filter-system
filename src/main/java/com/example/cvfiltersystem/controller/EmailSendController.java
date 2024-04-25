package com.example.cvfiltersystem.controller;

import com.example.cvfiltersystem.enums.ApplicationStatus;
import com.example.cvfiltersystem.request.MailRequest;
import com.example.cvfiltersystem.service.EmailService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/mail")
@CrossOrigin(origins = "*", allowedHeaders = "*")

public class EmailSendController {
    private EmailService emailService;


    public EmailSendController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("send-mail-to-all-applicants/{companyUsername}")
    public ResponseEntity<?> sendMailsToAllApplicants(@PathVariable String companyUsername, @RequestParam("subject") String subject, @RequestParam("body") String body, @RequestParam("applicantStatus") ApplicationStatus applicationStatus) {

        return emailService.sendApprovedOrRejectionMailsToAllApplicants(companyUsername, subject, body, applicationStatus);
    }

    @PreAuthorize("hasRole('client_admin')")
    @PostMapping("send-mail-to-applicant-by-id/{companyUsername}")
    public ResponseEntity<?> sendMailById(@PathVariable String companyUsername, @RequestBody MailRequest request) {
        return emailService.sendApprovedOrRejectionMailToApplicant(companyUsername, request.getApplicantId(), request.getSubject(), request.getBody());
    }

}