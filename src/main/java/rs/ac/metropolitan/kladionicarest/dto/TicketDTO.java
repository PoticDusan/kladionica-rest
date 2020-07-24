package rs.ac.metropolitan.kladionicarest.dto;

import java.sql.Date;
import java.sql.Time;

public class TicketDTO {

    private Long id;
    private java.sql.Date ticketDate;
    private java.sql.Time ticketTime;
    private double ticketTotalOdd;
    private double ticketTotalAmount;
    private String ticketStatus;

    public TicketDTO() {
    }

    public TicketDTO(double ticketTotalOdd, double ticketTotalAmount, String ticketStatus) {
        this.ticketTotalOdd = ticketTotalOdd;
        this.ticketTotalAmount = ticketTotalAmount;
        this.ticketStatus = ticketStatus;
    }

    public TicketDTO(Date ticketDate, Time ticketTime, double ticketTotalOdd, double ticketTotalAmount, String ticketStatus) {
        this.ticketDate = ticketDate;
        this.ticketTime = ticketTime;
        this.ticketTotalOdd = ticketTotalOdd;
        this.ticketTotalAmount = ticketTotalAmount;
        this.ticketStatus = ticketStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getTicketDate() {
        return ticketDate;
    }

    public void setTicketDate(Date ticketDate) {
        this.ticketDate = ticketDate;
    }

    public Time getTicketTime() {
        return ticketTime;
    }

    public void setTicketTime(Time ticketTime) {
        this.ticketTime = ticketTime;
    }

    public double getTicketTotalOdd() {
        return ticketTotalOdd;
    }

    public void setTicketTotalOdd(double ticketTotalOdd) {
        this.ticketTotalOdd = ticketTotalOdd;
    }

    public double getTicketTotalAmount() {
        return ticketTotalAmount;
    }

    public void setTicketTotalAmount(double ticketTotalAmount) {
        this.ticketTotalAmount = ticketTotalAmount;
    }

    public String getTicketStatus() {
        return ticketStatus;
    }

    public void setTicketStatus(String ticketStatus) {
        this.ticketStatus = ticketStatus;
    }
}
