package co.malm.mglam_reads.backend.sax;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import co.malm.mglam_reads.backend.model.FeedChannel;
import co.malm.mglam_reads.backend.model.FeedItem;
import co.malm.mglam_reads.backend.rss.RssConfig;

/**
 * Handler class for parse XML feed contents from remote feed url
 *
 * @author marlonlom
 */
public class RssFeedHandler extends DefaultHandler {

    /**
     * Count index for feed items collection
     */
    private int currentIndex;

    /**
     * Current tag used for parsing content
     */
    private String currentTag;

    /**
     * Set of categories
     */
    private HashMap<String, Integer> categories;

    /**
     * RSS Channel instance
     */
    private FeedChannel rssChannel;

    private StringBuilder contentBuilder;

    /**
     * Default constructor
     */
    public RssFeedHandler() {
        this.currentTag = "";
        this.setRssChannel(new FeedChannel());
    }

    /**
     * Returns actual feed item
     *
     * @return feed item
     */
    private FeedItem getCurrentItem() {
        return getRssChannel().getItems().get(currentIndex);
    }

    /**
     * Returns RSS Channel instance
     *
     * @return channel instance
     */
    public FeedChannel getRssChannel() {
        return rssChannel;
    }

    /**
     * Modify RSS Channel instance
     *
     * @param rssChannel channel instance to set
     */
    private void setRssChannel(FeedChannel rssChannel) {
        this.rssChannel = rssChannel;
    }

    /**
     * Prepares categories list for fee, using scores for each item on the list.<br/>
     * Selects the elements wich have score above the avegage value.
     */
    private void prepareCategoriesList() {
        this.getRssChannel().setCategories(new ArrayList<String>());
        int minCount = 0;
        int maxCount = 0;
        if (!categories.isEmpty()) {
            for (String k : categories.keySet()) {
                Integer keyValue = categories.get(k);
                if (Math.max(keyValue, maxCount) == keyValue) {
                    maxCount = keyValue;
                } else if (Math.min(keyValue, minCount) == keyValue) {
                    minCount = keyValue;
                }
            }
            double avgCount = (minCount + maxCount) / 2;

            for (String k : categories.keySet()) {
                Integer keyValue = categories.get(k);
                if (Math.max(keyValue, avgCount) == keyValue) {
                    String capitalizedTitle = k.substring(0, 1).toUpperCase().concat(k.substring(1).toLowerCase());
                    getRssChannel().getCategories().add(capitalizedTitle);
                }
            }

            Collections.sort(getRssChannel().getCategories());
        }
    }

    @Override
    public void startDocument() throws SAXException {
        categories = new HashMap<String, Integer>();
        currentIndex = -1;
        contentBuilder = new StringBuilder("");
    }


    @Override
    public void endDocument() throws SAXException {

        prepareCategoriesList();

    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        if (!contentBuilder.toString().isEmpty()) {
            contentBuilder.setLength(0);
        }

        if (qName.equalsIgnoreCase(RssConfig.RSS_ITEM.getTagValue())) {
            currentIndex++;
            getRssChannel().getItems().add(currentIndex, new FeedItem());
        }

    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

        boolean isInItem = currentIndex > -1;

        if (isInItem && qName.equalsIgnoreCase(RssConfig.RSS_ITEM_TITLE.getTagValue())) {
            getCurrentItem().setTitle(contentBuilder.toString());
        }

        if (isInItem && qName.equalsIgnoreCase(RssConfig.RSS_ITEM_LINK.getTagValue())) {
            getCurrentItem().setLink(contentBuilder.toString());
        }

        if (isInItem && qName.equalsIgnoreCase(RssConfig.RSS_ITEM_PUBDATE.getTagValue())) {
            getCurrentItem().setPubDate(contentBuilder.toString());
        }

        if (isInItem && qName.equalsIgnoreCase(RssConfig.RSS_ITEM_DESCRIPTION.getTagValue())) {
            String detailContent = contentBuilder.toString().replace("<p>", "");
            getCurrentItem().setDescription(detailContent.split("</p>")[0]);
        }

        if (isInItem && qName.equalsIgnoreCase(RssConfig.RSS_ITEM_CATEGORY.getTagValue())) {
            getCurrentItem().getCategories().add(contentBuilder.toString());
            if (!categories.containsKey(contentBuilder.toString())) {
                categories.put(contentBuilder.toString(), 0);
            } else {
                Integer score = categories.get(contentBuilder.toString());
                categories.put(contentBuilder.toString(), score + 1);
            }

        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {

        contentBuilder.append(ch, start, length);

    }

}
