package com.example.cvfiltersystem.request;

import lombok.Data;

@Data
public class MailRequest {
    private Long applicantId;
    private String subject;
    private String body;

}
