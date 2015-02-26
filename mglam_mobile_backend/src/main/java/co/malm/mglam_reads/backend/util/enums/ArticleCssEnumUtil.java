package co.malm.mglam_reads.backend.util.enums;

import java.util.logging.Logger;

/**
 * Utility class for reading RSS configuration properties file, and parsing to a
 * ENUM
 *
 * @author marlonlom
 */
public final class ArticleCssEnumUtil extends EnumUtil {

    /**
     * RSS configuration Properties path
     */
    private static final String PROPS_PATH = "WEB-INF/html_article.properties";

    /**
     * Logger for the utility class {@link ArticleCssEnumUtil}
     *
     * @see java.util.logging.Logger
     */
    private static final Logger LOGGER = Logger.getLogger(ArticleCssEnumUtil.class.getName());

    /**
     * Singleton instance for the class
     */
    private static final ArticleCssEnumUtil instance = new ArticleCssEnumUtil();

    /**
     * Private constructor, as it may contains static methods
     */
    private ArticleCssEnumUtil() {
        super();
        preparePropertiesContent(LOGGER, PROPS_PATH);
    }

    /**
     * Returns singleton instance of {@link ArticleCssEnumUtil}
     *
     * @return singleton instance
     */
    public static ArticleCssEnumUtil getInstance() {
        return instance;
    }

}
