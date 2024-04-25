package com.example.cvfiltersystem.service;

import com.example.cvfiltersystem.helper.BodyPartToFile;
import com.example.cvfiltersystem.helper.PdfOperations;
import com.example.cvfiltersystem.reponse.ApplicantResponse;
import com.example.cvfiltersystem.request.CVFilterDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import javax.mail.*;
import javax.mail.internet.MimeMultipart;
import javax.mail.search.AndTerm;
import javax.mail.search.ReceivedDateTerm;
import javax.mail.search.SearchTerm;
import javax.mail.search.SubjectTerm;
import javax.mail.util.ByteArrayDataSource;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class GmailMessagesService {

    CvFilterSystemService cvFilterSystemService;


    public GmailMessagesService(CvFilterSystemService cvFilterSystemService) {
        this.cvFilterSystemService = cvFilterSystemService;
    }

    public ResponseEntity<?> searchMessages(CVFilterDTO cvFilterDTO) {
        final String email = cvFilterDTO.getEmail();
        final String password = cvFilterDTO.getPassword();
        final String subjectToSearch = cvFilterDTO.getSubjectToSearch();
        final Date startDate = cvFilterDTO.getStartDateOfVacancy();
        final Date endDate = cvFilterDTO.getEndDateOfVacancy();

        Properties props = new Properties();
        props.setProperty("mail.store.protocol", "imaps");

        LocalDateTime currentDateTime1 = getCurrentDateTime();

        try {
            Session session = Session.getInstance(props, null);
            Store store = session.getStore();
            store.connect("imap.gmail.com", email, password);

            Folder inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_ONLY);


            SearchTerm startDateTerm = new ReceivedDateTerm(ReceivedDateTerm.GT, startDate);
            SearchTerm endDateTerm = new ReceivedDateTerm(ReceivedDateTerm.LE, endDate);
            SearchTerm subjectTerm = new SubjectTerm(subjectToSearch);

            SearchTerm[] dateRangeTerms = new SearchTerm[]{startDateTerm, endDateTerm};
            AndTerm dateRangeAndTerm = new AndTerm(dateRangeTerms);

            SearchTerm[] finalSearchTerms = new SearchTerm[]{dateRangeAndTerm, subjectTerm};
            AndTerm finalAndTerm = new AndTerm(finalSearchTerms);

            Message[] filteredMessages = inbox.search(finalAndTerm);
//            Message[] filteredMessages = inbox.search(searchTerm);
            List<String> stringList = new ArrayList<>();
            List<ApplicantResponse> applicantResponseList = new ArrayList<>();

            for (Message message : filteredMessages) {
                if (message.isMimeType("multipart/*")) {
                    System.out.println(message.getInputStream());
                    InputStream inputStream1 = message.getInputStream();
                    Multipart multipart = new MimeMultipart(new ByteArrayDataSource(inputStream1, "multipart/mixed"));
//                    Multipart multipart = (Multipart) message.getContent();
                    for (int i = 0; i < multipart.getCount(); i++) {
                        BodyPart bodyPart = multipart.getBodyPart(i);
                        if (bodyPart.getContentType().toLowerCase().contains("application/pdf")) {
                            MultipartFile multipartFile = new BodyPartToFile(bodyPart, "file", bodyPart.getFileName(), "application/pdf");


                            try {
                                InputStream inputStream = bodyPart.getInputStream();
                                String text = PdfOperations.convertPdfToText(inputStream);
                                stringList.add(text);
                                System.out.println(text);
                                System.out.println("------------------------------------");

                                applicantResponseList.add(cvFilterSystemService.filterCVs(multipartFile, cvFilterDTO.getEducationAndFieldInfo(),
                                        cvFilterDTO.getPosition(),
                                        (long) cvFilterDTO.getExperienceYears(),
                                        cvFilterDTO.getSkills(),
                                        cvFilterDTO.getLanguagesAndLevels(),
                                        cvFilterDTO.getCertificates(),
                                        cvFilterDTO.getResidencePlaceOfPerson(), cvFilterDTO.getMinimumScoreRequirement(), cvFilterDTO.getCompanyUsername()));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }
                    }
                }
            }
            Duration duration = Duration.between(currentDateTime1, getCurrentDateTime());

            System.out.println(duration);
            inbox.close(false);
            store.close();
            return ResponseEntity.ok(applicantResponseList);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found.");

        }
    }


    private LocalDateTime getCurrentDateTime() {
        return LocalDateTime.now();
    }
}
