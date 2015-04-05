package co.malm.mglam_reads.mobile.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import co.malm.mglam_reads.backend.endpoints.mglamRssEndpoint.model.FeedItem;
import co.malm.mglam_reads.mobile.activities.R;
import co.malm.mglam_reads.mobile.application.MglamApplication;

/**
 * Recycler view adapter class used for articles listing
 *
 * @author marlonlom
 */
public class ArticleListAdapter extends RecyclerView.Adapter<ArticleListAdapter.ViewHolder> {

    private LayoutInflater mInflater;

    /**
     * Data for articles list
     */
    private List<FeedItem> mArticles;

    public ArticleListAdapter(Context context, List<FeedItem> list) {
        mInflater = LayoutInflater.from(context);
        this.setArticlesList(list);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = mInflater.inflate(R.layout.articles_list_item, parent, Boolean.FALSE);
        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        FeedItem currentData = mArticles.get(position);
        holder.titleView.setText(currentData.getTitle());
        holder.detailView.setText(Html.fromHtml(currentData.getDescription()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                highlightItemAt(position);
            }
        });
    }

    private void highlightItemAt(int position) {
        FeedItem item = mArticles.get(position);
        Log.d(MglamApplication.TAG, "selected article: " + item.getTitle());
    }

    @Override
    public int getItemCount() {
        boolean nonEmptyList = mArticles != null && !mArticles.isEmpty();
        return nonEmptyList ? mArticles.size() : 0;
    }

    public List<FeedItem> getArticlesList() {
        return mArticles;
    }

    public void setArticlesList(List<FeedItem> mArticles) {
        this.mArticles = mArticles;
    }

    /**
     * View Holder implementation class for holding category list items
     *
     * @author marlonlom
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView titleView;
        public TextView detailView;

        public ViewHolder(View itemView) {
            super(itemView);
            titleView = (TextView) itemView.findViewById(R.id.article_title_text);
            detailView = (TextView) itemView.findViewById(R.id.article_detail_text);
        }
    }
}
