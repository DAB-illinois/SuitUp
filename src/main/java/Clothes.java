
import main.java.Attributes;

import java.util.HashMap;

public class Clothes {
    private HashMap<String, Double> attributes;
    private Attributes descriptions;

    public Clothes() {
        descriptions = new Attributes();
        attributes = new HashMap<String, Double>();
        attributes.put("Gender", descriptions.getGender());
        attributes.put("Casual", descriptions.getCasual());
        attributes.put("Formal", descriptions.getFormal());
        attributes.put("Price", descriptions.getPrice());
        attributes.put("Pattern", descriptions.getPattern());
    }
}
