/*
 * Copyright 2015 marlonlom
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
     * CSS config code, integer
     */
    Integer cssCode;
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
