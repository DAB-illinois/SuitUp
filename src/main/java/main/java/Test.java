package main.java;

import mongodb.MongoDBUtility;

import java.util.HashMap;

public class Test {

    private double cosineSimilarity (HashMap<String, Double> query, HashMap<String, Double> rec) {
        double scalar = 0, norm1 = 0, norm2 = 0;
        for (String k : query.keySet()) {
            scalar += rec.get(k) * query.get(k); //dot product
        }
        for (String k : query.keySet()) {
            norm1 += query.get(k) * query.get(k); //magnitude for query
        }
        norm1 = Math.sqrt(norm1);
        for (String k : rec.keySet()) {
            norm2 += rec.get(k) * rec.get(k); //magnitude for rec
        }
        norm2 = Math.sqrt(norm2);
        return scalar/(norm1*norm2);
    }

    public static void main (String[] args) {
        MongoDBUtility testData = new MongoDBUtility();
        System.out.println(testData.getJsonFromString(testData.retrieveData()));
    }
}
