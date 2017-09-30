package main.java;

import mongodb.MongoDBUtility;

import java.util.*;

/**
 * Basic machine learning that learns from what the user likes/dislikes
 */
public class SuitUpAI {

    private static final String IGNORE = "ignore";

    private static HashMap<DatabaseItem, DatabaseItem> userPreference;
    private static DatabaseItem[] databaseItems;

    /**
     * This method checks whether the general type is matching and the general gender is different between all the
     * other data and the query data. When either of them are true, items get added/removed to/from the
     * truncatedDataBseitems arraylist.
     *
     * @return truncatedDataBaseitems : list of the data that has been truncated with getGeneralType() and getGender()
     */
    public static ArrayList<DatabaseItem> getAllRelatedItems(DatabaseItem queryItem) {
        if (databaseItems == null || databaseItems.length == 0 || queryItem == null) {
            return null;
        }

        ArrayList<DatabaseItem> filteredDataBaseItems = new ArrayList<>();

        for (int i = 0; i < databaseItems.length; i++) {
            DatabaseItem currentItem = databaseItems[i];
            if (!queryItem.getName().equalsIgnoreCase(currentItem.getName()) &&
                    queryItem.getGender().equalsIgnoreCase(currentItem.getGender()) &&
                    !queryItem.getGeneralType().equalsIgnoreCase(currentItem.getGeneralType()) &&
                    !currentItem.getGeneralType().equalsIgnoreCase(IGNORE)) {
                filteredDataBaseItems.add(databaseItems[i]);
            }
        }

        return filteredDataBaseItems;
    }

    /**
     * This method uses the ValueSimilarity class to retrieve cosine values (smallest gaps between two vectors) and
     * sort them to find the most similar product.
     *
     * @param filteredDataBaseItems : Items that have been truncated based on the user's taste
     * @return sortedSimilarItems : returns the HashMap<DatabaseItem, Double> that is sorted
     */
    public static List<DatabaseItem> retrieveSortedCosineSimilarity(DatabaseItem queryItem,
                                                                    ArrayList<DatabaseItem> filteredDataBaseItems) {
        if (filteredDataBaseItems == null || filteredDataBaseItems.size() == 0 || queryItem == null) {
            return null;
        }

        final HashMap<DatabaseItem, Double> similarityWithQueryitem = new HashMap<>();
        double[] queryVectorValues = queryItem.generateValues();


        for (DatabaseItem item : filteredDataBaseItems) {
            double[] dataVectorValues = item.generateValues();
            double temp = ValueSimilarity.cosineSimilarity(dataVectorValues, queryVectorValues);
            similarityWithQueryitem.put(item, temp);
        }

        List<DatabaseItem> sorted = ValueSimilarity.sort(similarityWithQueryitem);

        return sorted;
    }

    public static void presentItemToUser(DatabaseItem queryItem, List<DatabaseItem> itemPresentOrder) {
        if (itemPresentOrder == null || itemPresentOrder.size() == 0) {
            return;
        }
        Scanner userInput = new Scanner(System.in);

        Iterator iterator = itemPresentOrder.iterator();
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
        databaseItems = MongoDBUtility.retrieveMongoDatabase();
        DatabaseItem queryItem = databaseItems[752];
        if (!queryItem.getGeneralType().equalsIgnoreCase(IGNORE)) {
            System.out.println(databaseItems[752].toString());

            ArrayList<DatabaseItem> filtered = getAllRelatedItems(queryItem);
            System.out.println(filtered.size());
            List<DatabaseItem> sorted = retrieveSortedCosineSimilarity(queryItem, filtered);
            presentItemToUser(queryItem, sorted);
        }
    }
}