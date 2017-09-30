package mongodb;

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

    /**
     * @return the url that directs all the data to/from
     */
    public String getUrl() {
        return url;
    }

    /**
     * Retrieves the data that has been put on with python from MongoDB.
     * Then the datas are stored in a list, which are then stored into a String[] in order to convert to a JSON file.
     *
     * @return dataSet : String[] of all the data
     */
    public String[] retrieveData() {
        MongoClient mongoClient = new MongoClient(new MongoClientURI(getUrl()));
        MongoDatabase check = mongoClient.getDatabase("bmDataset");

        MongoCollection<Document> collection = check.getCollection("tommyHil");

        List<Document> documents = (List<Document>) collection.find().into(new ArrayList<Document>());
        String[] dataSet = new String[documents.size()];

        Iterator iterator = documents.iterator();
        int count = 0;
        while (iterator.hasNext()) {
            dataSet[count] = iterator.next().toString();
            count++;
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
    public String getJsonFromString(String[] stringData) {
        Gson gson = new Gson();

        String jsonData = "";
        for (int i = 0; i < stringData.length; i++) {
            jsonData += gson.toJson(stringData[i]) + "\n";
        }
        return jsonData;
    }

    public static void main(String[] args) {
        MongoDBUtility testData = new MongoDBUtility();
        System.out.println(testData.getJsonFromString(testData.retrieveData()));
    }
}
