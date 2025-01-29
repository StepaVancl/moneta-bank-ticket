package com.bank.tickets;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping
    public ResponseEntity<TicketDTO> createTicket() {
        TicketDTO ticket = ticketService.createTicket();
        return ResponseEntity.status(201).body(ticket);
    }

    @GetMapping("/active")
    public ResponseEntity<List<TicketDTO>> getActiveTickets() {
        List<TicketDTO> tickets = ticketService.getActiveTickets();
        return ResponseEntity.ok(tickets);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteOldestTicket() {
        boolean deleted = ticketService.deleteOldestTicket();
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
