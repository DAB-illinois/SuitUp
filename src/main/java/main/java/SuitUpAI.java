package main.java;

import javax.xml.crypto.Data;
import java.util.*;

/**
 * Basic machine learning that learns from what the user likes/dislikes
 */
public class SuitUpAI {

    private ValueSimilarity valueSimilarity;

    public SuitUpAI() {
        valueSimilarity = new ValueSimilarity();
    }

    /**
     * This method checks whether the general type is matching and the general gender is different between all the
     * other data and the query data. When either of them are true, items get added/removed to/from the
     * truncatedDataBseitems arraylist.
     *
     * @param databaseItems : array of all the data
     * @param queryItem : one particular data that the user provided
     * @return truncatedDataBaseitems : list of the data that has been truncated with getGeneralType() and getGender()
     */
    public ArrayList<DatabaseItem> getAllRelatedItems(DatabaseItem[] databaseItems, DatabaseItem queryItem) {
        ArrayList<DatabaseItem> truncatedDataBaseItems = new ArrayList<>();

        for (int i = 0; i < databaseItems.length; i++) {
            if (queryItem.getGeneralType().equalsIgnoreCase(databaseItems[i].getGeneralType())) {
                truncatedDataBaseItems.add(databaseItems[i]);
            }
        }

        for (int i = 0; i < truncatedDataBaseItems.size(); i++) {
            if (!queryItem.getGender().equalsIgnoreCase(truncatedDataBaseItems.get(i).getGender())) {
                truncatedDataBaseItems.remove(truncatedDataBaseItems.get(i));
            }
        }

        return truncatedDataBaseItems;
    }

    /**
     * This method uses the ValueSimilarity class to retrieve cosine values (smallest gaps between two vectors) and
     * sort them to find the most similar product.
     *
     * @param truncatedDataBaseItems : Items that have been truncated based on the user's taste
     * @param queryItem : Item that the user inputed
     * @return sortedSimilarItems : returns the HashMap<DatabaseItem, Double> that is sorted
     */
    public HashMap<DatabaseItem, Double> retrieveSortedCosinSimilarity(ArrayList<DatabaseItem> truncatedDataBaseItems,
                                                             DatabaseItem queryItem) {
        HashMap<Double, DatabaseItem> similarityWithQueryitem = new HashMap<>();
        double[] queryVectorValues = queryItem.generateValues();

        for (DatabaseItem item : truncatedDataBaseItems) {
            double[] dataVectorValues = item.generateValues();
            double temp = valueSimilarity.cosineSimilarity(dataVectorValues, queryVectorValues);
            similarityWithQueryitem.put(temp, item);
        }

        List<Double> sortCosinSimilarity = new ArrayList<Double>(similarityWithQueryitem.keySet());
        Collections.sort(sortCosinSimilarity);
        HashMap<DatabaseItem, Double> sortedSimilarItems = new HashMap<>();

        for (int i = 0; i < similarityWithQueryitem.size(); i++) {
            sortedSimilarItems.put(similarityWithQueryitem.get(sortCosinSimilarity.get(i)), sortCosinSimilarity.get(i));
        }

        return sortedSimilarItems;
    }
}
