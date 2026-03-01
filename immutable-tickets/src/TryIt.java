import com.example.tickets.IncidentTicket;
import com.example.tickets.TicketService;

import java.util.List;

/**
 * Starter demo that shows why mutability is risky.
 *
 * After refactor:
 * - direct mutation should not compile (no setters)
 * - external modifications to tags should not affect the ticket
 * - service "updates" should return a NEW ticket instance
 */
public class TryIt {

    public static void main(String[] args) {
        TicketService service = new TicketService();

        IncidentTicket t = service.createTicket("TCK-1001", "reporter@example.com", "Payment failing on checkout");
        System.out.println("Created: " + t);

        // Demonstrate immutability: service returns NEW ticket instances
        IncidentTicket assigned = service.assign(t, "agent@example.com");
        IncidentTicket escalated = service.escalateToCritical(assigned);
        System.out.println("\nOriginal unchanged: " + t);
        System.out.println("After assign: " + assigned);
        System.out.println("After escalate: " + escalated);

        // Demonstrate external mutation protection: tags list is immutable
        try {
            List<String> tags = t.getTags();
            tags.add("HACKED_FROM_OUTSIDE");
            System.out.println("\nERROR: Should not be able to modify tags!");
        } catch (UnsupportedOperationException e) {
            System.out.println("\n✓ Tags are protected from external modification");
        }
    }
}
