package ru.igap.cophis.dataservice.model;

import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.Hibernate;

import javax.persistence.*;
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

    @ManyToOne()
    @JoinColumn(name="type_id")
    public CompanyType type;

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
        return Objects.equals(id, company.id) && Objects.equals(name, company.name) && Objects.equals(phone, company.phone) && Objects.equals(email, company.email) && Objects.equals(home_url, company.home_url) && Objects.equals(type, company.type) && Objects.equals(inn, company.inn) && Objects.equals(kpp, company.kpp) && Objects.equals(ogrn, company.ogrn) && Objects.equals(address, company.address) && Objects.equals(full_name, company.full_name) && Objects.equals(description, company.description) && Objects.equals(created_at, company.created_at);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, phone, email, home_url, type, inn, kpp, ogrn, address, full_name, description, created_at);
    }
}
