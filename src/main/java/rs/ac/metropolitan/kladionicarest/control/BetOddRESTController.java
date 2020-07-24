package rs.ac.metropolitan.kladionicarest.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.metropolitan.kladionicarest.config.Path;
import rs.ac.metropolitan.kladionicarest.dto.BetOddDTO;
import rs.ac.metropolitan.kladionicarest.model.BetOdd;
import rs.ac.metropolitan.kladionicarest.repository.BetOddRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(Path.API)
public class BetOddRESTController {

    @Autowired
    private BetOddRepository betOddRepository;

    @GetMapping(Path.BETODDS)
    public ResponseEntity<List<BetOdd>> getBetOdds() {
        List<BetOdd> betOdds = betOddRepository.findAll();
        if (betOdds.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(betOdds, HttpStatus.OK);
    }

    @GetMapping(Path.BETODD_ID)
    public ResponseEntity<BetOdd> getBetOdd(@PathVariable("betOddId") long id) {
        Optional<BetOdd> betOdd = betOddRepository.findById(id);
        if (!betOdd.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(betOdd.get(), HttpStatus.OK);
    }

    @PostMapping(Path.BETODDS)
    public ResponseEntity<Void> addBetOdd(@RequestBody BetOddDTO betOddDTO) {
        BetOdd betOdd = new BetOdd();
        betOdd.setBetOddName(betOddDTO.getBetOddName());
        betOdd.setBetOddInfo(betOddDTO.getBetOddInfo());
        betOddRepository.saveAndFlush(betOdd);
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PutMapping(Path.BETODD_ID)
    public ResponseEntity<BetOdd> updateBetOdd(@PathVariable("betOddId") long id, @RequestBody BetOddDTO betOddDTO) {
        Optional<BetOdd> b = betOddRepository.findById(id);
        if(!b.isPresent()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        b.get().setBetOddName(betOddDTO.getBetOddName());
        b.get().setBetOddInfo(betOddDTO.getBetOddInfo());
        betOddRepository.saveAndFlush(b.get());
        return new ResponseEntity<>(b.get(), HttpStatus.OK);
    }

    @DeleteMapping(Path.BETODD_ID)
    public ResponseEntity<BetOdd> deleteBetOdd(@PathVariable("betOddId") long id) {
        Optional<BetOdd> betOdd = betOddRepository.findById(id);
        if (!betOdd.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        betOddRepository.delete(betOdd.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
