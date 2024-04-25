package com.example.cvfiltersystem.request;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CVFilterDTO {
    private String educationAndFieldInfo;
    private String position;
    private int experienceYears;
    private List<String> skills;
    private List<String> languagesAndLevels;
    private List<String> certificates;
    private String residencePlaceOfPerson;
    private String email;
    private String password;
    private String subjectToSearch;
    private Date startDateOfVacancy;
    private Date endDateOfVacancy;
    private Double minimumScoreRequirement;
    private String companyUsername;

}
