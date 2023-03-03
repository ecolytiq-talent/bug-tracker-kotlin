package com.ecolytiq.bugtracker.rest

import com.ecolytiq.bugtracker.domain.Ticket
import org.springframework.stereotype.Component

@Component
class TicketDtoConverter {

    fun convertToTicketDto(ticket: Ticket) = TicketDto(
        id = ticket.id,
        title = ticket.title,
        status = ticket.status,
        createdAt = ticket.createdAt,
    )
}
