package DiscountRules;

public class StaffDiscountRule implements DiscountRule {
    @Override
    public double discountAmount(DiscountData data) {
        if(data.getDistinctLines() >= 3) return 15.0;
        return 5.0;
    }
}
