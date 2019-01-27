package main;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class RunToolsOptimizer {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("\nEnter the number of places you want to use: ");
        int nbPlace = sc.nextInt();

        int nbTool = 0, nbOperation = 0, unitTime = 0;
        /*
        int nbTool = Setters.setNbTool();
        int nbPlace = Setters.setNbPlace();
        int nbOperation = Setters.setNbOperation();
        int unitTime = Setters.setUnitTime();
        */

        try {
            BufferedReader br = new BufferedReader(new FileReader("Dataset.txt"));
            String line;
            line = br.readLine();
            nbTool = Integer.parseInt(line);
            line = br.readLine();
            //nbPlace = Integer.parseInt(line);
            line = br.readLine();
            nbOperation = Integer.parseInt(line);
            line = br.readLine();
            unitTime = Integer.parseInt(line);


            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //int nbSimulation = Setters.setNbSimulation();

        ArrayList<Place> allPlaces = createPlacesList(nbPlace);
        ArrayList<Place> bestPosition = new ArrayList<Place> (allPlaces);


        ArrayList<Operation> allOperations = createOperationsList(nbOperation);

        showOperations(allOperations);

        allPlaces = insertTools(allPlaces, nbTool); // Ici on a une solution potentielle

        // Debut du recuit simulé
        double T = 1.0;
        double alpha = 0.999;
        double T_min = 0.001;

        int oldTime = 0, newTime = 0;

        while(T > T_min) {
            for (int i = 0 ; i < 500; i ++)
            {
                //System.out.println("Boucle for, iteration no :" + i);
                //System.out.println("Taille de la solution actuelle : " + allPlaces.size());
                oldTime = startSimulation(allPlaces, allOperations,  unitTime, allOperations.get(allOperations.size()-1).getProcessTime());
                ArrayList<Place> newSolution = closeSolution(allPlaces); // La nouvelle solution est une dérivée de l'ancienne
                //System.out.println("Taille de la nouvelle solution, dérivee de l'ancienne :" + newSolution.size());


                //System.out.println("Temps de la solution :" + oldTime);
                //System.out.println("Taille de la nouvelle solution  v1 : " + allPlaces.size());

                newTime = startSimulation(newSolution, allOperations, unitTime, allOperations.get(allOperations.size()-1).getProcessTime());
                //System.out.println("Temps de la NOUVELLE solution :" + newTime);
                //System.out.println("Taille de la nouvelle solution  v2 : " + newSolution.size());
                //System.out.println( newTime + "/" + oldTime);
                if (newTime <= oldTime)
                {
                    //System.out.println("Taille de la solution avant clear : " + newSolution.size());
                    allPlaces.clear();
                    //System.out.println("Taille de la solution avant addAll : " + newSolution.size());
                    allPlaces.addAll(newSolution);
                    //Stores the best ArrayList
                    bestPosition = new ArrayList<Place>(newSolution);
                    //System.out.println("Taille de la solution apres addAll : " + newSolution.size());
                    //System.out.println("Current total time" + startSimulation(allPlaces, allOperations,  unitTime, newTime) );
                }
                else
                {
                    //System.out.println( newTime + "/" + oldTime);
                    double acceptProb = acceptanceProbability(newTime, oldTime, T);
                    double rand = Math.random();
                    if (acceptProb > rand)
                    {
                        allPlaces.clear();
                        allPlaces.addAll(newSolution);
                        //System.out.println("Taille de la solution apres addAll : " + newSolution.size());
                    }
                    //System.out.println("Current total time" + startSimulation(allPlaces, allOperations,  unitTime, oldTime) );
                }
            }
            if (startSimulation(allPlaces, allOperations,  unitTime, oldTime) < 90 ){System.out.println("\n Current total time" + startSimulation(allPlaces, allOperations,  unitTime, oldTime) +"\n" );}

            System.out.println("Temperature :" + T + " / Acceptance Probabilty :" + acceptanceProbability(newTime, oldTime, T));
            T = T * alpha;

        }
        for (Place allPlace : allPlaces) {
            System.out.println(allPlace.getTool());
        }
        System.out.println("Temps total : " + PrintSimulation(allPlaces, allOperations,  unitTime, allOperations.get(allOperations.size()-1).getProcessTime()));
        System.out.println("Temps total meilleur solution: " + PrintSimulation(bestPosition, allOperations,  unitTime, allOperations.get(allOperations.size()-1).getProcessTime()));

    /*
        for (int i = 1; i <= nbSimulation; i++) {

            allPlaces = insertTools(allPlaces, nbTool);

            int time = startSimulation(allPlaces, allOperations, unitTime, allOperations.get(allOperations.size()-1).getProcessTime());



            showPlaces(allPlaces);
            System.out.println("The total time for this simulation is: " + time);

        }
        */
    }

    private static ArrayList<Place> createPlacesList(int nbPlace) {

        ArrayList<Place> allPlaces = new ArrayList<Place>();

        for (int i = 1; i <= nbPlace; i++) {
            allPlaces.add(new Place(i, i+1));
        }

        return allPlaces;
    }

    private static ArrayList<Place> insertTools(ArrayList<Place> allPlaces, int nbTool) {

        ArrayList<Integer> ToolList = createToolsList(nbTool);

        for(int i = 0; i < ToolList.size(); i++) {
            allPlaces.get(i).setTool(ToolList.get(i));
        }

        return allPlaces;
    }

    private static ArrayList<Integer> createToolsList(int nbTool) {

        ArrayList<Integer> ToolList = new ArrayList<Integer>();

        for (int i = 0; i < nbTool; i++) {
            int myRand = (int)(Math.random() * nbTool + 1);

            if (isInside(ToolList, myRand))
                i--;
            else ToolList.add(myRand);
        }

        //System.out.println(ToolList);

        return ToolList;
    }

    private static boolean isInside(ArrayList<Integer> ToolList, int target) {

        boolean isInside = false;

        for (Integer aToolList : ToolList) {
            if (aToolList == target)
                isInside = true;
        }

        return isInside;
    }

    private static ArrayList<Operation> createOperationsList(int nbOperation) {

        ArrayList<Operation> allTools = new ArrayList<Operation>();
        /*
        Scanner sc = new Scanner(System.in);

        for (int i = 1; i <= nbOperation; i++) {
            System.out.println("\n--- Operation n°" + i + " ---");
            System.out.print("Tool: ");
            int toolNumber = sc.nextInt();
            System.out.print("Time: ");
            int processTime = sc.nextInt();
            allTools.add(new Operation(i, toolNumber, processTime));
        }
        */

        try {
            BufferedReader br = new BufferedReader(new FileReader("Dataset.txt"));
            String line;
            line = br.readLine();
            line = br.readLine();
            line = br.readLine();
            line = br.readLine();

            ArrayList<int []> workTime = new ArrayList<int[]>();
            for(int i = 0; i < nbOperation; i ++) {
                line = br.readLine();
                String[] lines = line.split("	");
                int[] monTab = {Integer.parseInt(lines[0]),Integer.parseInt(lines[1])};
                allTools.add(new Operation(i, monTab[0],  monTab[1]));
            }

            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return(allTools);
    }

    private static void showOperations(ArrayList<Operation> allOperations) {
        System.out.println("\n--- Operations List ---\n Id  | Tool | Time");

        for (Operation allOperation : allOperations) {
            System.out.print("  " + allOperation.getId());
            System.out.print("  |   " + allOperation.getToolNumber());
            System.out.println("  |   " + allOperation.getProcessTime());
        }
    }


    private static int startSimulation(ArrayList<Place> allPlaces, ArrayList<Operation> allOperations, int unitTime, int lasttime) {

        int time = 0;
        int position = 0;
        int oldPosition = 0;
        int target = allOperations.get(0).getToolNumber();

        for (Place allPlace : allPlaces) {

            if (allPlace.getTool() == target) {
                position = allPlace.getId();
                oldPosition = position;
            }
        }

        for (int i = 0; i < allOperations.size(); i++) {

            target = allOperations.get(i).getToolNumber();

            for (Place allPlace : allPlaces) {
                if (allPlace.getTool() == target) position = allPlace.getId();
            }

            if (i == 0 ){
                time = 0;
            }
            else if (Math.abs((oldPosition-position)*unitTime) > allOperations.get(i-1).getProcessTime()) {
                time = time + (Math.abs((oldPosition-position)*unitTime));
            } else {
                time = time + allOperations.get(i-1).getProcessTime();
            }
            //System.out.println("\nboucle:"+i+"\nposition:"+position+"\noldPosition:"+oldPosition+"\ntarget:"+target+"\ntime:"+time);
            oldPosition = position;
        }

        time += lasttime;
        return time;
    }

    private static int PrintSimulation(ArrayList<Place> allPlaces, ArrayList<Operation> allOperations, int unitTime, int lasttime) {

        int time = 0;
        int position = 0;
        int oldPosition = 0;
        int target = allOperations.get(0).getToolNumber();

        for (Place allPlace1 : allPlaces) {

            if (allPlace1.getTool() == target) {
                position = allPlace1.getId();
                oldPosition = position;
            }
        }

        for (int i = 0; i < allOperations.size(); i++) {

            target = allOperations.get(i).getToolNumber();

            for (Place allPlace : allPlaces) {
                if (allPlace.getTool() == target) position = allPlace.getId();
            }

            if (i == 0 ){
                time = 0;
            }
            else if (allOperations.get(i-1).getProcessTime() < Math.abs((oldPosition-position)*unitTime) ){
                time = time + Math.abs((oldPosition-position)*unitTime);
            }
            else {
                time = time + allOperations.get(i-1).getProcessTime();
            }
            System.out.println("\nboucle:"+i+"\nposition:"+position+"\noldPosition:"+oldPosition+"\ntarget:"+target+"\ntime:"+time);
            oldPosition = position;
        }

        time += lasttime;
        return time;
    }


    private static ArrayList<Place> closeSolution(ArrayList<Place> solution){
        //System.out.println("Solution.size = " + solution.size());
        int swap1 = 1 + (int)(Math.random() * ((solution.size() - 1) ));
        int swap2 = 1 + (int)(Math.random() * ((solution.size() - 1) ));
        if (swap1 == swap2) return closeSolution(solution);
        else
        {
            ArrayList<Place> newSolution = new ArrayList<Place>(solution);

            int temp = newSolution.get(swap1).getTool();
            newSolution.get(swap1).setTool(newSolution.get(swap2).getTool());
            newSolution.get(swap2).setTool(temp);

            return newSolution;
        }
    }

    private static double acceptanceProbability(int newSolutionTime, int oldSolutionTime, double T){
        return 1 / (1 + Math.exp( ( ( (double)(newSolutionTime) - (double)(oldSolutionTime) )/T ) ) );
    }

}