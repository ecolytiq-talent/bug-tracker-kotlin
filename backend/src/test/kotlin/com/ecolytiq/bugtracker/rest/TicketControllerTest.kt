package com.ecolytiq.bugtracker.rest

import com.ecolytiq.bugtracker.domain.Status.CODE_REVIEW
import com.ecolytiq.bugtracker.domain.Status.OPEN
import com.ecolytiq.bugtracker.domain.Ticket
import com.ecolytiq.bugtracker.domain.TicketService
import org.hamcrest.Matchers.hasSize
import org.junit.jupiter.api.Test
import org.mockito.kotlin.whenever
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import java.time.OffsetDateTime

@SpringBootTest
@AutoConfigureMockMvc
@MockBean(TicketService::class)
class TicketControllerTest @Autowired constructor(
    private val mockMvc: MockMvc,
    private val ticketService: TicketService,
) {

    @Test
    fun getAllTickets_noTicketsArePresent_returnsEmptyList() {
        whenever(ticketService.getAllTickets()).thenReturn(emptyList())

        mockMvc.get("/api/v1/tickets")
            .andExpect {
                status { isOk() }
                content { contentType(APPLICATION_JSON) }
                content { json("[]") }
            }
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
        whenever(ticketService.getAllTickets()).thenReturn(givenTickets)

        mockMvc.get("/api/v1/tickets")
            .andExpect {
                status { isOk() }
                content { contentType(APPLICATION_JSON) }
                jsonPath("$") { hasSize<Int>(2) }
                jsonPath("$[0].id") { value("BUG-123") }
                jsonPath("$[0].title") { value("Save Button not responding") }
                jsonPath("$[0].status") { value("CODE_REVIEW") }
                jsonPath("$[0].created_at") { value("2023-02-15T10:25:43.785+01:00") }
                jsonPath("$[1].id") { value("BUG-456") }
                jsonPath("$[1].title") { value("Incorrect password reset link") }
                jsonPath("$[1].status") { value("OPEN") }
                jsonPath("$[1].created_at") { value("2023-02-27T15:36:26.517+01:00") }
            }
    }
}
