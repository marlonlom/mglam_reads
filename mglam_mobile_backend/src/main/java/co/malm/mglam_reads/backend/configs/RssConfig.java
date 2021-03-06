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

import co.malm.mglam_reads.backend.util.enums.RssEnumUtil;

/**
 * Constant Set for handling RSS Feed used tags
 *
 * @author marlonlom
 */
public enum RssConfig {

    /**
     * Constant for RSS feed url
     */
    RSS_URL(0),
    /**
     * Constant for RSS child item
     */
    RSS_ITEM(1),
    /**
     * Constant for RSS item child title
     */
    RSS_ITEM_TITLE(2),
    /**
     * Constant for RSS item child link
     */
    RSS_ITEM_LINK(3),
    /**
     * Constant for RSS item child publish date
     */
    RSS_ITEM_PUBDATE(4),
    /**
     * Constant for RSS item child category
     */
    RSS_ITEM_CATEGORY(5),
    /**
     * Constant for RSS item child description
     */
    RSS_ITEM_DESCRIPTION(6);

    static {
        RssConfig[] enumValue = RssConfig.values();
        RssEnumUtil enumUtil = RssEnumUtil.getInstance();
        ArrayList<String> propertyKeys = enumUtil.getPropertyKeys();

        for (int i = 0; i < enumValue.length; i++) {
            final String[] split = enumValue[i].toString().split("_");
            final String suffix = split[split.length - 1];
            for (String _k : propertyKeys) {
                if (_k.toLowerCase().endsWith(suffix.toLowerCase())) {
                    enumValue[i].setTagValue(enumUtil.getProperty(_k));
                }
            }
        }
    }

    /**
     * Config name, String
     */
    private String tagValue;
    /**
     * Config code, integer
     */
    private Integer tagCode;

    /**
     * Private constructor based on a numeric code for a constant
     *
     * @param aTagCode code, number
     */
    private RssConfig(Integer aTagCode) {
        this.setTagValue("");
        this.setTagCode(aTagCode);
    }

    /**
     * Obtain the code property for enum constant
     *
     * @return the tag code reference
     */
    public Integer getTagCode() {
        return this.tagCode;
    }

    /**
     * Modify the name property for enum constant
     *
     * @paramCode theCode to set
     */
    public void setTagCode(Integer aTagCode) {
        this.tagCode = aTagCode;
    }

    /**
     * Obtain the name property value for enum constant
     *
     * @return the tag name value
     */
    public String getTagValue() {
        return this.tagValue;
    }

    /**
     * Modify the name property for enum constant
     *
     * @paramName aTagName to set
     */
    public void setTagValue(String aTagName) {
        this.tagValue = aTagName;
    }

}
