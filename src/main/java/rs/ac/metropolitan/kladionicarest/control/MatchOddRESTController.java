package rs.ac.metropolitan.kladionicarest.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.metropolitan.kladionicarest.dto.MatchOddDTO;
import rs.ac.metropolitan.kladionicarest.model.BetOdd;
import rs.ac.metropolitan.kladionicarest.model.Match;
import rs.ac.metropolitan.kladionicarest.model.MatchOdd;
import rs.ac.metropolitan.kladionicarest.repository.BetOddRepository;
import rs.ac.metropolitan.kladionicarest.repository.MatchOddRepository;
import rs.ac.metropolitan.kladionicarest.repository.MatchRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
@RestController
@RequestMapping(value = "/matchOdd", produces = MediaType.APPLICATION_JSON_VALUE)
public class MatchOddRESTController {

    @Autowired
    private MatchOddRepository matchOddRepository;

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private BetOddRepository betOddRepository;

    @GetMapping
    public ResponseEntity<List<MatchOdd>> getAllMatchOdds() {
        List<MatchOdd> matchOddList = matchOddRepository.findAll();

        if (matchOddList.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(matchOddList, HttpStatus.OK);
    }

    @GetMapping(value = "{matchId}")
    public ResponseEntity<List<MatchOdd>> getMatchOddsByMatchId(@PathVariable("matchId") long id) {
        List<MatchOdd> matchOddList = matchOddRepository.getAllByMatchId(id);

        if (matchOddList.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(matchOddList, HttpStatus.OK);
    }

    @PutMapping(value = "{matchId}")
    public ResponseEntity<List<MatchOdd>> updateMatchOddsByMatchId(@PathVariable("matchId") long id, @RequestBody List<MatchOddDTO> newMatchOddDTOList) {
        //find match
        Optional<Match> match = matchRepository.findById(id);
        if (!match.isPresent()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        //check and find requested list
        if (newMatchOddDTOList.size() < 1) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        List<MatchOdd> curtMatchOddList = matchOddRepository.getAllByMatchId(id);

        //check and get every betOdd from request
        List<MatchOdd> newMatchOddList = new ArrayList<>();
        for (MatchOddDTO matchOddDTO : newMatchOddDTOList) {
            Optional<BetOdd> betOdd = betOddRepository.findById(matchOddDTO.getBetOddId());
            if (!betOdd.isPresent()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            newMatchOddList.add(new MatchOdd(match.get(), betOdd.get(), matchOddDTO.getMatchOddValue(), matchOddDTO.isMatchOddEnabled()));
        }

        //delete previous matchOdds
        matchOddRepository.deleteAll(curtMatchOddList);
        matchOddRepository.flush();

        //add new matchOdds
        match.get().setMatchOdds(newMatchOddList);
        matchRepository.saveAndFlush(match.get());

        return new ResponseEntity<>(match.get().getMatchOdds(), HttpStatus.OK);
    }

    @DeleteMapping(value = "{matchId}")
    public ResponseEntity<Void> deleteMatchOddsByMatchId(@PathVariable("matchId") long id) {
        List<MatchOdd> matchOddList = matchOddRepository.getAllByMatchId(id);
        if (matchOddList.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        for (MatchOdd matchOdd : matchOddList) {
            matchOddRepository.delete(matchOdd);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
