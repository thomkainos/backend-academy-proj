package org.example.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@AllArgsConstructor @Getter @Setter
public class JobRole {
    int roleId;
    String roleName;
    String location;
    String capability;
    String band;
    Date closingDate;
    int roleStatus;
}
