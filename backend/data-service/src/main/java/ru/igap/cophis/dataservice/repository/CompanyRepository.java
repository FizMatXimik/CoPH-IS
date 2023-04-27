package ru.igap.cophis.dataservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.igap.cophis.dataservice.model.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, String> {
    Page findByNameLike(String like, PageRequest request);
}
