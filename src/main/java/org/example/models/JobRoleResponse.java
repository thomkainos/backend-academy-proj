package org.example.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@AllArgsConstructor @Getter @Setter
public class JobRoleResponse {
    private int roleId;
    private String roleName;
    private String location;
    private String capability;
    private String band;
    private Date closingDate;
    private int roleStatus;
}
