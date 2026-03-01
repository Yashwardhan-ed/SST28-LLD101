public class ClassroomController {
    private final DeviceRegistry reg;

    public ClassroomController(DeviceRegistry reg) { this.reg = reg; }

    public void startClass() {
        Power pj = (Power) reg.getFirstOfType("Projector");
        pj.powerOn();
        ((Input) pj).connectInput("HDMI-1");

        Brightness lights = (Brightness) reg.getFirstOfType("LightsPanel");
        lights.setBrightness(60);

        Temperature ac = (Temperature) reg.getFirstOfType("AirConditioner");
        ac.setTemperatureC(24);

        Scanning scan = (Scanning) reg.getFirstOfType("AttendanceScanner");
        System.out.println("Attendance scanned: present=" + scan.scanAttendance());
    }

    public void endClass() {
        System.out.println("Shutdown sequence:");
        ((Power) reg.getFirstOfType("Projector")).powerOff();
        ((Power) reg.getFirstOfType("LightsPanel")).powerOff();
        ((Power) reg.getFirstOfType("AirConditioner")).powerOff();
    }
}
