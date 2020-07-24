package rs.ac.metropolitan.kladionicarest.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.metropolitan.kladionicarest.model.MatchTeam;
import rs.ac.metropolitan.kladionicarest.repository.MatchRepository;
import rs.ac.metropolitan.kladionicarest.repository.MatchTeamRepository;
import rs.ac.metropolitan.kladionicarest.repository.TeamRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@RestController
@RequestMapping(value = "/matchTeam", produces = MediaType.APPLICATION_JSON_VALUE)
public class MatchTeamRESTController {

    @Autowired
    private MatchTeamRepository matchTeamRepository;

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private TeamRepository teamRepository;

    @GetMapping
    public ResponseEntity<List<MatchTeam>> getAllMatchTeams() {
        List<MatchTeam> matchTeamList = matchTeamRepository.findAll();

        if (matchTeamList.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(matchTeamList, HttpStatus.OK);
    }

    @DeleteMapping(value = "{matchId}")
    public ResponseEntity<Void> deleteMatchTeamsByMatchId(@PathVariable("matchId") long id) {
        List<MatchTeam> matchTeamList = matchTeamRepository.getAllByMatchId(id);
        if (matchTeamList.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        for (MatchTeam mt : matchTeamList) {
            matchTeamRepository.delete(mt);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
