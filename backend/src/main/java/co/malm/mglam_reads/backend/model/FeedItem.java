package co.malm.mglam_reads.backend.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * The object model for the data we are sending through endpoints
 *
 * @author marlonlom
 */
public class FeedItem implements Serializable {

    private String title;
    private String link;
    private String description;
    private ArrayList<String> categories;
    private String pubDate;

    public FeedItem() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<String> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<String> categories) {
        this.categories = categories;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public boolean containsCategory(String aCategory) {
        return this.getCategories().contains(aCategory);
    }
}