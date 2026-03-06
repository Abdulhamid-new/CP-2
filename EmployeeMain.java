public class EmployeeMain {

    public static void main(String[] args) {

        Manager manager = new Manager();
        manager.name = "Ali";
        manager.employeeID = 101;
        manager.salary = 5000;

        manager.displayInfo();
        manager.assignTask("Develop Login System");

        System.out.println();

        Developer developer = new Developer();
        developer.name = "Abdulhamid";
        developer.employeeID = 102;
        developer.salary = 4000;

        developer.displayInfo();
        developer.writeCode();
        developer.fixBug();

        System.out.println();

        Intern intern = new Intern();
        intern.name = "Asad";
        intern.employeeID = 103;
        intern.salary = 1000;

        intern.displayInfo();
        intern.attendTraining();
        intern.submitReport();
    }
}