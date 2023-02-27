package ru.igap.cophis.dataservice.service;

import ru.igap.cophis.dataservice.dto.CompanyDTO;
import ru.igap.cophis.dataservice.model.Company;

import java.util.List;
import java.util.Optional;

public interface CompanyService {

    List<Company> getCompaniesPage(Integer page, Integer companies_per_page);

    Optional<Company> createCompany(CompanyDTO companyDTO);


}
