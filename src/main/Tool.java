package main;

public class Tool {
    
    private int id;
    private int nbUse;

    Tool(int id) {
        this.id = id;
        this.nbUse = 1;
    }

    public void setId(int id) { this.id = id; }
    public void setNbUse(int nbUse) { this.nbUse = nbUse; }

    public int getId() { return id; }
    public int getNbUse() { return nbUse; }
}
