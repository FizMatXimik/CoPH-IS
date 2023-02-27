package ru.igap.cophis.dataservice.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
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

    private String inn;

    private String phone;

    private String home_url;

    private String email;

    private int type_id;

    private String address;

    private String description;

    private LocalDateTime created_at;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return id == company.id && type_id == company.type_id && Objects.equals(name, company.name) && Objects.equals(inn, company.inn) && Objects.equals(phone, company.phone) && Objects.equals(home_url, company.home_url) && Objects.equals(email, company.email) && Objects.equals(address, company.address) && Objects.equals(description, company.description) && Objects.equals(created_at, company.created_at);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, inn, phone, home_url, email, type_id, address, description, created_at);
    }
}
