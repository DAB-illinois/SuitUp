package main.java;

import mongodb.MongoDBUtility;

import javax.sound.midi.SysexMessage;
import javax.xml.crypto.Data;
import java.util.*;

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

    public static List<DatabaseItem> sort(HashMap<DatabaseItem, Double> hashMap) {
        HashMap<DatabaseItem, Double> copy = new HashMap<DatabaseItem, Double>(hashMap);
        List<Double> values = new ArrayList<>(hashMap.values());
        Collections.sort(values);
        Collections.reverse(values);

        List<DatabaseItem> sorted = new ArrayList<>();

        for (int i = 0; i < values.size(); i++) {
            double value = values.get(i);

            for (DatabaseItem databaseItem : copy.keySet()) {
                if (copy.get(databaseItem) == value) {
                    System.out.println(sorted.size());
                    sorted.add(databaseItem);
                    copy.remove(databaseItem);
                    break;
                }
            }
        }

        return sorted;
    }
}
