import java.util.*;

public class CafeteriaSystem {
    private final Map<String, MenuItem> menu = new LinkedHashMap<>();
    OrderRepo repo;

    CafeteriaSystem(OrderRepo repo) {
        this.repo = repo;
    }

    private int invoiceSeq = 1000;

    public void addToMenu(MenuItem i) { menu.put(i.id, i); }

    // Intentionally SRP-violating: menu mgmt + tax + discount + format + persistence.
    public void checkout(String customerType, List<OrderLine> lines) {
        
        String invId = "INV-" + (++invoiceSeq);

        // Tax Calculation
        TaxCalculator calculator = new TaxCalculator();
        double[] res = calculator.calculateTax(lines, menu, customerType);
        double tax = res[0];
        double taxPct = res[1];
        double subtotal = res[2];

        // Discount Calculation
        DiscountCalculator discountCalculator = new DiscountCalculator();
        double discount = discountCalculator.calculateDiscount(customerType, subtotal, invoiceSeq);

        // formatting
        Formatter ser = new Formatter();
        StringBuilder invoice = ser.printInvoice(lines, menu, invoiceSeq, tax, taxPct, discount);

        String printable = InvoiceFormatter.identityFormat(invoice.toString());
        System.out.print(printable);

        repo.saveToDB(invId, printable);
        System.out.println("Saved invoice: " + invId + " (lines=" + repo.countLines(invId) + ")");
    }
}
