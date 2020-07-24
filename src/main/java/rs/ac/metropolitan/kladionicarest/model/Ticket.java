package rs.ac.metropolitan.kladionicarest.model;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "ticket")
public class Ticket {

    @Id
    @Column(name = "ticketId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ticketDate")
    private java.sql.Date ticketDate;

    @Column(name = "ticketTime")
    private java.sql.Time ticketTime;

    @Column(name = "ticketTotalOdd")
    private double ticketTotalOdd;

    @Column(name = "ticketTotalAmount")
    private double ticketTotalAmount;

    @Column(name = "ticketStatus")
    private String ticketStatus;

    public Ticket() {
        this.setCurrentDateAndTime();
    }

    public Ticket(double ticketTotalOdd, double ticketTotalAmount, String ticketStatus) {
        this.ticketTotalOdd = ticketTotalOdd;
        this.ticketTotalAmount = ticketTotalAmount;
        this.ticketStatus = ticketStatus;
        this.setCurrentDateAndTime();
    }

    public Ticket(Date ticketDate, Time ticketTime, double ticketTotalOdd, double ticketTotalAmount, String ticketStatus) {
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

    public java.sql.Date getTicketDate() {
        return ticketDate;
    }

    public void setTicketDate(java.sql.Date ticketDate) {
        this.ticketDate = ticketDate;
    }

    public java.sql.Time getTicketTime() {
        return ticketTime;
    }

    public void setTicketTime(java.sql.Time ticketTime) {
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

    public void setTicketAmount(double ticketAmount) {
        this.ticketTotalAmount = ticketAmount;
    }

    public String getTicketStatus() {
        return ticketStatus;
    }

    public void setTicketStatus(String ticketStatus) {
        this.ticketStatus = ticketStatus;
    }

    public void setCurrentDateAndTime() {
       java.util.Date date = new java.util.Date();
       this.ticketDate = new java.sql.Date(date.getTime());
       this.ticketTime = new java.sql.Time(date.getTime());
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", ticketDate=" + ticketDate +
                ", ticketTime=" + ticketTime +
                ", ticketTotalOdd=" + ticketTotalOdd +
                ", ticketAmount=" + ticketTotalAmount +
                ", ticketStatus='" + ticketStatus + '\'' +
                '}';
    }
}
