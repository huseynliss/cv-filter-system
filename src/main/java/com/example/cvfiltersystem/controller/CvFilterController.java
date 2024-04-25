package com.example.cvfiltersystem.controller;

import com.example.cvfiltersystem.request.CVFilterDTO;
import com.example.cvfiltersystem.service.CvFilterSystemService;
//import com.example.cvfiltersystem.service.GmailMessagesService;
import com.example.cvfiltersystem.service.GmailMessagesService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cv-filter-system")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CvFilterController {

    private final CvFilterSystemService cvFilterSystemService;
    private final GmailMessagesService gmailMessagesService;

    public CvFilterController(CvFilterSystemService cvFilterSystemService, GmailMessagesService gmailMessagesService) {
        this.cvFilterSystemService = cvFilterSystemService;
        this.gmailMessagesService = gmailMessagesService;
    }
    @PostMapping("/filter-cv")
    public ResponseEntity<?> filterCVs(@RequestBody CVFilterDTO cvFilterDTO
    ) {
        return gmailMessagesService.searchMessages(
                cvFilterDTO);
    }
}
