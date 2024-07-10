package rut.miit.hotel.model;

import jakarta.persistence.*;
import rut.miit.hotel.model.enums.PaymentStatus;

import java.time.OffsetDateTime;

@Entity
@Table(name = "payments")
public class Payment extends BaseEntity {
    private Long amount;
    private OffsetDateTime dateOfPayment;
    private String bankName;
    private Long bankAccount;
    private PaymentStatus status;
    private Booking booking;

    @Column(name = "amount", nullable = false)
    public Long getAmount() {
        return amount;
    }

    @Column(name = "date_payment", nullable = false)
    public OffsetDateTime getDateOfPayment() {
        return dateOfPayment;
    }

    @Column(name = "bank_name", nullable = false)
    public String getBankName() {
        return bankName;
    }

    @Column(name = "bank_account", nullable = false)
    public Long getBankAccount() {
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

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public void setDateOfPayment(OffsetDateTime dateOfPayment) {
        this.dateOfPayment = dateOfPayment;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public void setBankAccount(Long bankAccount) {
        this.bankAccount = bankAccount;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }
}