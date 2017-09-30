package url.mongodb;

/**
 * The url for submitting and retrieving data from the MongoDB database.
 */
public class MongoDBUrl {
    private final String url = "\n" +
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
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        mongoClient.getDatabaseNames().forEach(System.out::println);
    }
}
