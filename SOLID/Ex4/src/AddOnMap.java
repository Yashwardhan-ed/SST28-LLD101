import java.util.HashMap;
import java.util.Map;

public class AddOnMap {
    public Map<AddOn, Money> addonMap;
    public AddOnMap() {
        this.addonMap = new HashMap<>();
        addonMap.put(AddOn.GYM, new Money(300.0));
        addonMap.put(AddOn.LAUNDRY, new Money(500.0));
        addonMap.put(AddOn.MESS, new Money(1000.0));
    }
    public Money getPrice(AddOn a) {
        return addonMap.get(a);
    }
    
}
