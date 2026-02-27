public class Main {
    public static void main(String[] args) {
        // Problem 1:
        Flight flight = new Flight("AA123", "Tashkent", "1", "A1");
        flight.displayFlightInfo();

        // Problem 2:
        SmartDevice smartBulb = new SmartDevice("Artel");
        smartBulb.powerOn();

        Hub<SmartDevice> smartHub = new Hub<>();
        smartHub.storeDevice(smartBulb);
        smartHub.statusReport();

        SmartDevice retrievedDevice = smartHub.getDevice();
        System.out.println("Retrieved: " + retrievedDevice.getBrand());

        // Problem 3:
        Book book = new Book("Book1", "Author1");
        Professor professor = new Professor("Ali");

        LibrarySystem library = new LibrarySystem();
        library.checkout(professor, book);
    }
}