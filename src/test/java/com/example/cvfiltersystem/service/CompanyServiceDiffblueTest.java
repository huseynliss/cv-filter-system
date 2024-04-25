package com.example.cvfiltersystem.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.cvfiltersystem.model.Company;
import com.example.cvfiltersystem.reponse.CompanyResponse;
import com.example.cvfiltersystem.repository.CompanyRepository;
import com.example.cvfiltersystem.request.CompanyRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {CompanyService.class})
@ExtendWith(SpringExtension.class)
class CompanyServiceDiffblueTest {
    @MockBean
    private CompanyRepository companyRepository;

    @Autowired
    private CompanyService companyService;

    /**
     * Method under test: {@link CompanyService#createCompany(CompanyRequest)}
     */
    @Test
    void testCreateCompany() {
        Company company = new Company();
        company.setCompanyUsername("janedoe");
        company.setId(1L);
        when(companyRepository.findByCompanyUsername(Mockito.<String>any())).thenReturn(company);

        CompanyRequest companyRequest = new CompanyRequest();
        companyRequest.setCompanyUsername("janedoe");
        companyRequest.setId(1L);
        CompanyResponse actualCreateCompanyResult = companyService.createCompany(companyRequest);
        verify(companyRepository).findByCompanyUsername(Mockito.<String>any());
        assertEquals("janedoe", actualCreateCompanyResult.getCompanyUsername());
        assertEquals(1L, actualCreateCompanyResult.getId().longValue());
    }

    /**
     * Method under test: {@link CompanyService#getAllCompanies()}
     */
    @Test
    void testGetAllCompanies() {
        when(companyRepository.findAll()).thenReturn(new ArrayList<>());
        List<CompanyResponse> actualAllCompanies = companyService.getAllCompanies();
        verify(companyRepository).findAll();
        assertTrue(actualAllCompanies.isEmpty());
    }

    /**
     * Method under test: {@link CompanyService#getAllCompanies()}
     */
    @Test
    void testGetAllCompanies2() {
        Company company = new Company();
        company.setCompanyUsername("janedoe");
        company.setId(1L);

        ArrayList<Company> companyList = new ArrayList<>();
        companyList.add(company);
        when(companyRepository.findAll()).thenReturn(companyList);
        List<CompanyResponse> actualAllCompanies = companyService.getAllCompanies();
        verify(companyRepository).findAll();
        assertEquals(1, actualAllCompanies.size());
        CompanyResponse getResult = actualAllCompanies.get(0);
        assertEquals("janedoe", getResult.getCompanyUsername());
        assertEquals(1L, getResult.getId().longValue());
    }

    /**
     * Method under test: {@link CompanyService#getAllCompanies()}
     */
    @Test
    void testGetAllCompanies3() {
        Company company = new Company();
        company.setCompanyUsername("janedoe");
        company.setId(1L);

        Company company2 = new Company();
        company2.setCompanyUsername("Company Username");
        company2.setId(2L);

        ArrayList<Company> companyList = new ArrayList<>();
        companyList.add(company2);
        companyList.add(company);
        when(companyRepository.findAll()).thenReturn(companyList);
        List<CompanyResponse> actualAllCompanies = companyService.getAllCompanies();
        verify(companyRepository).findAll();
        assertEquals(2, actualAllCompanies.size());
        CompanyResponse getResult = actualAllCompanies.get(0);
        assertEquals("Company Username", getResult.getCompanyUsername());
        CompanyResponse getResult2 = actualAllCompanies.get(1);
        assertEquals("janedoe", getResult2.getCompanyUsername());
        assertEquals(1L, getResult2.getId().longValue());
        assertEquals(2L, getResult.getId().longValue());
    }

