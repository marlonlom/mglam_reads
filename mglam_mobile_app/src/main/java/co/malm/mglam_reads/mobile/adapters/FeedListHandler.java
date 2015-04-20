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

package co.malm.mglam_reads.mobile.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import co.malm.mglam_reads.backend.endpoints.mglamRssEndpoint.model.FeedItem;
import co.malm.mglam_reads.mobile.activities.R;
import co.malm.mglam_reads.mobile.adapters.viewholder.ItemsListContentViewHolder;
import co.malm.mglam_reads.mobile.adapters.viewholder.ItemsListHeaderViewHolder;

/**
 * RecyclerView Adapter that allows to add a header view, using feed items list as data.
 *
 * @author marlonlom
 */
public class FeedListHandler extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HEADER = 2;
    private static final int TYPE_ITEM = 1;
    private List<FeedItem> mItemList;

    public FeedListHandler(List<FeedItem> feedItems) {
        this.mItemList = feedItems;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        if (viewType == TYPE_ITEM) {
            final View view = LayoutInflater.from(context).inflate(R.layout.articles_list_item, parent, false);
            return new ItemsListContentViewHolder(view);
        } else if (viewType == TYPE_HEADER) {
            final View view = LayoutInflater.from(context).inflate(R.layout.articles_list_header, parent, false);
            return new ItemsListHeaderViewHolder(view);
        }
        throw new RuntimeException("There is no type that matches the type " + viewType + " + make sure your using types correctly");
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        if (!isPositionHeader(position)) {
            ItemsListContentViewHolder holder = (ItemsListContentViewHolder) viewHolder;
            FeedItem feedItem = mItemList.get(position - 1);
            holder.getTitleText().setText(Html.fromHtml(feedItem.getTitle()));
            holder.getDetailText().setText(Html.fromHtml(feedItem.getDescription()));
            holder.getPubdateText().setText(Html.fromHtml(feedItem.getPubDate()));
        }
    }

    public int getBasicItemCount() {
        return mItemList == null ? 0 : mItemList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position)) {
            return TYPE_HEADER;
        }

        return TYPE_ITEM;
    }

    @Override
    public int getItemCount() {
        return getBasicItemCount() + 1;
    }

    private boolean isPositionHeader(int position) {
        return position == 0;
    }
}
