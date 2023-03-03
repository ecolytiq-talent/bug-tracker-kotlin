package com.ecolytiq.bugtracker.rest

import com.ecolytiq.bugtracker.domain.Status
import java.time.OffsetDateTime

data class TicketDto(

    val id: String?,
    val title: String?,
    val status: Status,
    val createdAt: OffsetDateTime,
)
