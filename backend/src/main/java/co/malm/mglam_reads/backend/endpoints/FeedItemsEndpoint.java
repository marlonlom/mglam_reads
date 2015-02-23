package co.malm.mglam_reads.backend.endpoints;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import java.util.logging.Logger;

import co.malm.mglam_reads.backend.http.RSSConnection;
import co.malm.mglam_reads.backend.model.FeedChannel;

/**
 * Created by marlonlom on 22/02/15.
 */
@Api(
        name = "mglamArticlesApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "endpoints.backend.mglam_reads.malm.co",
                ownerName = "endpoints.backend.mglam_reads.malm.co",
                packagePath = ""
        )
)
public class FeedItemsEndpoint {

    private static final Logger logger = Logger.getLogger(FeedItemsEndpoint.class.getName());

    @ApiMethod(name = "getArticles")
    public FeedChannel getChannel() {
        FeedChannel channel = new FeedChannel();
        String retrievedFeed = RSSConnection.getInstance().retrieveFeed();
        channel.setName(retrievedFeed);
        return channel;
    }

}
