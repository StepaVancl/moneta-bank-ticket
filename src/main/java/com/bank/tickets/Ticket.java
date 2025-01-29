package com.bank.tickets;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tickets")
@Getter
@Setter
@NoArgsConstructor
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime created;

    @Column(nullable = false)
    private boolean active = true;

    public Ticket(LocalDateTime created) {
        this.created = created;
        this.active = true;
    }
}

