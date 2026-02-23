import java.util.List;
import java.util.Map;

public class Formatter {
    
    StringBuilder printInvoice(List<OrderLine> lines, Map<String, MenuItem> menu, int invoiceSeq, double tax, double taxPct, double discount) {
        String invId = "INV-" + (invoiceSeq);

        StringBuilder out = new StringBuilder();
        out.append("Invoice# ").append(invId).append("\n");

        double subtotal = 0.0;
        for (OrderLine l : lines) {
            MenuItem item = menu.get(l.itemId);
            double lineTotal = item.price * l.qty;
            subtotal += lineTotal;
            out.append(String.format("- %s x%d = %.2f\n", item.name, l.qty, lineTotal));
        }

        double total = subtotal + tax - discount;
        out.append(String.format("Subtotal: %.2f\n", subtotal));
        out.append(String.format("Tax(%.0f%%): %.2f\n", taxPct, tax));
        out.append(String.format("Discount: -%.2f\n", discount));
        out.append(String.format("TOTAL: %.2f\n", total));
        return out;
    }
}
