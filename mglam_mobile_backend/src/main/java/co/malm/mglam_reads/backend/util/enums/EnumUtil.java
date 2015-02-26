package co.malm.mglam_reads.backend.util.enums;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * Created by marlonlom on 25/02/15.
 */
public abstract class EnumUtil {
    /**
     * Internal properties for reading RSS configurations
     *
     * @see java.util.Properties
     */
    private Properties props = new Properties();

    /**
     * Reads EnumUtil.PROPS_PATH and saves content into properties
     *
     * @param logger    log utility
     * @param propsPath properties file path
     */
    void preparePropertiesContent(Logger logger, String propsPath) {
        try {
            props.load(new FileReader(propsPath));
        } catch (Exception e) {
            logger.severe(e.getMessage());
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
