package ru.igap.cophis.dataservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.igap.cophis.dataservice.dto.CompanyDTO;
import ru.igap.cophis.dataservice.model.Company;
import ru.igap.cophis.dataservice.service.CompanyService;
import ru.igap.cophis.dataservice.utils.DataValidationException;

import java.util.List;


@RestController
@RequestMapping("/api/v1/company")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @GetMapping
    public ResponseEntity<List<Company>> getCompanyPage(@RequestParam(required = false) Integer page,
                                                       @RequestParam(required = false) Integer companies_per_page) {
        return ResponseEntity.ok(companyService.getCompaniesPage(page, companies_per_page));
    }

    @PostMapping
    public ResponseEntity<Company> createCompany(@RequestBody CompanyDTO companyDTO) throws DataValidationException {
        return companyService.createCompany(companyDTO).map(s -> new ResponseEntity<>(s, HttpStatus.OK))
                .orElseThrow(() -> new DataValidationException("Компания не была сохранен"));
    }


}
