package com.ecolytiq.bugtracker.domain

import com.ecolytiq.bugtracker.domain.Status.CODE_REVIEW
import com.ecolytiq.bugtracker.domain.Status.OPEN
import com.ecolytiq.bugtracker.persistence.TicketRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.time.OffsetDateTime

@SpringBootTest
class TicketServiceTest @Autowired constructor(
    private val ticketService: TicketService,
    private val ticketRepository: TicketRepository,
) {

    @BeforeEach
    fun cleanUp() {
        ticketRepository.deleteAll()
    }

    @Test
    fun getAllTickets_noTicketsArePresent_returnsEmptyList() {
        val actualTickets = ticketService.getAllTickets()

        assertThat(actualTickets).hasSize(0)
    }

    @Test
    fun getAllTickets_ticketsArePresent_returnsAllTickets() {
        val givenTickets = listOf(
            Ticket(
                id = "BUG-123",
                title = "Save Button not responding",
                status = CODE_REVIEW,
                createdAt = OffsetDateTime.parse("2023-02-15T10:25:43.785+01:00"),
            ),
            Ticket(
                id = "BUG-456",
                title = "Incorrect password reset link",
                status = OPEN,
                createdAt = OffsetDateTime.parse("2023-02-27T15:36:26.517+01:00"),
            ),
        )
        ticketRepository.saveAll(givenTickets)

        val actualTickets = ticketService.getAllTickets()

        assertThat(actualTickets).hasSize(2)
        assertThat(actualTickets).usingRecursiveComparison().isEqualTo(givenTickets)
    }
}
