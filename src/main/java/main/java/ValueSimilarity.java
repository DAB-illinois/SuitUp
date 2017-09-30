package main.java;

import mongodb.MongoDBUtility;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.HashMap;

public class ValueSimilarity {

    public static double cosineSimilarity(double[] query, double[] rec) {
        double scalar = 0, norm1 = 0, norm2 = 0;

        scalar = computeDotProduct(query, rec);
        norm1 = Math.sqrt(computeDotProduct(query, query));
        norm2 = Math.sqrt(computeDotProduct(rec, rec));

        return scalar / (norm1 * norm2);
    }

    private static double computeDotProduct(double[] queryValues, double[] recValues) {
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

    public static ArrayList<DatabaseItem> sort(HashMap<DatabaseItem, Double> hashMap) {
        return null;
    }
}
