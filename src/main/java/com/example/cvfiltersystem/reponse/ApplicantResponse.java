package com.example.cvfiltersystem.reponse;

import com.example.cvfiltersystem.enums.ApplicationStatus;
import lombok.Data;

@Data
public class ApplicantResponse {
    private Long id;

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

    private Long applicantCVId;
}
