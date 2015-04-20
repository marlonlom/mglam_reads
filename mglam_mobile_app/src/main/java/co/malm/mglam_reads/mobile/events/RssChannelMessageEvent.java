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

package co.malm.mglam_reads.mobile.events;

import co.malm.mglam_reads.backend.endpoints.mgReadingsEndpoint.model.FeedChannel;

/**
 * Event Implementation for handling Rss Feed channel contents
 *
 * @author marlonlom
 */
public class RssChannelMessageEvent {
    private FeedChannel rssChannel;

    /**
     * Default Constructor
     */
    public RssChannelMessageEvent() {
        super();
    }

    /**
     * Constructor, using a rss channel as parameter
     *
     * @param channel a rss feed channel
     */
    public RssChannelMessageEvent(FeedChannel channel) {
        this.setRssChannel(channel);
    }

    /**
     * Returns feed channel
     *
     * @return rssChannel
     */
    public FeedChannel getRssChannel() {
        return rssChannel;
    }

    /**
     * Change the rss chanel
     *
     * @param channel a rss chanel downloaded
     */
    public void setRssChannel(FeedChannel channel) {
        this.rssChannel = channel;
    }
}
