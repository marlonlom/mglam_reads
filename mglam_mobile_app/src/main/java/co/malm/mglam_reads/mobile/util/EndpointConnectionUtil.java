package co.malm.mglam_reads.mobile.util;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

import co.malm.mglam_reads.backend.endpoints.mglamRssEndpoint.MglamRssEndpoint;

/**
 * Cloud Endpoints utility class for instantiating cloud endpoints api
 *
 * @author marlonlom
 */
public final class EndpointConnectionUtil {
    /**
     * Cloud endpoint implementation instance
     */
    private static MglamRssEndpoint endpoint;

    /**
     * Returns cloud endpoints api once
     *
     * @return singleton endpoint instance
     */
    public static MglamRssEndpoint obtainCloudEndpoint() {
        if (endpoint == null) {
            final MglamRssEndpoint.Builder builder = new MglamRssEndpoint.Builder(
                    AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null);
            endpoint = builder.build();
        }
        return endpoint;
    }
}
