package co.malm.mglam_reads.backend.endpoints;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import javax.inject.Named;

import co.malm.mglam_reads.backend.model.ArticleContent;
import co.malm.mglam_reads.backend.model.FeedChannel;
import co.malm.mglam_reads.backend.services.RssFeedService;

/**
 * Cloud Endpoint API for Rss Feed contents with information of #MundoGlam
 * fashion website
 *
 * @author marlonlom
 */
@Api(name = "mglamArticlesApi", version = "v1", namespace = @ApiNamespace(ownerDomain = "endpoints.backend.mglam_reads.malm.co", ownerName = "endpoints.backend.mglam_reads.malm.co", packagePath = ""))
public class FeedItemsEndpoint {

    /**
     * Service implementation
     *
     * @see co.malm.mglam_reads.backend.services.RssFeedService
     */
    private RssFeedService service = new RssFeedService();

    @ApiMethod(name = "getFeed")
    public FeedChannel getChannel() {
        return service.obtainRssChannel();
    }

    @ApiMethod(name = "getArticleContents")
    public ArticleContent getFullDescription(@Named("link") String articleLink) {
        return service.obtainHtmlContent(articleLink);
    }

}
