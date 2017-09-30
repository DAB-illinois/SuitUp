package main.java;

import java.util.ArrayList;

public class SuitUpAI {
    public ArrayList<DatabaseItem> getAllRelatedItems(DatabaseItem[] databaseItems, DatabaseItem queryItem) {
        ArrayList<DatabaseItem> truncatedDataBaseItems = new ArrayList<>();

        for (int i = 0; i < databaseItems.length; i++) {
            if (queryItem.getType().equalsIgnoreCase(databaseItems[i].getType())) {
                truncatedDataBaseItems.add(databaseItems[i]);
            }
        }

        for (int i = 0; i < databaseItems.length; i++) {}
        return null;
    }
}
