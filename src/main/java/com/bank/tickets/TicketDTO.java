package com.bank.tickets;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class TicketDTO {
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime created;

    private int order;

    public TicketDTO(Ticket ticket, int order) {
        this.id = ticket.getId();
        this.created = ticket.getCreated();
        this.order = order;
    }
}

