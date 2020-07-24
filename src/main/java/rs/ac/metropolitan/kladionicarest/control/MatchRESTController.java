package rs.ac.metropolitan.kladionicarest.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.metropolitan.kladionicarest.config.Path;
import rs.ac.metropolitan.kladionicarest.dto.BetOptionDTO;
import rs.ac.metropolitan.kladionicarest.dto.MatchDTO;
import rs.ac.metropolitan.kladionicarest.dto.TeamDTO;
import rs.ac.metropolitan.kladionicarest.dto.TeamOptionDTO;
import rs.ac.metropolitan.kladionicarest.model.*;
import rs.ac.metropolitan.kladionicarest.repository.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
@RestController
@RequestMapping(Path.API)
public class MatchRESTController {

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private MatchTeamRepository matchTeamRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private MatchOddRepository matchOddRepository;

    @Autowired
    private BetOddRepository betOddRepository;

    @GetMapping(Path.MATCHES)
    public ResponseEntity<List<Match>> getMatches() {
        List<Match> matches = matchRepository.findAll();
        if (matches.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(matches, HttpStatus.OK);
    }

    @GetMapping(Path.MATCH_ID)
    public ResponseEntity<Match> getMatchById(@PathVariable("matchId") long id) {
        Optional<Match> match = matchRepository.findById(id);

        if (!match.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(match.get(), HttpStatus.OK);
    }

    @PostMapping(Path.MATCHES)
    public ResponseEntity<Void> addMatch(@RequestBody MatchDTO newMatch) {

        //finding event
        Optional<Event> event = eventRepository.findById(newMatch.getEventId());
        if (!event.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Match match = new Match(event.get(), newMatch.getMatchDate(), newMatch.getMatchTime(),
                newMatch.getMatchStatus(), newMatch.getMatchInfo());

        if(newMatch.getTeamOptionDTOS().size() != 2) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        //adding teams
        Team team1 = new Team();
        List<MatchTeam> matchTeamList = new ArrayList<>();
        for (TeamOptionDTO teamOptionDTO : newMatch.getTeamOptionDTOS()) {
            Optional<Team> team = teamRepository.findById(teamOptionDTO.getTeamId());
            if (!team.isPresent()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            if(team1 == null) team1.setId(team.get().getId());
            else if(team1.getId() == team.get().getId()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            matchTeamList.add(new MatchTeam(match, team.get(), teamOptionDTO.getTeamScore(), teamOptionDTO.getTeamAtHome()));
        }

        //adding matchOdds
        List<MatchOdd> matchOddList = new ArrayList<>();
        for (BetOptionDTO betOptionDTO : newMatch.getBetOptionDTOS()) {
            Optional<BetOdd> betOdd = betOddRepository.findById(betOptionDTO.getBetOddId());
            if (!betOdd.isPresent()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            matchOddList.add(new MatchOdd(match, betOdd.get(), betOptionDTO.getMatchOddValue(), betOptionDTO.isMatchOddEnabled()));
        }

        match.setMatchTeams(matchTeamList);
        match.setMatchOdds(matchOddList);
        matchRepository.saveAndFlush(match);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(Path.MATCH_ID)
    public ResponseEntity<Match> updateMatch(@PathVariable("matchId") long id, @RequestBody MatchDTO newMatch) {
        //finding match
        Optional<Match> match = matchRepository.findById(id);
        if (!match.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        //finding event
        Optional<Event> event = eventRepository.findById(newMatch.getEventId());
        if (!event.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        //setting match
        match.get().setEvent(event.get());
        match.get().setMatchDate(newMatch.getMatchDate());
        match.get().setMatchTime(newMatch.getMatchTime());
        match.get().setMatchStatus(newMatch.getMatchStatus());
        match.get().setMatchInfo(newMatch.getMatchInfo());
        match.get().setMatchTeams(new ArrayList<>());

        //adding matchTeams
        if (newMatch.getTeamOptionDTOS().size() != 2) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        List<MatchTeam> matchTeamList = new ArrayList<>();
        for (TeamOptionDTO teamOptionDTO : newMatch.getTeamOptionDTOS()) {
            Optional<Team> team = teamRepository.findById(teamOptionDTO.getTeamId());
            if (!team.isPresent()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            matchTeamList.add(new MatchTeam(match.get(), team.get(), teamOptionDTO.getTeamScore(),
                    teamOptionDTO.getTeamAtHome()));
        }

        //adding matchOdds
        if (newMatch.getBetOptionDTOS().size() < 1) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        List<MatchOdd> matchOddList = new ArrayList<>();
        for (BetOptionDTO betOptionDTO : newMatch.getBetOptionDTOS()) {
            Optional<BetOdd> betOdd = betOddRepository.findById(betOptionDTO.getBetOddId());
            if (!betOdd.isPresent()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            matchOddList.add(new MatchOdd(match.get(), betOdd.get(), betOptionDTO.getMatchOddValue(),
                    betOptionDTO.isMatchOddEnabled()));
        }

        //deleting current lists
        matchTeamRepository.deleteMatchTeamsByMatchId(id);
        matchTeamRepository.flush();

        matchOddRepository.deleteMatchOddsByMatchId(id);
        matchOddRepository.flush();

        //adding new lists
        match.get().setMatchTeams(matchTeamList);
        match.get().setMatchOdds(matchOddList);

        matchRepository.saveAndFlush(match.get());
        return new ResponseEntity<>(match.get(), HttpStatus.OK);
    }

    @DeleteMapping(Path.MATCH_ID)
    public ResponseEntity<Void> deleteMatch(@PathVariable("matchId") long id) {
        Optional<Match> match = matchRepository.findById(id);
        if (!match.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        matchTeamRepository.deleteMatchTeamsByMatchId(id);
        matchTeamRepository.flush();
        matchRepository.delete(match.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(Path.MATCH_ID + Path.MATCHTEAM)
    public ResponseEntity<List<MatchTeam>> getAllMatchTeamByMatchId(@PathVariable("matchId") long id) {
        List<MatchTeam> matchTeamList = matchTeamRepository.getAllByMatchId(id);
        if (matchTeamList.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(matchTeamList, HttpStatus.OK);
    }
}
