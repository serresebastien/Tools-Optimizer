package main;

import java.util.Comparator;

public class Operation {

    //Variable
    private int id;
    private int toolNumber;
    private int processTime;

    //Constructor
    Operation(int id, int toolNumber, int processTime) {
        this.id = id;
        this.toolNumber = toolNumber;
        this.processTime = processTime;
    }

    //Getters
    public int getId() { return id; }
    public int getToolNumber() { return toolNumber; }
    public int getProcessTime() { return processTime; }

    //Setters
    public void setId(int id) { this.id = id; }
    public void setToolNumber(int toolNumber) { this.toolNumber = toolNumber; }
    public void setProcessTime(int processTime) { this.processTime = processTime; }

    //Methods
    public void showTool() {
        System.out.println("\nID | Tool Number | Process Time");
        System.out.println(" "+id+" |      "+toolNumber+"      |      "+processTime);
    }

    //Comparator id
    public static Comparator<Operation> ComparatorId = new Comparator<Operation>() {

        @Override
        public int compare(Operation pr1, Operation pr2) {
            return (int) (pr1.getId() - pr2.getId());
        }
    };

    //Comparator processTime
    public static Comparator<Operation> ComparatorProcessTime = new Comparator<Operation>() {

        @Override
        public int compare(Operation pr1, Operation pr2) {
            return (int) (pr1.getProcessTime() - pr2.getProcessTime());
        }
    };
}



