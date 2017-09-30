package main.java;

import java.util.ArrayList;
import java.util.HashMap;

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

    public HashMap<String, Double[]> retrieveSortedCosinSimilarity(ArrayList<DatabaseItem> truncatedDataBaseItems,
                                                             DatabaseItem queryItem) {
        HashMap<Double, DatabaseItem> similarityWithQueryitem = new HashMap<>();
        double[] queryVectorValues = queryItem.generateValues();

        for (DatabaseItem item : truncatedDataBaseItems) {
            double[] dataVectorValues = item.generateValues();
            double temp = valueSimilarity.cosineSimilarity(dataVectorValues, queryVectorValues);
            similarityWithQueryitem.put(temp, item);
        }

        
        return null;
    }
}
