package co.malm.mglam_reads.mobile.fragments;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import co.malm.mglam_reads.mobile.activities.R;
import co.malm.mglam_reads.mobile.adapters.NavigationDrawerAdapter;
import co.malm.mglam_reads.mobile.events.SelectedCategoryEvent;
import co.malm.mglam_reads.mobile.util.SharedPreferenceDataUtil;
import de.greenrobot.event.EventBus;


/**
 * Fragment implementation class for navigation drawer
 *
 * @author marlonlom
 */
public class NavigationDrawerFragment extends Fragment {

    /**
     * Per the design guidelines, you should show the drawer on launch until the user manually
     * expands it. This shared preference tracks this.
     */
    private static final String PREF_USER_LEARNED_DRAWER = "navigation_drawer_learned";

    /**
     * Helper component that ties the action bar to the navigation drawer.
     */
    private ActionBarDrawerToggle mActionBarDrawerToggle;

    private DrawerLayout mDrawerLayout;
    private NavigationDrawerAdapter mDrawerAdapter;
    private RecyclerView mDrawerList;
    private View mFragmentContainerView;
    private boolean mFromSavedInstanceState;
    private boolean mUserLearnedDrawer;

    /**
     * Default constructor
     */
    public NavigationDrawerFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String learnedDrawer = SharedPreferenceDataUtil.readFromPreferences(getActivity(), PREF_USER_LEARNED_DRAWER, "false");
        mUserLearnedDrawer = Boolean.valueOf(learnedDrawer);
        if (savedInstanceState != null) {
            mFromSavedInstanceState = true;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);

        mDrawerList = (RecyclerView) rootView.findViewById(R.id.drawerList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mDrawerList.setLayoutManager(layoutManager);
        mDrawerList.setHasFixedSize(true);

        return rootView;
    }

    public void setup(int navigation_drawer, DrawerLayout drawerLayout, final Toolbar mToolbar) {
        mFragmentContainerView = (View) getActivity().findViewById(navigation_drawer).getParent();

        mDrawerLayout = drawerLayout;
        mActionBarDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, mToolbar, R.string.app_name, R.string.app_name) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if (!mUserLearnedDrawer) {
                    mUserLearnedDrawer = true;
                    SharedPreferenceDataUtil.saveToPreferences(getActivity(), PREF_USER_LEARNED_DRAWER, mUserLearnedDrawer + "");
                }
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                mToolbar.setAlpha(1 - slideOffset / 2);
            }
        };

        if (!mUserLearnedDrawer && !mFromSavedInstanceState) {
            mDrawerLayout.openDrawer(mFragmentContainerView);
        }

        mDrawerLayout.setDrawerListener(mActionBarDrawerToggle);
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mActionBarDrawerToggle.syncState();
            }
        });
    }

    public void setupDrawerList(List<String> categories) {
        categories.add(0, getString(R.string.default_all_categories));
        mDrawerAdapter = new NavigationDrawerAdapter(getActivity(), categories);
        mDrawerList.setAdapter(mDrawerAdapter);
        highlightElement(0);
    }


    public void closeDrawer() {
        mDrawerLayout.closeDrawer(mFragmentContainerView);
    }

    public boolean isDrawerOpen() {
        return mDrawerLayout != null && mDrawerLayout.isDrawerOpen(mFragmentContainerView);
    }

    public void onEvent(SelectedCategoryEvent event) {
        highlightElement(event.getPosition());
    }

    private void highlightElement(Integer position) {
        final int viewCount = mDrawerList.getChildCount();
        if (viewCount > 0) {
            for (int curr = 0; curr < viewCount; curr++) {
                View childView = mDrawerList.getChildAt(curr);
                childView.setBackgroundResource(R.color.transparent);
            }
            View childView = mDrawerList.getChildAt(position);
            childView.setBackgroundResource(R.color.colorAccent);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Forward the new configuration the drawer toggle component.
        mActionBarDrawerToggle.onConfigurationChanged(newConfig);
    }
}
