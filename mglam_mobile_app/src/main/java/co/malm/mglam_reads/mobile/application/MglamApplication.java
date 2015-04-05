package co.malm.mglam_reads.mobile.application;

import android.app.Application;
import android.util.Log;

import co.malm.mglam_reads.backend.endpoints.mglamRssEndpoint.model.FeedChannel;
import co.malm.mglam_reads.mobile.asynctasks.RetrieveChannelAsyncTask;

/**
 * Application class
 *
 * @author marlonlom
 */
public class MglamApplication extends Application {
    public static final String TAG = MglamApplication.class.getSimpleName();
    private static MglamApplication INSTANCE = null;
    private FeedChannel rssData;

    public static MglamApplication getInstance() {
        if (INSTANCE == null)
            throw new IllegalStateException("Application not created yet!");
        return INSTANCE;
    }

    @Override
    public void onCreate() {
        INSTANCE = this;
        Log.d(TAG, "loading rss data");
        new RetrieveChannelAsyncTask(this).execute();
    }

    public FeedChannel getRssData() {
        return rssData;
    }

    public void setRssData(FeedChannel rssData) {
        this.rssData = rssData;
    }
}
