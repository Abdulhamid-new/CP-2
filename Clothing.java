class Clothing extends Product {
    String size;
    String color;

    void checkSizeAvailability() {
        System.out.println("Available size: " + size);
        System.out.println("Color: " + color);
    }
}