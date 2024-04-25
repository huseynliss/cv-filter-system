package com.example.cvfiltersystem.controller;

import com.example.cvfiltersystem.reponse.ApplicantResponse;
import com.example.cvfiltersystem.request.ApplicantRequest;
import com.example.cvfiltersystem.request.ApplicantUpdateRequest;
import com.example.cvfiltersystem.service.ApplicantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/applicants")
@CrossOrigin(origins = "*", allowedHeaders = "*")

public class ApplicantController {

    private final ApplicantService applicantService;

    public ApplicantController(ApplicantService applicantService) {
        this.applicantService = applicantService;
    }

    @PostMapping("/create-applicant")
    public ResponseEntity<ApplicantResponse> createApplicant(@RequestBody ApplicantRequest requestDTO) {
        return applicantService.createApplicant(requestDTO);
    }

    @PreAuthorize("hasRole('client_admin')")
    @GetMapping("/get-all-applicants/{companyUsername}")
    public ResponseEntity<List<ApplicantResponse>> getAllApplicants(@PathVariable String companyUsername) {
        return applicantService.getAllApplicants(companyUsername);
    }
    @PreAuthorize("hasRole('client_admin')")
    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<ApplicantResponse> getApplicantById(@PathVariable Long id) {
        return applicantService.getApplicantById(id);
    }
    @PreAuthorize("hasRole('client_admin')")
    @PutMapping("/update-applicant/{applicantId}")
    public ResponseEntity<ApplicantResponse> updateApplicant(@PathVariable Long applicantId, @RequestBody ApplicantUpdateRequest applicantUpdateRequest) {
        return applicantService.updateApplicant(applicantId, applicantUpdateRequest);
    }
    @PreAuthorize("hasRole('client_admin')")
    @DeleteMapping("/delete-by-id/{id}")
    public ResponseEntity<Void> deleteApplicant(@PathVariable Long id) {
        return applicantService.deleteApplicant(id);
    }
}
