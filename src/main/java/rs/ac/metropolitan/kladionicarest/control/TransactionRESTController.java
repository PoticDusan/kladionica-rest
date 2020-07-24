package rs.ac.metropolitan.kladionicarest.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.metropolitan.kladionicarest.dto.TransactionDTO;
import rs.ac.metropolitan.kladionicarest.model.Transaction;
import rs.ac.metropolitan.kladionicarest.model.TransactionType;
import rs.ac.metropolitan.kladionicarest.model.Wallet;
import rs.ac.metropolitan.kladionicarest.repository.TransactionRepository;
import rs.ac.metropolitan.kladionicarest.repository.TransactionTypeRepository;
import rs.ac.metropolitan.kladionicarest.repository.WalletRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/transaction", produces = MediaType.APPLICATION_JSON_VALUE)
public class TransactionRESTController {


    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private TransactionTypeRepository transactionTypeRepository;

    @Autowired
    private WalletRepository walletRepository;

    @GetMapping
    public ResponseEntity<List<Transaction>> getTransactions() {
        List<Transaction> transactions = transactionRepository.findAll();
        if (transactions.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @GetMapping(value = "{transactionId}")
    public ResponseEntity<Transaction> getTransaction(@PathVariable("transactionId") long id) {
        Optional<Transaction> transaction = transactionRepository.findById(id);
        if (!transaction.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(transaction.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> addTransaction(@RequestBody TransactionDTO transaction) {
        Optional<TransactionType> transactionType = transactionTypeRepository.findById(transaction.getTransactionTypeId());
        Optional<Wallet> wallet = walletRepository.findById(transaction.getWalletId());

        if (!transactionType.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (!wallet.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }

        Transaction newTransaction = new Transaction();
        java.util.Date date = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        java.sql.Time sqlTime = new java.sql.Time(date.getTime());

        newTransaction.setTransactionDate(sqlDate);
        newTransaction.setTransactionTime(sqlTime);
        newTransaction.setTransactionAmount(transaction.getTransactionAmount());
        newTransaction.setTransactionFromAccount(transaction.getTransactionFromAccount());
        newTransaction.setTransactionToAccount(transaction.getTransactionToAccount());
        newTransaction.setTransactionType(transactionType.get());
        newTransaction.setTransactionStatus(transaction.getTransactionStatus());
        transactionRepository.saveAndFlush(newTransaction);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value = "{transactionId}")
    public ResponseEntity<Transaction> updateEvent(@PathVariable("transactionId") long id, @RequestBody TransactionDTO newTransaction) {
        Optional<Transaction> transaction = transactionRepository.findById(id);
        if (!transaction.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Optional<TransactionType> transactionType = transactionTypeRepository.findById(newTransaction.getTransactionTypeId());
        if (!transactionType.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Optional<Wallet> wallet = walletRepository.findById(newTransaction.getWalletId());

        if (!wallet.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
        transaction.get().setTransactionAmount(newTransaction.getTransactionAmount());
        transaction.get().setTransactionFromAccount((newTransaction.getTransactionFromAccount()));
        transaction.get().setTransactionToAccount(newTransaction.getTransactionToAccount());
        transaction.get().setTransactionStatus(newTransaction.getTransactionStatus());

        transactionRepository.saveAndFlush(transaction.get());
        return new ResponseEntity<>(transaction.get(), HttpStatus.OK);
    }

    @DeleteMapping(value = "{transactionId}")
    public ResponseEntity<Void> deleteEvent(@PathVariable("transactionId") long id) {
        Optional<Transaction> transaction = transactionRepository.findById(id);
        if (!transaction.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        transactionRepository.delete(transaction.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
