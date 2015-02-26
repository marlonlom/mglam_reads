package co.malm.mglam_reads.backend.util.enums;

import java.util.logging.Logger;

/**
 * Utility class for reading RSS configuration properties file, and parsing to a
 * ENUM
 *
 * @author marlonlom
 */
public final class RssEnumUtil extends EnumUtil {

    /**
     * RSS configuration Properties path
     */
    private static final String PROPS_PATH = "WEB-INF/rss.properties";

    /**
     * Logger for the utility class {@link co.malm.mglam_reads.backend.util.enums.RssEnumUtil}
     *
     * @see java.util.logging.Logger
     */
    private static final Logger LOGGER = Logger.getLogger(RssEnumUtil.class.getName());

    /**
     * Singleton instance for the class
     */
    private static final RssEnumUtil instance = new RssEnumUtil();

    /**
     * Private constructor, as it may contains static methods
     */
    private RssEnumUtil() {
        super();
        preparePropertiesContent(LOGGER, PROPS_PATH);
    }

    /**
     * Returns singleton instance of {@link co.malm.mglam_reads.backend.util.enums.RssEnumUtil}
     *
     * @return singleton instance
     */
    public static RssEnumUtil getInstance() {
        return instance;
    }

}
