package co.malm.mglam_reads.backend.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Model class for RSS channel definition
 *
 * @author marlonlom
 */
public class FeedChannel implements Serializable {

    /**
     * Rss Feed items
     */
    private ArrayList<FeedItem> items;

    /**
     * Rss Feed categories
     */
    private ArrayList<String> categories;

    /**
     * Default constructor
     */
    public FeedChannel() {
        super();
        this.setItems(new ArrayList<FeedItem>());
        this.setCategories(new ArrayList<String>());
    }

    /**
     * Returns feed items list
     *
     * @return items list
     */
    public ArrayList<FeedItem> getItems() {
        return items;
    }

    /**
     * Change feed feedItems list
     *
     * @param feedItems list to set
     */
    public void setItems(ArrayList<FeedItem> feedItems) {
        this.items = new ArrayList<FeedItem>(feedItems);
    }

    /**
     * Returns feed items categories
     *
     * @return categories list
     */
    public ArrayList<String> getCategories() {
        return categories;
    }

    /**
     * Modifies feed categories list
     *
     * @param someCategories list to set
     */
    public void setCategories(ArrayList<String> someCategories) {
        this.categories = new ArrayList<String>(someCategories);
    }

}
