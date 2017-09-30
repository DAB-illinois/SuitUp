package main.java;

import mongodb.MongoDBUtility;

import java.util.HashMap;

public class ValueSimilarity {

    //private double cosineValue;

    public double cosineSimilarity(Double[] query, Double[] rec, DatabaseItem databaseItem) {
        double scalar = 0, norm1 = 0, norm2 = 0;

        scalar = computeValues(query, rec);
        norm1 = Math.sqrt(computeValues(query, query));
        norm2 = Math.sqrt(computeValues(rec, rec));
        //cosineValue = scalar / (norm1 * norm2);

        //HashMap<Double, DatabaseItem> cosineValueSet = new HashMap<>();
        //cosineValueSet.put(cosineValue, databaseItem);

        return scalar / (norm1 * norm2);
    }

    private double computeValues(Double[] queryValues, Double[] recValues) {
        double outputValue = 0.0;

        for (int i = 0; i < queryValues.length; i++) {
            outputValue += recValues[i] * queryValues[i];
        }

        return outputValue;
    }

    public static void main (String[] args) {
        MongoDBUtility testData = new MongoDBUtility();
        System.out.println(testData.getJsonFromString(testData.retrieveData()));
    }
}
