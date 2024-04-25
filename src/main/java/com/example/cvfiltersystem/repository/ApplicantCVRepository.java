package com.example.cvfiltersystem.repository;

import com.example.cvfiltersystem.model.ApplicantCV;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApplicantCVRepository extends JpaRepository<ApplicantCV, Long> {
    Optional<ApplicantCV> findApplicantCVByApplicantId(Long applicantId);

}
