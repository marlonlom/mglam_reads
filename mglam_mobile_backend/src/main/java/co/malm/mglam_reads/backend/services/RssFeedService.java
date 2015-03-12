package co.malm.mglam_reads.backend.services;

import co.malm.mglam_reads.backend.http.RssConnection;
import co.malm.mglam_reads.backend.http.WebPageConnection;
import co.malm.mglam_reads.backend.model.ArticleContent;
import co.malm.mglam_reads.backend.model.FeedChannel;
import co.malm.mglam_reads.backend.sax.RssFeedXmlParser;
import co.malm.mglam_reads.backend.util.ArticleHtmlParserUtil;

/**
 * Service implementation class for obtaining RSS feed
 *
 * @author marlonlom
 */
public final class RssFeedService {

    /**
     * Returns remote feed channel
     *
     * @return rss channel
     */
    public FeedChannel obtainRssChannel() {
        RssFeedXmlParser xmlParser = new RssFeedXmlParser();

        /*retrieves rss feed xml contents*/
        String retrievedFeed = RssConnection.retrieveFeed();

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
        ArticleHtmlParserUtil articleParser = ArticleHtmlParserUtil.getInstance();

        /*retrieves given url contents*/
        String retrievedWebsite = WebPageConnection.retrieveWebPageContent(articleLink);

        /*if not empty, parses web content*/
        if (!retrievedWebsite.isEmpty()) {
            articleParser.parseArticle(articleContent, retrievedWebsite);
        }

        return articleContent;
    }


}
