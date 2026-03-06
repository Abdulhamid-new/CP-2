class Member extends Person {

    void borrowBook(String bookName) {
        System.out.println("Borrowed book: " + bookName);
    }

    void returnBook(String bookName) {
        System.out.println("Returned book: " + bookName);
    }

    void viewBorrowedBooks() {
        System.out.println("Viewing borrowed books...");
    }
}