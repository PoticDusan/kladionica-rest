package rs.ac.metropolitan.kladionicarest.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.metropolitan.kladionicarest.model.Transaction;
import rs.ac.metropolitan.kladionicarest.model.TransactionType;
import rs.ac.metropolitan.kladionicarest.repository.TransactionRepository;
import rs.ac.metropolitan.kladionicarest.repository.TransactionTypeRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/transactionType", produces = MediaType.APPLICATION_JSON_VALUE)
public class TransactionTypeRESTController {

    @Autowired
    private TransactionTypeRepository transactionTypeRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @GetMapping
    public ResponseEntity<List<TransactionType>> getTransactionTypes() {
        List<TransactionType> transactionTypes = transactionTypeRepository.findAll();
        if (transactionTypes.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(transactionTypes, HttpStatus.OK);
    }

    @GetMapping(value = "{transactionTypeId}")
    public ResponseEntity<TransactionType> getTransactionTypes(@PathVariable("transactionTypeId") long id) {
        Optional<TransactionType> transactionType = transactionTypeRepository.findById(id);
        if (!transactionType.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(transactionType.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> addTransactionType(@RequestBody TransactionType transactionType) {
        transactionTypeRepository.saveAndFlush(transactionType);
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PutMapping(value = "{transactionTypeId}")
    public ResponseEntity<TransactionType> updateTransactionType(@PathVariable("transactionTypeId") long id, @RequestBody TransactionType transactionType) {
        transactionType.setId(id);
        transactionTypeRepository.saveAndFlush(transactionType);
        return new ResponseEntity<>(transactionType, HttpStatus.OK);
    }

    @DeleteMapping(value = "{transactionTypeId}")
    public ResponseEntity<TransactionType> deleteRole(@PathVariable("transactionTypeId") long id) {
        Optional<TransactionType> transactionType = transactionTypeRepository.findById(id);
        if (!transactionType.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        transactionTypeRepository.delete(transactionType.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // transaction-transactionType methods


    @GetMapping(value = "{transactionTypeId}/transaction")
    public ResponseEntity<List<Transaction>> getTransactionsByTransactionTypeId(@PathVariable("transactionTypeId") long transactionTypeId) {
        List<Transaction> transactions = transactionRepository.findAllByTransactionTypeId(transactionTypeId);
        if (transactions.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

}
