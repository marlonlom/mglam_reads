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

package co.malm.mglam_reads.backend.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * The object model for the data we are sending through endpoints
 *
 * @author marlonlom
 */
public class FeedItem implements Serializable {

    /**
     * Rss Feed item title
     */
    private String title;

    /**
     * Rss Feed item link
     */
    private String link;

    /**
     * Rss Feed item description
     */
    private String description;

    /**
     * Rss Feed item categories list
     */
    private ArrayList<String> categories;

    /**
     * Rss Feed item publish date
     */
    private String pubDate;

    /**
     * Default constructor
     */
    public FeedItem() {
        this.setCategories(new ArrayList<String>());
    }

    /**
     * Returns Rss Feed item title
     *
     * @return item title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Modifies Rss Feed item title
     *
     * @param title item title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Returns Rss Feed item link
     *
     * @return item link
     */
    public String getLink() {
        return link;
    }

    /**
     * Modifies Rss Feed item link
     *
     * @param aLink url address
     */
    public void setLink(String aLink) {
        this.link = aLink;
    }

    /**
     * Returns Rss Feed item description
     *
     * @return item description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Modifies Rss Feed item description
     *
     * @param text item description
     */
    public void setDescription(String text) {
        this.description = text;
    }

    /**
     * Returns Rss Feed item categories list
     *
     * @return item categories
     */
    public ArrayList<String> getCategories() {
        return categories;
    }

    /**
     * Modifies Rss Feed item categoriesList list
     *
     * @param categoriesList categories list
     */
    public void setCategories(ArrayList<String> categoriesList) {
        this.categories = categoriesList;
    }

    /**
     * Returns Rss Feed item publish date
     *
     * @return item publish date
     */
    public String getPubDate() {
        return pubDate;
    }

    /**
     * Modifies Rss Feed item publish date
     *
     * @param aPubDate
     */
    public void setPubDate(String aPubDate) {
        this.pubDate = aPubDate;
    }

    /**
     * Indicates in feed item belongs to selected category
     *
     * @param aCategory category to validate
     * @return true if category exists in feed item
     */
    public boolean containsCategory(String aCategory) {
        return this.getCategories().contains(aCategory.toLowerCase());
    }
}