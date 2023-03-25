package ru.igap.cophis.dataservice.service;

import ru.igap.cophis.dataservice.model.Company;

import java.util.List;

public interface CompanyService {

    List<Company> getCompaniesPage(Integer page, Integer companies_per_page);

    Company createCompany(Company company);

    Company updateCompany(String id, Company companyRequest);

    void deleteCompany(String id);

    Company getCompanyById(String id);


}
