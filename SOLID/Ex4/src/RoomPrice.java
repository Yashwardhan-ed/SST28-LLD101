import java.util.HashMap;
import java.util.Map;

public class RoomPrice {
    Map<Integer, Money> roomTypes;
    public RoomPrice() {
        this.roomTypes = new HashMap<>();
        roomTypes.put(1, new Money(14000.0));
        roomTypes.put(2, new Money(15000.0));
        roomTypes.put(3, new Money(12000.0));
        roomTypes.put(4, new Money(16000.0));
    }
    public Money getRoomPrice(int roomType) {
        return roomTypes.get(roomType);
    }
}
