package com.herego.api.controllers.dto;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetUsers {
    private Integer id;
    private String name;
    private String lastName;
    private String userType;
    private Date birthday;
    private String phone;
    private String dni;
    private String email;
    private String userState;

}
