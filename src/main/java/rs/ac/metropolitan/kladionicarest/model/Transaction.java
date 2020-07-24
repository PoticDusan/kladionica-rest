package rs.ac.metropolitan.kladionicarest.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transactionId")
    private Long id;

    @Column(name = "transactionFromAccount")
    private String transactionFromAccount;

    @Column(name = "transactionToAccount")
    private String transactionToAccount;

    @Column(name = "transactionDate")
    private java.sql.Date transactionDate;

    @Column(name = "transactionTime")
    private java.sql.Time transactionTime;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transactionTypeId", nullable = false)
    private TransactionType transactionType;

    @Column(name = "transactionAmount")
    private double transactionAmount;

    @Column(name = "transactionStatus")
    private String transactionStatus;

    @JsonBackReference(value = "walletId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "walletId", nullable = false)
    private Wallet wallet;

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTransactionFromAccount() {
        return transactionFromAccount;
    }

    public void setTransactionFromAccount(String transactionFromAccount) {
        this.transactionFromAccount = transactionFromAccount;
    }

    public String getTransactionToAccount() {
        return transactionToAccount;
    }

    public void setTransactionToAccount(String transactionToAccount) {
        this.transactionToAccount = transactionToAccount;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Time getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(Time transactionTime) {
        this.transactionTime = transactionTime;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", transactionFromAccount='" + transactionFromAccount + '\'' +
                ", transactionToAccount='" + transactionToAccount + '\'' +
                ", transactionDate=" + transactionDate +
                ", transactionTime=" + transactionTime +
                ", transactionType=" + transactionType.getId() +
                ", transactionAmount=" + transactionAmount +
                ", transactionStatus='" + transactionStatus + '\'' +
                '}';
    }
}
