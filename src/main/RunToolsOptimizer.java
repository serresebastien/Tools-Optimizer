package main;

import java.util.ArrayList;
import java.util.Scanner;

public class RunToolsOptimizer {

    public static void main(String[] args) {

        int nbPlace = Setters.setNbPlace();
        int nbTool = Setters.setNbTool();
        int unitTime = Setters.setUnitTime();
        int nbOperation = Setters.setNbOperation();
        int nbSimulation = Setters.setNbSimulation();

        ArrayList<Place> allPlaces = createPlacesList(nbPlace);

        ArrayList<Operation> allOperations = createOperationsList(nbOperation);

        showOperations(allOperations);

        for (int i = 1; i <= nbSimulation; i++) {

            allPlaces = insertTools(allPlaces, nbTool);

            int time = startSimulation(allPlaces, allOperations, unitTime);

            showPlaces(allPlaces);
            System.out.println("The total time for this simulation is: " + time);

        }
    }

    public static ArrayList<Place> createPlacesList(int nbPlace) {

        ArrayList<Place> allPlaces = new ArrayList<Place>();

        for (int i = 1; i <= nbPlace; i++) {
            allPlaces.add(new Place(i, i+1));
        }

        return allPlaces;
    }

    public static ArrayList<Place> insertTools(ArrayList<Place> allPlaces, int nbTool) {

        ArrayList<Integer> ToolList = createToolsList(nbTool);

        for(int i = 0; i < ToolList.size(); i++) {
            allPlaces.get(i).setTool(ToolList.get(i));
        }

        return allPlaces;
    }

    public static ArrayList<Integer> createToolsList(int nbTool) {

        ArrayList<Integer> ToolList = new ArrayList<Integer>();

        for (int i = 0; i < nbTool; i++) {
            int myRand = (int)(Math.random() * nbTool + 1);;

            if (isInside(ToolList, myRand) == true)
                i--;
            else ToolList.add(myRand);
        }

        //System.out.println(ToolList);

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

    public static ArrayList<Operation> createOperationsList(int nbOperation) {

        ArrayList<Operation> allTools = new ArrayList<Operation>();
        Scanner sc = new Scanner(System.in);

        for (int i = 1; i <= nbOperation; i++) {
            System.out.println("\n--- Operation n°" + i + " ---");
            System.out.print("Tool: ");
            int toolNumber = sc.nextInt();
            System.out.print("Time: ");
            int processTime = sc.nextInt();
            allTools.add(new Operation(i, toolNumber, processTime));
        }

        return(allTools);
    }

    public static void showOperations(ArrayList<Operation> allOperations) {
        System.out.println("\n--- Operations List ---\n Id  | Tool | Time");

        for(int i = 0; i < allOperations.size(); i++) {
            System.out.print("  "+allOperations.get(i).getId());
            System.out.print("  |   "+allOperations.get(i).getToolNumber());
            System.out.println("  |   "+allOperations.get(i).getProcessTime());
        }
    }

    public static void showPlaces(ArrayList<Place> allPlaces) {
        System.out.println("\n--- Simulation Setup ---\n Id  | Tool");

        for(int i = 0; i < allPlaces.size(); i++) {
            System.out.print("  "+allPlaces.get(i).getId());
            System.out.println("  |   "+allPlaces.get(i).getTool());
        }
    }

    public static int startSimulation(ArrayList<Place> allPlaces, ArrayList<Operation> allOperations, int unitTime) {

        int time = 0;
        int position = 0;
        int oldPosition = 0;
        int target = allOperations.get(0).getToolNumber();

        for (int j=0; j < allPlaces.size(); j++) {

            if (allPlaces.get(j).getTool() == target) {
                position = allPlaces.get(j).getId();
                oldPosition = position;
            }
        }

        for (int i = 0; i < allOperations.size(); i++) {

            target = allOperations.get(i).getToolNumber();

            for (int j=0; j < allPlaces.size(); j++) {
                if (allPlaces.get(j).getTool() == target) position = allPlaces.get(j).getId();
            }

            if (i == 0 && allOperations.get(i).getProcessTime() <= unitTime) {
                time = unitTime;
            } else if (Math.abs((oldPosition-position)*unitTime) > allOperations.get(i).getProcessTime()) {
                time = time + (Math.abs((oldPosition-position)*unitTime));
            } else {
                time = time + allOperations.get(i).getProcessTime();
            }

            System.out.println("\nboucle:"+i+"\nposition:"+position+"\noldPosition:"+oldPosition+"\ntarget:"+target+"\ntime:"+time);

            oldPosition = position;

        }

        return time;
    }
}