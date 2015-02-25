package co.malm.mglam_reads.backend.http;

import java.util.logging.Logger;

/**
 * HTTP Connection implementation class for retrieving feed
 * xml contents
 *
 * @author marlonlom
 */
public final class WebpageConnection extends CommonUrlConnection {

    /**
     * Logging utility
     *
     * @see java.util.logging.Logger
     */
    private static final Logger LOGGER = Logger.getLogger(WebpageConnection.class.getName());

    /**
     * Obtain the contents of a website using its url
     *
     * @param aLink
     * @return contents of the url address
     */
    public static String retrieveWebpageContent(String aLink) {
        StringBuilder builder = new StringBuilder("");
        try {

            performHttpGET(builder, aLink);

        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
        }
        return builder.toString();
    }


}
