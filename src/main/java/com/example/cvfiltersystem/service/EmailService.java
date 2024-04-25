package com.example.cvfiltersystem.service;

import com.example.cvfiltersystem.enums.ApplicationStatus;
import com.example.cvfiltersystem.model.Applicant;
import com.example.cvfiltersystem.reponse.CompanyResponse;
import com.example.cvfiltersystem.repository.ApplicantRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmailService {

    @Value("${spring.mail.username}")
    private String fromEmail;

    private final JavaMailSender javaMailSender;

    private final ApplicantRepository applicantRepository;

    private final CompanyService companyService;


    public EmailService(JavaMailSender javaMailSender, ApplicantRepository applicantRepository, CompanyService companyService) {
        this.javaMailSender = javaMailSender;
        this.applicantRepository = applicantRepository;
        this.companyService = companyService;
    }

    public ResponseEntity<?> sendMail(String companyUsername, String to, String subject, String body) {
        if (to.contains(" ")) {
            return new ResponseEntity<>("Invalid sender email address", HttpStatus.BAD_REQUEST);
        }

        try {

            CompanyResponse companyByCompanyUsername = companyService.getCompanyByCompanyUsername(companyUsername);

            JavaMailSenderImpl mailSenderImpl = (JavaMailSenderImpl) javaMailSender;
            mailSenderImpl.setPassword(companyByCompanyUsername.getCompanyMailPassword());


            MimeMessagePreparator preparator = mimeMessage -> {
                MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
                mimeMessageHelper.setFrom(companyByCompanyUsername.getCompanyMail());
                mimeMessageHelper.setTo(to);
                mimeMessageHelper.setSubject(subject);
                mimeMessageHelper.setText(body);
            };

            javaMailSender.send(preparator);

            return new ResponseEntity<>("Email sent successfully", HttpStatus.OK);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public ResponseEntity<?> sendApprovedOrRejectionMailsToAllApplicants(String companyUsername, String subject, String body, ApplicationStatus applicationStatus) {
        List<Applicant> byApplicationStatus = applicantRepository.findByApplicationStatus(applicationStatus);

        for (Applicant applicant : byApplicationStatus) {
            sendMail(companyUsername, applicant.getMail(), subject, body);
        }
        return new ResponseEntity<>("Mails sent successfully.", HttpStatus.OK);
    }


    public ResponseEntity<?> sendApprovedOrRejectionMailToApplicant(String companyUsername, Long applicantId, String subject, String body) {
        Optional<Applicant> byId = applicantRepository.findById(applicantId);
        if (byId.isPresent()) {
            return new ResponseEntity<>(sendMail(companyUsername, byId.get().getMail(), subject, body), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Applicant not found.", HttpStatus.NOT_FOUND);
        }
    }
}
