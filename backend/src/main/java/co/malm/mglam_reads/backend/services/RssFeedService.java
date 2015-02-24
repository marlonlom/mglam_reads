package co.malm.mglam_reads.backend.services;

import co.malm.mglam_reads.backend.http.RSSConnection;
import co.malm.mglam_reads.backend.model.FeedChannel;
import co.malm.mglam_reads.backend.sax.RssFeedXMLParser;

/**
 * Service implementation class for obtaining RSS feed
 *
 * @author marlonlom
 */
public final class RssFeedService {

    /**
     * Returns remote feed channel
     *
     * @return rss channel
     */
    public FeedChannel obtainRssChannel() {
        RssFeedXMLParser xmlParser = new RssFeedXMLParser();

        /*retrieves rss feed xml contents*/
        String retrievedFeed = RSSConnection.retrieveFeed();

        /*if not empty, parses xml content*/
        if (!retrievedFeed.isEmpty()) {
            xmlParser.performParsing(retrievedFeed);

            return xmlParser.getFeedChannel();
        }

        return null;
    }
}
