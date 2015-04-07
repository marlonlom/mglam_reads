package co.malm.mglam_reads.backend.configs;

import java.util.ArrayList;

import co.malm.mglam_reads.backend.util.enums.DiffLabelEnumUtil;

/**
 * Constant Set for handling date diff labels used for representing creation time
 * for rss articles.
 *
 * @author marlonlom
 */
public enum DateDiffLabelConfig {

    /**
     * Constant for diff label, applied to years
     */
    DIFF_LABEL_YEARS(0),

    /**
     * Constant for diff label, applied to months
     */
    DIFF_LABEL_MONTHS(1),

    /**
     * Constant for diff label, applied to weeks
     */
    DIFF_LABEL_WEEKS(2),

    /**
     * Constant for diff label, applied to days
     */
    DIFF_LABEL_DAYS(3),

    /**
     * Constant for diff label, applied to hours
     */
    DIFF_LABEL_HOURS(4),

    /**
     * Constant for diff label, applied to minutes
     */
    DIFF_LABEL_MINUTES(5),

    /**
     * Constant for diff label, applied to seconds
     */
    DIFF_LABEL_SECONDS(6);

    /**
     * Diff label name, String
     */
    private String labelPattern;

    /**
     * Diff label code, integer
     */
    private Integer labelCode;

    static {
        DateDiffLabelConfig[] enumValue = DateDiffLabelConfig.values();
        DiffLabelEnumUtil enumUtil = DiffLabelEnumUtil.getInstance();
        ArrayList<String> propertyKeys = enumUtil.getPropertyKeys();

        for (int i = 0; i < enumValue.length; i++) {
            final String[] split = enumValue[i].toString().split("_");
            final String suffix = split[split.length - 1];
            for (String _k : propertyKeys) {
                if (_k.toLowerCase().endsWith(suffix.toLowerCase())) {
                    enumValue[i].setLabelPattern(enumUtil.getProperty(_k));
                }
            }
        }
    }

    /**
     * Private constructor based on a numeric code for a constant
     *
     * @param aLabelCode code, number
     */
    private DateDiffLabelConfig(Integer aLabelCode) {
        this.setLabelPattern("");
        this.setLabelCode(aLabelCode);
    }

    /**
     * Obtain the code property value for enum constant
     *
     * @return diff label code
     */
    public Integer getLabelCode() {
        return labelCode;
    }

    /**
     * Modify the code property value for enum constant
     *
     * @param labelCode diff label code to set
     */
    public void setLabelCode(Integer labelCode) {
        this.labelCode = labelCode;
    }

    /**
     * Obtain the text pattern value for enum constant
     *
     * @return diff label pattern, String
     */
    public String getLabelPattern() {
        return labelPattern;
    }

    /**
     * Modify the text pattern property for enum constant
     *
     * @param labelPattern text pattern to set
     */
    public void setLabelPattern(String labelPattern) {
        this.labelPattern = labelPattern;
    }
}
