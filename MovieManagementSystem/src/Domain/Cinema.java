package Domain;

import java.io.Serializable;
import java.util.ArrayList;

public class Cinema implements Serializable {
    public String getCinemaName() {
        return CinemaName;
    }

    public void setCinemaName(String cinemaName) {
        CinemaName = cinemaName;
    }

    private String CinemaName;
    private ArrayList<Hall> halls;

    public ArrayList<Hall> getHalls() {
        return halls;
    }

    public void setHalls(ArrayList<Hall> halls) {
        this.halls = halls;
    }
}
