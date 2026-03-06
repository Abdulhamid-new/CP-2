public class LibraryMain {

    public static void main(String[] args) {

        Librarian librarian = new Librarian();
        librarian.name = "Ali";
        librarian.id = 1;
        librarian.age = 18;

        librarian.displayInfo();
        librarian.addBook("Java Programming");
        librarian.issueBook("Java Programming", "Abdulhamid");

        System.out.println();

        Member member = new Member();
        member.name = "Abdulhamid";
        member.id = 2;
        member.age = 18;

        member.displayInfo();
        member.borrowBook("Java Programming");
        member.returnBook("Java Programming");

        System.out.println();

        Guest guest = new Guest();
        guest.name = "Suxrobjon";
        guest.id = 3;
        guest.age = 18;

        guest.displayInfo();
        guest.viewCatalog();
    }
}