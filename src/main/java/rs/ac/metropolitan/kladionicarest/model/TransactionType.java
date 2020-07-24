package rs.ac.metropolitan.kladionicarest.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "transactionType")
public class TransactionType {

    @Id
    @Column(name = "transactionTypeId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "transactionTypeName")
    private String transactionTypeName;

    @JsonManagedReference
    @OneToMany(mappedBy = "transactionType", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Transaction> transactions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTransactionTypeName() {
        return transactionTypeName;
    }

    public void setTransactionTypeName(String transactionTypeName) {
        this.transactionTypeName = transactionTypeName;
    }

    @Override
    public String toString() {
        return "TransactionType{" +
                "id=" + id +
                ", transactionTypeName='" + transactionTypeName + '\'' +
                '}';
    }
}
