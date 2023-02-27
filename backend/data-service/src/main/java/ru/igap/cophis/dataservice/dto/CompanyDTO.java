package ru.igap.cophis.dataservice.dto;

import lombok.Data;

@Data
public class CompanyDTO {

    private String name;
    private String inn;
    private String phone;
    private String home_url;
    private String email;
    private String address;
    private String description;

}
