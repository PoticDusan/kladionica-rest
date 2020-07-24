package rs.ac.metropolitan.kladionicarest.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.metropolitan.kladionicarest.config.Path;
import rs.ac.metropolitan.kladionicarest.dto.TeamDTO;
import rs.ac.metropolitan.kladionicarest.model.MatchTeam;
import rs.ac.metropolitan.kladionicarest.model.Sport;
import rs.ac.metropolitan.kladionicarest.model.Team;
import rs.ac.metropolitan.kladionicarest.repository.MatchTeamRepository;
import rs.ac.metropolitan.kladionicarest.repository.SportRepository;
import rs.ac.metropolitan.kladionicarest.repository.TeamRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(Path.API)
public class TeamRESTController {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private SportRepository sportRepository;

    @Autowired
    private MatchTeamRepository matchTeamRepository;

    @GetMapping(Path.TEAMS)
    public ResponseEntity<List<Team>> getTeams() {
        List<Team> teams = teamRepository.findAll();
        if (teams.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(teams, HttpStatus.OK);
    }

    @GetMapping(Path.TEAM_ID)
    public ResponseEntity<Team> getTeam(@PathVariable("teamId") long id) {
        Optional<Team> team = teamRepository.findById(id);
        if (!team.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(team.get(), HttpStatus.OK);
    }

    @PostMapping(Path.TEAMS)
    public ResponseEntity<Void> addTeam(@RequestBody TeamDTO team) {
        if (teamRepository.countAllByTeamName(team.getTeamName()) == 0) {
            Optional<Sport> sport = sportRepository.findById(team.getSportId());
            if (!sport.isPresent()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            Team newTeam = new Team();
            newTeam.setTeamName(team.getTeamName());
            newTeam.setSport(sport.get());

            teamRepository.saveAndFlush(newTeam);
            HttpHeaders headers = new HttpHeaders();
            return new ResponseEntity<>(headers, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(Path.TEAM_ID)
    public ResponseEntity<Team> updateTeam(@PathVariable("teamId") long id, @RequestBody TeamDTO newTeam) {
        Optional<Team> team = teamRepository.findById(id);
        if (!team.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Optional<Sport> sport = sportRepository.findById(newTeam.getSportId());
        if (!sport.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        team.get().setTeamName(newTeam.getTeamName());
        team.get().setSport(sport.get());

        teamRepository.saveAndFlush(team.get());
        return new ResponseEntity<>(team.get(), HttpStatus.OK);
    }

    //Team - Match methods

    @GetMapping(Path.TEAMMATCHES)
    public ResponseEntity<List<MatchTeam>> getAllMatchTeamByTeamId(@PathVariable("teamId") long id) {
        List<MatchTeam> matchTeamList = matchTeamRepository.getAllByTeamId(id);
        if (matchTeamList.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(matchTeamList, HttpStatus.OK);
    }
}
