package DiscountRules;

public class DiscountData {
    private double subtotal;
    private int distinctLines;

    public DiscountData(double subtotal, int distinctLines) {
        this.subtotal = subtotal;
        this.distinctLines = distinctLines;
    }

    public double getSubTotal() { return this.subtotal; }
    public int getDistinctLines() { return this.distinctLines; }
}
