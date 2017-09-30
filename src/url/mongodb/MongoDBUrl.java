package url.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.*;

/**
 * The url for submitting and retrieving data from the MongoDB database.
 */
public class MongoDBUrl {
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

    public void test() {
        MongoClient mongoClient = new MongoClient(new MongoClientURI(url));
        MongoDatabase check = mongoClient.getDatabase("bmDataset");

        MongoCollection<Document> collection = check
                .getCollection("tommyHil");

        List<Document> documents = (List<Document>) collection.find().into(new ArrayList<Document>());

        Iterator iterator = documents.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next().toString());
        }


        //for (String identity : data)
        //mongoClient.getDatabaseNames().forEach(System.out::println);
    }
    public static void main(String[] args) {
        MongoDBUrl testData = new MongoDBUrl();
        testData.test();
    }
}
