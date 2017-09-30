package url.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

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

        List<Document> documents = (List<Document>) collection.find().into(
                new ArrayList<Document>());

        System.out.println(documents);

        //HashMap<Document, Set<String>> data = new HashMap<>();
        for(Document document : documents){
            System.out.println(document.keySet().toString());

        }
        //mongoClient.getDatabaseNames().forEach(System.out::println);
    }
    public static void main(String[] args) {
        MongoDBUrl testData = new MongoDBUrl();
        testData.test();
    }
}
