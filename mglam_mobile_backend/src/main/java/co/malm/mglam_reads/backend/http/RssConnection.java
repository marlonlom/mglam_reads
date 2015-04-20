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

package co.malm.mglam_reads.backend.http;

import java.util.logging.Logger;

import co.malm.mglam_reads.backend.configs.RssConfig;

/**
 * HTTP Connection implementation class for retrieving feed
 * xml contents
 *
 * @author marlonlom
 */
public final class RssConnection extends CommonUrlConnection {

    /**
     * Logging utility
     *
     * @see java.util.logging.Logger
     */
    private static final Logger LOGGER = Logger.getLogger(RssConnection.class.getName());

    /**
     * Obtain the contents of an XML RSS Feed using its url
     *
     * @return xml contents of the feed
     */
    public static String retrieveFeed() {
        StringBuilder builder = new StringBuilder("");
        try {

            performHttpGET(builder, RssConfig.RSS_URL.getTagValue());

        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
        }
        return builder.toString();
    }


}
