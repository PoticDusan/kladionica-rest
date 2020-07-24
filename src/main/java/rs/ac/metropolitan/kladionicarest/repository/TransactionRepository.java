package rs.ac.metropolitan.kladionicarest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.metropolitan.kladionicarest.model.Transaction;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findAllByTransactionTypeId(long transactionTypeId);

    List<Transaction> findAllByWalletId(long walletId);

}
