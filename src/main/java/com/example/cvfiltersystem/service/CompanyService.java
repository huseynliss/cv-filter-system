package com.example.cvfiltersystem.service;

import com.example.cvfiltersystem.model.Company;
import com.example.cvfiltersystem.reponse.CompanyResponse;
import com.example.cvfiltersystem.repository.CompanyRepository;
import com.example.cvfiltersystem.request.CompanyRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompanyService {

    private CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public CompanyResponse createCompany(CompanyRequest companyRequest) {
        Company existingCompany = companyRepository.findByCompanyUsername(companyRequest.getCompanyUsername());

        if (existingCompany != null) {
            return convertToResponse(existingCompany);
        }

        Company company = new Company();
        company.setCompanyUsername(companyRequest.getCompanyUsername());

        Company savedCompany = companyRepository.save(company);

        return convertToResponse(savedCompany);
    }


    public List<CompanyResponse> getAllCompanies() {
        List<Company> companies = companyRepository.findAll();
        return companies.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    public CompanyResponse getCompanyById(Long id) {
        Optional<Company> optionalCompany = companyRepository.findById(id);
        return optionalCompany.map(this::convertToResponse).orElse(null);
    }

    public CompanyResponse updateCompany(String companyUsername, CompanyRequest companyRequest) {
        Company byCompanyUsername = companyRepository.findByCompanyUsername(companyUsername);
        if (byCompanyUsername != null) {
//            byCompanyUsername.setCompanyUsername(companyRequest.getCompanyUsername());
            byCompanyUsername.setCompanyMail(companyRequest.getCompanyMail());
            byCompanyUsername.setCompanyMailPassword(companyRequest.getCompanyMailPassword());
            Company updatedCompany = companyRepository.save(byCompanyUsername);
            return convertToResponse(updatedCompany);
        } else {
            return null;
        }
    }

    public CompanyResponse getCompanyByCompanyUsername(String companyUsername) {
        Company existingCompany = companyRepository.findByCompanyUsername(companyUsername);
        return convertToResponse(existingCompany);
    }

    public void deleteCompany(Long id) {
        companyRepository.deleteById(id);
    }

    private CompanyResponse convertToResponse(Company company) {
        CompanyResponse response = new CompanyResponse();
        response.setId(company.getId());
        response.setCompanyUsername(company.getCompanyUsername());
        response.setCompanyMail(company.getCompanyMail());
        response.setCompanyMailPassword(company.getCompanyMailPassword());
        return response;
    }

}

