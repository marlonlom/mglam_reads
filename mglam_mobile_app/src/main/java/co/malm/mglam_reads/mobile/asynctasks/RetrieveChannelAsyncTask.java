package co.malm.mglam_reads.mobile.asynctasks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;

import co.malm.mglam_reads.backend.endpoints.mglamRssEndpoint.MglamRssEndpoint;
import co.malm.mglam_reads.backend.endpoints.mglamRssEndpoint.model.FeedChannel;
import co.malm.mglam_reads.mobile.events.RssChannelMessageEvent;
import co.malm.mglam_reads.mobile.util.EndpointConnectionUtil;
import de.greenrobot.event.EventBus;

/**
 * AsyncTack implementation class for retrieve Rss Channel.
 *
 * @author marlonlom
 */
public class RetrieveChannelAsyncTask extends AsyncTask<Void, Void, FeedChannel> {

    private static final String TAG = RetrieveChannelAsyncTask.class.getSimpleName();

    Context context;

    /**
     * Default constructor
     *
     * @param aContext
     */
    public RetrieveChannelAsyncTask(Context aContext) {
        this.setContext(aContext);
    }

    @Override
    protected FeedChannel doInBackground(Void... params) {
        FeedChannel channel = null;
        try {
            final MglamRssEndpoint endpoint = EndpointConnectionUtil.obtainCloudEndpoint();
            channel = endpoint.getChannel().execute();
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
        }
        return channel;
    }

    @Override
    protected void onPostExecute(FeedChannel feedChannel) {
        if (feedChannel != null) {
            RssChannelMessageEvent categoriesEvent = new RssChannelMessageEvent();
            categoriesEvent.setRssChannel(feedChannel);
            EventBus.getDefault().post(categoriesEvent);
        }
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
