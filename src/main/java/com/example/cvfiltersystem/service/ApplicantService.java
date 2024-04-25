package com.example.cvfiltersystem.service;

import com.example.cvfiltersystem.model.Applicant;
import com.example.cvfiltersystem.reponse.ApplicantResponse;
import com.example.cvfiltersystem.repository.ApplicantCVRepository;
import com.example.cvfiltersystem.repository.ApplicantRepository;
import com.example.cvfiltersystem.request.ApplicantRequest;
import com.example.cvfiltersystem.request.ApplicantUpdateRequest;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ApplicantService {

    private final ApplicantRepository applicantRepository;
    private final ApplicantCVRepository applicantCVRepository;
    private final ModelMapper modelMapper;

    public ApplicantService(ApplicantRepository applicantRepository, ApplicantCVRepository applicantCVRepository, ModelMapper modelMapper) {
        this.applicantRepository = applicantRepository;
        this.applicantCVRepository = applicantCVRepository;
        this.modelMapper = modelMapper;
    }

    public ResponseEntity<ApplicantResponse> createApplicant(ApplicantRequest requestDTO) {
        try {
            Applicant applicant = modelMapper.map(requestDTO, Applicant.class);
            Applicant savedApplicant = applicantRepository.save(applicant);
            ApplicantResponse applicantResponse = modelMapper.map(savedApplicant, ApplicantResponse.class);
            Long applicantCVId = applicantCVRepository.findApplicantCVByApplicantId(savedApplicant.getId())
                    .orElseThrow(() -> new RuntimeException("Applicant CV not found")).getApplicantId();
            applicantResponse.setApplicantCVId(applicantCVId);
            return new ResponseEntity<>(applicantResponse, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<ApplicantResponse>> getAllApplicants(String companyUsername) {
        try {
            List<Applicant> applicants = applicantRepository.findApplicantsByCompanyUsername(companyUsername);
            List<ApplicantResponse> applicantResponses = applicants.stream()
                    .map(applicant -> {
                        ApplicantResponse applicantResponse = modelMapper.map(applicant, ApplicantResponse.class);
                        Long applicantCVId = applicantCVRepository.findApplicantCVByApplicantId(applicant.getId())
                                .orElseThrow(() -> new RuntimeException("Applicant CV not found")).getApplicantId();
                        applicantResponse.setApplicantCVId(applicantCVId);
                        applicantResponse.setLanguages(applicant.getLanguages().replaceAll("\\(1\\)", "(Advanced)").replaceAll("\\(2\\)", "(Intermediate)").replaceAll("\\(3\\)", "(Basic)"));

                        return applicantResponse;
                    })
                    .collect(Collectors.toList());
            return ResponseEntity.status(HttpStatus.OK).body(applicantResponses);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<ApplicantResponse> getApplicantById(Long id) {
        try {
            Optional<Applicant> optionalApplicant = applicantRepository.findById(id);
            if (optionalApplicant.isPresent()) {
                Applicant applicant = optionalApplicant.get();
                ApplicantResponse applicantResponse = modelMapper.map(applicant, ApplicantResponse.class);
                Long applicantCVId = applicantCVRepository.findApplicantCVByApplicantId(applicant.getId())
                        .orElseThrow(() -> new RuntimeException("Applicant CV not found")).getApplicantId();
                applicantResponse.setApplicantCVId(applicantCVId);
                applicantResponse.setLanguages(applicant.getLanguages().replaceAll("\\(1\\)", "(Advanced)").replaceAll("\\(2\\)", "(Intermediate)").replaceAll("\\(3\\)", "(Basic)"));
                return new ResponseEntity<>(applicantResponse, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<ApplicantResponse> updateApplicant(Long applicantId, ApplicantUpdateRequest requestDTO) {
        try {
            Optional<Applicant> optionalApplicant = applicantRepository.findById(applicantId);
            if (optionalApplicant.isPresent()) {
                Applicant applicant = optionalApplicant.get();
                modelMapper.map(requestDTO, applicant);
                applicant.setId(applicantId);

                applicant.setLanguages(requestDTO.getLanguages().replaceAll("\\(Advanced\\)", "(1)").replaceAll("\\(Intermediate\\)", "(2)").replaceAll("\\(Basic\\)", "(3)"));
                Applicant savedApplicant = applicantRepository.save(applicant);
                ApplicantResponse applicantResponse = modelMapper.map(savedApplicant, ApplicantResponse.class);
                Long applicantCVId = applicantCVRepository.findApplicantCVByApplicantId(savedApplicant.getId())
                        .orElseThrow(() -> new RuntimeException("Applicant CV not found")).getApplicantId();
                applicantResponse.setApplicantCVId(applicantCVId);
                return new ResponseEntity<>(applicantResponse, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Void> deleteApplicant(Long id) {
        if (id == null || id <= 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (!applicantRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        try {
            applicantRepository.deleteById(id);
            applicantCVRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
