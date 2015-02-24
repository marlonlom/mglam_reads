package co.malm.mglam_reads.backend.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Model class for RSS channel definition
 *
 * @author marlonlom
 */
public class FeedChannel implements Serializable {

    private ArrayList<FeedItem> items;
    private ArrayList<String> categories;

    public FeedChannel() {
        super();
        this.setItems(new ArrayList<FeedItem>());
        this.setCategories(new ArrayList<String>());
    }

    public FeedChannel(ArrayList<FeedItem> feedItems) {
        this.setItems((ArrayList<FeedItem>) feedItems.clone());
        this.setCategories(new ArrayList<String>());
    }

    public FeedChannel(ArrayList<FeedItem> feedItems, ArrayList<String> feedCategories) {
        this.setItems((ArrayList<FeedItem>) feedItems.clone());
        this.setCategories((ArrayList<String>) feedCategories.clone());
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

}
