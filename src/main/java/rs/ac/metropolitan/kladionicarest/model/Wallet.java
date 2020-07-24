package rs.ac.metropolitan.kladionicarest.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "wallet")
public class Wallet {

    @Id
    @Column(name = "walletId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "walletBalance")
    private double walletBalance;

    @Column(name = "walletStatus")
    private String walletStatus;

    @JsonManagedReference
    @OneToMany(mappedBy = "wallet")
    private Set<Transaction> transactions;

    public Wallet() {
    }

    public Set<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(Set<Transaction> transactions) {
        this.transactions = transactions;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getWalletBalance() {
        return walletBalance;
    }

    public void setWalletBalance(double walletBalance) {
        this.walletBalance = walletBalance;
    }

    public String getWalletStatus() {
        return walletStatus;
    }

    public void setWalletStatus(String walletStatus) {
        this.walletStatus = walletStatus;
    }

    @Override
    public String toString() {
        return "Wallet{" +
                "id=" + id +
                ", walletBalance=" + walletBalance +
                ", walletStatus='" + walletStatus + '\'' +
                '}';
    }
}
