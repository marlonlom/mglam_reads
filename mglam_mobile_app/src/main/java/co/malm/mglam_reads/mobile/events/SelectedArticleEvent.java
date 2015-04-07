package co.malm.mglam_reads.mobile.events;

import co.malm.mglam_reads.backend.endpoints.mglamRssEndpoint.model.FeedItem;

/**
 * Event class for handling selected article from list
 *
 * @author marlonlom
 */
public class SelectedArticleEvent {

    private FeedItem articleItem;

    public SelectedArticleEvent() {
        super();
    }

    public SelectedArticleEvent(FeedItem articleItem) {
        this.articleItem = articleItem;
    }

    public FeedItem getArticleItem() {
        return articleItem;
    }

    public void setArticleItem(FeedItem articleItem) {
        this.articleItem = articleItem;
    }
}
