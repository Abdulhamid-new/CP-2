public class ShoppingMain {

    public static void main(String[] args) {

        Electronics phone = new Electronics();
        phone.productID = 1;
        phone.name = "Smartphone";
        phone.price = 800;
        phone.brand = "Samsung";
        phone.warranty = 2;

        phone.displayProductDetails();
        phone.getWarrantyDetails();

        System.out.println();

        Clothing shirt = new Clothing();
        shirt.productID = 2;
        shirt.name = "T-Shirt";
        shirt.price = 25;
        shirt.size = "M";
        shirt.color = "Black";

        shirt.displayProductDetails();
        shirt.checkSizeAvailability();

        System.out.println();

        Book book = new Book();
        book.productID = 3;
        book.name = "Kecha va Kunduz";
        book.price = 30;
        book.author = "Abdulhamid Cho'lpon";
        book.ISBN = "123456789";

        book.displayProductDetails();
        book.getAuthorInfo();
    }
}