package co.malm.mglam_reads.backend.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Model class that define an html article and its contents
 *
 * @author marlonlom
 */
public class ArticleContent implements Serializable {

    /**
     * List of page contents, every paragraph
     */
    private ArrayList<String> paragraphs;

    /**
     * Default constructor
     */
    public ArticleContent() {
        this.paragraphs = new ArrayList<String>();
    }

    /**
     * Returns the list of page contents
     *
     * @return page texts list
     */
    public ArrayList<String> getParagraphs() {
        return paragraphs;
    }

    /**
     * Modifies the list of page contents
     *
     * @param paragraphsList page texts list
     */
    public void setParagraphs(ArrayList<String> paragraphsList) {
        this.paragraphs = new ArrayList<String>(paragraphsList);
    }
}
