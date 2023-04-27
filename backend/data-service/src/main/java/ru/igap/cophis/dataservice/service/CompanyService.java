package ru.igap.cophis.dataservice.service;

import org.springframework.data.domain.Page;
import ru.igap.cophis.dataservice.model.Company;

import java.util.List;

public interface CompanyService {

    Page getCompaniesPage(Integer page, Integer companies_per_page);
    Page getCompaniesPageSearch(String search, Integer page, Integer companies_per_page);

    Company createCompany(Company company);

    Company updateCompany(String id, Company companyRequest);

    void deleteCompany(String id);

    Company getCompanyById(String id);


}
