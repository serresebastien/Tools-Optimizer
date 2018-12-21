package main;

import java.util.Comparator;

public class Tool {

    //Variable
    private int id;
    private int toolNumber;
    private int processTime;

    Tool(int id, int toolNumber, int processTime) {
        this.id = id;
        this.toolNumber = toolNumber;
        this.processTime = processTime;
    }

    //Getters
    public int getId() {return id;}
    public int getToolNumber() {return toolNumber;}
    public int getProcessTime() {return processTime;}

    //Setters
    public void setId(int id) {this.id = id;}
    public void setToolNumber(int toolNumber) {this.toolNumber = toolNumber;}
    public void setProcessTime(int processTime) {this.processTime = processTime;}

    //Methods
    public void showAllTools() {
        System.out.println("\nID | Tool Number | Process Time");
        System.out.println(" "+id+" |      "+toolNumber+"      |      "+processTime);
    }








    //Comparator id
    public static Comparator<Tool> ComparatorId = new Comparator<Tool>() {

        @Override
        public int compare(Tool pr1, Tool pr2) {
            return (int) (pr1.getId() - pr2.getId());
        }
    };

    //Comparator processTime
    public static Comparator<Tool> ComparatorProcessTime = new Comparator<Tool>() {

        @Override
        public int compare(Tool pr1, Tool pr2) {
            return (int) (pr1.getProcessTime() - pr2.getProcessTime());
        }
    };
}
