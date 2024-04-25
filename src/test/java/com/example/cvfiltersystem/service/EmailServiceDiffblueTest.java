package com.example.cvfiltersystem.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.cvfiltersystem.enums.ApplicationStatus;
import com.example.cvfiltersystem.model.Applicant;
import com.example.cvfiltersystem.repository.ApplicantRepository;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ContextConfiguration(classes = {EmailService.class})
@ExtendWith(SpringExtension.class)
class EmailServiceDiffblueTest {
    @MockBean
    private CompanyService companyService;

    @MockBean
    private ApplicantRepository applicantRepository;

    @Autowired
    private EmailService emailService;

    @MockBean
    private JavaMailSender javaMailSender;

    /**
     * Method under test:
     * {@link EmailService#sendMail(String, String, String, String)}
     */
    @Test
    void testSendMail() {
        ResponseEntity<?> actualSendMailResult = emailService.sendMail("janedoe", " ", "Hello from the Dreaming Spires",
                "Not all who wander are lost");
        assertEquals("Invalid sender email address", actualSendMailResult.getBody());
        assertEquals(400, actualSendMailResult.getStatusCodeValue());
        assertTrue(actualSendMailResult.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link EmailService#sendMail(String, String, String, String)}
     */
    @Test
    void testSendMail2() {
        when(companyService.getCompanyByCompanyUsername(Mockito.<String>any())).thenThrow(new RuntimeException(" "));
        assertThrows(RuntimeException.class, () -> emailService.sendMail("janedoe", "alice.liddell@example.org",
                "Hello from the Dreaming Spires", "Not all who wander are lost"));
        verify(companyService).getCompanyByCompanyUsername(Mockito.<String>any());
    }

    /**
     * Method under test:
     * {@link EmailService#sendApprovedOrRejectionMailsToAllApplicants(String, String, String, ApplicationStatus)}
     */
    @Test
    void testSendApprovedOrRejectionMailsToAllApplicants() {
        when(applicantRepository.findByApplicationStatus(Mockito.<ApplicationStatus>any())).thenReturn(new ArrayList<>());
        ResponseEntity<?> actualSendApprovedOrRejectionMailsToAllApplicantsResult = emailService
                .sendApprovedOrRejectionMailsToAllApplicants("janedoe", "Hello from the Dreaming Spires",
                        "Not all who wander are lost", ApplicationStatus.APPROVED);
        verify(applicantRepository).findByApplicationStatus(Mockito.<ApplicationStatus>any());
        assertEquals("Mails sent successfully.", actualSendApprovedOrRejectionMailsToAllApplicantsResult.getBody());
        assertEquals(200, actualSendApprovedOrRejectionMailsToAllApplicantsResult.getStatusCodeValue());
        assertTrue(actualSendApprovedOrRejectionMailsToAllApplicantsResult.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link EmailService#sendApprovedOrRejectionMailsToAllApplicants(String, String, String, ApplicationStatus)}
     */
    @Test
    void testSendApprovedOrRejectionMailsToAllApplicants2() {
        Applicant applicant = new Applicant();
        applicant.setApplicationStatus(ApplicationStatus.APPROVED);
        applicant.setCertificates("Mails sent successfully.");
        applicant.setCompanyUsername("janedoe");
        applicant.setEducationsAndFields("Mails sent successfully.");
        applicant.setFullName("Dr Jane Doe");
        applicant.setId(1L);
        applicant.setLanguages("en");
        applicant.setMail("Mails sent successfully.");
        applicant.setPhoneNumber("6625550144");
        applicant.setPlaceOfResidence("Mails sent successfully.");
        applicant.setScore(10.0d);
        applicant.setSkills("Mails sent successfully.");
        applicant.setVacancyName("Mails sent successfully.");
        applicant.setWorkExperienceInfo("Mails sent successfully.");

        ArrayList<Applicant> applicantList = new ArrayList<>();
        applicantList.add(applicant);
        when(applicantRepository.findByApplicationStatus(Mockito.<ApplicationStatus>any())).thenReturn(applicantList);
        ResponseEntity<?> actualSendApprovedOrRejectionMailsToAllApplicantsResult = emailService
                .sendApprovedOrRejectionMailsToAllApplicants("janedoe", "Hello from the Dreaming Spires",
                        "Not all who wander are lost", ApplicationStatus.APPROVED);
        verify(applicantRepository).findByApplicationStatus(Mockito.<ApplicationStatus>any());
        assertEquals("Mails sent successfully.", actualSendApprovedOrRejectionMailsToAllApplicantsResult.getBody());
        assertEquals(200, actualSendApprovedOrRejectionMailsToAllApplicantsResult.getStatusCodeValue());
        assertTrue(actualSendApprovedOrRejectionMailsToAllApplicantsResult.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link EmailService#sendApprovedOrRejectionMailsToAllApplicants(String, String, String, ApplicationStatus)}
     */
    @Test
    void testSendApprovedOrRejectionMailsToAllApplicants3() {
        when(applicantRepository.findByApplicationStatus(Mockito.<ApplicationStatus>any())).thenReturn(new ArrayList<>());
        ResponseEntity<?> actualSendApprovedOrRejectionMailsToAllApplicantsResult = emailService
                .sendApprovedOrRejectionMailsToAllApplicants("janedoe", "Hello from the Dreaming Spires",
                        "Not all who wander are lost", ApplicationStatus.REJECTED);
        verify(applicantRepository).findByApplicationStatus(Mockito.<ApplicationStatus>any());
        assertEquals("Mails sent successfully.", actualSendApprovedOrRejectionMailsToAllApplicantsResult.getBody());
        assertEquals(200, actualSendApprovedOrRejectionMailsToAllApplicantsResult.getStatusCodeValue());
        assertTrue(actualSendApprovedOrRejectionMailsToAllApplicantsResult.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link EmailService#sendApprovedOrRejectionMailsToAllApplicants(String, String, String, ApplicationStatus)}
     */
    @Test
    void testSendApprovedOrRejectionMailsToAllApplicants4() {
        when(applicantRepository.findByApplicationStatus(Mockito.<ApplicationStatus>any())).thenReturn(new ArrayList<>());
        ResponseEntity<?> actualSendApprovedOrRejectionMailsToAllApplicantsResult = emailService
                .sendApprovedOrRejectionMailsToAllApplicants("janedoe", "Hello from the Dreaming Spires",
                        "Not all who wander are lost", ApplicationStatus.IN_REVIEW);
        verify(applicantRepository).findByApplicationStatus(Mockito.<ApplicationStatus>any());
        assertEquals("Mails sent successfully.", actualSendApprovedOrRejectionMailsToAllApplicantsResult.getBody());
        assertEquals(200, actualSendApprovedOrRejectionMailsToAllApplicantsResult.getStatusCodeValue());
        assertTrue(actualSendApprovedOrRejectionMailsToAllApplicantsResult.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link EmailService#sendApprovedOrRejectionMailsToAllApplicants(String, String, String, ApplicationStatus)}
     */
    @Test
    void testSendApprovedOrRejectionMailsToAllApplicants5() {
        when(applicantRepository.findByApplicationStatus(Mockito.<ApplicationStatus>any()))
                .thenThrow(new RuntimeException("Mails sent successfully."));
        assertThrows(RuntimeException.class, () -> emailService.sendApprovedOrRejectionMailsToAllApplicants("janedoe",
                "Hello from the Dreaming Spires", "Not all who wander are lost", ApplicationStatus.APPROVED));
        verify(applicantRepository).findByApplicationStatus(Mockito.<ApplicationStatus>any());
    }

    /**
     * Method under test:
     * {@link EmailService#sendApprovedOrRejectionMailsToAllApplicants(String, String, String, ApplicationStatus)}
     */
    @Test
    void testSendApprovedOrRejectionMailsToAllApplicants6() {
        Applicant applicant = new Applicant();
        applicant.setApplicationStatus(ApplicationStatus.IN_REVIEW);
        applicant.setCertificates("com.example.cvfiltersystem.model.Applicant");
        applicant.setCompanyUsername("Company Username");
        applicant.setEducationsAndFields("com.example.cvfiltersystem.model.Applicant");
        applicant.setFullName("Prof Albert Einstein");
        applicant.setId(3L);
        applicant.setLanguages("Mails sent successfully.");
        applicant.setMail("com.example.cvfiltersystem.model.Applicant");
        applicant.setPhoneNumber("+44 1865 4960636");
        applicant.setPlaceOfResidence("com.example.cvfiltersystem.model.Applicant");
        applicant.setScore(-0.5d);
        applicant.setSkills("com.example.cvfiltersystem.model.Applicant");
        applicant.setVacancyName("com.example.cvfiltersystem.model.Applicant");
        applicant.setWorkExperienceInfo("com.example.cvfiltersystem.model.Applicant");

        ArrayList<Applicant> applicantList = new ArrayList<>();
        applicantList.add(applicant);
        when(applicantRepository.findByApplicationStatus(Mockito.<ApplicationStatus>any())).thenReturn(applicantList);
        when(companyService.getCompanyByCompanyUsername(Mockito.<String>any())).thenThrow(new RuntimeException(" "));
        assertThrows(RuntimeException.class, () -> emailService.sendApprovedOrRejectionMailsToAllApplicants("janedoe",
                "Hello from the Dreaming Spires", "Not all who wander are lost", ApplicationStatus.REJECTED));
        verify(applicantRepository).findByApplicationStatus(Mockito.<ApplicationStatus>any());
        verify(companyService).getCompanyByCompanyUsername(Mockito.<String>any());
    }

    /**
     * Method under test:
     * {@link EmailService#sendApprovedOrRejectionMailToApplicant(String, Long, String, String)}
     */
    @Test
    void testSendApprovedOrRejectionMailToApplicant() {
        Applicant applicant = new Applicant();
        applicant.setApplicationStatus(ApplicationStatus.APPROVED);
        applicant.setCertificates("Certificates");
        applicant.setCompanyUsername("janedoe");
        applicant.setEducationsAndFields("Educations And Fields");
        applicant.setFullName("Dr Jane Doe");
        applicant.setId(1L);
        applicant.setLanguages("en");
        applicant.setMail("Mail");
        applicant.setPhoneNumber("6625550144");
        applicant.setPlaceOfResidence("Place Of Residence");
        applicant.setScore(10.0d);
        applicant.setSkills("Skills");
        applicant.setVacancyName("Vacancy Name");
        applicant.setWorkExperienceInfo("Work Experience Info");
        Optional<Applicant> ofResult = Optional.of(applicant);
        when(applicantRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        when(companyService.getCompanyByCompanyUsername(Mockito.<String>any())).thenThrow(new RuntimeException(" "));
        assertThrows(RuntimeException.class, () -> emailService.sendApprovedOrRejectionMailToApplicant("janedoe", 1L,
                "Hello from the Dreaming Spires", "Not all who wander are lost"));
        verify(companyService).getCompanyByCompanyUsername(Mockito.<String>any());
        verify(applicantRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test:
     * {@link EmailService#sendApprovedOrRejectionMailToApplicant(String, Long, String, String)}
     */
    @Test
    void testSendApprovedOrRejectionMailToApplicant2() {
        Applicant applicant = new Applicant();
        applicant.setApplicationStatus(ApplicationStatus.APPROVED);
        applicant.setCertificates("Certificates");
        applicant.setCompanyUsername("janedoe");
        applicant.setEducationsAndFields("Educations And Fields");
        applicant.setFullName("Dr Jane Doe");
        applicant.setId(1L);
        applicant.setLanguages("en");
        applicant.setMail(" ");
        applicant.setPhoneNumber("6625550144");
        applicant.setPlaceOfResidence("Place Of Residence");
        applicant.setScore(10.0d);
        applicant.setSkills("Skills");
        applicant.setVacancyName("Vacancy Name");
        applicant.setWorkExperienceInfo("Work Experience Info");
        Optional<Applicant> ofResult = Optional.of(applicant);
        when(applicantRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        ResponseEntity<?> actualSendApprovedOrRejectionMailToApplicantResult = emailService
                .sendApprovedOrRejectionMailToApplicant("janedoe", 1L, "Hello from the Dreaming Spires",
                        "Not all who wander are lost");
        verify(applicantRepository).findById(Mockito.<Long>any());
        assertEquals("Invalid sender email address",
                ((ResponseEntity<Object>) actualSendApprovedOrRejectionMailToApplicantResult.getBody()).getBody());
        assertEquals(200, actualSendApprovedOrRejectionMailToApplicantResult.getStatusCodeValue());
        assertEquals(400,
                ((ResponseEntity<Object>) actualSendApprovedOrRejectionMailToApplicantResult.getBody()).getStatusCodeValue());
        assertTrue(actualSendApprovedOrRejectionMailToApplicantResult.hasBody());
        assertTrue(actualSendApprovedOrRejectionMailToApplicantResult.getHeaders().isEmpty());
        assertTrue(
                ((ResponseEntity<Object>) actualSendApprovedOrRejectionMailToApplicantResult.getBody()).getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link EmailService#sendApprovedOrRejectionMailToApplicant(String, Long, String, String)}
     */
    @Test
    void testSendApprovedOrRejectionMailToApplicant3() {
        Optional<Applicant> emptyResult = Optional.empty();
        when(applicantRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);
        ResponseEntity<?> actualSendApprovedOrRejectionMailToApplicantResult = emailService
                .sendApprovedOrRejectionMailToApplicant("janedoe", 1L, "Hello from the Dreaming Spires",
                        "Not all who wander are lost");
        verify(applicantRepository).findById(Mockito.<Long>any());
        assertEquals("Applicant not found.", actualSendApprovedOrRejectionMailToApplicantResult.getBody());
        assertEquals(404, actualSendApprovedOrRejectionMailToApplicantResult.getStatusCodeValue());
        assertTrue(actualSendApprovedOrRejectionMailToApplicantResult.getHeaders().isEmpty());
    }

}
