package main;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) { setTools(); }

    public static ArrayList<Tool> setTools() {

        ArrayList<Tool> allTools = new ArrayList<Tool>();
        Scanner sc = new Scanner(System.in);

        System.out.print("\nEnter the number of tools: ");
        int nbTools = sc.nextInt();

        System.out.print("\nEnter the number of places: ");
        int nbPlaces = sc.nextInt();

        System.out.print("\nEnter the number of operations: ");
        int nbOperatons = sc.nextInt();

        System.out.print("\nEnter the unit of time: ");
        int time = sc.nextInt();

        for (int i = 1; i <= nbOperatons; i++) {
            System.out.print("Enter the tool number you want to use: ");
            int toolNumber = sc.nextInt();
            System.out.print("Enter the time you need to use this tool number " + toolNumber + ": ");
            int processTime = sc.nextInt();
            allTools.add(new Tool(i, toolNumber, processTime));
        }

        showTools(allTools);

        return(allTools);
    }

    public static void showTools(ArrayList<Tool> allTools) {
        System.out.println("\n Id  | Number | Time");

        for(int i = 0; i < allTools.size(); i++) {
            System.out.print("  "+allTools.get(i).getId());
            System.out.print("  |    "+allTools.get(i).getToolNumber());
            System.out.println("   |   "+allTools.get(i).getProcessTime());
        }
    }

}
