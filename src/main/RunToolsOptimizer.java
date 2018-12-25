package main;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class RunToolsOptimizer {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("\nEnter the number of places: ");
        int nbPlaces = sc.nextInt();
        ArrayList<Place> allPlaces = setPlaces(nbPlaces);

        System.out.print("\nEnter the number of tools: ");
        int nbTools = sc.nextInt();

        showPlaces(allPlaces);

        allPlaces = setTools(allPlaces, nbTools);

        showPlaces(allPlaces);

        System.out.print("\nEnter the unit of time: ");
        int time = sc.nextInt();

        System.out.print("\nEnter the number of operations: ");
        int nbOperatons = sc.nextInt();
        ArrayList<Operation> allOperations = setOperations(nbOperatons);

        showOperations(allOperations);

        System.out.println();
    }

    public static ArrayList<Place> setPlaces(int nbPlace) {

        ArrayList<Place> allPlaces = new ArrayList<Place>();

        for (int i = 1; i <= nbPlace; i++) {
            allPlaces.add(new Place(i, i+1));
        }

        return allPlaces;
    }

    public static ArrayList<Place> setTools(ArrayList<Place> allPlaces, int nbTool) {

        ArrayList<Integer> ToolList = setToolList(nbTool);

        for(int i = 0; i < ToolList.size(); i++) {
            allPlaces.get(i).setTool(ToolList.get(i));
        }

        return allPlaces;
    }

    public static ArrayList<Operation> setOperations(int nbOperation) {

        ArrayList<Operation> allTools = new ArrayList<Operation>();
        Scanner sc = new Scanner(System.in);

        for (int i = 1; i <= nbOperation; i++) {
            System.out.println("\nOperation n°" + i);
            System.out.print("Tool: ");
            int toolNumber = sc.nextInt();
            System.out.print("Time: ");
            int processTime = sc.nextInt();
            allTools.add(new Operation(i, toolNumber, processTime));
        }

        return(allTools);
    }

    public static void showOperations(ArrayList<Operation> allOperations) {
        System.out.println("\n Id  | Tool | Time");

        for(int i = 0; i < allOperations.size(); i++) {
            System.out.print("  "+allOperations.get(i).getId());
            System.out.print("  |   "+allOperations.get(i).getToolNumber());
            System.out.println("  |   "+allOperations.get(i).getProcessTime());
        }
    }

    public static void showPlaces(ArrayList<Place> allPlaces) {
        System.out.println("\n Id  | Tool | nId");

        for(int i = 0; i < allPlaces.size(); i++) {
            System.out.print("  "+allPlaces.get(i).getId());
            System.out.print("  |   "+allPlaces.get(i).getTool());
            System.out.println("  |  "+allPlaces.get(i).getNextId());
        }
    }

    public static void startProcessing(ArrayList<Operation> allOperations, int time) {

        for (int i = 0; i <= allOperations.size(); i++) {



        }
    }

    public static ArrayList<Integer> setToolList(int nbTool) {

        ArrayList<Integer> ToolList = new ArrayList<Integer>();

        for (int i = 0; i < nbTool; i++) {
            int myRand = (int)(Math.random() * nbTool + 1);;

            if (isInside(ToolList, myRand) == true)
                i--;
            else ToolList.add(myRand);
        }

        System.out.println(ToolList);

        return ToolList;
    }

    public static boolean isInside(ArrayList<Integer> ToolList, int target) {

        boolean isInside = false;

        for(int i = 0; i < ToolList.size(); i++) {
            if (ToolList.get(i) == target)
                isInside = true;
        }

        return isInside;
    }
}
