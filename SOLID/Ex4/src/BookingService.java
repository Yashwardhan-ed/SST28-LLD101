import java.util.Random;

public class BookingService {
    private final HostelFeeCalculator calculator;
    private final FakeBookingRepo repo;

    public BookingService(HostelFeeCalculator calculator, FakeBookingRepo repo) {
        this.calculator = calculator;
        this.repo = repo;
    }
    
    public void process(BookingRequest req) {
        BookingResult result = calculator.calculate(req);
        
        ReceiptPrinter.print(req, result.monthly, result.deposit);
        
        String bookingId = generateBookingId();
        repo.save(bookingId, req, result.monthly, result.deposit);
    }

    private String generateBookingId() {
        return "H-" + (7000 + new Random(1).nextInt(1000));
    }
}