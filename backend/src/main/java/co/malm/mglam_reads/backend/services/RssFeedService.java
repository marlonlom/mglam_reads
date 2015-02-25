package co.malm.mglam_reads.backend.services;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import co.malm.mglam_reads.backend.http.RSSConnection;
import co.malm.mglam_reads.backend.http.WebpageConnection;
import co.malm.mglam_reads.backend.model.ArticleContent;
import co.malm.mglam_reads.backend.model.FeedChannel;
import co.malm.mglam_reads.backend.sax.RssFeedXMLParser;

/**
 * Service implementation class for obtaining RSS feed
 *
 * @author marlonlom
 */
public final class RssFeedService {

    private static final String SELECTOR_ARTICLE = "section[itemprop=articleBody]";
    private static final String SELECTOR_PARAGRAPHS = "p:not([class])";

    /**
     * Returns remote feed channel
     *
     * @return rss channel
     */
    public FeedChannel obtainRssChannel() {
        RssFeedXMLParser xmlParser = new RssFeedXMLParser();

        /*retrieves rss feed xml contents*/
        String retrievedFeed = RSSConnection.retrieveFeed();

        /*if not empty, parses xml content*/
        if (!retrievedFeed.isEmpty()) {
            xmlParser.performParsing(retrievedFeed);

            return xmlParser.getFeedChannel();
        }

        return null;
    }

    /**
     * Obtains paragraphs contents for the given url address
     *
     * @param articleLink url address
     * @return html contents
     */
    public ArticleContent obtainHtmlContent(String articleLink) {
        ArticleContent articleContent = new ArticleContent();

        /*retrieves given url contents*/
        String retrievedWebsite = WebpageConnection.retrieveWebpageContent(articleLink);

        /*if not empty, parses webpage content*/
        if (!retrievedWebsite.isEmpty()) {
            prepareHtmlParagraphs(articleContent, retrievedWebsite);
        }

        return articleContent;
    }

    /**
     * Parses html text for retrieving article paragraphs
     *
     * @param article      article content instance
     * @param htmlContents webpage content, retrieve previously
     */
    private void prepareHtmlParagraphs(ArticleContent article, String htmlContents) {

        Document htmlDocument = Jsoup.parse(htmlContents);
        /*Selects first match for article section, using css selector*/
        Element articleSection = htmlDocument.select(SELECTOR_ARTICLE).first();

        if (articleSection != null) {
            /*Selects all paragraphs using css selector*/
            Elements paragraphs = articleSection.children();

            for (int i = 0; i < paragraphs.size(); i++) {
                Element element = paragraphs.get(i);

                /*adds html content (without p html tag) into list*/
                article.getParagraphs().add(element.outerHtml());
            }
        }
    }
}
