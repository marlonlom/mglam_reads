package co.malm.mglam_reads.mobile.events;

/**
 * Event class for handling selected category from navigation drawer
 *
 * @author marlonlom
 */
public class SelectedCategoryEvent {

    private Integer categoryPosition;
    private String categoryTitle;

    public SelectedCategoryEvent(Integer position, String title) {
        this.categoryPosition = position;
        this.categoryTitle = title;
    }

    public Integer getPosition() {
        return categoryPosition;
    }

    public void setPosition(Integer position) {
        this.categoryPosition = position;
    }

    public String getTitle() {
        return categoryTitle;
    }

    public void setTitle(String title) {
        this.categoryTitle = title;
    }
}
