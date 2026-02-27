public class SmartDevice {
    private String brand;
    private boolean isPowerOn;

    public SmartDevice(String brand) {
        this.brand = brand;
        this.isPowerOn = false;
    }

    public void powerOn() {
        isPowerOn = true;
    }

    public void powerOff() {
        isPowerOn = false;
    }

    public String getBrand() {
        return brand;
    }

    public boolean isPowerOn() {
        return isPowerOn;
    }
    @Override
    public String toString() {
        return brand + " Device [Power: " + (isPowerOn ? "ON" : "OFF") + "]";
    }
}