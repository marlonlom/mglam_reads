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
