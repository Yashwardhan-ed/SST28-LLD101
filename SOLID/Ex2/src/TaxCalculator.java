import java.util.HashMap;
import java.util.List;
import java.util.Map;

import TaxRules.NormalTaxRule;
import TaxRules.StaffTaxRule;
import TaxRules.StudentTaxRule;
import TaxRules.TaxRules;

public class TaxCalculator {
    private final Map<String, TaxRules> rulesByCustomerType = new HashMap<>();
    
    public TaxCalculator() {
        rulesByCustomerType.put("student", new StudentTaxRule());
        rulesByCustomerType.put("staff", new StaffTaxRule());
        rulesByCustomerType.put("normal", new NormalTaxRule());
    }

    double[] calculateTax(List<OrderLine> lines, Map<String, MenuItem> menu, String customerType) {
        double subtotal = 0.0;
        for(OrderLine l : lines) {
            MenuItem item = menu.get(l.itemId);
            double lineTotal = item.price * l.qty;
            subtotal += lineTotal;
        }
        TaxRules rule = rulesByCustomerType.get(customerType);
        double taxPct = rule.taxPercent();
        double tax = subtotal * (taxPct / 100.0);
        return new double[]{tax, taxPct, subtotal};
    }

}
