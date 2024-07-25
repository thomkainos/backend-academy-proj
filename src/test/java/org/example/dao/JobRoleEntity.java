package org.example.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name="roles")
@Getter @Setter @AllArgsConstructor
public class JobRoleEntity {
    @Id
    int roleId;
    String roleName;
    String location;
    String capability;
    String band;
    Date closingDate;
    int roleStatus;
}
