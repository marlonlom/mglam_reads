package co.malm.mglam_reads.backend.util.enums;

import java.util.logging.Logger;

/**
 * Utility class for reading Diff labels configuration properties file, and parsing to a
 * ENUM
 *
 * @author marlonlom
 */
public final class DiffLabelEnumUtil extends EnumUtil {

    /**
     * RSS configuration Properties path
     */
    private static final String PROPS_PATH = "WEB-INF/datediif_labels.properties";

    /**
     * Logger for the utility class {@link co.malm.mglam_reads.backend.util.enums.DiffLabelEnumUtil}
     *
     * @see java.util.logging.Logger
     */
    private static final Logger LOGGER = Logger.getLogger(DiffLabelEnumUtil.class.getName());

    /**
     * Singleton instance for the class
     */
    private static final DiffLabelEnumUtil instance = new DiffLabelEnumUtil();

    /**
     * Private constructor, as it may contains static methods
     */
    private DiffLabelEnumUtil() {
        super();
        preparePropertiesContent(LOGGER, PROPS_PATH);
    }

    /**
     * Returns singleton instance of {@link co.malm.mglam_reads.backend.util.enums.DiffLabelEnumUtil}
     *
     * @return singleton instance
     */
    public static DiffLabelEnumUtil getInstance() {
        return instance;
    }

}
