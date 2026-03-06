class Book extends Product {
    String author;
    String ISBN;

    void getAuthorInfo() {
        System.out.println("Author: " + author);
        System.out.println("ISBN: " + ISBN);
    }
}