package rut.miit.hotel.dto.request;


import java.time.LocalDate;
import java.util.List;

public class BookingRequestDto {
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer roomId;
    private Integer customerId;
    private List<PaymentRequestDto> payments;
    private List<BookingOptionRequestDto> bookingOptions;

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

    public List<PaymentRequestDto> getPayments() {
        return payments;
    }

    public void setPayments(List<PaymentRequestDto> payments) {
        this.payments = payments;
    }

    public List<BookingOptionRequestDto> getBookingOptions() {
        return bookingOptions;
    }

    public void setBookingOptions(List<BookingOptionRequestDto> bookingOptions) {
        this.bookingOptions = bookingOptions;
    }
}
