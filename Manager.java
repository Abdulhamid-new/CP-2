class Manager extends Employee {

    void assignTask(String task) {
        System.out.println("Manager assigned task: " + task);
    }

    void approveLeave(String employeeName) {
        System.out.println("Leave approved for " + employeeName);
    }
}