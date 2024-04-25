package com.example.cvfiltersystem.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.example.cvfiltersystem.helper.BodyPartToFile;
import com.example.cvfiltersystem.repository.ApplicantCVRepository;
import com.example.cvfiltersystem.repository.ApplicantRepository;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.mail.MessagingException;
import javax.mail.internet.InternetHeaders;
import javax.mail.internet.MimeBodyPart;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.ai.client.AiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.multipart.MultipartFile;

@ContextConfiguration(classes = {CvFilterSystemService.class})
@ExtendWith(SpringExtension.class)
class CvFilterSystemServiceDiffblueTest {
    @MockBean
    private AiClient aiClient;

    @MockBean
    private ApplicantCVRepository applicantCVRepository;

    @MockBean
    private ApplicantRepository applicantRepository;

    @Autowired
    private CvFilterSystemService cvFilterSystemService;

    @MockBean
    private ModelMapper modelMapper;

    /**
     * Method under test:
     * {@link CvFilterSystemService#filterCVs(MultipartFile, String, String, Long, List, List, List, String, double, String)}
     */
    @Test
    void testFilterCVs() throws IOException {
        BodyPartToFile file = new BodyPartToFile(new MimeBodyPart(), "Name", "foo.txt", "text/plain");

        ArrayList<String> skillsRequirements = new ArrayList<>();
        ArrayList<String> languagesRequirements = new ArrayList<>();
        assertThrows(IOException.class,
                () -> cvFilterSystemService.filterCVs(file, "Education Level", "Position", 1L, skillsRequirements,
                        languagesRequirements, new ArrayList<>(), "Residence Place Requirements", 10.0d, "janedoe"));
    }

