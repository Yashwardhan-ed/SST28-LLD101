public class AttendanceScanner implements Device, Power, Scanning {
    @Override public void powerOn() { /* ok */ }
    @Override public void powerOff() { /* no output */ }
    @Override public int scanAttendance() { return 3; }
}
