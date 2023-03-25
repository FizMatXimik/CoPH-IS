package ru.igap.cophis.dataservice.model;

import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Accessors(chain = true)
@Table(name = "companies")
public class Company {

    @Id
    private String id;

    private String name;

    private String phone;

    private String email;

    private String home_url;

    private int type_id;

    private String inn;

    private String kpp;

    private String ogrn;

    private String address;

    private String full_name;

    private String description;

    private LocalDateTime created_at;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return type_id == company.type_id && Objects.equals(id, company.id) && Objects.equals(name, company.name) && Objects.equals(phone, company.phone) && Objects.equals(email, company.email) && Objects.equals(home_url, company.home_url) && Objects.equals(inn, company.inn) && Objects.equals(kpp, company.kpp) && Objects.equals(ogrn, company.ogrn) && Objects.equals(address, company.address) && Objects.equals(full_name, company.full_name) && Objects.equals(description, company.description) && Objects.equals(created_at, company.created_at);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, phone, email, home_url, type_id, inn, kpp, ogrn, address, full_name, description, created_at);
    }
}
