package com.bank.tickets;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByActiveTrueOrderByCreatedAsc();
    Optional<Ticket> findFirstByActiveTrueOrderByCreatedAsc();
    int countByActive(boolean active);
}

