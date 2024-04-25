package com.example.cvfiltersystem.service;

import com.example.cvfiltersystem.enums.ApplicationStatus;
import com.example.cvfiltersystem.model.Applicant;
import com.example.cvfiltersystem.model.ApplicantCV;
import com.example.cvfiltersystem.reponse.ApplicantResponse;
import com.example.cvfiltersystem.repository.ApplicantCVRepository;
import com.example.cvfiltersystem.repository.ApplicantRepository;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.modelmapper.ModelMapper;
import org.springframework.ai.client.AiClient;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CvFilterSystemService {

    private final ApplicantRepository applicantRepository;
    private final ApplicantCVRepository applicantCVRepository;
    private final AiClient aiClient;

    private final ModelMapper modelMapper;

    public CvFilterSystemService(ApplicantRepository applicantRepository, ApplicantCVRepository applicantCVRepository, AiClient aiClient, ModelMapper modelMapper) {
        this.applicantRepository = applicantRepository;
        this.applicantCVRepository = applicantCVRepository;
        this.aiClient = aiClient;
        this.modelMapper = modelMapper;
    }

    public ApplicantResponse filterCVs(MultipartFile file, String educationLevel,
                                       String position,
                                       Long experienceYearsRequirements,
                                       List<String> skillsRequirements,
                                       List<String> languagesRequirements,
                                       List<String> certificatesRequirements,
                                       String residencePlaceRequirements, double minimumScoreRequirement, String companyUsername) throws IOException {
        try {
            String convertedPdfToText = convertPdfToText(file.getInputStream());
            String fullText = prepareFullText(convertedPdfToText);

            String generated = aiClient.generate(fullText);

            String[] lines = generated.split("\n");

            String fullNameFromPdf = lines[0].substring(lines[0].indexOf("1) ") + 3);
            String languagesFromPdf = lines[1].substring(lines[1].indexOf("2) ") + 3);
            String skillsFromPdf = lines[2].substring(lines[2].indexOf("3) ") + 3);
            String certificatesFromPdf = lines[3].substring(lines[3].indexOf("4) ") + 3);
            String educationAndFieldFromPdf = lines[4].substring(lines[4].indexOf("5) ") + 3);
            String placeOfResidenceFromPdf = lines[5].substring(lines[5].indexOf("6) ") + 3);
            String workExperienceInfoFromPdf = lines[6].substring(lines[6].indexOf("7) ") + 3);
            String phoneNumberFromPdf = lines[7].substring(lines[7].indexOf("8) ") + 3);
            String mailFromPdf = lines[8].substring(lines[8].indexOf("9) ") + 3);

            double skillsFinalScore = calculateSkillsScore(skillsRequirements, skillsFromPdf);
            double languageFinalScore = calculateLanguageScore(languagesRequirements, languagesFromPdf);

            String generated1 = generateTextForScoring(educationLevel, residencePlaceRequirements, position,
                    certificatesRequirements, educationAndFieldFromPdf, placeOfResidenceFromPdf,
                    workExperienceInfoFromPdf, certificatesFromPdf);

            String[] linesOfGenerated1 = generated1.split("\n");

            boolean educationAndFieldBoolean = Boolean.valueOf(linesOfGenerated1[0].substring(linesOfGenerated1[0].indexOf("1) ") + 3).toLowerCase().trim());
            boolean residencePlaceBoolean = Boolean.valueOf(linesOfGenerated1[1].substring(linesOfGenerated1[1].indexOf("2) ") + 3).toLowerCase().trim());
            int totalOfWorkExperienceYears = Integer.valueOf(linesOfGenerated1[2].substring(linesOfGenerated1[2].indexOf("3) ") + 3).toLowerCase().trim());
            boolean certificatesBoolean = Boolean.valueOf(linesOfGenerated1[3].substring(linesOfGenerated1[3].indexOf("4) ") + 3).toLowerCase().trim());
            System.out.println("work experience: " + totalOfWorkExperienceYears);
            double workExperienceScore = calculateWorkExperienceScore(totalOfWorkExperienceYears, Math.toIntExact(experienceYearsRequirements));

            double totalScoreOfCV = calculateTotalScore(skillsRequirements == null || skillsRequirements.contains("") ? 100 : skillsFinalScore, languagesRequirements == null || languagesRequirements.contains("") ? 100 : languageFinalScore, experienceYearsRequirements == null || experienceYearsRequirements==0 ? 100 : workExperienceScore,
                    residencePlaceRequirements == null || residencePlaceRequirements.contains("") ? true : residencePlaceBoolean, educationLevel == null || educationLevel.equals("") ? null : educationAndFieldBoolean, certificatesRequirements == null || certificatesRequirements.contains("") ? true : certificatesBoolean);

            ApplicantResponse applicantResponse = createApplicantObject(file, fullNameFromPdf, certificatesFromPdf,
                    skillsFromPdf, languagesFromPdf, educationAndFieldFromPdf, placeOfResidenceFromPdf,
                    workExperienceInfoFromPdf, phoneNumberFromPdf, mailFromPdf, totalScoreOfCV, minimumScoreRequirement, position, companyUsername);


            System.out.println(totalScoreOfCV);
            return applicantResponse;
        } catch (IOException e) {
            throw new IOException("Error occurred while processing CV: " + e.getMessage());
        }
    }

    private String convertPdfToText(InputStream inputStream) throws IOException {
        try (PDDocument document = PDDocument.load(inputStream)) {
            PDFTextStripper stripper = new PDFTextStripper();
            return stripper.getText(document);
        }
    }

    private String prepareFullText(String convertedPdfText) {
        String additionalDetails = "-------------- Provide the person's full name, including both name and surname. Place the sign \"1)\" before the answer." + "\n" +
                "-------------- Analyze the languages this person speaks. and analyze at what level this person speaks the languages he knows. Then divide the languages that this person can speak into 3 categories according to his level: 1, 2 and 3. If the man knows the language very well(Keywords such as 'advanced' and 'native' may help you understand this.), write 1 next to the language in the bracket, for example: English (1), if the man knows the language at an intermediate level, write 2 next to the language in the bracket. for example: spanish(2). If the person knows the language poorly or at a basic level, write 3 next to the language in a bracket, for example: french(3). For example, the output at the end might be: \"English (2), German (1), French (3), Spanish (1).If the person does not share any information about the level at which he knows the language, consider it as advanced. Place the sign \"2)\" before the answer. If no information is given, write \"Not specified in CV\"." + "\n" +
                "-------------- Specify only the skills possessed by the person, separated by commas. Place the sign \"3)\" before the answer. If no information is given, write \"Not specified in CV\"." + "\n" +
                "-------------- List only the certificates obtained by the person, separated by commas. Place the sign \"4)\" before the answer. If there is no information, write \"No certificates specified in CV\"." + "\n" +
                "-------------- Provide the names of educational institutions attended by the person, along with the fields of study. If no field of study is given, write only the name of the educational institution. Separate each entry with a comma. Place the sign \"5)\" before the answer. If no information is given, write \"Not specified in CV\"." + "\n" +
                "-------------- Just write the name of the city where this person lives. Place the sign \"6)\" before the answer. If the residence place cannot be found, search for the text with the location icon before it. If no information is given, write \"Not specified in CV\"." + "\n" +
                "-------------- Just write in what professions, what companies and what time intervals this person works.for example, it should be like that, for example: Social Media Manager at Company Name (2015-2017), Digital Marketing Manager at Company Name (Jan 2022-Present), Social Media Manager at Company Name (2017-2019). Place the sign \"7)\" before the answer. If no information is given, write \"Not specified in CV\"." + "\n" +
                "-------------- Provide the person's phone number if available. Place the sign \"8)\" before the answer. If no information is given, write \"Not specified in CV\"." + "\n" +
                "-------------- Provide the person's email address if available. Place the sign \"9)\" before the answer. If no information is given, write \"Not specified in CV\".";

        return convertedPdfText + "\n" + additionalDetails;
    }

    private double calculateSkillsScore(List<String> skillsRequirements, String skillsFromPdf) {
        List<String> skillsList = Arrays.stream(skillsFromPdf.split(", "))
                .map(String::toLowerCase)
                .collect(Collectors.toList());

        long matchedSkillsCount = skillsRequirements.stream()
                .map(String::toLowerCase)
                .filter(skillsList::contains)
                .count();

        return (double) matchedSkillsCount / skillsRequirements.size() * 100;
    }

    private double calculateLanguageScore(List<String> languagesRequirements, String languagesFromPdf) {
        String[] languagesListFromPdf = languagesFromPdf.replaceAll("\\s+", "").toLowerCase().split(",");

        long matchedLanguagesCount = languagesRequirements.stream()
                .filter(langReq -> Arrays.stream(languagesListFromPdf).anyMatch(langPdf ->
                        isPrefixEqual(langReq.toLowerCase(), langPdf.toLowerCase()) && checkNumbers(langReq, langPdf)))
                .count();

        return (double) matchedLanguagesCount / languagesRequirements.size() * 100;
    }

    private String generateTextForScoring(String educationLevel, String residencePlaceRequirements, String position,
                                          List<String> certificatesRequirements, String educationAndFieldFromPdf,
                                          String placeOfResidenceFromPdf, String totalOfWorkExperienceYearsFromPdf, String certificatesFromPdf) {
        StringBuilder sb = new StringBuilder();
        sb.append("These are the education and fields required as minimum requirements: ").append(educationLevel).append("\n");
        sb.append("These are the education and fields the person has: ").append(educationAndFieldFromPdf).append("\n");
        sb.append("write just true if the person's education and fields match the minimum requirements, otherwise write just false. Place the sign \"1)\" before the answer.\n");
        sb.append("If this person lives near or in ").append(residencePlaceRequirements).append(", write just true. If not, write just false. Area where the person lives: ").append(placeOfResidenceFromPdf).append(". Place the sign \"2)\" before the answer.\n");
        sb.append("This is person's work experience info: ").append(totalOfWorkExperienceYearsFromPdf).append("Analyze and then calculate how many years of work experience this person has as just a ").append(position).append(" only.Just write digit. You should always return digit only. Calculate considering always present or current time as " + LocalDateTime.now().getYear() + ". Place the sign \"3)\" before the answer.\n");
        sb.append("These are the certificates required as minimum requirements: ").append(certificatesRequirements).append("\n");
        sb.append("These are the certificates the person has: ").append(certificatesFromPdf).append("\n");
        sb.append("write true if the person's certificates match the minimum requirements, otherwise false. Place the sign \"4)\" before the answer.\n");
        return aiClient.generate(sb.toString());
    }
//        sb.append("Analyze and then calculate how many years of work experience this person has as a ").append(position).append(" only. This is person's work experience info: ").append(totalOfWorkExperienceYearsFromPdf).append(". Just write digit. You should always return digit only. Place the sign \"3)\" before the answer.\n");

    private double calculateWorkExperienceScore(int totalOfWorkExperienceYears, int experienceYearsRequirements) {
        return totalOfWorkExperienceYears >= experienceYearsRequirements ? 100.0 : 0.0;
    }

    private double calculateTotalScore(double skillsFinalScore, double languageFinalScore, double workExperienceScore,
                                       boolean residencePlaceBoolean, boolean educationAndFieldBoolean,
                                       boolean certificatesBoolean) {
        double totalScore = (skillsFinalScore + languageFinalScore + workExperienceScore +
                (residencePlaceBoolean ? 100 : 0) + (educationAndFieldBoolean ? 100 : 0) + (certificatesBoolean ? 100 : 0)) / 6;
        return roundToNDecimals(totalScore, 2);
    }

    public static double roundToNDecimals(double number, int n) {
        double powerOfTen = Math.pow(10, n);
        return Math.round(number * powerOfTen) / powerOfTen;
    }

    private boolean isPrefixEqual(String str1, String str2) {
        int index = str1.indexOf('(');
        if (index == -1 || str2.indexOf('(') != index) {
            return false;
        }
        return str1.substring(0, index).equals(str2.substring(0, index));
    }

    private boolean checkNumbers(String str1, String str2) {
        int index1 = str1.indexOf('(');
        int index2 = str2.indexOf('(');
        int num1 = Integer.parseInt(str1.substring(index1 + 1, str1.length() - 1));
        int num2 = Integer.parseInt(str2.substring(index2 + 1, str2.length() - 1));

        return num1 >= num2;
    }

    private ApplicantResponse createApplicantObject(MultipartFile file, String fullNameFromPdf, String certificatesFromPdf,
                                                    String skillsFromPdf, String languagesFromPdf, String educationAndFieldFromPdf,
                                                    String placeOfResidenceFromPdf, String totalOfWorkExperienceYearsFromPdf,
                                                    String phoneNumberFromPdf, String mailFromPdf, double totalScoreOfCV, double minimumScoreRequirement, String position, String companyUsername) throws IOException {
        Applicant applicant = new Applicant();
        applicant.setCertificates(certificatesFromPdf);
        applicant.setSkills(skillsFromPdf);
        applicant.setLanguages(languagesFromPdf);
        applicant.setFullName(fullNameFromPdf);
        applicant.setEducationsAndFields(educationAndFieldFromPdf);
        applicant.setPlaceOfResidence(placeOfResidenceFromPdf);
        applicant.setWorkExperienceInfo(totalOfWorkExperienceYearsFromPdf);
        applicant.setPhoneNumber(phoneNumberFromPdf);
        applicant.setMail(mailFromPdf);
        applicant.setScore(totalScoreOfCV);
        applicant.setVacancyName(position);
        applicant.setCompanyUsername(companyUsername);
        if (totalScoreOfCV < minimumScoreRequirement) {
            applicant.setApplicationStatus(ApplicationStatus.REJECTED);
        } else {
            applicant.setApplicationStatus(ApplicationStatus.APPROVED);
        }
        Applicant saved = applicantRepository.save(applicant);
        ApplicantCV applicantCV = new ApplicantCV();
        applicantCV.setData(file.getBytes());
        applicantCV.setContentType(file.getContentType());
        applicantCV.setFileName(file.getName());
        applicantCV.setApplicantId(saved.getId());
        ApplicantCV savedApplicationCV = applicantCVRepository.save(applicantCV);

        ApplicantResponse applicantResponse = modelMapper.map(saved, ApplicantResponse.class);
        applicantResponse.setApplicantCVId(savedApplicationCV.getId());


        return applicantResponse;
    }
}
