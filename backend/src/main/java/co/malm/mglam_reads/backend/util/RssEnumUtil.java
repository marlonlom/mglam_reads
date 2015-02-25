package co.malm.mglam_reads.backend.util;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * Utility class for reading RSS configuration properties file, and parsing to a
 * ENUM
 *
 * @author marlonlom
 */
public final class RssEnumUtil {

    /**
     * RSS configuration Properties path
     */
    private static final String PROPS_PATH = "WEB-INF/rss.properties";

    /**
     * Logger for the utility class {@link co.malm.mglam_reads.backend.util.RssEnumUtil}
     *
     * @see java.util.logging.Logger
     */
    private static final Logger LOGGER = Logger.getLogger(RssEnumUtil.class.getName());

    /**
     * Singleton instance for the class
     */
    private static final RssEnumUtil instance = new RssEnumUtil();

    /**
     * Internal properties for reading RSS configurations
     *
     * @see java.util.Properties
     */
    private Properties props = new Properties();

    /**
     * Private constructor, as it may contains static methods
     */
    private RssEnumUtil() {
        super();
        preparePropertiesContent();
    }

    /**
     * Returns singleton instance of {@link co.malm.mglam_reads.backend.util.RssEnumUtil}
     *
     * @return singleton instance
     */
    public static RssEnumUtil getInstance() {
        return instance;
    }

    /**
     * Reads RssEnumUtil.PROPS_PATH and saves content into properties
     */
    private void preparePropertiesContent() {
        try {
            props.load(new FileReader(PROPS_PATH));
        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
        }
    }

    /**
     * Returns the value of the property key
     *
     * @param key some property key
     * @return property value
     */
    public String getProperty(String key) {
        return props.getProperty(key);
    }

    /**
     * Return rss properties keys list
     *
     * @return list of property keys
     */
    public ArrayList<String> getPropertyKeys() {

        Enumeration<?> propertyNames = props.propertyNames();
        ArrayList<String> propKeys = new ArrayList<>();

        for (; propertyNames.hasMoreElements(); ) {
            String propName = (String) propertyNames.nextElement();
            propKeys.add(propName);
        }

        return propKeys;
    }

}
