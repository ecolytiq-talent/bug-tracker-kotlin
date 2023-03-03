package com.ecolytiq.bugtracker.domain

import com.ecolytiq.bugtracker.persistence.TicketRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TicketService @Autowired constructor(
    private val ticketRepository: TicketRepository,
) {
    fun getAllTickets(): List<Ticket> {
        return ticketRepository.findAll()
    }
}
