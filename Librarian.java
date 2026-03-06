class Librarian extends Person {

    void addBook(String bookName) {
        System.out.println("Book added: " + bookName);
    }

    void removeBook(String bookName) {
        System.out.println("Book removed: " + bookName);
    }

    void issueBook(String bookName, String memberName) {
        System.out.println(bookName + " issued to " + memberName);
    }
}