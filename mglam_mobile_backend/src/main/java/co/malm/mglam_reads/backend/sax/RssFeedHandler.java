/*
 * Copyright 2015 marlonlom
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package co.malm.mglam_reads.backend.sax;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;

import co.malm.mglam_reads.backend.configs.RssConfig;
import co.malm.mglam_reads.backend.model.FeedChannel;
import co.malm.mglam_reads.backend.model.FeedItem;
import co.malm.mglam_reads.backend.util.DateDiffUtil;

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
            // obtains min and max values
            for (String k : categories.keySet()) {
                Integer keyValue = categories.get(k);
                if (Math.max(keyValue, maxCount) == keyValue) {
                    maxCount = keyValue;
                } else if (Math.min(keyValue, minCount) == keyValue) {
                    minCount = keyValue;
                }
            }

            // calculate average
            double avgCount = (minCount + maxCount) / 2;

            // add categories with score above average
            for (String k : categories.keySet()) {
                Integer keyValue = categories.get(k);
                if (Math.max(keyValue, avgCount) == keyValue) {
                    String capitalizedTitle = k.substring(0, 1).toUpperCase().concat(k.substring(1).toLowerCase());
                    getRssChannel().getCategories().add(capitalizedTitle);
                }
            }

            //Sorts categories list
            Collections.sort(getRssChannel().getCategories());
        }
    }

    /**
     * Performs update for items publish date attributes
     */
    private void prepareArticleShortPubdate() {
        if (!getRssChannel().getItems().isEmpty()) {
            final Date now = new Date();
            for (FeedItem rssItem : getRssChannel().getItems()) {
                String textDate = rssItem.getPubDate();
                String diffDate = DateDiffUtil.getInstance().diff(textDate, now);
                rssItem.setPubDate(diffDate);
            }
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

        prepareArticleShortPubdate();

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
            String aCategory = contentBuilder.toString().toLowerCase();
            getCurrentItem().getCategories().add(aCategory);
            if (!categories.containsKey(aCategory)) {
                categories.put(aCategory, 0);
            } else {
                Integer score = categories.get(aCategory);
                categories.put(aCategory, score + 1);
            }

        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {

        contentBuilder.append(ch, start, length);

    }

}
