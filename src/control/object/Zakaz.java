package control.object;

/**
 * Created by mikhail on 30.03.15.
 */
public class Zakaz {
    private String address;
    private String date;
    private String status;
    private int idPlace;

    public String getAddress() {
        return address;
    }

    public Zakaz(String address, String date,int idPlace) {
        this.address = address;
        this.date = date;
        this.idPlace = idPlace;

    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;

    }

    public String getStatus() {
        return status;
    }
}
