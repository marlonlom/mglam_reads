package co.malm.mglam_reads.backend.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

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
     * @throws IOException if an error happens
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

        int responseCode = con.getResponseCode();

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;

        while ((inputLine = in.readLine()) != null) {
            builder.append(inputLine);
        }

        in.close();

    }
}
