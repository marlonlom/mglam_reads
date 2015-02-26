package co.malm.mglam_reads.backend.configs;

import java.util.ArrayList;

import co.malm.mglam_reads.backend.util.enums.ArticleCssEnumUtil;

/**
 * Constant Set for handling CSS selectors used for querying and parsing texts
 * from article html document.
 *
 * @author marlonlom
 */
public enum ArticleCssConfig {

    /**
     * Constant for CSS article body
     */
    CSS_ARTICLE_BODY(0),
    /**
     * Constant for CSS main header image
     */
    CSS_HEADER_IMG_MAIN(1),
    /**
     * Constant for CSS header image in gallery
     */
    CSS_HEADER_IMG_GALLERY(2);
    /**
     * CSS config code, integer
     */
    Integer cssCode;

    static {
        ArticleCssConfig[] enumValue = ArticleCssConfig.values();
        ArticleCssEnumUtil enumUtil = ArticleCssEnumUtil.getInstance();
        ArrayList<String> propertyKeys = enumUtil.getPropertyKeys();

        for (int i = 0; i < enumValue.length; i++) {
            String cssSelector = enumValue[i].toString().replace("_", ".");
            for (String _k : propertyKeys) {
                if (_k.toLowerCase().equalsIgnoreCase(cssSelector)) {
                    enumValue[i].setCssSelector(enumUtil.getProperty(_k));
                }
            }
        }
    }

    /**
     * CSS config selector, String
     */
    private String cssSelector;

    /**
     * Private constructor based on a numeric code for a constant
     *
     * @param aCssCode code, number
     */
    private ArticleCssConfig(Integer aCssCode) {
        this.setCssSelector("");
        this.setCssCode(aCssCode);
    }

    /**
     * Modify the code property for enum constant
     *
     * @param aCssCode theCode to set
     */
    public void setCssCode(Integer aCssCode) {
        this.cssCode = aCssCode;
    }

    /**
     * Obtain the css selector value for enum constant
     *
     * @return the tag name value
     */
    public String getCssSelector() {
        return this.cssSelector;
    }

    /**
     * Modify the css selector value for enum constant
     *
     * @paramName aCssSelector to set
     */
    public void setCssSelector(String aCssSelector) {
        this.cssSelector = aCssSelector;
    }

}
