public class BookingFactory implements Factory {
    public Calculator createCalculator() {return new DistanceCalculator();}
    public Allocator createAllocator() {return new DriverAllocator();}
    public Gateway createGateway() {return new PaymentGateway();}
}
