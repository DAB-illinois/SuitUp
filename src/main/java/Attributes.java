import java.util.HashMap;

public class Attributes {
    private double gender; //female = 100 male = -100
    private double casual; //athletic = positive leisure = negative
    private double formal; //business = positive fancy = negative
    private double price; //"high" = positive "low" = negative
    private double pattern; //true = positive false = negative

    public double getGender() { return gender; }

    public double getCasual() { return casual; }

    public double getFormal() { return formal; }

    public double getPrice() { return price; }

    public double getPattern() { return pattern; }
}
