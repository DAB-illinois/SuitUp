package main.java;

import org.jcp.xml.dsig.internal.dom.DOMUtils;

import java.util.HashMap;

public class DatabaseItem {

    private static final int VECTOR_LENGTH = 7;
    private static final int GENDER_WEIGHT = 10;
    private static final int PRICE_WEIGHT = 5;
    private static final int PRICE_MIN = 20;
    private static final int PRICE_MAX = 1690;

    private static final HashMap<String, Double> CASUAL_CATEGORY;
    static {
        CASUAL_CATEGORY = new HashMap<String, Double>();
        CASUAL_CATEGORY.put("denim", 8.0);
    }
    private static final HashMap<String, Double> FORMAL_CATEGORY;
    static {
        FORMAL_CATEGORY = new HashMap<String, Double>();
        FORMAL_CATEGORY.put("denim", 0.0);
    }
    private static final HashMap<String, Double[]> TYPE_VALUES;
    static {
        TYPE_VALUES = new HashMap<String, Double[]>();
        TYPE_VALUES.put("bomber", new Double[]{2.0, 5.0, 0.0, 0.0});
        TYPE_VALUES.put("tee", new Double[]{4.5, 2.5, 0.0, 0.0});
        TYPE_VALUES.put("jean", new Double[]{0.0, 8.0, 1.0, 0.0});
        TYPE_VALUES.put("jogger", new Double[]{7.0, 2.0, 0.0, 0.0});
        TYPE_VALUES.put("cardigan", new Double[]{0.0, 6.0, 3.0, 0.0});
        TYPE_VALUES.put("sweatshirt", new Double[]{3.0, 3.0, 0.0, 0.0});
        TYPE_VALUES.put("blouse", new Double[]{0.0, 2.0, 6.0, 0.0});
        TYPE_VALUES.put("dress", new Double[]{0.0, 7.0, 5.0, 2.0});
        TYPE_VALUES.put("skirt", new Double[]{0.0, 6.0, 6.0, 0.0});
        TYPE_VALUES.put("polo", new Double[]{0.0, 1.0, 7.0, 0.0});
        TYPE_VALUES.put("shirtdress", new Double[]{0.0, 6.0, 0.0, 0.0});
        TYPE_VALUES.put("boatneck", new Double[]{1.0, 7.0, 0.0, 0.0});
    }

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
    private String price; //use in vector (20-1690)

    private double athletic; // [0, 8]
    private double leisure; // [0, 8]
    private double business; // [0, 8]
    private double fancy; // [0, 8]


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
        vector[1] = generatePriceValue();
        //casual
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

    private double generatePriceValue() {
        double priceDouble = Double.parseDouble(this.price);
        priceDouble -= PRICE_MIN;
        priceDouble -= (PRICE_MAX-PRICE_MIN)/2.0;
        priceDouble /= (PRICE_MAX-PRICE_MIN)/2;
        priceDouble *= PRICE_WEIGHT;
        return priceDouble;
    }

    private double generateCasualValue() {
        return 0.0;
    }
}
