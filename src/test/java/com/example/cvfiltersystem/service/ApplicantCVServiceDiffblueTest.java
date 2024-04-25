package com.example.cvfiltersystem.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.cvfiltersystem.model.ApplicantCV;
import com.example.cvfiltersystem.repository.ApplicantCVRepository;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ApplicantCVService.class})
@ExtendWith(SpringExtension.class)
class ApplicantCVServiceDiffblueTest {
    @MockBean
    private ApplicantCVRepository applicantCVRepository;

    @Autowired
    private ApplicantCVService applicantCVService;

    /**
     * Method under test: {@link ApplicantCVService#getVideoById(Long)}
     */
    @Test
    void testGetVideoById() throws FileNotFoundException, UnsupportedEncodingException {
        ApplicantCV applicantCV = new ApplicantCV();
        applicantCV.setApplicantId(1L);
        applicantCV.setContentType("text/plain");
        applicantCV.setData("AXAXAXAX".getBytes("UTF-8"));
        applicantCV.setFileName("foo.txt");
        applicantCV.setId(1L);
        Optional<ApplicantCV> ofResult = Optional.of(applicantCV);
        when(applicantCVRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        ResponseEntity<?> actualVideoById = applicantCVService.getVideoById(1L);
        verify(applicantCVRepository).findById(Mockito.<Long>any());
        assertEquals(1, actualVideoById.getHeaders().size());
        assertEquals(200, actualVideoById.getStatusCodeValue());
        assertTrue(actualVideoById.hasBody());
    }

    /**
     * Method under test: {@link ApplicantCVService#getVideoById(Long)}
     */
    @Test
    void testGetVideoById2() throws FileNotFoundException {
        Optional<ApplicantCV> emptyResult = Optional.empty();
        when(applicantCVRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);
        assertThrows(FileNotFoundException.class, () -> applicantCVService.getVideoById(1L));
        verify(applicantCVRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link ApplicantCVService#getVideoByApplicantId(Long)}
     */
    @Test
    void testGetVideoByApplicantId() throws FileNotFoundException, UnsupportedEncodingException {
        ApplicantCV applicantCV = new ApplicantCV();
        applicantCV.setApplicantId(1L);
        applicantCV.setContentType("text/plain");
        applicantCV.setData("AXAXAXAX".getBytes("UTF-8"));
        applicantCV.setFileName("foo.txt");
        applicantCV.setId(1L);
        Optional<ApplicantCV> ofResult = Optional.of(applicantCV);
        when(applicantCVRepository.findApplicantCVByApplicantId(Mockito.<Long>any())).thenReturn(ofResult);
        ResponseEntity<?> actualVideoByApplicantId = applicantCVService.getVideoByApplicantId(1L);
        verify(applicantCVRepository).findApplicantCVByApplicantId(Mockito.<Long>any());
        assertEquals(1, actualVideoByApplicantId.getHeaders().size());
        assertEquals(200, actualVideoByApplicantId.getStatusCodeValue());
        assertTrue(actualVideoByApplicantId.hasBody());
    }

    /**
     * Method under test: {@link ApplicantCVService#getVideoByApplicantId(Long)}
     */
    @Test
    void testGetVideoByApplicantId2() throws FileNotFoundException {
        Optional<ApplicantCV> emptyResult = Optional.empty();
        when(applicantCVRepository.findApplicantCVByApplicantId(Mockito.<Long>any())).thenReturn(emptyResult);
        assertThrows(FileNotFoundException.class, () -> applicantCVService.getVideoByApplicantId(1L));
        verify(applicantCVRepository).findApplicantCVByApplicantId(Mockito.<Long>any());
    }
}
