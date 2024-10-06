package rut.miit.hotel.dto;

import rut.miit.hotel.domain.status.PaymentStatus;

import java.time.LocalDateTime;

public class PaymentDto extends IdDto{
    private Long amount;
    private LocalDateTime dateOfPayment;
    private String bankName;
    private String bankAccount;
    private PaymentStatus status;

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public LocalDateTime getDateOfPayment() {
        return dateOfPayment;
    }

    public void setDateOfPayment(LocalDateTime dateOfPayment) {
        this.dateOfPayment = dateOfPayment;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

}
