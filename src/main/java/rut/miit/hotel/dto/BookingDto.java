package rut.miit.hotel.dto;


import rut.miit.hotel.domain.status.BookingStatus;

import java.time.LocalDate;
import java.util.List;

public class BookingDto extends IdDto{
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer roomId;
    private Integer customerId;
    private BookingStatus bookingStatus;
    private List<PaymentDto> payments;
    private List<BookingOptionDto> bookingOptions;

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public List<PaymentDto> getPayments() {
        return payments;
    }

    public void setPayments(List<PaymentDto> payments) {
        this.payments = payments;
    }

    public List<BookingOptionDto> getBookingOptions() {
        return bookingOptions;
    }

    public void setBookingOptions(List<BookingOptionDto> bookingOptions) {
        this.bookingOptions = bookingOptions;
    }
}
