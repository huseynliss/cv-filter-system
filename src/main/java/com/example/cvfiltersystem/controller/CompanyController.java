package com.example.cvfiltersystem.controller;

import com.example.cvfiltersystem.reponse.CompanyResponse;
import com.example.cvfiltersystem.request.CompanyRequest;
import com.example.cvfiltersystem.service.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
@CrossOrigin(origins = "*", allowedHeaders = "*")

public class CompanyController {
    private final CompanyService companyService;
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }
    @PostMapping("create-company")
    public ResponseEntity<CompanyResponse> createCompany(@RequestBody CompanyRequest companyRequest) {
        CompanyResponse createdCompany = companyService.createCompany(companyRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCompany);
    }
    @PreAuthorize("hasRole('client_admin')")
    @GetMapping("get-all-companies")
    public ResponseEntity<List<CompanyResponse>> getAllCompanies() {
        List<CompanyResponse> companies = companyService.getAllCompanies();
        return ResponseEntity.ok(companies);
    }
    @PreAuthorize("hasRole('client_admin')")
    @GetMapping("get-company-by-id/{id}")
    public ResponseEntity<CompanyResponse> getCompanyById(@PathVariable Long id) {
        CompanyResponse company = companyService.getCompanyById(id);
        if (company != null) {
            return ResponseEntity.ok(company);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PreAuthorize("hasRole('client_admin')")
    @GetMapping("get-company-by-companyUsername/{companyUsername}")
    public ResponseEntity<CompanyResponse> getCompanyByCompanyUsername(@PathVariable String companyUsername) {
        CompanyResponse company = companyService.getCompanyByCompanyUsername(companyUsername);
        if (company != null) {
            return ResponseEntity.ok(company);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PreAuthorize("hasRole('client_admin')")
    @PutMapping("update-company/{companyUsername}")
    public ResponseEntity<CompanyResponse> updateCompany(@PathVariable String companyUsername, @RequestBody CompanyRequest companyRequest) {
        CompanyResponse updatedCompany = companyService.updateCompany(companyUsername, companyRequest);
        if (updatedCompany != null) {
            return ResponseEntity.ok(updatedCompany);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PreAuthorize("hasRole('client_admin')")
    @DeleteMapping("delete-company/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable Long id) {
        companyService.deleteCompany(id);
        return ResponseEntity.noContent().build();
    }
}
