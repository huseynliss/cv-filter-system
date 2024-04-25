package com.example.cvfiltersystem.model;

import com.example.cvfiltersystem.enums.ApplicationStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Entity
@Data
public class Applicant {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus applicationStatus;

    private String fullName;

    private String skills;

    private String languages;

    private String certificates;

    private String educationsAndFields;

    private String placeOfResidence;

    private String workExperienceInfo;

    private String phoneNumber;

    private String mail;

    private Double score;

    private String vacancyName;

    private String companyUsername;

    @Override
    public String toString() {
        return "Applicant{" +
                "id=" + id +
                ", applicationStatus=" + applicationStatus +
                ", fullName='" + fullName + '\'' +
                ", skills='" + skills + '\'' +
                ", languages='" + languages + '\'' +
                ", certificates='" + certificates + '\'' +
                ", educationsAndFields='" + educationsAndFields + '\'' +
                ", placeOfResidence='" + placeOfResidence + '\'' +
                ", workExperienceInfo='" + workExperienceInfo + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", mail='" + mail + '\'' +
                ", score=" + score +
                '}';
    }
}

