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

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * Abstract class for defining common HTTP operations (GET,POST) for retrieve
 * contents using defined URL
 *
 * @author marlonlom
 */
public abstract class CommonUrlConnection {

    private static final String USER_AGENT = "Mozilla/5.0";
    private static final Integer TIMEOUT = 10000;

    /**
     * Performs HTTP GET operation for retrieve contents using url
     *
     * @param builder string content builder
     * @param url     for retrieve its contents
     * @throws java.io.IOException if an error happens
     */
    protected static void performHttpGET(StringBuilder builder, String url) throws IOException {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("User-Agent", USER_AGENT);
        //sets the connect deadline to 10 sec
        con.setConnectTimeout(TIMEOUT);
        //sets the read deadline to 10 Sec
        con.setReadTimeout(TIMEOUT);

        // writes content to string builder
        String streamContent = IOUtils.toString(con.getInputStream(), Charset.forName("UTF-8"));
        builder.append(streamContent);

    }
}
