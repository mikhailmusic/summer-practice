package rut.miit.hotel.domain;

import jakarta.persistence.*;
import rut.miit.hotel.domain.status.PaymentStatus;

import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
public class Payment extends BaseEntity {
    private Double amount;
    private LocalDateTime dateOfPayment;
    private String bankName;
    private String bankAccount;
    private PaymentStatus status;
    private Booking booking;

    public Payment(Double amount, LocalDateTime dateOfPayment, String bankName, String bankAccount, PaymentStatus status, Booking booking) {
        this.amount = amount;
        this.dateOfPayment = dateOfPayment;
        this.bankName = bankName;
        this.bankAccount = bankAccount;
        this.status = status;
        this.booking = booking;
    }

    public Payment(Double amount, Booking booking) {
        this.amount = amount;
        this.status = PaymentStatus.CREATED;
        this.booking = booking;
    }

    protected Payment() {
    }

    @Column(name = "amount", nullable = false)
    public Double getAmount() {
        return amount;
    }

    @Column(name = "date_payment")
    public LocalDateTime getDateOfPayment() {
        return dateOfPayment;
    }

    @Column(name = "bank_name")
    public String getBankName() {
        return bankName;
    }

    @Column(name = "bank_account", length = 34)
    public String getBankAccount() {
        return bankAccount;
    }

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    public PaymentStatus getStatus() {
        return status;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "booking_id", nullable = false)
    public Booking getBooking() {
        return booking;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setDateOfPayment(LocalDateTime dateOfPayment) {
        this.dateOfPayment = dateOfPayment;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }
}