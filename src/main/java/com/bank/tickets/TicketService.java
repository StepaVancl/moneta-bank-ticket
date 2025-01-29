package com.bank.tickets;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Transactional
    public TicketDTO createTicket() {
        int countTickets = ticketRepository.countByActive(true);
        Ticket ticket = new Ticket(LocalDateTime.now());
        ticketRepository.save(ticket);
        return new TicketDTO(ticket, countTickets);
    }

    @Transactional(readOnly = true)
    public Optional<Ticket> getCurrentTicket() {
        return ticketRepository.findFirstByActiveTrueOrderByCreatedAsc();
    }

    @Transactional
    public boolean deleteOldestTicket() {
        return ticketRepository.findFirstByActiveTrueOrderByCreatedAsc()
                .map(ticket -> {
                    ticket.setActive(false);
                    ticketRepository.save(ticket);
                    return true;
                })
                .orElse(false);
    }

    @Transactional(readOnly = true)
    public List<TicketDTO> getActiveTickets() {
        List<Ticket> activeTickets = ticketRepository.findByActiveTrueOrderByCreatedAsc();

        return IntStream.range(0, activeTickets.size())
                .mapToObj(i -> new TicketDTO(activeTickets.get(i), i))
                .toList();
    }
}

