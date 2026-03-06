class Electronics extends Product {
    int warranty;
    String brand;

    void getWarrantyDetails() {
        System.out.println("Brand: " + brand);
        System.out.println("Warranty: " + warranty + " years");
    }
}