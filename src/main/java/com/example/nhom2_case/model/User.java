package com.example.nhom2_case.model;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

@Entity
@Table(name = "user")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser ;
    private String fullName ;
    private String avatar ;
    private String sex ;
    private int age ;
    private String address ;
    private String phone ;
    @Transient
    private MultipartFile file ;

    public User() {
    }

    public User(Long idUser, String fullName, String avatar, String sex, int age, String address, String phone, MultipartFile file) {
        this.idUser = idUser;
        this.fullName = fullName;
        this.avatar = avatar;
        this.sex = sex;
        this.age = age;
        this.address = address;
        this.phone = phone;
        this.file = file;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
