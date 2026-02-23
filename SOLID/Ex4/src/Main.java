import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Hostel Fee Calculator ===");
        BookingRequest req = new BookingRequest(LegacyRoomTypes.DOUBLE, List.of(AddOn.LAUNDRY, AddOn.MESS));
        BookingService service = new BookingService(new HostelFeeCalculator(), new FakeBookingRepo());
        service.process(req);
    }
}
