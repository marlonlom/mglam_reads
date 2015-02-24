package co.malm.mglam_reads.backend.http;

import java.util.logging.Logger;

import co.malm.mglam_reads.backend.rss.RssConfig;

/**
 * HTTP Connection implementation class for retrieving feed
 * xml contents
 *
 * @author marlonlom
 */
public final class RSSConnection extends CommonUrlConnection {

    /**
     * Logging utility
     *
     * @see java.util.logging.Logger
     */
    private static final Logger LOGGER = Logger.getLogger(RSSConnection.class.getName());

    /**
     * Obtain the contents of an XML RSS Feed using its url
     *
     * @return xml contents of the feed
     */
    public static String retrieveFeed() {
        StringBuilder builder = new StringBuilder("");
        try {

            performHttpGET(builder, RssConfig.RSS_URL.getTagValue());

        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
        }
        return builder.toString();
    }


}
