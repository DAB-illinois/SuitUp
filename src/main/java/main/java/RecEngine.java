package main.java;

import mongodb.MongoDBUtility;

public class RecEngine {

    private static DatabaseItem[] databaseItems;

    public static void setup() {
        databaseItems = MongoDBUtility.retrieveMongoDatabase();
    }

    public static DatabaseItem recommendOutfit(DatabaseItem databaseItem) {
        return null;
    }

}
