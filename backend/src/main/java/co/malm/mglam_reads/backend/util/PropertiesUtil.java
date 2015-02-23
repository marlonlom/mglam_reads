package co.malm.mglam_reads.backend.util;

import java.io.InputStream;
import java.util.Properties;

/**
 * Created by marlonlom on 22/02/15.
 */
public final class PropertiesUtil {
    private static final PropertiesUtil instance = new PropertiesUtil();

    public static PropertiesUtil getInstance() {
        return instance;
    }

    public Properties readProps(String propertiesFile) throws Exception {
        Properties props = new Properties();
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(propertiesFile);
        props.load(inputStream);
        return props;
    }
}
