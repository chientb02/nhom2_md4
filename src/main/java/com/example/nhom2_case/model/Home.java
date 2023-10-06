package com.example.nhom2_case.model;
import javax.persistence.*;
import java.util.List;

@Entity
@Table (name = "home" )
public class Home {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idHome;
    private String name;
    @ManyToOne
    private Address address;
    private int bedroom_count;
    private int bathroom_count;
    private String description;
    private double price;
    @Transient
    private List<Image> image;
    @ManyToOne
    private Status status;

    @ManyToOne
    private Account account ;

    public Home() {
    }

    public Home(Long idHome, String name, Address address, int bedroom_count, int bathroom_count, String description, double price, List<Image> image, Status status, Account account) {
        this.idHome = idHome;
        this.name = name;
        this.address = address;
        this.bedroom_count = bedroom_count;
        this.bathroom_count = bathroom_count;
        this.description = description;
        this.price = price;
        this.image = image;
        this.status = status;
        this.account = account;
    }

    public Long getIdHome() {
        return idHome;
    }

    public void setIdHome(Long idHome) {
        this.idHome = idHome;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public int getBedroom_count() {
        return bedroom_count;
    }

    public void setBedroom_count(int bedroom_count) {
        this.bedroom_count = bedroom_count;
    }

    public int getBathroom_count() {
        return bathroom_count;
    }

    public void setBathroom_count(int bathroom_count) {
        this.bathroom_count = bathroom_count;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<Image> getImage() {
        return image;
    }

    public void setImage(List<Image> image) {
        this.image = image;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}