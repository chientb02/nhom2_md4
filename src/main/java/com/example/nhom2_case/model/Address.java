package com.example.nhom2_case.model;
import javax.persistence.*;

@Entity
@Table (name = "address" )
public class Address {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long idAddress ;
    private String name ;
    @ManyToOne
    private City city ;

    public Address() {
    }

    public Address(Long idAddress, String name, City city) {
        this.idAddress = idAddress;
        this.name = name;
        this.city = city;
    }

    public Long getIdAddress() {
        return idAddress;
    }

    public void setIdAddress(Long idAddress) {
        this.idAddress = idAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
