package rs.ac.metropolitan.kladionicarest.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.metropolitan.kladionicarest.dto.TicketDTO;
import rs.ac.metropolitan.kladionicarest.model.Ticket;
import rs.ac.metropolitan.kladionicarest.repository.TicketRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/ticket", produces = MediaType.APPLICATION_JSON_VALUE)
public class TicketRESTController {

    @Autowired
    private TicketRepository ticketRepository;

    @GetMapping
    public ResponseEntity<List<Ticket>> getTickets() {
        List<Ticket> tickets = ticketRepository.findAll();
        if (tickets.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }

    @GetMapping(value = "{ticketId}")
    public ResponseEntity<Ticket> getTicketById(@PathVariable("ticketId") long id) {
        Optional<Ticket> ticket = ticketRepository.findById(id);

        if (!ticket.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ticket.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> addTicket(@RequestBody TicketDTO ticketDTO) {
        Ticket newTicket = new Ticket(ticketDTO.getTicketTotalOdd(),
                ticketDTO.getTicketTotalAmount(), ticketDTO.getTicketStatus());
        ticketRepository.saveAndFlush(newTicket);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value = "{ticketId}")
    public ResponseEntity<Ticket> updateTicket(@PathVariable("ticketId") long id, @RequestBody TicketDTO newTicket) {
        Optional<Ticket> ticket = ticketRepository.findById(id);
        if (!ticket.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        ticket.get().setTicketTotalOdd(newTicket.getTicketTotalOdd());
        ticket.get().setTicketAmount(newTicket.getTicketTotalAmount());
        ticket.get().setTicketStatus(newTicket.getTicketStatus());

        ticketRepository.saveAndFlush(ticket.get());
        return new ResponseEntity<>(ticket.get(), HttpStatus.OK);
    }

    @DeleteMapping(value = "{ticketId}")
    public ResponseEntity<Void> deleteTicket(@PathVariable("ticketId") long id) {
        Optional<Ticket> ticket = ticketRepository.findById(id);
        if (!ticket.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ticketRepository.delete(ticket.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
