package com.example.nhom2_case.model;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table (name = "bill" )
public class Bill {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long idBill ;

    @ManyToOne
    private Account account ;
    private LocalDate checkin;
    private LocalDate checkout;
    private double totalPrice ;

    public Bill() {
    }

    public Long getIdBill() {
        return idBill;
    }

    public void setIdBill(Long idBill) {
        this.idBill = idBill;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public LocalDate getCheckin() {
        return checkin;
    }

    public void setCheckin(LocalDate checkin) {
        this.checkin = checkin;
    }

    public LocalDate getCheckout() {
        return checkout;
    }

    public void setCheckout(LocalDate checkout) {
        this.checkout = checkout;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Bill(Long idBill, Account account, LocalDate checkin, LocalDate checkout, double totalPrice) {
        this.idBill = idBill;
        this.account = account;
        this.checkin = checkin;
        this.checkout = checkout;
        this.totalPrice = totalPrice;
    }
}
