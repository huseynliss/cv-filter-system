package com.example.cvfiltersystem.repository;
import com.example.cvfiltersystem.enums.ApplicationStatus;
import com.example.cvfiltersystem.model.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ApplicantRepository extends JpaRepository<Applicant, Long> {
    List<Applicant> findByApplicationStatus(ApplicationStatus applicationStatus);
    List<Applicant> findApplicantsByCompanyUsername(String companyUsername);


}
