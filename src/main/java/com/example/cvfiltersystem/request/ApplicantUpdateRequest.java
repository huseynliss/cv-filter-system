package com.example.cvfiltersystem.request;

import com.example.cvfiltersystem.enums.ApplicationStatus;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class ApplicantUpdateRequest {
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

}
