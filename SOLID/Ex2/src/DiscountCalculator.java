import java.util.HashMap;
import java.util.Map;

import DiscountRules.DiscountData;
import DiscountRules.DiscountRule;
import DiscountRules.NormalDiscountRule;
import DiscountRules.StaffDiscountRule;
import DiscountRules.StudentDiscountRule;

public class DiscountCalculator {
    private final Map<String, DiscountRule> rulesByCustomerType = new HashMap<>();
    private final DiscountRule defaultRule = new NormalDiscountRule();

    public DiscountCalculator() {
        rulesByCustomerType.put("student", new StudentDiscountRule());
        rulesByCustomerType.put("staff", new StaffDiscountRule());
        rulesByCustomerType.put("normal", new NormalDiscountRule());
    }

    public double calculateDiscount(String customerType, double subtotal, int distinctLines) {
        DiscountData data = new DiscountData(subtotal, distinctLines);
        DiscountRule rule = rulesByCustomerType.getOrDefault(
                customerType.toLowerCase(),
                defaultRule
        );
        return rule.discountAmount(data);
    }
}
