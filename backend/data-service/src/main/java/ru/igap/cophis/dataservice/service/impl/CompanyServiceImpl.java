package ru.igap.cophis.dataservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.igap.cophis.dataservice.dto.CompanyDTO;
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
    public Optional<Company> createCompany(CompanyDTO companyDTO) {
        Company company = new Company()
                .setName(companyDTO.getName())
                .setInn(companyDTO.getInn())
                .setPhone(companyDTO.getPhone())
                .setHome_url(companyDTO.getHome_url())
                .setEmail(companyDTO.getEmail())
                .setAddress(companyDTO.getAddress())
                .setDescription(companyDTO.getDescription())
                .setId(UUID.randomUUID().toString())
                .setCreated_at(LocalDateTime.now());

        return Optional.of(companyRepository.save(company));
    }
}
