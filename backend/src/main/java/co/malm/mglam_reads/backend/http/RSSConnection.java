package co.malm.mglam_reads.backend.http;

import java.util.Properties;
import java.util.logging.Logger;

import co.malm.mglam_reads.backend.util.PropertiesUtil;

/**
 * Created by marlonlom on 22/02/15.
 */
public final class RSSConnection extends CommonUrlConnection {

    private static final RSSConnection instance = new RSSConnection();
    private static final Logger LOGGER = Logger.getLogger(RSSConnection.class.getName());
    private static final String RSS_PROPERTIES = "rss.properties";
    private static final String RSS_URL = "rss_url";

    PropertiesUtil propertyUtil = PropertiesUtil.getInstance();

    public static RSSConnection getInstance() {
        return instance;
    }

    public String retrieveFeed() {
        StringBuilder builder = new StringBuilder("");
        try {
            Properties readProps = propertyUtil.readProps(RSS_PROPERTIES);
            String rssUrl = readProps.getProperty(RSS_URL);

            performConnection(builder, rssUrl);

        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
        }
        return builder.toString();
    }


}
