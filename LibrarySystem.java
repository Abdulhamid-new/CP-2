public class LibrarySystem {

    public void checkout(Professor p, Book b) {
        if (p != null && b != null) {
            System.out.println(p.getName() + " has checked out \"" +
                    b.getTitle() + "\" for research.");
        } else {
            System.out.println("Invalid checkout: Professor or Book is null.");
        }
    }
}