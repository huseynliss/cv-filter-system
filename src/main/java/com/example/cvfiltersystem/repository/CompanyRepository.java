package com.example.cvfiltersystem.repository;

import com.example.cvfiltersystem.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    Company findByCompanyUsername(String companyUsername);

}
