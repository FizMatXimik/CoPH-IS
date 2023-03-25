package ru.igap.cophis.dataservice.dto;

import lombok.Data;

@Data
public class CompanyDto {

    private String id;
    private String name;
    private String phone;
    private String email;
    private String home_url;
    private String inn;
    private String kpp;
    private String ogrn;
    private int type_id;
    private String address;
    private String full_name;
    private String description;

}
