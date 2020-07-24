package rs.ac.metropolitan.kladionicarest.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.metropolitan.kladionicarest.dto.EventDTO;
import rs.ac.metropolitan.kladionicarest.model.Event;
import rs.ac.metropolitan.kladionicarest.model.Sport;
import rs.ac.metropolitan.kladionicarest.repository.EventRepository;
import rs.ac.metropolitan.kladionicarest.repository.SportRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/event", produces = MediaType.APPLICATION_JSON_VALUE)
public class EventRESTController {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private SportRepository sportRepository;

    @GetMapping
    public ResponseEntity<List<Event>> getEvents() {
        List<Event> events = eventRepository.findAll();
        if (events.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @GetMapping(value = "{eventId}")
    public ResponseEntity<Event> getEvent(@PathVariable("eventId") long id) {
        Optional<Event> event = eventRepository.findById(id);
        if (!event.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(event.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> addEvent(@RequestBody EventDTO event) {
        Optional<Sport> sport = sportRepository.findById(event.getSportId());
        Event newEvent = new Event();
        newEvent.setEventName(event.getEventName());
        newEvent.setSport(sport.get());
        newEvent.setEventStatus(event.getEventStatus());
        eventRepository.saveAndFlush(newEvent);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "{eventId}")
    public ResponseEntity<Event> updateEvent(@PathVariable("eventId") long id, @RequestBody EventDTO newEvent) {
        Optional<Event> event = eventRepository.findById(id);
        if (!event.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Optional<Sport> sport = sportRepository.findById(newEvent.getSportId());
        if (!sport.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        event.get().setEventName(newEvent.getEventName());
        event.get().setEventStatus((newEvent.getEventStatus()));
        event.get().setSport(sport.get());

        eventRepository.saveAndFlush(event.get());
        return new ResponseEntity<>(event.get(), HttpStatus.OK);
    }

    @DeleteMapping(value = "{eventId}")
    public ResponseEntity<Void> deleteEvent(@PathVariable("eventId") long id) {
        Optional<Event> event = eventRepository.findById(id);
        if (!event.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        eventRepository.delete(event.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
