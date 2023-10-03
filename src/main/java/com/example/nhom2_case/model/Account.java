package com.example.nhom2_case.model;

import javax.persistence.*;

@Entity
@Table (name = "account")
public class Account {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long idAccount ;
    private String userName ;
    private String password ;
    @ManyToOne
    private User user;
    private String status ;
    @ManyToOne
    private Role role ;


    public Account() {
    }

    public Account(Long idAccount, String userName, String password, User user, String status, Role role) {
        this.idAccount = idAccount;
        this.userName = userName;
        this.password = password;
        this.user = user;
        this.status = status;
        this.role = role;
    }

    public Long getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(Long idAccount) {
        this.idAccount = idAccount;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
