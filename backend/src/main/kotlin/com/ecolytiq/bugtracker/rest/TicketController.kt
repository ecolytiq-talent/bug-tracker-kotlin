package com.ecolytiq.bugtracker.rest

import com.ecolytiq.bugtracker.domain.TicketService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin
class TicketController @Autowired constructor(
    private val ticketService: TicketService,
    private val ticketDtoConverter: TicketDtoConverter,
) {

    @GetMapping(path = ["/api/v1/tickets"], produces = [APPLICATION_JSON_VALUE])
    fun getAllTickets(): ResponseEntity<List<TicketDto>> {
        val allTickets = ticketService.getAllTickets()

        val response = allTickets.map { ticketDtoConverter.convertToTicketDto(it) }

        return ResponseEntity.ok(response)
    }
}
