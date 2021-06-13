package TruthTable;

public class CreateTable {

    Calculator calculator = new Calculator ();

    private void createVarRows (int numberOfVar, char[] variable) {
        for (int counter = 0; counter < numberOfVar; counter++) {
            System.out.print ("  ");
            System.out.print (variable[counter]);
            System.out.print ("  ");
            if (counter != numberOfVar - 1) {
                System.out.print ("|");
            }
        }
    }

    private void createRows (int numberOfVar) {
        for (int counter = 0; counter < numberOfVar - 1; counter++) {
            System.out.print ("_____");
            System.out.print ("|");
        }
        System.out.println ("_____");
    }

    private void createStatementRows (int numberOfVar, char[] variable) {
        createVarRows (numberOfVar, variable);
        System.out.print ("| Res");
        System.out.println ();
    }

    private boolean setTrueFalse (int currentChar, int currentColumn, int maxColumn) {
        double index = Math.pow (2, maxColumn - currentColumn + 1);
        double remaining = currentChar % index;
        return !(remaining > index / 2) && remaining != 0;
    }

    public char[][] getTrueFalse (int column) {
        int row = (int) Math.pow (2, column);
        char[][] trueFalse = new char[row][column];
        for (int counterColumn = 0; counterColumn < column; counterColumn++) {
            for (int counterRow = 0; counterRow < row; counterRow++) {
                trueFalse[counterRow][counterColumn] = calculator.convertBooleanToChar (setTrueFalse (counterRow + 1,counterColumn + 1 , column));
            }
        }
        return trueFalse;
    }

    private char[][] mergeArrays (char[][] trueFalse, char[] result, int numberOfVar) {
        int row = (int) Math.pow (2, numberOfVar);
        int column = numberOfVar + 1;
        char[][] finalResult = new char[row][column];
        for (int counterRow = 0; counterRow < row; counterRow++) {
            for (int counterColumn = 0; counterColumn < column - 1; counterColumn++) {
                finalResult[counterRow][counterColumn] = trueFalse[counterRow][counterColumn];
            }
            finalResult[counterRow][column -1] = result[counterRow];
        }
        return finalResult;
    }

    private char[][] createValues (int numberOfVar, char[] variable, String statement) {
        char[][] trueFalse = getTrueFalse (numberOfVar);
        char[] result = calculator.calculateAllValue (statement, trueFalse, numberOfVar, variable);
        return mergeArrays(trueFalse, result, numberOfVar);
    }

    private char[] arrayToArray (int numberOfVar, int currentRow, char[][] arrayChars) {
        char[] result = new char[numberOfVar];
        for (int counter = 0; counter < numberOfVar; counter++) {
            result[counter] = arrayChars[currentRow][counter];
        }
        return result;
    }

    public void printTrueTable (int numberOfVar, char[] variable, String statement) {
        createStatementRows (numberOfVar, variable);
        for (int counter = 0; counter < Math.pow(2, numberOfVar); counter++) {
            createRows (numberOfVar + 1);
            createVarRows (numberOfVar + 1, arrayToArray (numberOfVar + 1, counter, createValues(numberOfVar, variable, statement)));
            System.out.println ();
        }
    }
}