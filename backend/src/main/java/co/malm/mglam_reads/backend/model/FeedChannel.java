package co.malm.mglam_reads.backend.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by marlonlom on 22/02/15.
 */
public class FeedChannel implements Serializable {

    private String name;
    private ArrayList<FeedItem> items;
    private ArrayList<String> categories;

    public FeedChannel() {
        super();
    }

    public FeedChannel(ArrayList<FeedItem> items) {
        this.items = items;
    }

    public FeedChannel(ArrayList<FeedItem> items, ArrayList<String> categories) {
        this.items = items;
        this.categories = categories;
    }

    public ArrayList<FeedItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<FeedItem> items) {
        this.items = items;
    }

    public ArrayList<String> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<String> categories) {
        this.categories = categories;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
