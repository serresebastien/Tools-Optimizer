package main;

import java.util.Scanner;

public class Setters {

    public static int setNbTool() {

        Scanner sc = new Scanner(System.in);

        System.out.print("\nEnter the number of tools: ");
        int nbTool = sc.nextInt();

        return nbTool;
    }

    public static int setNbPlace() {

        Scanner sc = new Scanner(System.in);

        System.out.print("\nEnter the number of places: ");
        int nbPlace = sc.nextInt();

        return nbPlace;
    }

    public static int setUnitTime() {

        Scanner sc = new Scanner(System.in);

        System.out.print("\nEnter the unit of time: ");
        int unitTime = sc.nextInt();

        return unitTime;
    }

    public static int setNbOperation() {

        Scanner sc = new Scanner(System.in);

        System.out.print("\nEnter the number of operations: ");
        int nbOperation = sc.nextInt();

        return nbOperation;
    }

    public static int setNbSimulation() {

        Scanner sc = new Scanner(System.in);

        System.out.print("\nEnter the number of simulation: ");
        int nbSimulation = sc.nextInt();

        return nbSimulation;
    }

}
