package co.malm.mglam_reads.backend.model;

/**
 * Created by marlonlom on 22/02/15.
 */
public enum RSSTags {

    ITEM("item"),
    ITEM_TITLE("title"),
    ITEM_LINK("link"),
    ITEM_DETAIL("description"),
    ITEM_CATEGORY("category"),
    ITEM_PUBDATE("pubDate");

    private String tagName;

    private RSSTags(String tagName) {
        this.tagName = tagName;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
}
