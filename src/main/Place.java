package main;

public class Place {

    //Variable
    private int id;
    //private Tool tool;
    private int tool;
    private int nextId;

    //Constructor
    Place(int id, int nextId) {
        this.id = id;
        //this.tool = null;
        this.tool = 0;
        this.nextId = nextId;
    }

    //Setters
    public void setId(int id) { this.id = id; }
    //public void setTool(Tool tool) { this.tool = tool; }
    public void setTool(int tool) { this.tool = tool; }
    public void setNextId(int nextId) { this.nextId = nextId; }

    //Getters
    public int getId() { return id; }
    //public Tool getTool() { return tool; }
    public int getTool() { return tool; }
    public int getNextId() { return nextId; }

    //Methods
    public void showPlace() {
        System.out.println("\nID | Tool Number | Process Time");
        System.out.println(" "+id+" |      "+tool+"      |      "+nextId);
    }
}
