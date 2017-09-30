package main.java;

import mongodb.MongoDBUtility;

import java.util.ArrayList;
import java.util.List;

public class RecEngine {

    private static DatabaseItem[] databaseItems;

    public static void setup() {
        databaseItems = MongoDBUtility.retrieveMongoDatabase();
    }

    public static DatabaseItem recommendOutfit(DatabaseItem databaseItem) {
        if (databaseItem == null) {
            return null;
        }
        List<DatabaseItem> validItems = new ArrayList<>();
        String gender = databaseItem.getGender();
        String generalType = databaseItem.getGeneralType();
        double[] queryVector = databaseItem.generateValues();

        if (gender == null || generalType == null) {
            return null;
        }
        for (int i = 0; i < databaseItems.length; i++) {
            if (gender.equalsIgnoreCase(databaseItems[i].getGender()) &&
                    !generalType.equalsIgnoreCase(databaseItems[i].getGeneralType())) {
                validItems.add(databaseItems[i]);
            }
        }

        List<Double> cosineValues = new ArrayList<>();
        double maxCosine = -1;
        int maxCosineIndex = -1;

        for (int i = 0; i < validItems.size(); i++) {
            double[] recommendationVector = validItems.get(i).generateValues();
            cosineValues.add(ValueSimilarity.cosineSimilarity(queryVector, recommendationVector));
            if (cosineValues.get(i) > maxCosine) {
                maxCosine = cosineValues.get(i);
                maxCosineIndex = i;
            }
        }

        return validItems.get(maxCosineIndex);

    }

    public static void main(String args[]) {
        setup();
        DatabaseItem query = databaseItems[752];
        //DatabaseItem recommendation = recommendOutfit(query);

        //System.out.println("query: "+query.getLink());
        //System.out.println("recommendation:"+recommendation.getLink());

        //query = databaseItems[200];

        //query = databaseItems[400];
        DatabaseItem recommendation = recommendOutfit(query);

        System.out.println("query: "+query.getLink());
        System.out.println("recommendation:"+recommendation.getLink());
    }

}
