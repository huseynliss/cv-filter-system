package com.example.cvfiltersystem.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class ApplicantCV {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    private String fileName;

    private String contentType;

    @Column(columnDefinition = "BYTEA")
    private byte[] data;

    private Long applicantId;

}
