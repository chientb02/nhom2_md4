package com.example.nhom2_case.model;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

@Entity
@Table(name = "user")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser ;
    @OneToOne
    private Account account ;
    private String fullName ;
    private String avatar ;
    private String sex ;
    private Integer age ;
    private String address ;
    private String phone ;
    @Column(columnDefinition = "integer default 0")
    private Integer changeRole ;
    @Transient
    private MultipartFile file ;

    public User() {
    }

    public Integer getChangeRole() {
        return changeRole;
    }

    public void setChangeRole(Integer changeRole) {
        this.changeRole = changeRole;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
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
