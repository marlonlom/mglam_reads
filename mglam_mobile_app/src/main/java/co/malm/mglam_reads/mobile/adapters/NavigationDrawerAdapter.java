package co.malm.mglam_reads.mobile.adapters;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import co.malm.mglam_reads.mobile.activities.R;
import co.malm.mglam_reads.mobile.fragments.NavigationDrawerFragment;
import co.malm.mglam_reads.mobile.listeners.NavigationDrawerCallbacks;

/**
 * Recycler view adapter class used for navigation drawer categories listing
 *
 * @author marlonlom
 */
public class NavigationDrawerAdapter extends RecyclerView.Adapter<NavigationDrawerAdapter.ViewHolder> {

    private NavigationDrawerCallbacks mNavigationDrawerCallbacks;

    private int mSelectedPosition;
    private int mTouchedPosition = -1;

    /**
     * Data for categories list
     */
    private List<String> mCategories;
    private NavigationDrawerFragment navigationDrawerCallbacks;

    public NavigationDrawerAdapter(List<String> categories) {
        this.mCategories = categories;
    }

    public NavigationDrawerCallbacks getNavigationDrawerCallbacks() {
        return mNavigationDrawerCallbacks;
    }

    public void setNavigationDrawerCallbacks(NavigationDrawerFragment navigationDrawerCallbacks) {
        this.navigationDrawerCallbacks = navigationDrawerCallbacks;
    }

    private void touchPosition(int position) {
        int lastPosition = mTouchedPosition;
        mTouchedPosition = position;
        if (lastPosition >= 0)
            notifyItemChanged(lastPosition);
        if (position >= 0)
            notifyItemChanged(position);
    }

    public void selectPosition(int position) {
        int lastPosition = mSelectedPosition;
        mSelectedPosition = position;
        notifyItemChanged(lastPosition);
        notifyItemChanged(position);
    }

    /**
     * Prepares click and touch events for view holder
     *
     * @param vh the viewholder
     */
    private void prepareEventsForViewHolder(final ViewHolder vh) {
        vh.itemView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        touchPosition(vh.getPosition());
                        return false;
                    case MotionEvent.ACTION_CANCEL:
                        touchPosition(-1);
                        return false;
                    case MotionEvent.ACTION_MOVE:
                        return false;
                    case MotionEvent.ACTION_UP:
                        touchPosition(-1);
                        return false;
                }
                return true;
            }
        });

        vh.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mNavigationDrawerCallbacks != null) {
                    mNavigationDrawerCallbacks.onNavigationDrawerItemSelected(vh.getPosition());
                }
            }
        });

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.drawer_category_item, parent, false);
        final ViewHolder viewHolder = new ViewHolder(rootView);

        prepareEventsForViewHolder(viewHolder);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        final String categoryText = mCategories.get(position);
        viewHolder.textView.setText(categoryText);

        //TODO: selected menu position, change layout accordingly
        if (mSelectedPosition == position || mTouchedPosition == position) {
            viewHolder.itemView.setBackgroundColor(viewHolder.itemView.getContext().getResources().getColor(R.color.colorPrimaryDark));
        } else {
            viewHolder.itemView.setBackgroundColor(Color.TRANSPARENT);
        }
    }

    @Override
    public int getItemCount() {
        boolean nonEmptyList = mCategories != null && !mCategories.isEmpty();
        return nonEmptyList ? mCategories.size() : 0;
    }

    /**
     * View Holder implementation class for holding category list items
     *
     * @author marlonlom
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.categoryTitle);
        }
    }
}
