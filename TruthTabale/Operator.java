package TruthTabale;

public class Operator {

    public enum operator {
        Or, And, Condition, TowCondition, XOR, NULL
    }

    public boolean operatorNegation (boolean p) {
        return !p;
    }

    public boolean operatorOr (boolean p, boolean q) {
        return p || q;
    }

    public boolean operatorAnd (boolean p, boolean q) {
        return p && q;
    }

    public boolean operatorCondition (boolean p, boolean q) {
        return !p || q;
    }

    public boolean operatorTowCondition (boolean p, boolean q) {
        return (!p || q) && (p || !q);
    }

    public boolean operatorXOR (boolean p , boolean q) {
        return (p && !q) || (!p && q);
    }
}