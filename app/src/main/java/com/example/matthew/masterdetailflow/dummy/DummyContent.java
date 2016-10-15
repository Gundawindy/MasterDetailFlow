package com.example.matthew.masterdetailflow.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<DummyItem> ITEMS = new ArrayList<DummyItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();
//Sets how many dummy items to make.
    private static final int COUNT = 25;
/*
* This creates the array that stores the list and details. It simply cycles around COUNT times,
* calling the createDummyItem method and passing it the count at the time and adding the returned
* data to the ArrayList ITEMS and to the Map ITEM_MAP. */
    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem(createDummyItem(i));
        }
    }
/*
* Actually adds the generated item to the Arrays. */
    private static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }
/*
 * This creates the dummy list of items(cars). It simply returns a data type DummyItem which contains
  * the string version of the int position and the string "Item " + position, and the return from makeDetails
  * for that position.*/
    private static DummyItem createDummyItem(int position) {
        return new DummyItem(String.valueOf(position), "Item " + position, makeDetails(position));
    }
/*This just makes up String to return. It builds it by simply adding the same line of text over and
* over, dependant on the int position passed to it. So item 22 gets 22 additional lines where item 4
* only gets 4 additional lines.*/
    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class DummyItem {
        public final String id;
        public final String content;
        public final String details;

        public DummyItem(String id, String content, String details) {
            this.id = id;
            this.content = content;
            this.details = details;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}
