package com.example.cvfiltersystem.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.cvfiltersystem.enums.ApplicationStatus;
import com.example.cvfiltersystem.model.Applicant;
import com.example.cvfiltersystem.model.ApplicantCV;
import com.example.cvfiltersystem.reponse.ApplicantResponse;
import com.example.cvfiltersystem.repository.ApplicantCVRepository;
import com.example.cvfiltersystem.repository.ApplicantRepository;
import com.example.cvfiltersystem.request.ApplicantRequest;
import com.example.cvfiltersystem.request.ApplicantUpdateRequest;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ApplicantService.class})
@ExtendWith(SpringExtension.class)
class ApplicantServiceDiffblueTest {
  @MockBean
  private ApplicantCVRepository applicantCVRepository;

  @MockBean
  private ApplicantRepository applicantRepository;

  @Autowired
  private ApplicantService applicantService;

  @MockBean
  private ModelMapper modelMapper;

  /**
   * Method under test: {@link ApplicantService#createApplicant(ApplicantRequest)}
   */
  @Test
  void testCreateApplicant() {
    when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<Object>>any())).thenReturn("Map");

    ApplicantRequest requestDTO = new ApplicantRequest();
    requestDTO.setApplicationStatus(ApplicationStatus.APPROVED);
    requestDTO.setCertificates("Certificates");
    requestDTO.setCompanyUsername("janedoe");
    requestDTO.setEducationsAndFields("Educations And Fields");
    requestDTO.setFullName("Dr Jane Doe");
    requestDTO.setId(1L);
    requestDTO.setLanguages("en");
    requestDTO.setMail("Mail");
    requestDTO.setPhoneNumber("6625550144");
    requestDTO.setPlaceOfResidence("Place Of Residence");
    requestDTO.setScore(10.0d);
    requestDTO.setSkills("Skills");
    requestDTO.setVacancyName("Vacancy Name");
    requestDTO.setWorkExperienceInfo("Work Experience Info");
    ResponseEntity<ApplicantResponse> actualCreateApplicantResult = applicantService.createApplicant(requestDTO);
    verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<Applicant>>any());
    assertNull(actualCreateApplicantResult.getBody());
    assertEquals(500, actualCreateApplicantResult.getStatusCodeValue());
    assertTrue(actualCreateApplicantResult.getHeaders().isEmpty());
  }

  /**
   * Method under test: {@link ApplicantService#createApplicant(ApplicantRequest)}
   */
  @Test
  void testCreateApplicant2() {
    when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<Object>>any())).thenThrow(new RuntimeException("foo"));

    ApplicantRequest requestDTO = new ApplicantRequest();
    requestDTO.setApplicationStatus(ApplicationStatus.APPROVED);
    requestDTO.setCertificates("Certificates");
    requestDTO.setCompanyUsername("janedoe");
    requestDTO.setEducationsAndFields("Educations And Fields");
    requestDTO.setFullName("Dr Jane Doe");
    requestDTO.setId(1L);
    requestDTO.setLanguages("en");
    requestDTO.setMail("Mail");
    requestDTO.setPhoneNumber("6625550144");
    requestDTO.setPlaceOfResidence("Place Of Residence");
    requestDTO.setScore(10.0d);
    requestDTO.setSkills("Skills");
    requestDTO.setVacancyName("Vacancy Name");
    requestDTO.setWorkExperienceInfo("Work Experience Info");
    ResponseEntity<ApplicantResponse> actualCreateApplicantResult = applicantService.createApplicant(requestDTO);
    verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<Applicant>>any());
    assertNull(actualCreateApplicantResult.getBody());
    assertEquals(500, actualCreateApplicantResult.getStatusCodeValue());
    assertTrue(actualCreateApplicantResult.getHeaders().isEmpty());
  }

  /**
   * Method under test: {@link ApplicantService#createApplicant(ApplicantRequest)}
   */
  @Test
  void testCreateApplicant3() {
    Applicant applicant = new Applicant();
    applicant.setApplicationStatus(ApplicationStatus.APPROVED);
    applicant.setCertificates("Certificates");
    applicant.setCompanyUsername("janedoe");
    applicant.setEducationsAndFields("Educations And Fields");
    applicant.setFullName("Dr Jane Doe");
    applicant.setId(1L);
    applicant.setLanguages("en");
    applicant.setMail("Mail");
    applicant.setPhoneNumber("6625550144");
    applicant.setPlaceOfResidence("Place Of Residence");
    applicant.setScore(10.0d);
    applicant.setSkills("Skills");
    applicant.setVacancyName("Vacancy Name");
    applicant.setWorkExperienceInfo("Work Experience Info");
    when(applicantRepository.save(Mockito.<Applicant>any())).thenReturn(applicant);

    Applicant applicant2 = new Applicant();
    applicant2.setApplicationStatus(ApplicationStatus.APPROVED);
    applicant2.setCertificates("Certificates");
    applicant2.setCompanyUsername("janedoe");
    applicant2.setEducationsAndFields("Educations And Fields");
    applicant2.setFullName("Dr Jane Doe");
    applicant2.setId(1L);
    applicant2.setLanguages("en");
    applicant2.setMail("Mail");
    applicant2.setPhoneNumber("6625550144");
    applicant2.setPlaceOfResidence("Place Of Residence");
    applicant2.setScore(10.0d);
    applicant2.setSkills("Skills");
    applicant2.setVacancyName("Vacancy Name");
    applicant2.setWorkExperienceInfo("Work Experience Info");
    when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<Object>>any())).thenReturn(applicant2);

    ApplicantRequest requestDTO = new ApplicantRequest();
    requestDTO.setApplicationStatus(ApplicationStatus.APPROVED);
    requestDTO.setCertificates("Certificates");
    requestDTO.setCompanyUsername("janedoe");
    requestDTO.setEducationsAndFields("Educations And Fields");
    requestDTO.setFullName("Dr Jane Doe");
    requestDTO.setId(1L);
    requestDTO.setLanguages("en");
    requestDTO.setMail("Mail");
    requestDTO.setPhoneNumber("6625550144");
    requestDTO.setPlaceOfResidence("Place Of Residence");
    requestDTO.setScore(10.0d);
    requestDTO.setSkills("Skills");
    requestDTO.setVacancyName("Vacancy Name");
    requestDTO.setWorkExperienceInfo("Work Experience Info");
    ResponseEntity<ApplicantResponse> actualCreateApplicantResult = applicantService.createApplicant(requestDTO);
    verify(modelMapper, atLeast(1)).map(Mockito.<Object>any(), Mockito.<Class<Object>>any());
    verify(applicantRepository).save(Mockito.<Applicant>any());
    assertNull(actualCreateApplicantResult.getBody());
    assertEquals(500, actualCreateApplicantResult.getStatusCodeValue());
    assertTrue(actualCreateApplicantResult.getHeaders().isEmpty());
  }

  /**
   * Method under test: {@link ApplicantService#createApplicant(ApplicantRequest)}
   */
  @Test
  void testCreateApplicant4() throws UnsupportedEncodingException {
    Applicant applicant = new Applicant();
    applicant.setApplicationStatus(ApplicationStatus.APPROVED);
    applicant.setCertificates("Certificates");
    applicant.setCompanyUsername("janedoe");
    applicant.setEducationsAndFields("Educations And Fields");
    applicant.setFullName("Dr Jane Doe");
    applicant.setId(1L);
    applicant.setLanguages("en");
    applicant.setMail("Mail");
    applicant.setPhoneNumber("6625550144");
    applicant.setPlaceOfResidence("Place Of Residence");
    applicant.setScore(10.0d);
    applicant.setSkills("Skills");
    applicant.setVacancyName("Vacancy Name");
    applicant.setWorkExperienceInfo("Work Experience Info");
    when(applicantRepository.save(Mockito.<Applicant>any())).thenReturn(applicant);

    ApplicantCV applicantCV = new ApplicantCV();
    applicantCV.setApplicantId(1L);
    applicantCV.setContentType("text/plain");
    applicantCV.setData("AXAXAXAX".getBytes("UTF-8"));
    applicantCV.setFileName("foo.txt");
    applicantCV.setId(1L);
    Optional<ApplicantCV> ofResult = Optional.of(applicantCV);
    when(applicantCVRepository.findApplicantCVByApplicantId(Mockito.<Long>any())).thenReturn(ofResult);
    when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<Object>>any())).thenReturn(null);

    ApplicantRequest requestDTO = new ApplicantRequest();
    requestDTO.setApplicationStatus(ApplicationStatus.APPROVED);
    requestDTO.setCertificates("Certificates");
    requestDTO.setCompanyUsername("janedoe");
    requestDTO.setEducationsAndFields("Educations And Fields");
    requestDTO.setFullName("Dr Jane Doe");
    requestDTO.setId(1L);
    requestDTO.setLanguages("en");
    requestDTO.setMail("Mail");
    requestDTO.setPhoneNumber("6625550144");
    requestDTO.setPlaceOfResidence("Place Of Residence");
    requestDTO.setScore(10.0d);
    requestDTO.setSkills("Skills");
    requestDTO.setVacancyName("Vacancy Name");
    requestDTO.setWorkExperienceInfo("Work Experience Info");
    ResponseEntity<ApplicantResponse> actualCreateApplicantResult = applicantService.createApplicant(requestDTO);
    verify(applicantCVRepository).findApplicantCVByApplicantId(Mockito.<Long>any());
    verify(modelMapper, atLeast(1)).map(Mockito.<Object>any(), Mockito.<Class<Object>>any());
    verify(applicantRepository).save(Mockito.<Applicant>any());
    assertNull(actualCreateApplicantResult.getBody());
    assertEquals(500, actualCreateApplicantResult.getStatusCodeValue());
    assertTrue(actualCreateApplicantResult.getHeaders().isEmpty());
  }

  /**
   * Method under test: {@link ApplicantService#getAllApplicants(String)}
   */
  @Test
  void testGetAllApplicants() {
    ArrayList<Applicant> applicantList = new ArrayList<>();
    when(applicantRepository.findApplicantsByCompanyUsername(Mockito.<String>any())).thenReturn(applicantList);
    ResponseEntity<List<ApplicantResponse>> actualAllApplicants = applicantService.getAllApplicants("janedoe");
    verify(applicantRepository).findApplicantsByCompanyUsername(Mockito.<String>any());
    assertEquals(200, actualAllApplicants.getStatusCodeValue());
    assertTrue(actualAllApplicants.getHeaders().isEmpty());
    assertEquals(applicantList, actualAllApplicants.getBody());
  }

  /**
   * Method under test: {@link ApplicantService#getAllApplicants(String)}
   */
  @Test
  void testGetAllApplicants2() throws UnsupportedEncodingException {
    Applicant applicant = new Applicant();
    applicant.setApplicationStatus(ApplicationStatus.APPROVED);
    applicant.setCertificates("Certificates");
    applicant.setCompanyUsername("janedoe");
    applicant.setEducationsAndFields("Educations And Fields");
    applicant.setFullName("Dr Jane Doe");
    applicant.setId(1L);
    applicant.setLanguages("en");
    applicant.setMail("Mail");
    applicant.setPhoneNumber("6625550144");
    applicant.setPlaceOfResidence("Place Of Residence");
    applicant.setScore(10.0d);
    applicant.setSkills("Skills");
    applicant.setVacancyName("Vacancy Name");
    applicant.setWorkExperienceInfo("Work Experience Info");

    ArrayList<Applicant> applicantList = new ArrayList<>();
    applicantList.add(applicant);
    when(applicantRepository.findApplicantsByCompanyUsername(Mockito.<String>any())).thenReturn(applicantList);

    ApplicantCV applicantCV = new ApplicantCV();
    applicantCV.setApplicantId(1L);
    applicantCV.setContentType("text/plain");
    applicantCV.setData("AXAXAXAX".getBytes("UTF-8"));
    applicantCV.setFileName("foo.txt");
    applicantCV.setId(1L);
    Optional<ApplicantCV> ofResult = Optional.of(applicantCV);
    when(applicantCVRepository.findApplicantCVByApplicantId(Mockito.<Long>any())).thenReturn(ofResult);

    ApplicantResponse applicantResponse = new ApplicantResponse();
    applicantResponse.setApplicantCVId(1L);
    applicantResponse.setApplicationStatus(ApplicationStatus.APPROVED);
    applicantResponse.setCertificates("Certificates");
    applicantResponse.setCompanyUsername("janedoe");
    applicantResponse.setEducationsAndFields("Educations And Fields");
    applicantResponse.setFullName("Dr Jane Doe");
    applicantResponse.setId(1L);
    applicantResponse.setLanguages("en");
    applicantResponse.setMail("Mail");
    applicantResponse.setPhoneNumber("6625550144");
    applicantResponse.setPlaceOfResidence("Place Of Residence");
    applicantResponse.setScore(10.0d);
    applicantResponse.setSkills("Skills");
    applicantResponse.setVacancyName("Vacancy Name");
    applicantResponse.setWorkExperienceInfo("Work Experience Info");
    when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<ApplicantResponse>>any())).thenReturn(applicantResponse);
    ResponseEntity<List<ApplicantResponse>> actualAllApplicants = applicantService.getAllApplicants("janedoe");
    verify(applicantCVRepository).findApplicantCVByApplicantId(Mockito.<Long>any());
    verify(applicantRepository).findApplicantsByCompanyUsername(Mockito.<String>any());
    verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<ApplicantResponse>>any());
    assertEquals(200, actualAllApplicants.getStatusCodeValue());
    assertTrue(actualAllApplicants.hasBody());
    assertTrue(actualAllApplicants.getHeaders().isEmpty());
  }

  /**
   * Method under test: {@link ApplicantService#getAllApplicants(String)}
   */
  @Test
  void testGetAllApplicants3() {
    Applicant applicant = new Applicant();
    applicant.setApplicationStatus(ApplicationStatus.APPROVED);
    applicant.setCertificates("Certificates");
    applicant.setCompanyUsername("janedoe");
    applicant.setEducationsAndFields("Educations And Fields");
    applicant.setFullName("Dr Jane Doe");
    applicant.setId(1L);
    applicant.setLanguages("en");
    applicant.setMail("Mail");
    applicant.setPhoneNumber("6625550144");
    applicant.setPlaceOfResidence("Place Of Residence");
    applicant.setScore(10.0d);
    applicant.setSkills("Skills");
    applicant.setVacancyName("Vacancy Name");
    applicant.setWorkExperienceInfo("Work Experience Info");

    ArrayList<Applicant> applicantList = new ArrayList<>();
    applicantList.add(applicant);
    when(applicantRepository.findApplicantsByCompanyUsername(Mockito.<String>any())).thenReturn(applicantList);
    when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<ApplicantResponse>>any()))
            .thenThrow(new RuntimeException("foo"));
    ResponseEntity<List<ApplicantResponse>> actualAllApplicants = applicantService.getAllApplicants("janedoe");
    verify(applicantRepository).findApplicantsByCompanyUsername(Mockito.<String>any());
    verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<ApplicantResponse>>any());
    assertNull(actualAllApplicants.getBody());
    assertEquals(500, actualAllApplicants.getStatusCodeValue());
    assertTrue(actualAllApplicants.getHeaders().isEmpty());
  }

  /**
   * Method under test: {@link ApplicantService#getAllApplicants(String)}
   */
  @Test
  void testGetAllApplicants4() throws UnsupportedEncodingException {
    Applicant applicant = new Applicant();
    applicant.setApplicationStatus(ApplicationStatus.APPROVED);
    applicant.setCertificates("Certificates");
    applicant.setCompanyUsername("janedoe");
    applicant.setEducationsAndFields("Educations And Fields");
    applicant.setFullName("Dr Jane Doe");
    applicant.setId(1L);
    applicant.setLanguages("en");
    applicant.setMail("Mail");
    applicant.setPhoneNumber("6625550144");
    applicant.setPlaceOfResidence("Place Of Residence");
    applicant.setScore(10.0d);
    applicant.setSkills("Skills");
    applicant.setVacancyName("Vacancy Name");
    applicant.setWorkExperienceInfo("Work Experience Info");

    Applicant applicant2 = new Applicant();
    applicant2.setApplicationStatus(ApplicationStatus.REJECTED);
    applicant2.setCertificates("(2)");
    applicant2.setCompanyUsername("(1)");
    applicant2.setEducationsAndFields("(2)");
    applicant2.setFullName("Mr John Smith");
    applicant2.setId(2L);
    applicant2.setLanguages("eng");
    applicant2.setMail("(2)");
    applicant2.setPhoneNumber("8605550118");
    applicant2.setPlaceOfResidence("(2)");
    applicant2.setScore(0.5d);
    applicant2.setSkills("(2)");
    applicant2.setVacancyName("(2)");
    applicant2.setWorkExperienceInfo("(2)");

    ArrayList<Applicant> applicantList = new ArrayList<>();
    applicantList.add(applicant2);
    applicantList.add(applicant);
    when(applicantRepository.findApplicantsByCompanyUsername(Mockito.<String>any())).thenReturn(applicantList);

    ApplicantCV applicantCV = new ApplicantCV();
    applicantCV.setApplicantId(1L);
    applicantCV.setContentType("text/plain");
    applicantCV.setData("AXAXAXAX".getBytes("UTF-8"));
    applicantCV.setFileName("foo.txt");
    applicantCV.setId(1L);
    Optional<ApplicantCV> ofResult = Optional.of(applicantCV);
    when(applicantCVRepository.findApplicantCVByApplicantId(Mockito.<Long>any())).thenReturn(ofResult);

    ApplicantResponse applicantResponse = new ApplicantResponse();
    applicantResponse.setApplicantCVId(1L);
    applicantResponse.setApplicationStatus(ApplicationStatus.APPROVED);
    applicantResponse.setCertificates("Certificates");
    applicantResponse.setCompanyUsername("janedoe");
    applicantResponse.setEducationsAndFields("Educations And Fields");
    applicantResponse.setFullName("Dr Jane Doe");
    applicantResponse.setId(1L);
    applicantResponse.setLanguages("en");
    applicantResponse.setMail("Mail");
    applicantResponse.setPhoneNumber("6625550144");
    applicantResponse.setPlaceOfResidence("Place Of Residence");
    applicantResponse.setScore(10.0d);
    applicantResponse.setSkills("Skills");
    applicantResponse.setVacancyName("Vacancy Name");
    applicantResponse.setWorkExperienceInfo("Work Experience Info");
    when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<ApplicantResponse>>any())).thenReturn(applicantResponse);
    ResponseEntity<List<ApplicantResponse>> actualAllApplicants = applicantService.getAllApplicants("janedoe");
    verify(applicantCVRepository, atLeast(1)).findApplicantCVByApplicantId(Mockito.<Long>any());
    verify(applicantRepository).findApplicantsByCompanyUsername(Mockito.<String>any());
    verify(modelMapper, atLeast(1)).map(Mockito.<Object>any(), Mockito.<Class<ApplicantResponse>>any());
    assertEquals(200, actualAllApplicants.getStatusCodeValue());
    assertTrue(actualAllApplicants.hasBody());
    assertTrue(actualAllApplicants.getHeaders().isEmpty());
  }

  /**
   * Method under test: {@link ApplicantService#getAllApplicants(String)}
   */
  @Test
  void testGetAllApplicants5() throws UnsupportedEncodingException {
    Applicant applicant = new Applicant();
    applicant.setApplicationStatus(ApplicationStatus.APPROVED);
    applicant.setCertificates("Certificates");
    applicant.setCompanyUsername("janedoe");
    applicant.setEducationsAndFields("Educations And Fields");
    applicant.setFullName("Dr Jane Doe");
    applicant.setId(1L);
    applicant.setLanguages("(1)");
    applicant.setMail("Mail");
    applicant.setPhoneNumber("6625550144");
    applicant.setPlaceOfResidence("Place Of Residence");
    applicant.setScore(10.0d);
    applicant.setSkills("Skills");
    applicant.setVacancyName("Vacancy Name");
    applicant.setWorkExperienceInfo("Work Experience Info");

    ArrayList<Applicant> applicantList = new ArrayList<>();
    applicantList.add(applicant);
    when(applicantRepository.findApplicantsByCompanyUsername(Mockito.<String>any())).thenReturn(applicantList);

    ApplicantCV applicantCV = new ApplicantCV();
    applicantCV.setApplicantId(1L);
    applicantCV.setContentType("text/plain");
    applicantCV.setData("AXAXAXAX".getBytes("UTF-8"));
    applicantCV.setFileName("foo.txt");
    applicantCV.setId(1L);
    Optional<ApplicantCV> ofResult = Optional.of(applicantCV);
    when(applicantCVRepository.findApplicantCVByApplicantId(Mockito.<Long>any())).thenReturn(ofResult);

    ApplicantResponse applicantResponse = new ApplicantResponse();
    applicantResponse.setApplicantCVId(1L);
    applicantResponse.setApplicationStatus(ApplicationStatus.APPROVED);
    applicantResponse.setCertificates("Certificates");
    applicantResponse.setCompanyUsername("janedoe");
    applicantResponse.setEducationsAndFields("Educations And Fields");
    applicantResponse.setFullName("Dr Jane Doe");
    applicantResponse.setId(1L);
    applicantResponse.setLanguages("en");
    applicantResponse.setMail("Mail");
    applicantResponse.setPhoneNumber("6625550144");
    applicantResponse.setPlaceOfResidence("Place Of Residence");
    applicantResponse.setScore(10.0d);
    applicantResponse.setSkills("Skills");
    applicantResponse.setVacancyName("Vacancy Name");
    applicantResponse.setWorkExperienceInfo("Work Experience Info");
    when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<ApplicantResponse>>any())).thenReturn(applicantResponse);
    ResponseEntity<List<ApplicantResponse>> actualAllApplicants = applicantService.getAllApplicants("janedoe");
    verify(applicantCVRepository).findApplicantCVByApplicantId(Mockito.<Long>any());
    verify(applicantRepository).findApplicantsByCompanyUsername(Mockito.<String>any());
    verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<ApplicantResponse>>any());
    assertEquals(200, actualAllApplicants.getStatusCodeValue());
    assertTrue(actualAllApplicants.hasBody());
    assertTrue(actualAllApplicants.getHeaders().isEmpty());
  }

  /**
   * Method under test: {@link ApplicantService#getAllApplicants(String)}
   */
  @Test
  void testGetAllApplicants6() throws UnsupportedEncodingException {
    Applicant applicant = new Applicant();
    applicant.setApplicationStatus(ApplicationStatus.APPROVED);
    applicant.setCertificates("Certificates");
    applicant.setCompanyUsername("janedoe");
    applicant.setEducationsAndFields("Educations And Fields");
    applicant.setFullName("Dr Jane Doe");
    applicant.setId(1L);
    applicant.setLanguages("(2)");
    applicant.setMail("Mail");
    applicant.setPhoneNumber("6625550144");
    applicant.setPlaceOfResidence("Place Of Residence");
    applicant.setScore(10.0d);
    applicant.setSkills("Skills");
    applicant.setVacancyName("Vacancy Name");
    applicant.setWorkExperienceInfo("Work Experience Info");

    ArrayList<Applicant> applicantList = new ArrayList<>();
    applicantList.add(applicant);
    when(applicantRepository.findApplicantsByCompanyUsername(Mockito.<String>any())).thenReturn(applicantList);

    ApplicantCV applicantCV = new ApplicantCV();
    applicantCV.setApplicantId(1L);
    applicantCV.setContentType("text/plain");
    applicantCV.setData("AXAXAXAX".getBytes("UTF-8"));
    applicantCV.setFileName("foo.txt");
    applicantCV.setId(1L);
    Optional<ApplicantCV> ofResult = Optional.of(applicantCV);
    when(applicantCVRepository.findApplicantCVByApplicantId(Mockito.<Long>any())).thenReturn(ofResult);

    ApplicantResponse applicantResponse = new ApplicantResponse();
    applicantResponse.setApplicantCVId(1L);
    applicantResponse.setApplicationStatus(ApplicationStatus.APPROVED);
    applicantResponse.setCertificates("Certificates");
    applicantResponse.setCompanyUsername("janedoe");
    applicantResponse.setEducationsAndFields("Educations And Fields");
    applicantResponse.setFullName("Dr Jane Doe");
    applicantResponse.setId(1L);
    applicantResponse.setLanguages("en");
    applicantResponse.setMail("Mail");
    applicantResponse.setPhoneNumber("6625550144");
    applicantResponse.setPlaceOfResidence("Place Of Residence");
    applicantResponse.setScore(10.0d);
    applicantResponse.setSkills("Skills");
    applicantResponse.setVacancyName("Vacancy Name");
    applicantResponse.setWorkExperienceInfo("Work Experience Info");
    when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<ApplicantResponse>>any())).thenReturn(applicantResponse);
    ResponseEntity<List<ApplicantResponse>> actualAllApplicants = applicantService.getAllApplicants("janedoe");
    verify(applicantCVRepository).findApplicantCVByApplicantId(Mockito.<Long>any());
    verify(applicantRepository).findApplicantsByCompanyUsername(Mockito.<String>any());
    verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<ApplicantResponse>>any());
    assertEquals(200, actualAllApplicants.getStatusCodeValue());
    assertTrue(actualAllApplicants.hasBody());
    assertTrue(actualAllApplicants.getHeaders().isEmpty());
  }

  /**
   * Method under test: {@link ApplicantService#getAllApplicants(String)}
   */
  @Test
  void testGetAllApplicants7() throws UnsupportedEncodingException {
    Applicant applicant = new Applicant();
    applicant.setApplicationStatus(ApplicationStatus.APPROVED);
    applicant.setCertificates("Certificates");
    applicant.setCompanyUsername("janedoe");
    applicant.setEducationsAndFields("Educations And Fields");
    applicant.setFullName("Dr Jane Doe");
    applicant.setId(1L);
    applicant.setLanguages("(3)");
    applicant.setMail("Mail");
    applicant.setPhoneNumber("6625550144");
    applicant.setPlaceOfResidence("Place Of Residence");
    applicant.setScore(10.0d);
    applicant.setSkills("Skills");
    applicant.setVacancyName("Vacancy Name");
    applicant.setWorkExperienceInfo("Work Experience Info");

    ArrayList<Applicant> applicantList = new ArrayList<>();
    applicantList.add(applicant);
    when(applicantRepository.findApplicantsByCompanyUsername(Mockito.<String>any())).thenReturn(applicantList);

    ApplicantCV applicantCV = new ApplicantCV();
    applicantCV.setApplicantId(1L);
    applicantCV.setContentType("text/plain");
    applicantCV.setData("AXAXAXAX".getBytes("UTF-8"));
    applicantCV.setFileName("foo.txt");
    applicantCV.setId(1L);
    Optional<ApplicantCV> ofResult = Optional.of(applicantCV);
    when(applicantCVRepository.findApplicantCVByApplicantId(Mockito.<Long>any())).thenReturn(ofResult);

    ApplicantResponse applicantResponse = new ApplicantResponse();
    applicantResponse.setApplicantCVId(1L);
    applicantResponse.setApplicationStatus(ApplicationStatus.APPROVED);
    applicantResponse.setCertificates("Certificates");
    applicantResponse.setCompanyUsername("janedoe");
    applicantResponse.setEducationsAndFields("Educations And Fields");
    applicantResponse.setFullName("Dr Jane Doe");
    applicantResponse.setId(1L);
    applicantResponse.setLanguages("en");
    applicantResponse.setMail("Mail");
    applicantResponse.setPhoneNumber("6625550144");
    applicantResponse.setPlaceOfResidence("Place Of Residence");
    applicantResponse.setScore(10.0d);
    applicantResponse.setSkills("Skills");
    applicantResponse.setVacancyName("Vacancy Name");
    applicantResponse.setWorkExperienceInfo("Work Experience Info");
    when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<ApplicantResponse>>any())).thenReturn(applicantResponse);
    ResponseEntity<List<ApplicantResponse>> actualAllApplicants = applicantService.getAllApplicants("janedoe");
    verify(applicantCVRepository).findApplicantCVByApplicantId(Mockito.<Long>any());
    verify(applicantRepository).findApplicantsByCompanyUsername(Mockito.<String>any());
    verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<ApplicantResponse>>any());
    assertEquals(200, actualAllApplicants.getStatusCodeValue());
    assertTrue(actualAllApplicants.hasBody());
    assertTrue(actualAllApplicants.getHeaders().isEmpty());
  }

  /**
   * Method under test: {@link ApplicantService#getApplicantById(Long)}
   */
  @Test
  void testGetApplicantById() throws UnsupportedEncodingException {
    Applicant applicant = new Applicant();
    applicant.setApplicationStatus(ApplicationStatus.APPROVED);
    applicant.setCertificates("Certificates");
    applicant.setCompanyUsername("janedoe");
    applicant.setEducationsAndFields("Educations And Fields");
    applicant.setFullName("Dr Jane Doe");
    applicant.setId(1L);
    applicant.setLanguages("en");
    applicant.setMail("Mail");
    applicant.setPhoneNumber("6625550144");
    applicant.setPlaceOfResidence("Place Of Residence");
    applicant.setScore(10.0d);
    applicant.setSkills("Skills");
    applicant.setVacancyName("Vacancy Name");
    applicant.setWorkExperienceInfo("Work Experience Info");
    Optional<Applicant> ofResult = Optional.of(applicant);
    when(applicantRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

    ApplicantCV applicantCV = new ApplicantCV();
    applicantCV.setApplicantId(1L);
    applicantCV.setContentType("text/plain");
    applicantCV.setData("AXAXAXAX".getBytes("UTF-8"));
    applicantCV.setFileName("foo.txt");
    applicantCV.setId(1L);
    Optional<ApplicantCV> ofResult2 = Optional.of(applicantCV);
    when(applicantCVRepository.findApplicantCVByApplicantId(Mockito.<Long>any())).thenReturn(ofResult2);

    ApplicantResponse applicantResponse = new ApplicantResponse();
    applicantResponse.setApplicantCVId(1L);
    applicantResponse.setApplicationStatus(ApplicationStatus.APPROVED);
    applicantResponse.setCertificates("Certificates");
    applicantResponse.setCompanyUsername("janedoe");
    applicantResponse.setEducationsAndFields("Educations And Fields");
    applicantResponse.setFullName("Dr Jane Doe");
    applicantResponse.setId(1L);
    applicantResponse.setLanguages("en");
    applicantResponse.setMail("Mail");
    applicantResponse.setPhoneNumber("6625550144");
    applicantResponse.setPlaceOfResidence("Place Of Residence");
    applicantResponse.setScore(10.0d);
    applicantResponse.setSkills("Skills");
    applicantResponse.setVacancyName("Vacancy Name");
    applicantResponse.setWorkExperienceInfo("Work Experience Info");
    when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<ApplicantResponse>>any())).thenReturn(applicantResponse);
    ResponseEntity<ApplicantResponse> actualApplicantById = applicantService.getApplicantById(1L);
    verify(applicantCVRepository).findApplicantCVByApplicantId(Mockito.<Long>any());
    verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<ApplicantResponse>>any());
    verify(applicantRepository).findById(Mockito.<Long>any());
    ApplicantResponse body = actualApplicantById.getBody();
    assertEquals("en", body.getLanguages());
    assertEquals(1L, body.getApplicantCVId().longValue());
    assertEquals(200, actualApplicantById.getStatusCodeValue());
    assertTrue(actualApplicantById.hasBody());
    assertTrue(actualApplicantById.getHeaders().isEmpty());
  }

  /**
   * Method under test: {@link ApplicantService#getApplicantById(Long)}
   */
  @Test
  void testGetApplicantById2() {
    Applicant applicant = new Applicant();
    applicant.setApplicationStatus(ApplicationStatus.APPROVED);
    applicant.setCertificates("Certificates");
    applicant.setCompanyUsername("janedoe");
    applicant.setEducationsAndFields("Educations And Fields");
    applicant.setFullName("Dr Jane Doe");
    applicant.setId(1L);
    applicant.setLanguages("en");
    applicant.setMail("Mail");
    applicant.setPhoneNumber("6625550144");
    applicant.setPlaceOfResidence("Place Of Residence");
    applicant.setScore(10.0d);
    applicant.setSkills("Skills");
    applicant.setVacancyName("Vacancy Name");
    applicant.setWorkExperienceInfo("Work Experience Info");
    Optional<Applicant> ofResult = Optional.of(applicant);
    when(applicantRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
    when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<ApplicantResponse>>any()))
            .thenThrow(new RuntimeException("foo"));
    ResponseEntity<ApplicantResponse> actualApplicantById = applicantService.getApplicantById(1L);
    verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<ApplicantResponse>>any());
    verify(applicantRepository).findById(Mockito.<Long>any());
    assertNull(actualApplicantById.getBody());
    assertEquals(500, actualApplicantById.getStatusCodeValue());
    assertTrue(actualApplicantById.getHeaders().isEmpty());
  }

  /**
   * Method under test: {@link ApplicantService#getApplicantById(Long)}
   */
  @Test
  void testGetApplicantById3() throws UnsupportedEncodingException {
    Applicant applicant = new Applicant();
    applicant.setApplicationStatus(ApplicationStatus.APPROVED);
    applicant.setCertificates("Certificates");
    applicant.setCompanyUsername("janedoe");
    applicant.setEducationsAndFields("Educations And Fields");
    applicant.setFullName("Dr Jane Doe");
    applicant.setId(1L);
    applicant.setLanguages("(1)");
    applicant.setMail("Mail");
    applicant.setPhoneNumber("6625550144");
    applicant.setPlaceOfResidence("Place Of Residence");
    applicant.setScore(10.0d);
    applicant.setSkills("Skills");
    applicant.setVacancyName("Vacancy Name");
    applicant.setWorkExperienceInfo("Work Experience Info");
    Optional<Applicant> ofResult = Optional.of(applicant);
    when(applicantRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

    ApplicantCV applicantCV = new ApplicantCV();
    applicantCV.setApplicantId(1L);
    applicantCV.setContentType("text/plain");
    applicantCV.setData("AXAXAXAX".getBytes("UTF-8"));
    applicantCV.setFileName("foo.txt");
    applicantCV.setId(1L);
    Optional<ApplicantCV> ofResult2 = Optional.of(applicantCV);
    when(applicantCVRepository.findApplicantCVByApplicantId(Mockito.<Long>any())).thenReturn(ofResult2);

    ApplicantResponse applicantResponse = new ApplicantResponse();
    applicantResponse.setApplicantCVId(1L);
    applicantResponse.setApplicationStatus(ApplicationStatus.APPROVED);
    applicantResponse.setCertificates("Certificates");
    applicantResponse.setCompanyUsername("janedoe");
    applicantResponse.setEducationsAndFields("Educations And Fields");
    applicantResponse.setFullName("Dr Jane Doe");
    applicantResponse.setId(1L);
    applicantResponse.setLanguages("en");
    applicantResponse.setMail("Mail");
    applicantResponse.setPhoneNumber("6625550144");
    applicantResponse.setPlaceOfResidence("Place Of Residence");
    applicantResponse.setScore(10.0d);
    applicantResponse.setSkills("Skills");
    applicantResponse.setVacancyName("Vacancy Name");
    applicantResponse.setWorkExperienceInfo("Work Experience Info");
    when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<ApplicantResponse>>any())).thenReturn(applicantResponse);
    ResponseEntity<ApplicantResponse> actualApplicantById = applicantService.getApplicantById(1L);
    verify(applicantCVRepository).findApplicantCVByApplicantId(Mockito.<Long>any());
    verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<ApplicantResponse>>any());
    verify(applicantRepository).findById(Mockito.<Long>any());
    ApplicantResponse body = actualApplicantById.getBody();
    assertEquals("(Advanced)", body.getLanguages());
    assertEquals(1L, body.getApplicantCVId().longValue());
    assertEquals(200, actualApplicantById.getStatusCodeValue());
    assertTrue(actualApplicantById.hasBody());
    assertTrue(actualApplicantById.getHeaders().isEmpty());
  }

  /**
   * Method under test: {@link ApplicantService#getApplicantById(Long)}
   */
  @Test
  void testGetApplicantById4() throws UnsupportedEncodingException {
    Applicant applicant = new Applicant();
    applicant.setApplicationStatus(ApplicationStatus.APPROVED);
    applicant.setCertificates("Certificates");
    applicant.setCompanyUsername("janedoe");
    applicant.setEducationsAndFields("Educations And Fields");
    applicant.setFullName("Dr Jane Doe");
    applicant.setId(1L);
    applicant.setLanguages("(2)");
    applicant.setMail("Mail");
    applicant.setPhoneNumber("6625550144");
    applicant.setPlaceOfResidence("Place Of Residence");
    applicant.setScore(10.0d);
    applicant.setSkills("Skills");
    applicant.setVacancyName("Vacancy Name");
    applicant.setWorkExperienceInfo("Work Experience Info");
    Optional<Applicant> ofResult = Optional.of(applicant);
    when(applicantRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

    ApplicantCV applicantCV = new ApplicantCV();
    applicantCV.setApplicantId(1L);
    applicantCV.setContentType("text/plain");
    applicantCV.setData("AXAXAXAX".getBytes("UTF-8"));
    applicantCV.setFileName("foo.txt");
    applicantCV.setId(1L);
    Optional<ApplicantCV> ofResult2 = Optional.of(applicantCV);
    when(applicantCVRepository.findApplicantCVByApplicantId(Mockito.<Long>any())).thenReturn(ofResult2);

    ApplicantResponse applicantResponse = new ApplicantResponse();
    applicantResponse.setApplicantCVId(1L);
    applicantResponse.setApplicationStatus(ApplicationStatus.APPROVED);
    applicantResponse.setCertificates("Certificates");
    applicantResponse.setCompanyUsername("janedoe");
    applicantResponse.setEducationsAndFields("Educations And Fields");
    applicantResponse.setFullName("Dr Jane Doe");
    applicantResponse.setId(1L);
    applicantResponse.setLanguages("en");
    applicantResponse.setMail("Mail");
    applicantResponse.setPhoneNumber("6625550144");
    applicantResponse.setPlaceOfResidence("Place Of Residence");
    applicantResponse.setScore(10.0d);
    applicantResponse.setSkills("Skills");
    applicantResponse.setVacancyName("Vacancy Name");
    applicantResponse.setWorkExperienceInfo("Work Experience Info");
    when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<ApplicantResponse>>any())).thenReturn(applicantResponse);
    ResponseEntity<ApplicantResponse> actualApplicantById = applicantService.getApplicantById(1L);
    verify(applicantCVRepository).findApplicantCVByApplicantId(Mockito.<Long>any());
    verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<ApplicantResponse>>any());
    verify(applicantRepository).findById(Mockito.<Long>any());
    ApplicantResponse body = actualApplicantById.getBody();
    assertEquals("(Intermediate)", body.getLanguages());
    assertEquals(1L, body.getApplicantCVId().longValue());
    assertEquals(200, actualApplicantById.getStatusCodeValue());
    assertTrue(actualApplicantById.hasBody());
    assertTrue(actualApplicantById.getHeaders().isEmpty());
  }

  /**
   * Method under test: {@link ApplicantService#getApplicantById(Long)}
   */
  @Test
  void testGetApplicantById5() throws UnsupportedEncodingException {
    Applicant applicant = new Applicant();
    applicant.setApplicationStatus(ApplicationStatus.APPROVED);
    applicant.setCertificates("Certificates");
    applicant.setCompanyUsername("janedoe");
    applicant.setEducationsAndFields("Educations And Fields");
    applicant.setFullName("Dr Jane Doe");
    applicant.setId(1L);
    applicant.setLanguages("(3)");
    applicant.setMail("Mail");
    applicant.setPhoneNumber("6625550144");
    applicant.setPlaceOfResidence("Place Of Residence");
    applicant.setScore(10.0d);
    applicant.setSkills("Skills");
    applicant.setVacancyName("Vacancy Name");
    applicant.setWorkExperienceInfo("Work Experience Info");
    Optional<Applicant> ofResult = Optional.of(applicant);
    when(applicantRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

    ApplicantCV applicantCV = new ApplicantCV();
    applicantCV.setApplicantId(1L);
    applicantCV.setContentType("text/plain");
    applicantCV.setData("AXAXAXAX".getBytes("UTF-8"));
    applicantCV.setFileName("foo.txt");
    applicantCV.setId(1L);
    Optional<ApplicantCV> ofResult2 = Optional.of(applicantCV);
    when(applicantCVRepository.findApplicantCVByApplicantId(Mockito.<Long>any())).thenReturn(ofResult2);

    ApplicantResponse applicantResponse = new ApplicantResponse();
    applicantResponse.setApplicantCVId(1L);
    applicantResponse.setApplicationStatus(ApplicationStatus.APPROVED);
    applicantResponse.setCertificates("Certificates");
    applicantResponse.setCompanyUsername("janedoe");
    applicantResponse.setEducationsAndFields("Educations And Fields");
    applicantResponse.setFullName("Dr Jane Doe");
    applicantResponse.setId(1L);
    applicantResponse.setLanguages("en");
    applicantResponse.setMail("Mail");
    applicantResponse.setPhoneNumber("6625550144");
    applicantResponse.setPlaceOfResidence("Place Of Residence");
    applicantResponse.setScore(10.0d);
    applicantResponse.setSkills("Skills");
    applicantResponse.setVacancyName("Vacancy Name");
    applicantResponse.setWorkExperienceInfo("Work Experience Info");
    when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<ApplicantResponse>>any())).thenReturn(applicantResponse);
    ResponseEntity<ApplicantResponse> actualApplicantById = applicantService.getApplicantById(1L);
    verify(applicantCVRepository).findApplicantCVByApplicantId(Mockito.<Long>any());
    verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<ApplicantResponse>>any());
    verify(applicantRepository).findById(Mockito.<Long>any());
    ApplicantResponse body = actualApplicantById.getBody();
    assertEquals("(Basic)", body.getLanguages());
    assertEquals(1L, body.getApplicantCVId().longValue());
    assertEquals(200, actualApplicantById.getStatusCodeValue());
    assertTrue(actualApplicantById.hasBody());
    assertTrue(actualApplicantById.getHeaders().isEmpty());
  }

  /**
   * Method under test: {@link ApplicantService#getApplicantById(Long)}
   */
  @Test
  void testGetApplicantById6() {
    Optional<Applicant> emptyResult = Optional.empty();
    when(applicantRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);

    ApplicantResponse applicantResponse = new ApplicantResponse();
    applicantResponse.setApplicantCVId(1L);
    applicantResponse.setApplicationStatus(ApplicationStatus.APPROVED);
    applicantResponse.setCertificates("Certificates");
    applicantResponse.setCompanyUsername("janedoe");
    applicantResponse.setEducationsAndFields("Educations And Fields");
    applicantResponse.setFullName("Dr Jane Doe");
    applicantResponse.setId(1L);
    applicantResponse.setLanguages("en");
    applicantResponse.setMail("Mail");
    applicantResponse.setPhoneNumber("6625550144");
    applicantResponse.setPlaceOfResidence("Place Of Residence");
    applicantResponse.setScore(10.0d);
    applicantResponse.setSkills("Skills");
    applicantResponse.setVacancyName("Vacancy Name");
    applicantResponse.setWorkExperienceInfo("Work Experience Info");
    when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<Object>>any())).thenReturn("Map");
    when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<ApplicantResponse>>any())).thenReturn(applicantResponse);
    ResponseEntity<ApplicantResponse> actualApplicantById = applicantService.getApplicantById(1L);
    verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<Object>>any());
    verify(applicantRepository).findById(Mockito.<Long>any());
    assertNull(actualApplicantById.getBody());
    assertEquals(404, actualApplicantById.getStatusCodeValue());
    assertTrue(actualApplicantById.getHeaders().isEmpty());
  }

  /**
   * Method under test:
   * {@link ApplicantService#updateApplicant(Long, ApplicantUpdateRequest)}
   */
  @Test
  void testUpdateApplicant() throws UnsupportedEncodingException {
    Applicant applicant = new Applicant();
    applicant.setApplicationStatus(ApplicationStatus.APPROVED);
    applicant.setCertificates("Certificates");
    applicant.setCompanyUsername("janedoe");
    applicant.setEducationsAndFields("Educations And Fields");
    applicant.setFullName("Dr Jane Doe");
    applicant.setId(1L);
    applicant.setLanguages("en");
    applicant.setMail("Mail");
    applicant.setPhoneNumber("6625550144");
    applicant.setPlaceOfResidence("Place Of Residence");
    applicant.setScore(10.0d);
    applicant.setSkills("Skills");
    applicant.setVacancyName("Vacancy Name");
    applicant.setWorkExperienceInfo("Work Experience Info");
    Optional<Applicant> ofResult = Optional.of(applicant);

    Applicant applicant2 = new Applicant();
    applicant2.setApplicationStatus(ApplicationStatus.APPROVED);
    applicant2.setCertificates("Certificates");
    applicant2.setCompanyUsername("janedoe");
    applicant2.setEducationsAndFields("Educations And Fields");
    applicant2.setFullName("Dr Jane Doe");
    applicant2.setId(1L);
    applicant2.setLanguages("en");
    applicant2.setMail("Mail");
    applicant2.setPhoneNumber("6625550144");
    applicant2.setPlaceOfResidence("Place Of Residence");
    applicant2.setScore(10.0d);
    applicant2.setSkills("Skills");
    applicant2.setVacancyName("Vacancy Name");
    applicant2.setWorkExperienceInfo("Work Experience Info");
    when(applicantRepository.save(Mockito.<Applicant>any())).thenReturn(applicant2);
    when(applicantRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

    ApplicantCV applicantCV = new ApplicantCV();
    applicantCV.setApplicantId(1L);
    applicantCV.setContentType("text/plain");
    applicantCV.setData("AXAXAXAX".getBytes("UTF-8"));
    applicantCV.setFileName("foo.txt");
    applicantCV.setId(1L);
    Optional<ApplicantCV> ofResult2 = Optional.of(applicantCV);
    when(applicantCVRepository.findApplicantCVByApplicantId(Mockito.<Long>any())).thenReturn(ofResult2);

    ApplicantResponse applicantResponse = new ApplicantResponse();
    applicantResponse.setApplicantCVId(1L);
    applicantResponse.setApplicationStatus(ApplicationStatus.APPROVED);
    applicantResponse.setCertificates("Certificates");
    applicantResponse.setCompanyUsername("janedoe");
    applicantResponse.setEducationsAndFields("Educations And Fields");
    applicantResponse.setFullName("Dr Jane Doe");
    applicantResponse.setId(1L);
    applicantResponse.setLanguages("en");
    applicantResponse.setMail("Mail");
    applicantResponse.setPhoneNumber("6625550144");
    applicantResponse.setPlaceOfResidence("Place Of Residence");
    applicantResponse.setScore(10.0d);
    applicantResponse.setSkills("Skills");
    applicantResponse.setVacancyName("Vacancy Name");
    applicantResponse.setWorkExperienceInfo("Work Experience Info");
    when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<ApplicantResponse>>any())).thenReturn(applicantResponse);
    doNothing().when(modelMapper).map(Mockito.<Object>any(), Mockito.<Object>any());

    ApplicantUpdateRequest requestDTO = new ApplicantUpdateRequest();
    requestDTO.setApplicationStatus(ApplicationStatus.APPROVED);
    requestDTO.setCertificates("Certificates");
    requestDTO.setEducationsAndFields("Educations And Fields");
    requestDTO.setFullName("Dr Jane Doe");
    requestDTO.setId(1L);
    requestDTO.setLanguages("en");
    requestDTO.setMail("Mail");
    requestDTO.setPhoneNumber("6625550144");
    requestDTO.setPlaceOfResidence("Place Of Residence");
    requestDTO.setScore(10.0d);
    requestDTO.setSkills("Skills");
    requestDTO.setVacancyName("Vacancy Name");
    requestDTO.setWorkExperienceInfo("Work Experience Info");
    ResponseEntity<ApplicantResponse> actualUpdateApplicantResult = applicantService.updateApplicant(1L, requestDTO);
    verify(applicantCVRepository).findApplicantCVByApplicantId(Mockito.<Long>any());
    verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<ApplicantResponse>>any());
    verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Object>any());
    verify(applicantRepository).findById(Mockito.<Long>any());
    verify(applicantRepository).save(Mockito.<Applicant>any());
    assertEquals(1L, actualUpdateApplicantResult.getBody().getApplicantCVId().longValue());
    assertEquals(200, actualUpdateApplicantResult.getStatusCodeValue());
    assertTrue(actualUpdateApplicantResult.hasBody());
    assertTrue(actualUpdateApplicantResult.getHeaders().isEmpty());
  }

  /**
   * Method under test:
   * {@link ApplicantService#updateApplicant(Long, ApplicantUpdateRequest)}
   */
  @Test
  void testUpdateApplicant2() {
    Applicant applicant = new Applicant();
    applicant.setApplicationStatus(ApplicationStatus.APPROVED);
    applicant.setCertificates("Certificates");
    applicant.setCompanyUsername("janedoe");
    applicant.setEducationsAndFields("Educations And Fields");
    applicant.setFullName("Dr Jane Doe");
    applicant.setId(1L);
    applicant.setLanguages("en");
    applicant.setMail("Mail");
    applicant.setPhoneNumber("6625550144");
    applicant.setPlaceOfResidence("Place Of Residence");
    applicant.setScore(10.0d);
    applicant.setSkills("Skills");
    applicant.setVacancyName("Vacancy Name");
    applicant.setWorkExperienceInfo("Work Experience Info");
    Optional<Applicant> ofResult = Optional.of(applicant);
    when(applicantRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
    when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<Object>>any())).thenReturn("Map");
    when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<ApplicantResponse>>any()))
            .thenThrow(new RuntimeException("(Advanced)"));
    doThrow(new RuntimeException("(Advanced)")).when(modelMapper).map(Mockito.<Object>any(), Mockito.<Object>any());

    ApplicantUpdateRequest requestDTO = new ApplicantUpdateRequest();
    requestDTO.setApplicationStatus(ApplicationStatus.APPROVED);
    requestDTO.setCertificates("Certificates");
    requestDTO.setEducationsAndFields("Educations And Fields");
    requestDTO.setFullName("Dr Jane Doe");
    requestDTO.setId(1L);
    requestDTO.setLanguages("en");
    requestDTO.setMail("Mail");
    requestDTO.setPhoneNumber("6625550144");
    requestDTO.setPlaceOfResidence("Place Of Residence");
    requestDTO.setScore(10.0d);
    requestDTO.setSkills("Skills");
    requestDTO.setVacancyName("Vacancy Name");
    requestDTO.setWorkExperienceInfo("Work Experience Info");
    ResponseEntity<ApplicantResponse> actualUpdateApplicantResult = applicantService.updateApplicant(1L, requestDTO);
    verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<Object>>any());
    verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Object>any());
    verify(applicantRepository).findById(Mockito.<Long>any());
    assertNull(actualUpdateApplicantResult.getBody());
    assertEquals(500, actualUpdateApplicantResult.getStatusCodeValue());
    assertTrue(actualUpdateApplicantResult.getHeaders().isEmpty());
  }

  /**
   * Method under test:
   * {@link ApplicantService#updateApplicant(Long, ApplicantUpdateRequest)}
   */
  @Test
  void testUpdateApplicant3() {
    Optional<Applicant> emptyResult = Optional.empty();
    when(applicantRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);
    when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<Object>>any())).thenReturn("Map");
    when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<ApplicantResponse>>any()))
            .thenThrow(new RuntimeException("(Advanced)"));

    ApplicantUpdateRequest requestDTO = new ApplicantUpdateRequest();
    requestDTO.setApplicationStatus(ApplicationStatus.APPROVED);
    requestDTO.setCertificates("Certificates");
    requestDTO.setEducationsAndFields("Educations And Fields");
    requestDTO.setFullName("Dr Jane Doe");
    requestDTO.setId(1L);
    requestDTO.setLanguages("en");
    requestDTO.setMail("Mail");
    requestDTO.setPhoneNumber("6625550144");
    requestDTO.setPlaceOfResidence("Place Of Residence");
    requestDTO.setScore(10.0d);
    requestDTO.setSkills("Skills");
    requestDTO.setVacancyName("Vacancy Name");
    requestDTO.setWorkExperienceInfo("Work Experience Info");
    ResponseEntity<ApplicantResponse> actualUpdateApplicantResult = applicantService.updateApplicant(1L, requestDTO);
    verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<Object>>any());
    verify(applicantRepository).findById(Mockito.<Long>any());
    assertNull(actualUpdateApplicantResult.getBody());
    assertEquals(404, actualUpdateApplicantResult.getStatusCodeValue());
    assertTrue(actualUpdateApplicantResult.getHeaders().isEmpty());
  }

  @Test
  void testDeleteApplicant2() {
    doNothing().when(applicantRepository).deleteById(Mockito.<Long>any());
    when(applicantRepository.existsById(Mockito.<Long>any())).thenReturn(true);
    doThrow(new RuntimeException("foo")).when(applicantCVRepository).deleteById(Mockito.<Long>any());
    ResponseEntity<Void> actualDeleteApplicantResult = applicantService.deleteApplicant(1L);
    verify(applicantCVRepository).deleteById(Mockito.<Long>any());
    verify(applicantRepository).deleteById(Mockito.<Long>any());
    verify(applicantRepository).existsById(Mockito.<Long>any());
    assertNull(actualDeleteApplicantResult.getBody());
    assertEquals(500, actualDeleteApplicantResult.getStatusCodeValue());
    assertTrue(actualDeleteApplicantResult.getHeaders().isEmpty());
  }

  /**
   * Method under test: {@link ApplicantService#deleteApplicant(Long)}
   */
  @Test
  void testDeleteApplicant3() {
    when(applicantRepository.existsById(Mockito.<Long>any())).thenReturn(false);
    ResponseEntity<Void> actualDeleteApplicantResult = applicantService.deleteApplicant(1L);
    verify(applicantRepository).existsById(Mockito.<Long>any());
    assertNull(actualDeleteApplicantResult.getBody());
    assertEquals(404, actualDeleteApplicantResult.getStatusCodeValue());
    assertTrue(actualDeleteApplicantResult.getHeaders().isEmpty());
  }

  /**
   * Method under test: {@link ApplicantService#deleteApplicant(Long)}
   */
  @Test
  void testDeleteApplicant4() {
    ResponseEntity<Void> actualDeleteApplicantResult = applicantService.deleteApplicant(0L);
    assertNull(actualDeleteApplicantResult.getBody());
    assertEquals(400, actualDeleteApplicantResult.getStatusCodeValue());
    assertTrue(actualDeleteApplicantResult.getHeaders().isEmpty());
  }

  /**
   * Method under test: {@link ApplicantService#deleteApplicant(Long)}
   */
  @Test
  void testDeleteApplicant5() {
    ResponseEntity<Void> actualDeleteApplicantResult = applicantService.deleteApplicant(null);
    assertNull(actualDeleteApplicantResult.getBody());
    assertEquals(400, actualDeleteApplicantResult.getStatusCodeValue());
    assertTrue(actualDeleteApplicantResult.getHeaders().isEmpty());
  }
}
