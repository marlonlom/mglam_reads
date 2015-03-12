package co.malm.mglam_reads.mobile.events;

import co.malm.mglam_reads.backend.endpoints.mglamRssEndpoint.model.FeedChannel;

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
