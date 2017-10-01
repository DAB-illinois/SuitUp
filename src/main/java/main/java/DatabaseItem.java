package main.java;

import java.util.HashMap;

public class DatabaseItem {

    private static final int VECTOR_LENGTH = 7;
    private static final int GENDER_WEIGHT = 10;
    private static final int PRICE_WEIGHT = 5;
    private static final int PRICE_MIN = 20;
    private static final int PRICE_MAX = 1690;

    private static final String[] TOP = new String[]{"tee", "blouse", "polo", "shirt", "boatneck",
            "crewneck", "t-shirt", "top", "tank", "t-shirtdress", "crew", "turtleneck", "henley"};
    private static final String[] BOTTOM = new String[]{"jean", "jogger", "skirt", "pant",
            "jegging", "trouser", "short", "shorts", "legging", "trousers", "sweatpant", "chino",
            "sweatpant", "john", "tights"};
    private static final String[] FULL_BODY = new String[]{"dress", "shirtdress", "tunic",
            "jumpsuit", "coverup", "suit"};
    private static final String[] FOOTWEAR = new String[]{"sneaker", "shoe", "shoes", "loafer",
            "flat", "bootie", "hi-top", "creeper", "boot", "ballerina", "mule", "hiker", "slip-on",
            "oxford", "gilet", "moccasin", "wedge"};
    private static final String[] OUTER = new String[]{"bomber", "cardigan", "sweatshirt",
            "sweater", "hoodie", "jacket", "coat", "blazer", "flannel", "puffer", "parka", "bike",
            "vest", "windbreaker", "trench", "peacoat", "lounger", "fleece"};
    private static final String[] IGNORE = new String[]{"print", "bra", "brief", "bikini", "bag",
            "backpack", "scarf", "necklace", "tie", "belt", "set", "socks", "sock", "thong", "2pk",
            "3pk", "trunk", "bralette", "watch", "sunglasses", "square", "ring", "cap", "hat", "ii",
            "duffle", "halter", "beanie", "rugby", "flex", "tote", "wallet", "folio", "clutch",
            "crossover", "hipster", "hadid"};

    // value is for casual. 8-value = formal
    private static final HashMap<String, Double> CATEGORY_VALUES;

    static {
        CATEGORY_VALUES = new HashMap<String, Double>();
        CATEGORY_VALUES.put("denim", 8.0);
        CATEGORY_VALUES.put("t-shirts", 8.0);
        CATEGORY_VALUES.put("shirts", 2.0);
        CATEGORY_VALUES.put("blouses", 5.0);
        CATEGORY_VALUES.put("sweater", 8.0);
        CATEGORY_VALUES.put("jacket", 5.0);
        CATEGORY_VALUES.put("jean", 8.0);
        CATEGORY_VALUES.put("pants", 6.5);
        CATEGORY_VALUES.put("polos", 7.0);
        CATEGORY_VALUES.put("dresses", 7.0);
        CATEGORY_VALUES.put("shorts", 8.0);
        CATEGORY_VALUES.put("footwear", 4.0);
        CATEGORY_VALUES.put("athleisure", 8.0);
        CATEGORY_VALUES.put("dressshirts", 0.0);
        CATEGORY_VALUES.put("suit-separates", 0.0);
        CATEGORY_VALUES.put("rugbys", 6.0);
    }

    // athletic, leisure, business, fancy
    private static final HashMap<String, Double[]> TYPE_VALUES;

