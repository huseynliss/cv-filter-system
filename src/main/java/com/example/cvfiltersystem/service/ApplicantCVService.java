package com.example.cvfiltersystem.service;

import com.example.cvfiltersystem.model.ApplicantCV;
import com.example.cvfiltersystem.repository.ApplicantCVRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;

@Service
public class ApplicantCVService {
    private final ApplicantCVRepository applicantCVRepository;
//    private final ModelMapper modelMapper;

    public ApplicantCVService(ApplicantCVRepository applicantCVRepository) {
        this.applicantCVRepository = applicantCVRepository;
    }



    public ResponseEntity<?> getVideoById(Long id) throws FileNotFoundException {
        ApplicantCV applicantCV = applicantCVRepository.findById(id).orElseThrow(
                () -> new FileNotFoundException("File not found"));

        return ResponseEntity.status(HttpStatus.OK)
                .header("Content-Type", applicantCV.getContentType())
                .body(applicantCV.getData());
    }

    public ResponseEntity<?> getVideoByApplicantId(Long applicantId) throws FileNotFoundException {
        ApplicantCV applicantCV = applicantCVRepository.findApplicantCVByApplicantId(applicantId).orElseThrow(
                () -> new FileNotFoundException("File not found"));

        return ResponseEntity.status(HttpStatus.OK)
                .header("Content-Type", applicantCV.getContentType())
                .body(applicantCV.getData());
    }

}
