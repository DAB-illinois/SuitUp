package main.java;

import com.sun.corba.se.impl.orb.DataCollectorBase;
import mongodb.MongoDBUtility;

import javax.xml.crypto.Data;
import java.awt.image.DataBuffer;
import java.util.*;

/**
 * Basic machine learning that learns from what the user likes/dislikes
 */
public class SuitUpAI {

    private HashMap<DatabaseItem, DatabaseItem> userPreference;
    private static DatabaseItem[] database;
    private DatabaseItem queryItem;

    public SuitUpAI(DatabaseItem queryItem) {
        userPreference = new HashMap<>();
        database = MongoDBUtility.retrieveMongoDatabase();
        this.queryItem = queryItem;
    }

    /**
     * This method checks whether the general type is matching and the general gender is different between all the
     * other data and the query data. When either of them are true, items get added/removed to/from the
     * truncatedDataBseitems arraylist.
     *
     * @return truncatedDataBaseitems : list of the data that has been truncated with getGeneralType() and getGender()
     */
    public ArrayList<DatabaseItem> getAllRelatedItems() {
        if (database == null || database.length == 0 || queryItem == null) {
            return null;
        }

        ArrayList<DatabaseItem> truncatedDataBaseItems = new ArrayList<>();

        for (int i = 0; i < database.length; i++) {
            if (queryItem.getGeneralType().equalsIgnoreCase(database[i].getGeneralType())) {
                truncatedDataBaseItems.add(database[i]);
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
     * @return sortedSimilarItems : returns the HashMap<DatabaseItem, Double> that is sorted
     */
    public HashMap<DatabaseItem, Double> retrieveSortedCosinSimilarity(ArrayList<DatabaseItem> truncatedDataBaseItems) {
        if (truncatedDataBaseItems == null || truncatedDataBaseItems.size() == 0 || queryItem == null) {
            return null;
        }

        HashMap<Double, DatabaseItem> similarityWithQueryitem = new HashMap<>();
        double[] queryVectorValues = queryItem.generateValues();

        for (DatabaseItem item : truncatedDataBaseItems) {
            double[] dataVectorValues = item.generateValues();
            double temp = ValueSimilarity.cosineSimilarity(dataVectorValues, queryVectorValues);
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

    public void presentItemToUser(HashMap<DatabaseItem, Double> itemPresentOrder) {
        if (itemPresentOrder == null || itemPresentOrder.size() == 0) {
            return;
        }
        Scanner userInput = new Scanner(System.in);

        List<DatabaseItem> recItems = new ArrayList<DatabaseItem>(itemPresentOrder.keySet());
        Iterator iterator = recItems.iterator();
        boolean likedRecommendation = false;

        while (iterator.hasNext() && !likedRecommendation) {
            DatabaseItem currentRecommendation = (DatabaseItem) iterator.next();
            System.out.println(currentRecommendation.toString());
            System.out.println("\nIf you like the recommendation, type 'yes'. If not, type 'no'.");
            String answer = userInput.nextLine();
            if (answer.equalsIgnoreCase("yes")) {
                userPreference.put(queryItem, currentRecommendation);
                likedRecommendation = true;
            } else {
                System.out.println("Next Recommendation:");
            }
        }
    }

    public static void main(String[] args) {
        DatabaseItem[] databaseItems = MongoDBUtility.retrieveMongoDatabase();
        SuitUpAI suit = new SuitUpAI(databaseItems[752]);
        System.out.println(databaseItems[752].toString());
        ArrayList<DatabaseItem> truncated = suit.getAllRelatedItems();
        HashMap<DatabaseItem, Double> sorted = suit.retrieveSortedCosinSimilarity(truncated);
        suit.presentItemToUser(sorted);
    }
}
