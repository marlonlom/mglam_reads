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

package co.malm.mglam_reads.mobile.util;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

import co.malm.mglam_reads.backend.endpoints.mgReadingsEndpoint.MgReadingsEndpoint;

/**
 * Cloud Endpoints utility class for instantiating cloud endpoints api
 *
 * @author marlonlom
 */
public final class EndpointConnectionUtil {
    /**
     * Cloud endpoint implementation instance
     */
    private static MgReadingsEndpoint endpoint;

    /**
     * Returns cloud endpoints api once
     *
     * @return singleton endpoint instance
     */
    public static MgReadingsEndpoint obtainCloudEndpoint() {
        if (endpoint == null) {
            final MgReadingsEndpoint.Builder builder = new MgReadingsEndpoint.Builder(
                    AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null);
            endpoint = builder.build();
        }
        return endpoint;
    }
}
