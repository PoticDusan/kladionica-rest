package rs.ac.metropolitan.kladionicarest.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.metropolitan.kladionicarest.model.Event;
import rs.ac.metropolitan.kladionicarest.model.Sport;
import rs.ac.metropolitan.kladionicarest.model.Team;
import rs.ac.metropolitan.kladionicarest.repository.EventRepository;
import rs.ac.metropolitan.kladionicarest.repository.SportRepository;
import rs.ac.metropolitan.kladionicarest.repository.TeamRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/sport", produces = MediaType.APPLICATION_JSON_VALUE)
public class SportRESTController {

    @Autowired
    private SportRepository sportRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private EventRepository eventRepository;

    @GetMapping
    public ResponseEntity<List<Sport>> getSports() {
        List<Sport> sports = sportRepository.findAll();
        if (sports.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(sports, HttpStatus.OK);
    }

    @GetMapping(value = "{sportId}")
    public ResponseEntity<Sport> getSport(@PathVariable("sportId") long id) {
        Optional<Sport> sports = sportRepository.findById(id);
        if (!sports.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(sports.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> addSport(@RequestBody Sport sport) {
        sportRepository.saveAndFlush(sport);
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PutMapping(value = "{sportId}")
    public ResponseEntity<Sport> updateSport(@PathVariable("sportId") long id, @RequestBody Sport sport) {
        sport.setId(id);
        sportRepository.saveAndFlush(sport);
        return new ResponseEntity<>(sport, HttpStatus.OK);
    }

    @DeleteMapping(value = "{sportId}")
    public ResponseEntity<Void> deleteSport(@PathVariable("sportId") long id) {
        Optional<Sport> sport = sportRepository.findById(id);
        if (!sport.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        sportRepository.delete(sport.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // sport-team control methods

    @GetMapping(value = "{sportId}/team")
    public ResponseEntity<List<Team>> getTeamsBySportId(@PathVariable("sportId") long sportId) {
        List<Team> teams = teamRepository.findAllBySportId(sportId);
        if (teams.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(teams, HttpStatus.OK);
    }

    // sport-event control methods

    @GetMapping(value = "{sportId}/event")
    public ResponseEntity<List<Event>> getEventsBySportId(@PathVariable("sportId") long sportId) {
        List<Event> events = eventRepository.findAllBySportId(sportId);
        if (events.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(events, HttpStatus.OK);
    }
}
