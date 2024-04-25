package com.example.cvfiltersystem.request;

import lombok.Data;

@Data
public class CompanyRequest {
    private Long id;
    private String companyUsername;
    private String companyMail;
    private String companyMailPassword;

}
