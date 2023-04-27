package ru.igap.cophis.dataservice.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.igap.cophis.dataservice.dto.CompanyDto;
import ru.igap.cophis.dataservice.model.Company;
import ru.igap.cophis.dataservice.service.CompanyService;
import ru.igap.cophis.dataservice.utils.DataValidationException;

import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/v1/company")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    private final ModelMapper modelMapper;

    private static final Logger logger = LoggerFactory.getLogger(CompanyController.class);

    @GetMapping
    public Page getCompanyPage(@RequestParam(required = false) Integer page,
                               @RequestParam(required = false) Integer companies_per_page) {
        return companyService.getCompaniesPage(page, companies_per_page);
    }

    @GetMapping("/search")
    public Page getCompanyPage(@RequestParam() String search,
                               @RequestParam(required = false) Integer page,
                               @RequestParam(required = false) Integer companies_per_page) {
        return companyService.getCompaniesPageSearch(search, page, companies_per_page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyDto> getCompanyById(@PathVariable(name = "id") String id) {
        Company company = companyService.getCompanyById(id);
        logger.debug("DataService-Controller-getCompanyById --- Company: {}", company);

        CompanyDto companyResponse = modelMapper.map(company, CompanyDto.class);
        logger.debug("DataService-Controller-getCompanyById --- Response CompanyDto: {}", companyResponse);

        return ResponseEntity.ok().body(companyResponse);
    }

    @PostMapping
    public ResponseEntity<CompanyDto> createCompany(@RequestBody CompanyDto companyDto) {
        // convert DTO to entity
        Company companyRequest = modelMapper.map(companyDto, Company.class);
        Company company = companyService.createCompany(companyRequest);

        // convert entity to DTO
        CompanyDto companyResponse = modelMapper.map(company, CompanyDto.class);
        return new ResponseEntity<CompanyDto>(companyResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompanyDto> updateCompany(@PathVariable String id, @RequestBody CompanyDto companyDto) {

        // convert DTO to entity
        Company companyRequest = modelMapper.map(companyDto, Company.class);
        Company company = companyService.updateCompany(id, companyRequest);

        // convert entity to DTO
        CompanyDto companyResponse = modelMapper.map(company, CompanyDto.class);
        return ResponseEntity.ok().body(companyResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable(name = "id") String id) {
        companyService.deleteCompany(id);
        return new ResponseEntity<String>("Компания была удалена успешно!", HttpStatus.OK);
    }

}
