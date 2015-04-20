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

package co.malm.mglam_reads.backend.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import co.malm.mglam_reads.backend.configs.ArticleCssConfig;
import co.malm.mglam_reads.backend.model.ArticleContent;

/**
 * Utility class for reading HTML contents and preparing article data
 *
 * @author marlonlom
 */
public final class ArticleHtmlParserUtil {

    /**
     * Singleton instance for the class
     */
    private static final ArticleHtmlParserUtil instance = new ArticleHtmlParserUtil();

    /**
     * Private constructor, as it may contains static methods
     */
    private ArticleHtmlParserUtil() {
        super();
    }

    public static final ArticleHtmlParserUtil getInstance() {
        return instance;
    }

    /**
     * Parses html text for retrieving article body
     *
     * @param article      article content instance
     * @param htmlContents web content, retrieved previously
     */
    public void parseArticle(ArticleContent article, String htmlContents) {
        Document htmlDocument = Jsoup.parse(htmlContents);
        if (htmlDocument != null) {
            prepareHeaderImageUrl(htmlDocument, article);
            prepareHtmlParagraphs(htmlDocument, article);
        }

    }

    /**
     * Parses header image url for article
     *
     * @param html    parsed html document
     * @param article article content instance
     */
    private void prepareHeaderImageUrl(Document html, ArticleContent article) {

        final String[] headerImages = {ArticleCssConfig.CSS_HEADER_IMG_MAIN.getCssSelector(),
                ArticleCssConfig.CSS_HEADER_IMG_GALLERY.getCssSelector()};

        /*Searches for first match for header img, using css selector*/
        for (String cssImg : headerImages) {
            Element headImg = html.select(cssImg).first();
            boolean condition = headImg != null;

            if (condition) {
                article.setHeaderImageURL(headImg.attr("src"));
                break;
            }
        }

    }

    /**
     * Parses html text for retrieving html paragraphs
     *
     * @param html    html document content instance
     * @param article web content, retrieved previously
     */
    private void prepareHtmlParagraphs(Document html, ArticleContent article) {

        /*Selects first match for section, using css selector*/
        Element articleSection = html.select(ArticleCssConfig.CSS_ARTICLE_BODY.getCssSelector()).first();

        if (articleSection != null) {
            /*Selects all paragraphs using css selector*/
            Elements paragraphs = articleSection.children();

            for (int i = 0; i < paragraphs.size(); i++) {
                Element element = paragraphs.get(i);

                if (!element.text().isEmpty()) {
                    /*adds html content into list*/
                    article.getParagraphs().add(element.outerHtml());
                }
            }
        }
    }
}
