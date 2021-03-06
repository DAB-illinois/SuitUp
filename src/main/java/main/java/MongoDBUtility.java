package main.java;

import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.*;

/**
 * The url for submitting and retrieving data from the MongoDB database.
 */
public class MongoDBUtility {
    private final String url = "" +
            "mongodb://DAB:DAB020407@cluster0-shard-00-00-dx2qu.mongodb.net:27017,cluster0-shard-00-01-dx2qu." +
            "mongodb.net:27017,cluster0-shard-00-02-dx2qu.mongodb.net:27017/test?ssl=true&replicaSet=" +
            "Cluster0-shard-0&authSource=admin";

    private static final String DATABASE_NAME = "bmDataset";
    private static final String COLLECTION_NAME = "tommyHil";

    /**
     * @return the url that directs all the data to/from
     */
    public String getUrl() {
        return url;
    }

    public static String getCollection() {
        return COLLECTION_NAME;
    }

    public static String getDataBase() {
        return DATABASE_NAME;
    }

    /**
     * Retrieves the data that has been put on with python from MongoDB.
     * Then the datas are stored in a list, which are then stored into a String[] in order to convert to a JSON file.
     *
     * @return dataSet : String[] of all the data
     */
    public String[] retrieveData() {
        MongoClient mongoClient = new MongoClient(new MongoClientURI(getUrl()));
        MongoDatabase check = mongoClient.getDatabase(DATABASE_NAME);

        MongoCollection<Document> collection = check.getCollection(COLLECTION_NAME);

        List<Document> documents = (List<Document>) collection.find().into(new ArrayList<Document>());
        String[] dataSet = new String[documents.size()];

        for (int i = 0; i < documents.size(); i++) {
            Document document = documents.get(i);
            dataSet[i] = document.toJson();
        }

        return dataSet;
    }

    /**
     * Converts the given string array into a Json string
     * TODO: for some reason, the first element is being truncated. FIX THIS!
     *
     * @param stringData : string array that has all the data
     * @return jsonData : String array which represents the data that has been converted to a json String
     */
    public DatabaseItem[] getJsonFromString(String[] stringData) {
        Gson gson = new Gson();

        DatabaseItem[] databaseItems = new DatabaseItem[stringData.length];

        String jsonData = "";
        for (int i = 0; i < stringData.length; i++) {
            DatabaseItem databaseItem = gson.fromJson(stringData[i], DatabaseItem.class);
            databaseItems[i] = databaseItem;
        }
        return databaseItems;
    }

    public static void main(String[] args) {
        MongoDBUtility testData = new MongoDBUtility();
        DatabaseItem[] databaseItems = testData.getJsonFromString(testData.retrieveData());
        List<String> categories = new ArrayList<String>();

        for (DatabaseItem databaseItem : databaseItems) {
            String category = databaseItem.getCategory();
            if (!categories.contains(category)) {
                categories.add(category);
            }
        }

        for (String c : categories) {
            System.out.println(c);
        }
    }

    public static DatabaseItem[] retrieveMongoDatabase() {
        MongoDBUtility testData = new MongoDBUtility();
        return testData.getJsonFromString(testData.retrieveData());
    }

    public static DatabaseItem retrieveItemFromLink(String link, DatabaseItem[] databaseItems) {
        for (DatabaseItem databaseItem : databaseItems) {
            if (databaseItem.getLink().equalsIgnoreCase(link)) {
                return databaseItem;
            }
        }
        return null;
    }
}
