package ru.igap.cophis.dataservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.igap.cophis.dataservice.model.Company;
import ru.igap.cophis.dataservice.repository.CompanyRepository;
import ru.igap.cophis.dataservice.service.CompanyService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    @Override
    public List<Company> getCompaniesPage(Integer page, Integer companies_per_page) {
        if (page != null && companies_per_page != null) {
            return companyRepository.findAll(PageRequest.of(page, companies_per_page)).getContent();
        } else {
            return companyRepository.findAll();
        }
    }

    @Override
    public Company createCompany(Company company) {
        company.setId(UUID.randomUUID().toString())
                .setCreated_at(LocalDateTime.now());
        return companyRepository.save(company);
    }

    @Override
    public Company updateCompany(String id, Company companyRequest) {
        Optional<Company> companyOp = companyRepository.findById(id);

        if (companyOp.isPresent()) {
            Company company = companyOp.get();

            company.setName(companyRequest.getName())
                    .setPhone(companyRequest.getPhone())
                    .setEmail(companyRequest.getEmail())
                    .setHome_url(companyRequest.getHome_url())
                    .setInn(companyRequest.getInn())
                    .setKpp(companyRequest.getKpp())
                    .setOgrn(companyRequest.getOgrn())
                    .setType_id(companyRequest.getType_id())
                    .setAddress(companyRequest.getAddress())
                    .setFull_name(companyRequest.getFull_name())
                    .setDescription(companyRequest.getDescription());
            return companyRepository.save(company);
        }
        return null;
    }

    @Override
    public void deleteCompany(String id) {
        Optional<Company> companyOp = companyRepository.findById(id);

        if (companyOp.isPresent()) {
            Company company = companyOp.get();
            companyRepository.delete(company);
        }
    }

    @Override
    public Company getCompanyById(String id) {
        Optional<Company> companyOp = companyRepository.findById(id);

        return companyOp.orElse(null);
    }

}
