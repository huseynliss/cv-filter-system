package com.example.cvfiltersystem.reponse;

import lombok.Data;

@Data
public class CompanyResponse {
    private Long id;
    private String companyUsername;
    private String companyMail;
    private String companyMailPassword;
}
