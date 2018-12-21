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

        for (int i = 1; i < nbTools; i++) {
            System.out.print("Enter the time you need to use the tool number " + i + ": ");
            int nbm = sc.nextInt();
            //allTools.add(new Tool(i, nbm));
        }

        return(allTools);
    }
}
