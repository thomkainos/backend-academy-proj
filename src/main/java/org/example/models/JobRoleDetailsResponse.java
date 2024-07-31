package org.example.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@AllArgsConstructor @Getter @Setter @NoArgsConstructor
public class JobRoleDetailsResponse {
    private String roleName;
    private String location;
    private String capability;
    private String band;
    private Date closingDate;
    private int roleStatus;
    private String description;
    private String responsibilities;
    private String jobLink;
}