    static {
        TYPE_VALUES = new HashMap<String, Double[]>();
        TYPE_VALUES.put("bomber", new Double[]{2.0, 5.0, 0.0, 0.0});
        TYPE_VALUES.put("tee", new Double[]{4.5, 6.5, 0.0, 0.0});
        TYPE_VALUES.put("t-shirt", new Double[]{4.5, 6.5, 0.0, 0.0});
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
        TYPE_VALUES.put("crewneck", new Double[]{0.0, 6.0, 1.0, 0.0});
        TYPE_VALUES.put("shirt", new Double[]{0.0, 2.0, 6.0, 0.0});
        TYPE_VALUES.put("pant", new Double[]{0.0, 7.0, 8.0, 6.0});
        TYPE_VALUES.put("top", new Double[]{0.0, 7.0, 8.0, 6.0});
        TYPE_VALUES.put("hoodie", new Double[]{4.0, 3.0, 0.0, 0.0});
        TYPE_VALUES.put("sweater", new Double[]{4.0, 3.0, 0.0, 0.0});
        TYPE_VALUES.put("tank", new Double[]{4.0, 4.0, 0.0, 0.0});
        TYPE_VALUES.put("jegging", new Double[]{3.0, 6.0, 0.0, 0.0});
        TYPE_VALUES.put("jacket", new Double[]{1.0, 6.0, 0.0, 0.0});
        TYPE_VALUES.put("tunic", new Double[]{1.0, 7.0, 0.0, 0.0});
        TYPE_VALUES.put("flannel", new Double[]{0.0, 8.0, 0.0, 0.0});
        TYPE_VALUES.put("puffer", new Double[]{0.0, 8.0, 0.0, 0.0});
        TYPE_VALUES.put("parka", new Double[]{0.0, 5.0, 3.0, 0.0});
        TYPE_VALUES.put("coat", new Double[]{0.0, 1.0, 3.0, 8.0});
        TYPE_VALUES.put("blazer", new Double[]{0.0, 0.0, 2.0, 8.0});
        TYPE_VALUES.put("biker", new Double[]{0.0, 8.0, 0.0, 0.0});
        TYPE_VALUES.put("trouser", new Double[]{0.0, 2.0, 6.0, 4.0});
        TYPE_VALUES.put("legging", new Double[]{3.0, 6.0, 0.0, 0.0});
        TYPE_VALUES.put("chino", new Double[]{0.0, 6.0, 3.0, 0.0});
        TYPE_VALUES.put("jumpsuit", new Double[]{0.0, 8.0, 0.0, 0.0});
        TYPE_VALUES.put("t-shirtdress", new Double[]{0.0, 8.0, 0.0, 0.0});
        TYPE_VALUES.put("short", new Double[]{6.0, 2.0, 0.0, 0.0});
        TYPE_VALUES.put("shorts", new Double[]{0.0, 6.0, 2.0, 0.0});
        TYPE_VALUES.put("suit", new Double[]{0.0, 0.0, 4.0, 6.0});
        TYPE_VALUES.put("bootie", new Double[]{0.0, 8.0, 0.0, 0.0});
        TYPE_VALUES.put("hi-top", new Double[]{0.0, 8.0, 0.0, 0.0});
        TYPE_VALUES.put("sneaker", new Double[]{6.0, 8.0, 0.0, 0.0});
        TYPE_VALUES.put("creeper", new Double[]{0.0, 8.0, 0.0, 0.0});
        TYPE_VALUES.put("flat", new Double[]{0.0, 8.0, 2.0, 0.0});
        TYPE_VALUES.put("ballerina", new Double[]{0.0, 6.0, 2.0, 0.0});
        TYPE_VALUES.put("mule", new Double[]{0.0, 4.0, 4.0, 0.0});
        TYPE_VALUES.put("boot", new Double[]{0.0, 6.0, 4.0, 0.0});
        TYPE_VALUES.put("hiker", new Double[]{0.0, 6.0, 2.0, 0.0});
        TYPE_VALUES.put("slip-on", new Double[]{0.0, 8.0, 0.0, 0.0});
        TYPE_VALUES.put("oxford", new Double[]{0.0, 0.0, 7.0, 2.0});
        TYPE_VALUES.put("cardigan", new Double[]{0.0, 4.0, 4.0, 0.0});
        TYPE_VALUES.put("gilet", new Double[]{0.0, 8.0, 0.0, 0.0});
        TYPE_VALUES.put("vest", new Double[]{0.0, 6.0, 2.0, 2.0});
        TYPE_VALUES.put("trousers", new Double[]{0.0, 3.0, 6.0, 1.0});
        TYPE_VALUES.put("crew", new Double[]{0.0, 2.0, 6.0, 0.0});
        TYPE_VALUES.put("sweatpant", new Double[]{5.0, 4.0, 0.0, 0.0});
        TYPE_VALUES.put("sweatpants", new Double[]{5.0, 4.0, 0.0, 0.0});
        TYPE_VALUES.put("lounger", new Double[]{4.0, 4.0, 0.0, 0.0});
        TYPE_VALUES.put("turtleneck", new Double[]{0.0, 6.0, 2.0, 0.0});
        TYPE_VALUES.put("rugby", new Double[]{0.0, 8.0, 1.0, 0.0});
        TYPE_VALUES.put("henley", new Double[]{0.0, 8.0, 0.0, 0.0});
        TYPE_VALUES.put("moccasin", new Double[]{0.0, 7.0, 1.0, 0.0});
        TYPE_VALUES.put("shoe", new Double[]{0.0, 2.0, 8.0, 8.0});
        TYPE_VALUES.put("shoes", new Double[]{0.0, 2.0, 8.0, 8.0});
        TYPE_VALUES.put("loafer", new Double[]{0.0, 2.0, 6.0, 3.0});
        TYPE_VALUES.put("john", new Double[]{0.0, 6.0, 2.0, 0.0});
        TYPE_VALUES.put("flex", new Double[]{0.0, 0.0, 2.0, 8.0});
        TYPE_VALUES.put("tights", new Double[]{0.0, 7.0, 1.0, 0.0});
        TYPE_VALUES.put("fleece", new Double[]{0.0, 8.0, 0.0, 0.0});
        TYPE_VALUES.put("wedge", new Double[]{0.0, 7.0, 1.0, 0.0});
        TYPE_VALUES.put("peacoat", new Double[]{0.0, 2.0, 4.0, 6.0});
        TYPE_VALUES.put("trench", new Double[]{0.0, 6.0, 6.0, 6.0});
        TYPE_VALUES.put("windbreaker", new Double[]{0.0, 8.0, 0.0, 0.0});

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
    private String general_type; //"ignore" if not giving recommendations for

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

    public String getGeneralType() {
        return general_type;
    }

    public double[] generateValues() {
        vector = new double[VECTOR_LENGTH];
        vector[0] = generateGenderValue();
        vector[1] = generatePriceValue();
        //casual
        //formal
        //pattern
        return vector;
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
        priceDouble -= (PRICE_MAX - PRICE_MIN) / 2.0;
        priceDouble /= (PRICE_MAX - PRICE_MIN) / 2;
        priceDouble *= PRICE_WEIGHT;
        return priceDouble;
    }

    private double[] generateStyleValue() {
        double[] fourStyles = new double[4];
        if ("ignore".equalsIgnoreCase(general_type)) {
            return new double[]{0.0, 0.0, 0.0, 0.0};
        }

        // get category value
        String categoryLower = category.toLowerCase();
        double casualCategoryValue = -1;
        double formalCategoryValues = -1;
        for (String c : CATEGORY_VALUES.keySet()) {
            if (categoryLower.contains(c)) {
                casualCategoryValue = CATEGORY_VALUES.get(c);
                formalCategoryValues = 8 - casualCategoryValue;
                break;
            }
        }

        if (casualCategoryValue == -1) {
            Double[] typeValues = TYPE_VALUES.get(type.toLowerCase());
            for (int i = 0; i < typeValues.length; i++) {
                fourStyles[i] = typeValues[i];
            }
        } else {

        }


        return fourStyles;
    }
}
