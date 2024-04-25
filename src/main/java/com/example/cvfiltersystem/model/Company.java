package com.example.cvfiltersystem.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    private String companyUsername;

    private String companyMail;

    private String companyMailPassword;


}
