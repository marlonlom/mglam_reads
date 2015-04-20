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

package co.malm.mglam_reads.mobile.asynctasks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;

import co.malm.mglam_reads.backend.endpoints.mgReadingsEndpoint.MgReadingsEndpoint;
import co.malm.mglam_reads.backend.endpoints.mgReadingsEndpoint.model.FeedChannel;
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
            final MgReadingsEndpoint endpoint = EndpointConnectionUtil.obtainCloudEndpoint();
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
