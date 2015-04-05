package co.malm.mglam_reads.mobile.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import co.malm.mglam_reads.mobile.activities.R;
import co.malm.mglam_reads.mobile.events.SelectedCategoryEvent;
import co.malm.mglam_reads.mobile.listeners.NavigationDrawerCallbacks;
import de.greenrobot.event.EventBus;

/**
 * Recycler view adapter class used for navigation drawer categories listing
 *
 * @author marlonlom
 */
public class NavigationDrawerAdapter extends RecyclerView.Adapter<NavigationDrawerAdapter.ViewHolder> {

    private LayoutInflater mInflater;

    private NavigationDrawerCallbacks mNavigationDrawerCallbacks;

    /**
     * Data for categories list
     */
    private List<String> mCategories = Collections.emptyList();

    public NavigationDrawerAdapter(Context context, List<String> list) {
        mInflater = LayoutInflater.from(context);
        this.setCategoriesList(list);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = mInflater.inflate(R.layout.drawer_category_item, parent, Boolean.FALSE);
        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        String currentData = mCategories.get(position);
        holder.categoryTextView.setText(currentData);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                highlightItemAt(position);
            }
        });
        if (position == 0) {
            holder.itemView.setBackgroundResource(R.color.colorAccent);
        }
    }

    private void highlightItemAt(int position) {
        String category_title = mCategories.get(position);
        SelectedCategoryEvent event = new SelectedCategoryEvent(position, category_title);
        EventBus.getDefault().post(event);
    }

    @Override
    public int getItemCount() {
        boolean nonEmptyList = mCategories != null && !mCategories.isEmpty();
        return nonEmptyList ? mCategories.size() : 0;
    }

    public void setCategoriesList(List<String> categories) {
        this.mCategories = categories;
    }

    /**
     * View Holder implementation class for holding category list items
     *
     * @author marlonlom
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView categoryTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            categoryTextView = (TextView) itemView.findViewById(R.id.categoryTitle);
        }
    }
}
