package com.example.nhom2_case.model;

import javax.persistence.*;

@Entity
@Table (name = "role")
public class Role {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long idRole ;
    private String name ;

    public Role() {
    }

    public Role(Long idRole, String name) {
        this.idRole = idRole;
        this.name = name;
    }

    public Long getIdRole() {
        return idRole;
    }

    public void setIdRole(Long idRole) {
        this.idRole = idRole;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
