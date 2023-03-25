package ru.igap.cophis.dataservice.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
public class CompanyDto {

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

}
