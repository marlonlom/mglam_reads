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

package co.malm.mglam_reads.backend.endpoints;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import javax.inject.Named;

import co.malm.mglam_reads.backend.model.ArticleContent;
import co.malm.mglam_reads.backend.model.FeedChannel;
import co.malm.mglam_reads.backend.services.RssFeedService;

/**
 * Cloud Endpoint API for retrieving RSS Feed contents with information of MG's
 * fashion website
 *
 * @author marlonlom
 */
@Api(name = "mgReadingsEndpoint", version = "v1", namespace = @ApiNamespace(ownerDomain = "endpoints.backend.mglam_reads.malm.co", ownerName = "endpoints.backend.mglam_reads.malm.co", packagePath = ""))
public class FeedEndpoint {

    /**
     * Service implementation
     *
     * @see co.malm.mglam_reads.backend.services.RssFeedService
     */
    private RssFeedService service = new RssFeedService();

    /**
     * get RSS channel with latest articles
     *
     * @return rss feed contents
     */
    @ApiMethod(name = "getChannel", path = "channel", httpMethod = ApiMethod.HttpMethod.GET)
    public FeedChannel getChannel() {
        return service.obtainRssChannel();
    }

    /**
     * Api method that reads articles contents, using url address
     *
     * @param articleLink article url
     * @return article contents
     */
    @ApiMethod(name = "getArticle", path = "articles/{link}", httpMethod = ApiMethod.HttpMethod.GET)
    public ArticleContent getArticle(@Named("link") String articleLink) {
        return service.obtainHtmlContent(articleLink);
    }

}
