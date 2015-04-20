package co.malm.mglam_reads.mobile.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import co.malm.mglam_reads.backend.endpoints.mglamRssEndpoint.model.FeedItem;
import co.malm.mglam_reads.mobile.activities.R;
import co.malm.mglam_reads.mobile.adapters.FeedListHandler;
import co.malm.mglam_reads.mobile.models.FeedDataModel;

/**
 * Fragment subclass for handling feed items listings.
 *
 * @author marlonlom
 */
public class FeedListFragment extends Fragment {

    private RecyclerView mItemsList;

    /**
     * Required empty public constructor
     */
    public FeedListFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_feed_list, container, false);
        /*Obtaining reference to recyclerview*/
        mItemsList = (RecyclerView) rootView.findViewById(R.id.articles_list);
        mItemsList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mItemsList.setHasFixedSize(false);
        return rootView;
    }

    public void initRecyclerView() {
        List<FeedItem> feedItems = FeedDataModel.getInstance().getFeedItems();
        FeedListHandler adapter = new FeedListHandler(feedItems);
        mItemsList.setAdapter(adapter);
    }


}
