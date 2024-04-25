package com.example.cvfiltersystem.controller;
import com.example.cvfiltersystem.service.ApplicantCVService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;

@RestController
@RequestMapping("/applicant")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ApplicantCVController {

    private final ApplicantCVService applicantCVService;

    ApplicantCVController(ApplicantCVService applicantCVService){
        this.applicantCVService = applicantCVService;
    }
    @PreAuthorize("hasRole('client_admin')")
    @GetMapping("get-cv-file-by-id/{id}")
    public ResponseEntity<?> getCVFileById(@PathVariable Long id) throws FileNotFoundException {
        return applicantCVService.getVideoById(id);
    }
    @PreAuthorize("hasRole('client_admin')")
    @GetMapping("get-cv-file-by-applicantId/{applicantId}")
    public ResponseEntity<?> getCVFileByApplicantId(@PathVariable Long applicantId) throws FileNotFoundException {
        return applicantCVService.getVideoByApplicantId(applicantId);
    }
}
