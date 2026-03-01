public class TransportBookingService {
    private Calculator calculator;
    private Allocator allocator;
    private Gateway gateway;
    public TransportBookingService(Calculator calculator, Allocator allocator, Gateway gateway) {
        this.calculator = calculator;
        this.allocator = allocator;
        this.gateway = gateway;
    }
    // DIP violation: direct concretes
    public void book(TripRequest req) {

        double km = calculator.km(req.from, req.to);
        System.out.println("DistanceKm=" + km);

        String driver = allocator.allocate(req.studentId);
        System.out.println("Driver=" + driver);

        double fare = 50.0 + km * 6.6666666667; // messy pricing
        fare = Math.round(fare * 100.0) / 100.0;

        String txn = gateway.charge(req.studentId, fare);
        System.out.println("Payment=PAID txn=" + txn);

        BookingReceipt r = new BookingReceipt("R-501", fare);
        System.out.println("RECEIPT: " + r.id + " | fare=" + String.format("%.2f", r.fare));
    }
}
