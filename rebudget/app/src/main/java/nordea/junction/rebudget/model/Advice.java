package nordea.junction.rebudget.model;

/**
 * Model of the POJO object of an Advice.
 *
 * Created by Bel on 24/11/2017.
 */

public class Advice {

    private int id;
    private String title;

    public Advice() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Advice(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
