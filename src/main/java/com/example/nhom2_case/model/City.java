package com.example.nhom2_case.model;

import javax.persistence.*;

@Entity
@Table (name = "city" )
public class City {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long idCity ;
    private String name ;

    public City() {
    }

    public City(Long idCity, String name) {
        this.idCity = idCity;
        this.name = name;
    }

    public Long getIdCity() {
        return idCity;
    }

    public void setIdCity(Long idCity) {
        this.idCity = idCity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
