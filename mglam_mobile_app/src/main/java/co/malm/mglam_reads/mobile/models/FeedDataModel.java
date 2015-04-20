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

package co.malm.mglam_reads.mobile.models;

import java.util.Collections;
import java.util.List;

import co.malm.mglam_reads.backend.endpoints.mglamRssEndpoint.model.FeedItem;

/**
 * Data Model class for storing rss channel articles data
 *
 * @author marlonlom
 */
public final class FeedDataModel {
    public static final String TAG = FeedDataModel.class.getSimpleName();
    private static final FeedDataModel INSTANCE = new FeedDataModel();
    private List<FeedItem> feedItems;
    private List<String> categories;

    private FeedDataModel() {
        this.setCategories(Collections.<String>emptyList());
        this.setFeedItems(Collections.<FeedItem>emptyList());
    }

    public static FeedDataModel getInstance() {
        return INSTANCE;
    }

    public boolean noData() {
        boolean emptyCategory = this.categories.isEmpty();
        boolean emptyItems = this.feedItems.isEmpty();
        return emptyCategory && emptyItems;

    }

    public List<FeedItem> getFeedItems() {
        return this.feedItems;
    }

    public void setFeedItems(List<FeedItem> feedItems) {
        this.feedItems = feedItems;
    }

    public List<FeedItem> getFeedItems(String category) {
        List<FeedItem> items = Collections.emptyList();
        for (FeedItem itm : this.getFeedItems()) {
            final List<String> itmCategories = itm.getCategories();
            if (itmCategories.contains(category)) {
                items.add(itm);
            }
        }
        return items;
    }

    public List<String> getCategories() {
        return this.categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

}