    /**
     * Method under test: {@link CompanyService#getCompanyById(Long)}
     */
    @Test
    void testGetCompanyById() {
        Company company = new Company();
        company.setCompanyUsername("janedoe");
        company.setId(1L);
        Optional<Company> ofResult = Optional.of(company);
        when(companyRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        CompanyResponse actualCompanyById = companyService.getCompanyById(1L);
        verify(companyRepository).findById(Mockito.<Long>any());
        assertEquals("janedoe", actualCompanyById.getCompanyUsername());
        assertEquals(1L, actualCompanyById.getId().longValue());
    }

    /**
     * Method under test: {@link CompanyService#getCompanyById(Long)}
     */
    @Test
    void testGetCompanyById2() {
        Optional<Company> emptyResult = Optional.empty();
        when(companyRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);
        CompanyResponse actualCompanyById = companyService.getCompanyById(1L);
        verify(companyRepository).findById(Mockito.<Long>any());
        assertNull(actualCompanyById);
    }

    /**
     * Method under test:
     * {@link CompanyService#updateCompany(String, CompanyRequest)}
     */
    @Test
    void testUpdateCompany() {
        Company company = new Company();
        company.setCompanyMail("Company Mail");
        company.setCompanyMailPassword("iloveyou");
        company.setCompanyUsername("janedoe");
        company.setId(1L);

        Company company2 = new Company();
        company2.setCompanyMail("Company Mail");
        company2.setCompanyMailPassword("iloveyou");
        company2.setCompanyUsername("janedoe");
        company2.setId(1L);
        when(companyRepository.save(Mockito.<Company>any())).thenReturn(company2);
        when(companyRepository.findByCompanyUsername(Mockito.<String>any())).thenReturn(company);

        CompanyRequest companyRequest = new CompanyRequest();
        companyRequest.setCompanyMail("Company Mail");
        companyRequest.setCompanyMailPassword("iloveyou");
        companyRequest.setCompanyUsername("janedoe");
        companyRequest.setId(1L);
        CompanyResponse actualUpdateCompanyResult = companyService.updateCompany("janedoe", companyRequest);
        verify(companyRepository).findByCompanyUsername(Mockito.<String>any());
        verify(companyRepository).save(Mockito.<Company>any());
        assertEquals("janedoe", actualUpdateCompanyResult.getCompanyUsername());
        assertEquals(1L, actualUpdateCompanyResult.getId().longValue());
    }

    //    /**
    //     * Method under test: {@link CompanyService#updateCompany(Long, CompanyRequest)}
    //     */
    //    @Test
    //    void testUpdateCompany() {
    //        Company company = new Company();
    //        company.setCompanyUsername("janedoe");
    //        company.setId(1L);
    //        Optional<Company> ofResult = Optional.of(company);
    //
    //        Company company2 = new Company();
    //        company2.setCompanyUsername("janedoe");
    //        company2.setId(1L);
    //        when(companyRepository.save(Mockito.<Company>any())).thenReturn(company2);
    //        when(companyRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
    //
    //        CompanyRequest companyRequest = new CompanyRequest();
    //        companyRequest.setCompanyUsername("janedoe");
    //        companyRequest.setId(1L);
    //        CompanyResponse actualUpdateCompanyResult = companyService.updateCompany(1L, companyRequest);
    //        verify(companyRepository).findById(Mockito.<Long>any());
    //        verify(companyRepository).save(Mockito.<Company>any());
    //        assertEquals("janedoe", actualUpdateCompanyResult.getCompanyUsername());
    //        assertEquals(1L, actualUpdateCompanyResult.getId().longValue());
    //    }
    //
    //    /**
    //     * Method under test: {@link CompanyService#updateCompany(Long, CompanyRequest)}
    //     */
    //    @Test
    //    void testUpdateCompany2() {
    //        Optional<Company> emptyResult = Optional.empty();
    //        when(companyRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);
    //
    //        CompanyRequest companyRequest = new CompanyRequest();
    //        companyRequest.setCompanyUsername("janedoe");
    //        companyRequest.setId(1L);
    //        CompanyResponse actualUpdateCompanyResult = companyService.updateCompany(1L, companyRequest);
    //        verify(companyRepository).findById(Mockito.<Long>any());
    //        assertNull(actualUpdateCompanyResult);
    //    }
    //
    //    /**
    //     * Method under test: {@link CompanyService#deleteCompany(Long)}
    //     */
    @Test
    void testDeleteCompany() {
        doNothing().when(companyRepository).deleteById(Mockito.<Long>any());
        companyService.deleteCompany(1L);
        verify(companyRepository).deleteById(Mockito.<Long>any());
        assertTrue(companyService.getAllCompanies().isEmpty());
    }
}