    /**
     * Method under test:
     * {@link CvFilterSystemService#filterCVs(MultipartFile, String, String, Long, List, List, List, String, double, String)}
     */
    @Test
    void testFilterCVs2() throws IOException, MessagingException {
        BodyPartToFile file = new BodyPartToFile(new MimeBodyPart(new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"))),
                "Name", "foo.txt", "text/plain");

        ArrayList<String> skillsRequirements = new ArrayList<>();
        ArrayList<String> languagesRequirements = new ArrayList<>();
        assertThrows(IOException.class,
                () -> cvFilterSystemService.filterCVs(file, "Education Level", "Position", 1L, skillsRequirements,
                        languagesRequirements, new ArrayList<>(), "Residence Place Requirements", 10.0d, "janedoe"));
    }

    /**
     * Method under test:
     * {@link CvFilterSystemService#filterCVs(MultipartFile, String, String, Long, List, List, List, String, double, String)}
     */
    @Test
    void testFilterCVs3() throws IOException, MessagingException {
        InternetHeaders headers = new InternetHeaders();
        BodyPartToFile file = new BodyPartToFile(new MimeBodyPart(headers, "AXAXAXAX".getBytes("UTF-8")), "Name", "foo.txt",
                "text/plain");

        ArrayList<String> skillsRequirements = new ArrayList<>();
        ArrayList<String> languagesRequirements = new ArrayList<>();
        assertThrows(IOException.class,
                () -> cvFilterSystemService.filterCVs(file, "Education Level", "Position", 1L, skillsRequirements,
                        languagesRequirements, new ArrayList<>(), "Residence Place Requirements", 10.0d, "janedoe"));
    }

    /**
     * Method under test:
     * {@link CvFilterSystemService#filterCVs(MultipartFile, String, String, Long, List, List, List, String, double, String)}
     */
    @Test
    void testFilterCVs4() throws IOException {
        BodyPartToFile file = new BodyPartToFile(new MimeBodyPart(), "Name", "foo.txt", "text/plain");

        ArrayList<String> skillsRequirements = new ArrayList<>();
        skillsRequirements.add("foo");
        ArrayList<String> languagesRequirements = new ArrayList<>();
        assertThrows(IOException.class,
                () -> cvFilterSystemService.filterCVs(file, "Education Level", "Position", 1L, skillsRequirements,
                        languagesRequirements, new ArrayList<>(), "Residence Place Requirements", 10.0d, "janedoe"));
    }

    /**
     * Method under test:
     * {@link CvFilterSystemService#filterCVs(MultipartFile, String, String, Long, List, List, List, String, double, String)}
     */
    @Test
    void testFilterCVs5() throws IOException {
        BodyPartToFile file = new BodyPartToFile(new MimeBodyPart(), "Name", "foo.txt", "text/plain");

        ArrayList<String> skillsRequirements = new ArrayList<>();
        skillsRequirements.add("42");
        skillsRequirements.add("foo");
        ArrayList<String> languagesRequirements = new ArrayList<>();
        assertThrows(IOException.class,
                () -> cvFilterSystemService.filterCVs(file, "Education Level", "Position", 1L, skillsRequirements,
                        languagesRequirements, new ArrayList<>(), "Residence Place Requirements", 10.0d, "janedoe"));
    }

    /**
     * Method under test:
     * {@link CvFilterSystemService#filterCVs(MultipartFile, String, String, Long, List, List, List, String, double, String)}
     */
    @Test
    void testFilterCVs6() throws IOException {
        BodyPartToFile file = new BodyPartToFile(new MimeBodyPart(), "Name", "foo.txt", "text/plain");

        ArrayList<String> skillsRequirements = new ArrayList<>();

        ArrayList<String> languagesRequirements = new ArrayList<>();
        languagesRequirements.add("foo");
        assertThrows(IOException.class,
                () -> cvFilterSystemService.filterCVs(file, "Education Level", "Position", 1L, skillsRequirements,
                        languagesRequirements, new ArrayList<>(), "Residence Place Requirements", 10.0d, "janedoe"));
    }

    /**
     * Method under test:
     * {@link CvFilterSystemService#filterCVs(MultipartFile, String, String, Long, List, List, List, String, double, String)}
     */
    @Test
    void testFilterCVs7() throws IOException {
        BodyPartToFile file = new BodyPartToFile(new MimeBodyPart(), "Name", "foo.txt", "text/plain");

        ArrayList<String> skillsRequirements = new ArrayList<>();

        ArrayList<String> languagesRequirements = new ArrayList<>();
        languagesRequirements.add("42");
        languagesRequirements.add("foo");
        assertThrows(IOException.class,
                () -> cvFilterSystemService.filterCVs(file, "Education Level", "Position", 1L, skillsRequirements,
                        languagesRequirements, new ArrayList<>(), "Residence Place Requirements", 10.0d, "janedoe"));
    }

    /**
     * Method under test:
     * {@link CvFilterSystemService#filterCVs(MultipartFile, String, String, Long, List, List, List, String, double, String)}
     */
    @Test
    void testFilterCVs8() throws IOException {
        BodyPartToFile file = new BodyPartToFile(new MimeBodyPart(), "Name", "foo.txt", "text/plain");

        ArrayList<String> skillsRequirements = new ArrayList<>();
        ArrayList<String> languagesRequirements = new ArrayList<>();

        ArrayList<String> certificatesRequirements = new ArrayList<>();
        certificatesRequirements.add("foo");
        assertThrows(IOException.class,
                () -> cvFilterSystemService.filterCVs(file, "Education Level", "Position", 1L, skillsRequirements,
                        languagesRequirements, certificatesRequirements, "Residence Place Requirements", 10.0d, "janedoe"));
    }

    /**
     * Method under test:
     * {@link CvFilterSystemService#filterCVs(MultipartFile, String, String, Long, List, List, List, String, double, String)}
     */
    @Test
    void testFilterCVs9() throws IOException {
        BodyPartToFile file = new BodyPartToFile(new MimeBodyPart(), "Name", "foo.txt", "text/plain");

        ArrayList<String> skillsRequirements = new ArrayList<>();
        ArrayList<String> languagesRequirements = new ArrayList<>();

        ArrayList<String> certificatesRequirements = new ArrayList<>();
        certificatesRequirements.add("42");
        certificatesRequirements.add("foo");
        assertThrows(IOException.class,
                () -> cvFilterSystemService.filterCVs(file, "Education Level", "Position", 1L, skillsRequirements,
                        languagesRequirements, certificatesRequirements, "Residence Place Requirements", 10.0d, "janedoe"));
    }

    @Test
    void testRoundToNDecimals() {
        assertEquals(10.0d, CvFilterSystemService.roundToNDecimals(10.0d, 3));
        assertEquals(0.5d, CvFilterSystemService.roundToNDecimals(0.5d, 3));
        assertEquals(-0.5d, CvFilterSystemService.roundToNDecimals(-0.5d, 3));
        assertEquals(0.0d, CvFilterSystemService.roundToNDecimals(Double.NaN, 3));
    }
}
