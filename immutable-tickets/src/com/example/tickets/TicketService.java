package com.example.tickets;

import java.util.ArrayList;
import java.util.List;

/**
 * Service layer that creates tickets.
 *
 * CURRENT STATE (BROKEN ON PURPOSE):
 * - creates partially valid objects
 * - mutates after creation (bad for auditability)
 * - validation is scattered & incomplete
 *
 * TODO (student):
 * - After introducing immutable IncidentTicket + Builder, refactor this to stop mutating.
 */
public class TicketService {

    public IncidentTicket createTicket(String id, String reporterEmail, String title) {
        
        List<String> tags = new ArrayList<>();
        tags.add("NEW");
        IncidentTicket t = new IncidentTicket.Builder()
        .id(id)
        .reporterEmail(reporterEmail)
        .title(title)
        .priority("MEDIUM")
        .source("CLI")
        .visibility(false)
        .tags(tags)
        .build();

        return t;
    }

    public IncidentTicket escalateToCritical(IncidentTicket t) {
        // Create a new immutable ticket with updated priority and tags
        List<String> updatedTags = new ArrayList<>(t.getTags());
        updatedTags.add("ESCALATED");
        return t.toBuilder()
            .priority("CRITICAL")
            .tags(updatedTags)
            .build();
    }

    public IncidentTicket assign(IncidentTicket t, String assigneeEmail) {
        // scattered validation
        if (assigneeEmail != null && !assigneeEmail.contains("@")) {
            throw new IllegalArgumentException("assigneeEmail invalid");
        }
        return t.toBuilder()
            .assigneeEmail(assigneeEmail)
            .build();
    }
}
