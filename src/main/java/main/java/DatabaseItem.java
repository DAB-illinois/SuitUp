package main.java;

public class DatabaseItem {

    private static final int VECTOR_LENGTH = 5;
    private static final int GENDER_WEIGHT = 10;
    private static final int PRICE_WEIGHT = 2;
    private static final int PRICE_MIN = 20;
    private static final int PRICE_MAX = 395;

    // Document{{_id=59cf60d1f9a4758a54139dfa, styleId=mw00750, gender=men,
    // link=http://usa.tommy.com/en/sale/sale-final-sale-men/final-sale-city-windbreaker-mw00750,
    // description=Tommy Hilfiger men's jacket. Ideal for those in-between days of spring and fall,
    // this scaled-down version of the classic windbreaker features clean lines and a
    // trend-resistant silhouette. The bold metal zipper gives it a modern finish.• Classic fit.•
    // 100% polyamid.• Zip closure, slash pockets.• Machine washable.• Imported.,
    // pic_link=http://shoptommy.scene7.com/is/image/ShopTommy/MW00750_405_FNT?wid=455&hei=455&fmt=
    // jpeg&qlt=90%2c0&op_sharpen=1&resMode=trilin&op_usm=0.8%2c1.0%2c6%2c0&iccEmbed=0,
    // colors=[MIDNIGHT, NAUTICAL BLUE], name=FINAL SALE-CITY WINDBREAKER,
    // category=sale-final-sale-men, type=WINDBREAKER, price=74.98}}

    private String gender; //use in vector
    private String link;
    private String description;
    private String pic_link;
    private String[] colors;
    private String name;
    private String category;
    private String type;
    private String price; //use in vector (20-395)

    private double casual; //athletic = positive leisure = negative
    private double formal; //business = positive fancy = negative
    private double pattern; //true = positive false = negative
    private double[] vector;

    public String getGender() {
        return gender;
    }

    public String getLink() {
        return link;
    }

    public String getDescription() {
        return description;
    }

    public String getPicLink() {
        return pic_link;
    }

    public String[] getColors() {
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

    public String getPrice() {
        return price;
    }

    public void generateValues() {
        vector = new double[VECTOR_LENGTH];
        vector[0] = generateGenderValue();
        //price
        //causul
        //formal
        //pattern
    }

    private double generateGenderValue() {
        if (gender.equalsIgnoreCase("women")) {
            return GENDER_WEIGHT;
        } else {
            return -1 * GENDER_WEIGHT;
        }
    }

    public static double generatePriceValue(String price) {
        double priceDouble = Double.parseDouble(price);
        priceDouble -= PRICE_MIN;
        priceDouble -= (PRICE_MAX-PRICE_MIN)/2.0;
        priceDouble /= (PRICE_MAX-PRICE_MIN)/2;
        priceDouble *= PRICE_WEIGHT;
        return priceDouble;
    }
}
