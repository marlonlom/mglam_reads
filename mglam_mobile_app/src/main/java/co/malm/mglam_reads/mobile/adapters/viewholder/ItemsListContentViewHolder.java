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

package co.malm.mglam_reads.mobile.adapters.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import co.malm.mglam_reads.mobile.activities.R;

/**
 * View holder class for items list header view.
 *
 * @author marlonlom
 */
public class ItemsListContentViewHolder extends RecyclerView.ViewHolder {

    private TextView mTitleText;
    private TextView mDetailText;
    private TextView mPubdateText;

    public ItemsListContentViewHolder(View itemView) {
        super(itemView);
        this.setTitleText((TextView) itemView.findViewById(R.id.article_title_text));
        this.setDetailText((TextView) itemView.findViewById(R.id.article_detail_text));
        this.setPubdateText((TextView) itemView.findViewById(R.id.article_pubdate_text));
    }

    public TextView getTitleText() {
        return mTitleText;
    }

    public void setTitleText(TextView mTitleText) {
        this.mTitleText = mTitleText;
    }

    public TextView getDetailText() {
        return mDetailText;
    }

    public void setDetailText(TextView detailText) {
        this.mDetailText = detailText;
    }

    public TextView getPubdateText() {
        return mPubdateText;
    }

    public void setPubdateText(TextView pubdateText) {
        this.mPubdateText = pubdateText;
    }
}
