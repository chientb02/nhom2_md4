package com.example.nhom2_case.model;
import javax.persistence.*;

@Entity
@Table (name = "status" )
public class Status {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long idStatus ;
    private String name ;

    public Status() {
    }

    public Status(Long idStatus, String name) {
        this.idStatus = idStatus;
        this.name = name;
    }

    public Long getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(Long idStatus) {
        this.idStatus = idStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
