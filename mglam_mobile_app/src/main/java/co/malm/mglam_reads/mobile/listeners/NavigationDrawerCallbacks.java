package co.malm.mglam_reads.mobile.listeners;


import android.view.View;

/**
 * Interface declaration for navigation fragment callbacks
 *
 * @author marlonlom
 */
public interface NavigationDrawerCallbacks {
    /**
     * Called when selected an item from navigation drawer category list
     *
     * @param position
     */
    void onNavigationDrawerItemSelected(View v, int position);
}
