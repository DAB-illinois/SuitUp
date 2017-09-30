package main.java;

import java.util.HashMap;

public class Attributes {
    private String id;
    private String styleid;
    private double gender; //female = 10 male = -10
    private String link;
    private String description;
    private String pic_link;
    private String colors;
    private String name;
    private String category;
    private String type;
    private double price; //"high" = positive (2) "low" = negative (-2)

    private double casual; //athletic = positive leisure = negative
    private double formal; //business = positive fancy = negative
    private double pattern; //true = positive false = negative

    public String getId() {
        return id;
    }

    public String getStyleid() {
        return styleid;
    }

    public double getGender() { return gender; }

    public String getLink() {
        return link;
    }

    public String getDescription() {
        return description;
    }

    public String getPic_link() {
        return pic_link;
    }

    public String getColors() {
        return colors;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getType() {
        return type;
    }

    public double getPrice() { return price; }

    public double getCasual() { return casual; }

    public double getFormal() { return formal; }

    public double getPattern() { return pattern; }
}
