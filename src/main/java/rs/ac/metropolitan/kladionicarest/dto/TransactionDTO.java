package rs.ac.metropolitan.kladionicarest.dto;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

public class TransactionDTO implements Serializable {

    private Long id;
    private String transactionFromAccount;
    private String transactionToAccount;
    private java.sql.Date transactionDate;
    private java.sql.Time transactionTime;
    private Long transactionTypeId;
    private double transactionAmount;
    private String transactionStatus;
    private Long walletId;

    public Long getWalletId() {
        return walletId;
    }

    public void setWalletId(Long walletId) {
        this.walletId = walletId;
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

    public Long getTransactionTypeId() {
        return transactionTypeId;
    }

    public void setTransactionTypeId(Long transactionTypeId) {
        this.transactionTypeId = transactionTypeId;
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
}
