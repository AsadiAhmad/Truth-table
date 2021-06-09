package TruthTabale;

import java.util.Scanner;

public class Main {

    private static final CreateTable createTable = new CreateTable ();

    public static void main(String[] args) {

        Scanner myScanner = new Scanner (System.in);
        System.out.println ("How many variable do you want ?");
        int numberOfScan = myScanner.nextInt ();
        System.out.println ("Please enter your name of variable");
        char[] variable = new char[numberOfScan];
        for (int counter = 0; counter < numberOfScan; counter++) {
            variable[counter] = myScanner.next().charAt (0);
        }
        System.out.println ("Please enter your statement");
        myScanner.nextLine (); // for fixing shit scanner bug
        String statement = myScanner.nextLine ();
        myScanner.close ();

        statement = statement.replaceAll(" ","");
        createTable.printTrueTable (numberOfScan, variable, statement);
    }
}