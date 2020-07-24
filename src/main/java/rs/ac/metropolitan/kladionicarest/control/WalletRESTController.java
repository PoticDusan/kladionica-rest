package rs.ac.metropolitan.kladionicarest.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.metropolitan.kladionicarest.config.Path;
import rs.ac.metropolitan.kladionicarest.dto.WalletDTO;
import rs.ac.metropolitan.kladionicarest.model.Transaction;
import rs.ac.metropolitan.kladionicarest.model.Wallet;
import rs.ac.metropolitan.kladionicarest.repository.TransactionRepository;
import rs.ac.metropolitan.kladionicarest.repository.WalletRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(Path.API)
public class WalletRESTController {

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @GetMapping(Path.WALLETS)
    public ResponseEntity<List<Wallet>> getWallets() {
        List<Wallet> wallets = walletRepository.findAll();
        if (wallets.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(wallets, HttpStatus.OK);
    }

    @GetMapping(Path.WALLET_ID)
    public ResponseEntity<Wallet> getWallet(@PathVariable("walletId") long id) {
        Optional<Wallet> wallet = walletRepository.findById(id);
        if (!wallet.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(wallet.get(), HttpStatus.OK);
    }

    @PostMapping(Path.WALLETS)
    public ResponseEntity<Void> addWallet(@RequestBody WalletDTO walletDTO) {
        Wallet w = new Wallet();
        w.setWalletBalance(walletDTO.getWalletBalance());
        w.setWalletStatus(walletDTO.getWalletStatus());
        walletRepository.saveAndFlush(w);
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PutMapping(Path.WALLET_ID)
    public ResponseEntity<Wallet> updateWallet(@PathVariable("walletId") long id, @RequestBody WalletDTO walletDTO) {
        Optional<Wallet> wallet = walletRepository.findById(id);
        if(!wallet.isPresent()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        wallet.get().setWalletBalance(walletDTO.getWalletBalance());
        wallet.get().setWalletStatus(walletDTO.getWalletStatus());
        walletRepository.saveAndFlush(wallet.get());
        return new ResponseEntity<>(wallet.get(), HttpStatus.OK);
    }

    @DeleteMapping(Path.WALLET_ID)
    public ResponseEntity<Void> deleteWallet(@PathVariable("walletId") long id) {
        Optional<Wallet> wallet = walletRepository.findById(id);
        if (!wallet.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        walletRepository.delete(wallet.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // wallet-transaction methods

    @GetMapping(Path.WALLETTRANSACTION)
    public ResponseEntity<List<Transaction>> getTransactionsByTransactionTypeId(@PathVariable("walletId") long walletId) {
        List<Transaction> transactions = transactionRepository.findAllByWalletId(walletId);
        if (transactions.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }
}
