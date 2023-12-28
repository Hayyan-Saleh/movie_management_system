package Domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Hall implements Serializable {
    private String name;
    private int totalChairs;
    private ArrayList<Movie> movies;
    private Map<Hour, Boolean> hourBooleanMap;
    private Map<Day, Map<Hour, Boolean>> isTimeTaken;

    public Hall(Hall hall){
        this.name=hall.getName();
        this.totalChairs=hall.getTotalChairs();
        this.movies=hall.getMovies();
        this.hourBooleanMap=hall.hourBooleanMap;
        this.isTimeTaken=hall.isTimeTaken;
    }
    public Hall() {
        hourBooleanMap = new LinkedHashMap<>();
        isTimeTaken = new HashMap<>();
        for (Day day : Day.values()) {
            for (Hour h :
                    Hour.values()) {
                hourBooleanMap.put(h, false);
            }
            isTimeTaken.put(day, hourBooleanMap);
            hourBooleanMap = new LinkedHashMap<>();
        }
    }


    public Map<Hour, Boolean> getIsTimeTaken(Day day) {
        return isTimeTaken.get(day);
    }

    public void setTimeTaken(Day day, Hour hour) {
        isTimeTaken.get(day).put(hour, true);
    }

    public Hall(String name, int totalChairs, ArrayList<Movie> movies) {
        hourBooleanMap = new LinkedHashMap<>();
        isTimeTaken = new HashMap<>();
        for (Day day : Day.values()) {
            for (Hour h :
                    Hour.values()) {
                hourBooleanMap.put(h, false);
            }
            isTimeTaken.put(day, hourBooleanMap);
            hourBooleanMap = new LinkedHashMap<>();
        }
        this.name = name;
        this.totalChairs = totalChairs;
        this.movies = movies;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalChairs() {
        return totalChairs;
    }

    public void setTotalChairs(int totalChairs) {
        this.totalChairs = totalChairs;
    }

    public ArrayList<Movie> getMovies() {
        return movies;
    }

    public void setMovies(ArrayList<Movie> movies) {
        this.movies = movies;
    }


    @Override
    public String toString() {
        return  name ;
    }  public ArrayList<Hour> getAvailableHours(Day day){
        ArrayList<Hour> availableHours=new ArrayList<>();
        for (Hour hour:Hour.values()) {
            if(!isTimeTaken.get(day).get(hour)){
                availableHours.add(hour);
            }

        } return availableHours;}
}
