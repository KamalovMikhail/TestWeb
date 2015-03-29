package control.object;

/**
 * Created by mikhail on 21.03.15.
 */
public class Place {
    private String name;

    private int id;

    public Place(String name, Integer id) {
        this.name = name;
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {

        return id;
    }

    public String getName() {
        return name;
    }



}
