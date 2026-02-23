import java.util.*;

public class HostelFeeCalculator {

    public BookingResult calculate(BookingRequest req) {
        Money monthly = calculateMonthly(req);
        Money deposit = new Money(5000.00);
        return new BookingResult(monthly, deposit);  
    }

    private Money calculateMonthly(BookingRequest req) {
        
        RoomPrice roomPrice = new RoomPrice();
        Money base = roomPrice.getRoomPrice(req.roomType);

        Money addon = new Money(0);
        AddOnMap m = new AddOnMap();
        for (AddOn a : req.addOns) {
            addon = addon.plus(m.getPrice(a));
        }

        return base.plus(addon);
    }
}
