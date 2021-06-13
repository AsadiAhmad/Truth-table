package TruthTable;

public class ScanString {

    public String foundInternalParenthesis (String statement) {
        int startParenthesis = 0, endParenthesis = 0, result = -1;
        statement = statement.replaceAll(" ","");
        for (int counter = 0; counter < statement.length(); counter ++) {
            if (statement.charAt (counter) == '(') {
                startParenthesis = counter;
                result = 0; // space free !
            }
            if (statement.charAt (counter) == ')' && result == 0) {
                endParenthesis = counter;
                result = 1; // space full !
            }
        }
        return statement.substring (startParenthesis + 1, endParenthesis);
    }

    public Operator.operator foundOperator (String internalStatement) {
        if (internalStatement.contains ("&&")) {
            return Operator.operator.And;
        }
        else if (internalStatement.contains ("||")) {
            return Operator.operator.Or;
        }
        else if (internalStatement.contains ("-->")) {
            return Operator.operator.Condition;
        }
        else if (internalStatement.contains ("<->")) {
            return Operator.operator.TowCondition;
        }
        else if(internalStatement.contains ("@@")) {
            return Operator.operator.XOR;
        }
        else {
            return Operator.operator.NULL;
        }
    }

    public int foundFirstVariable (String internalStatement, int numberOfVar, char[] variable) {
        for (int counter = 0; counter < numberOfVar; counter++) {
            if (internalStatement.startsWith (String.valueOf(variable[counter]))) {
                return counter + 2;
            }
            else if (internalStatement.startsWith ("~" + variable[counter])) {
                return (counter * -1) - 2;
            }
            else if (internalStatement.startsWith ("T")) {
                return 1;
            }
            else if (internalStatement.startsWith ("F")) {
                return -1;
            }
        }
        return 0;
    }

    public int foundLastVariable (String internalStatement, int numberOfVar, char[] variable) {
        for (int counter = 0; counter < numberOfVar; counter++) {
            if (internalStatement.endsWith (String.valueOf(variable[counter]))) {
                return counter + 2;
            }
            else if (internalStatement.endsWith ("~" + variable[counter])) {
                return (counter * -1) - 2;
            }
            else if (internalStatement.endsWith ("T")) {
                return 1;
            }
            else if (internalStatement.endsWith ("F")) {
                return -1;
            }
        }
        return 0;
    }
}