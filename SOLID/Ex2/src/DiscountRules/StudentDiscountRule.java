package DiscountRules;

public class StudentDiscountRule implements DiscountRule {
    @Override
    public double discountAmount(DiscountData data) {
        if(data.getSubTotal() >= 180) return 10.0;
        return 0.0;
    }
}
