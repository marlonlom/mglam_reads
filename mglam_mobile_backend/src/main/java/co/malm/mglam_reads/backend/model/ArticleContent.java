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

package co.malm.mglam_reads.backend.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Model class that define an html article and its contents
 *
 * @author marlonlom
 */
public class ArticleContent implements Serializable {

    /**
     * Main URl image for article
     */
    private String headerImageURL;

    /**
     * List of page contents, every paragraph
     */
    private ArrayList<String> paragraphs;

    /**
     * Default constructor
     */
    public ArticleContent() {
        this.paragraphs = new ArrayList<String>();
    }

    /**
     * Returns the list of page contents
     *
     * @return page texts list
     */
    public ArrayList<String> getParagraphs() {
        return paragraphs;
    }

    /**
     * Modifies the list of page contents
     *
     * @param paragraphsList page texts list
     */
    public void setParagraphs(ArrayList<String> paragraphsList) {
        this.paragraphs = new ArrayList<String>(paragraphsList);
    }

    /**
     * Obtains article header image url
     *
     * @return image url
     */
    public String getHeaderImageURL() {
        return headerImageURL;
    }

    /**
     * Modifies  article header image url
     *
     * @param anImageURL image url
     */
    public void setHeaderImageURL(String anImageURL) {
        this.headerImageURL = anImageURL;
    }
}
