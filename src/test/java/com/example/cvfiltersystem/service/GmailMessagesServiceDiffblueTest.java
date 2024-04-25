package com.example.cvfiltersystem.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import com.example.cvfiltersystem.request.CVFilterDTO;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {GmailMessagesService.class})
@ExtendWith(SpringExtension.class)
class GmailMessagesServiceDiffblueTest {
    @MockBean
    private CvFilterSystemService cvFilterSystemService;

    @Autowired
    private GmailMessagesService gmailMessagesService;

    /**
     * Method under test: {@link GmailMessagesService#searchMessages(CVFilterDTO)}
     */
    @Test
    void testSearchMessages() {
        CVFilterDTO cvFilterDTO = new CVFilterDTO();
        cvFilterDTO.setCertificates(new ArrayList<>());
        cvFilterDTO.setCompanyUsername("janedoe");
        cvFilterDTO.setEducationAndFieldInfo("Education And Field Info");
        cvFilterDTO.setEmail("jane.doe@example.org");
        cvFilterDTO
                .setEndDateOfVacancy(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        cvFilterDTO.setExperienceYears(1);
        cvFilterDTO.setLanguagesAndLevels(new ArrayList<>());
        cvFilterDTO.setMinimumScoreRequirement(10.0d);
        cvFilterDTO.setPassword("iloveyou");
        cvFilterDTO.setPosition("Position");
        cvFilterDTO.setResidencePlaceOfPerson("Residence Place Of Person");
        cvFilterDTO.setSkills(new ArrayList<>());
        cvFilterDTO
                .setStartDateOfVacancy(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        cvFilterDTO.setSubjectToSearch("Hello from the Dreaming Spires");
        ResponseEntity<?> actualSearchMessagesResult = gmailMessagesService.searchMessages(cvFilterDTO);
        assertEquals("Not found.", actualSearchMessagesResult.getBody());
        assertEquals(404, actualSearchMessagesResult.getStatusCodeValue());
        assertTrue(actualSearchMessagesResult.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link GmailMessagesService#searchMessages(CVFilterDTO)}
     */
    @Test
    void testSearchMessages2() {
        ArrayList<String> certificates = new ArrayList<>();
        certificates.add("mail.store.protocol");

        CVFilterDTO cvFilterDTO = new CVFilterDTO();
        cvFilterDTO.setCertificates(certificates);
        cvFilterDTO.setCompanyUsername("janedoe");
        cvFilterDTO.setEducationAndFieldInfo("Education And Field Info");
        cvFilterDTO.setEmail("jane.doe@example.org");
        cvFilterDTO
                .setEndDateOfVacancy(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        cvFilterDTO.setExperienceYears(1);
        cvFilterDTO.setLanguagesAndLevels(new ArrayList<>());
        cvFilterDTO.setMinimumScoreRequirement(10.0d);
        cvFilterDTO.setPassword("iloveyou");
        cvFilterDTO.setPosition("Position");
        cvFilterDTO.setResidencePlaceOfPerson("Residence Place Of Person");
        cvFilterDTO.setSkills(new ArrayList<>());
        cvFilterDTO
                .setStartDateOfVacancy(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        cvFilterDTO.setSubjectToSearch("Hello from the Dreaming Spires");
        ResponseEntity<?> actualSearchMessagesResult = gmailMessagesService.searchMessages(cvFilterDTO);
        assertEquals("Not found.", actualSearchMessagesResult.getBody());
        assertEquals(404, actualSearchMessagesResult.getStatusCodeValue());
        assertTrue(actualSearchMessagesResult.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link GmailMessagesService#searchMessages(CVFilterDTO)}
     */
    @Test
    void testSearchMessages3() {
        CVFilterDTO cvFilterDTO = new CVFilterDTO();
        cvFilterDTO.setCertificates(new ArrayList<>());
        cvFilterDTO.setCompanyUsername("janedoe");
        cvFilterDTO.setEducationAndFieldInfo("Education And Field Info");
        cvFilterDTO.setEmail("jane.doe@example.org");
        cvFilterDTO.setEndDateOfVacancy(mock(java.sql.Date.class));
        cvFilterDTO.setExperienceYears(1);
        cvFilterDTO.setLanguagesAndLevels(new ArrayList<>());
        cvFilterDTO.setMinimumScoreRequirement(10.0d);
        cvFilterDTO.setPassword("iloveyou");
        cvFilterDTO.setPosition("Position");
        cvFilterDTO.setResidencePlaceOfPerson("Residence Place Of Person");
        cvFilterDTO.setSkills(new ArrayList<>());
        cvFilterDTO.setStartDateOfVacancy(
                java.util.Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        cvFilterDTO.setSubjectToSearch("Hello from the Dreaming Spires");
        ResponseEntity<?> actualSearchMessagesResult = gmailMessagesService.searchMessages(cvFilterDTO);
        assertEquals("Not found.", actualSearchMessagesResult.getBody());
        assertEquals(404, actualSearchMessagesResult.getStatusCodeValue());
        assertTrue(actualSearchMessagesResult.getHeaders().isEmpty());
    }
}
