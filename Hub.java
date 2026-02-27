public class Hub<T> {
    private T device;

    public void storeDevice(T item) {
        this.device = item;
    }

    public T getDevice() {
        return device;
    }

    public void statusReport() {
        if (device != null) {
            System.out.println("Hub Status: " + device.toString());
        } else {
            System.out.println("Hub Status: No device connected");
        }
    }
}