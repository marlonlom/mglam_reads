package co.malm.mglam_reads.mobile.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import co.malm.mglam_reads.backend.endpoints.mglamRssEndpoint.model.FeedItem;
import co.malm.mglam_reads.mobile.activities.R;
import co.malm.mglam_reads.mobile.adapters.ArticleListAdapter;
import co.malm.mglam_reads.mobile.events.SelectedArticleEvent;
import de.greenrobot.event.EventBus;

/**
 * A simple {@link Fragment} subclass.
 *
 * @author marlonlom
 */
public class ArticlesListFragment extends Fragment {

    private RecyclerView mArticlesList;

    public ArticlesListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        View rootView = inflater.inflate(R.layout.fragment_articles_list, container, false);
        mArticlesList = (RecyclerView) rootView.findViewById(R.id.articles_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mArticlesList.setLayoutManager(layoutManager);
        mArticlesList.setHasFixedSize(true);
        return rootView;
    }

    public void setupArticlesList(List<FeedItem> articles) {
        ArticleListAdapter mListAdapter = new ArticleListAdapter(getActivity(), articles);
        mArticlesList.setAdapter(mListAdapter);
        mArticlesList.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    public void onEvent(SelectedArticleEvent event) {
        Toast.makeText(getActivity(), event.getArticleItem().getTitle(), Toast.LENGTH_SHORT).show();
    }

}
