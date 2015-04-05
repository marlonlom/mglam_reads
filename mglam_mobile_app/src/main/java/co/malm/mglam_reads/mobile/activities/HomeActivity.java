package co.malm.mglam_reads.mobile.activities;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import co.malm.mglam_reads.mobile.application.MglamApplication;
import co.malm.mglam_reads.mobile.events.RssChannelMessageEvent;
import co.malm.mglam_reads.mobile.fragments.ArticlesListFragment;
import co.malm.mglam_reads.mobile.fragments.NavigationDrawerFragment;
import de.greenrobot.event.EventBus;

/**
 * Main Activity class for mobile application.
 *
 * @author marlonlom
 */
public class HomeActivity extends BaseActivity {

    private NavigationDrawerFragment mNavigationDrawerFragment;
    private ArticlesListFragment mArticlesListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupAppBar(R.id.toolbar_actionbar);
        setActionBarIcon(R.drawable.ic_navigation_drawer);
        setupNavigationDrawer();
        setupArticlesListFragment();
        prepareContents();
    }

    private void setupNavigationDrawer() {
        mNavigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_drawer);
        mNavigationDrawerFragment.setup(R.id.fragment_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
    }

    private void setupArticlesListFragment() {
        mArticlesListFragment = (ArticlesListFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_articles_wrapper);
    }

    @Override
    public void onBackPressed() {
        if (mNavigationDrawerFragment.isDrawerOpen()) {
            mNavigationDrawerFragment.closeDrawer();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main;
    }

    protected void prepareContents() {
        EventBus.getDefault().register(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            getMenuInflater().inflate(R.menu.menu_main, menu);
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    public void onEvent(RssChannelMessageEvent event) {
        MglamApplication.getInstance().setRssData(event.getRssChannel());
        mNavigationDrawerFragment.setupDrawerList(event.getRssChannel().getCategories());
        mArticlesListFragment.setupArticlesList(event.getRssChannel().getItems());
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

}
