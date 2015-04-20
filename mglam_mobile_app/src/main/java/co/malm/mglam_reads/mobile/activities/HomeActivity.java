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

package co.malm.mglam_reads.mobile.activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import co.malm.mglam_reads.mobile.asynctasks.RetrieveChannelAsyncTask;
import co.malm.mglam_reads.mobile.events.RssChannelMessageEvent;
import co.malm.mglam_reads.mobile.fragments.FeedListFragment;
import co.malm.mglam_reads.mobile.models.FeedDataModel;
import de.greenrobot.event.EventBus;

/**
 * Main Activity class for mobile application.
 *
 * @author marlonlom
 */
public class HomeActivity extends BaseActivity {

    private static final String TAG = HomeActivity.class.getSimpleName();
    private FeedListFragment mFeedListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupAppBar(R.id.toolbar);
        setActionBarIcon(R.drawable.ic_navigation_drawer);
        /*Obtain feed list fragment*/
        mFeedListFragment = (FeedListFragment) getFragmentById(R.id.fragment_feed_list);
        prepareFeedContents();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main;
    }

    protected void prepareFeedContents() {
        EventBus.getDefault().register(this);
        if (FeedDataModel.getInstance().noData()) {
            new RetrieveChannelAsyncTask(this).execute();
        } else {
            prepareFeedList();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    public void onEvent(RssChannelMessageEvent event) {
        if (FeedDataModel.getInstance().noData()) {
            FeedDataModel.getInstance().setCategories(event.getRssChannel().getCategories());
            FeedDataModel.getInstance().setFeedItems(event.getRssChannel().getItems());
        }
        prepareFeedList();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_about) {
            Toast.makeText(this, "Acerca de MG", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    private void prepareFeedList() {
        mFeedListFragment.initRecyclerView();
    }

}
