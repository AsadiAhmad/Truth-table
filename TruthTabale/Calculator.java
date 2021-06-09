package TruthTabale;

public class Calculator {

    ScanString scanString = new ScanString ();
    Operator operatorWork = new Operator ();

    public char convertBooleanToChar (boolean trueFalse) {
        if (trueFalse) {
            return 'T';
        }
        else {
            return 'F';
        }
    }

    public boolean convertCharToBoolean (char trueFalse) {
        return trueFalse == 'T';
    }

    private boolean calculateOperator (Operator.operator myOperator, boolean firstStatement, boolean lastStatement) {
        boolean result = false;
        switch (myOperator) {
            case Or:
                result = operatorWork.operatorOr (firstStatement, lastStatement);
                break;
            case And:
                result = operatorWork.operatorAnd (firstStatement, lastStatement);
                break;
            case Condition:
                result = operatorWork.operatorCondition (firstStatement, lastStatement);
                break;
            case TowCondition:
                result = operatorWork.operatorTowCondition (firstStatement, lastStatement);
                break;
            case XOR:
                result = operatorWork.operatorXOR (firstStatement, lastStatement);
                break;
        }
        return result;
    }

    private boolean foundVariableValue (char[][] trueFalse, int row, int numOfVariable) {
        if (numOfVariable == 1) {
            return true;
        }
        else if (numOfVariable == -1) {
            return false;
        }
        else if (numOfVariable < 0) {
            numOfVariable = (numOfVariable * -1) - 2;
            return operatorWork.operatorNegation(convertCharToBoolean(trueFalse[row][numOfVariable]));
        }
        else {
            numOfVariable = numOfVariable -2;
            return convertCharToBoolean(trueFalse[row][numOfVariable]);
        }
    }

    private boolean calculateInternalParenthesis (String internalStatement, char[][] trueFalse, int numberOfVar, char[] variable, int row) {
        int numOfFirstVariable = scanString.foundFirstVariable(internalStatement, numberOfVar, variable);
        int numOfLastVariable = scanString.foundLastVariable(internalStatement, numberOfVar, variable);
        Operator.operator myOperator = scanString.foundOperator (internalStatement);
        boolean firstVariable = foundVariableValue (trueFalse, row, numOfFirstVariable);
        boolean lastVariable = foundVariableValue (trueFalse, row, numOfLastVariable);
        return calculateOperator (myOperator, firstVariable, lastVariable);
    }

    public boolean calculateAllParenthesis (String statement, char[][] trueFalse, int numberOfVar, char[] variable, int row) {
        String internalStatement;
        boolean internalParenthesis = true;
        while (!statement.equals("T") && !statement.equals("F")) {
            internalStatement = scanString.foundInternalParenthesis(statement);
            internalParenthesis = calculateInternalParenthesis(internalStatement, trueFalse, numberOfVar, variable, row);
            statement = statement.replace("(" + internalStatement + ")", String.valueOf(convertBooleanToChar(internalParenthesis)));
        }
        return internalParenthesis;
    }

    public char[] calculateAllValue (String statement, char[][] trueFalse, int numberOfVar, char[] variable) {
        int row = (int) Math.pow (2, numberOfVar);
        char[] result = new char[row];
        boolean statementResult;
        for (int counter = 0; counter < row; counter++) {
            statementResult = calculateAllParenthesis (statement, trueFalse, numberOfVar, variable, counter);
            result[counter] = convertBooleanToChar (statementResult);
        }
        return result;
    }

}