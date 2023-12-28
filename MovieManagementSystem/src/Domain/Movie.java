package Domain;

import java.io.Serializable;
import java.util.*;


public class Movie implements Serializable {

    private static int GlobalID = 10000;
    private int ID;
    private String movieName;
    private Category movieCategory;



    Map<Day,ArrayList<Hour>> movieShowTimes;
    private Double price;
    private Map<Integer, Hall> hourHallMap;
    private Map<Integer, Map<Integer, Hall>> movieHalls;
    private Map<Integer, ArrayList<Boolean>> hourHallChairsMap;
    private Map<Integer, Map<Integer, ArrayList<Boolean>>> dayHourHallCharisMap;

    public Hall getHallAt(int dayIndex, int hourIndex) {
        return movieHalls.get(dayIndex).get(hourIndex);
    }


    public Movie() {
        hourHallChairsMap = new LinkedHashMap<>();
        dayHourHallCharisMap = new HashMap<>();
        hourHallMap = new LinkedHashMap<>();
        movieHalls = new HashMap<>();
        ID = GlobalID++;
    }

    public void insertInHallMap(int dayIndex, ArrayList<Integer> hourIndexes, Hall hall) {
        for (int i : hourIndexes) {
            hourHallMap.put(i,new Hall( hall));
        }
        movieHalls.put(dayIndex, hourHallMap);
        hourHallMap = new LinkedHashMap<>();
        hourHallChairsMap = new LinkedHashMap<>();
        for (int i : hourIndexes) {
            hourHallChairsMap.put(i, new ArrayList<>(Collections.nCopies(hall.getTotalChairs(), false)));
        }
        dayHourHallCharisMap.put(dayIndex, hourHallChairsMap);
    }

    public static int getGlobalID() {
        return GlobalID;
    }

    public static void setGlobalID(int globalID) {
        GlobalID = globalID;
    }

    public Map<Integer, Map<Integer, Hall>> getMovieHalls() {
        return movieHalls;
    }

    public void setMovieHalls(Map<Integer, Map<Integer, Hall>> movieHalls) {
        this.movieHalls = movieHalls;
    }

    public Map<Integer, ArrayList<Boolean>> getHourHallChairsMap() {
        return hourHallChairsMap;
    }

    public void setHourHallChairsMap(Map<Integer, ArrayList<Boolean>> hourHallChairsMap) {
        this.hourHallChairsMap = hourHallChairsMap;
    }

    public Map<Integer, Map<Integer, ArrayList<Boolean>>> getDayHourHallCharisMap() {
        return dayHourHallCharisMap;
    }

    public void setDayHourHallCharisMap(Map<Integer, Map<Integer, ArrayList<Boolean>>> dayHourHallCharisMap) {
        this.dayHourHallCharisMap = dayHourHallCharisMap;
    }

    public Movie(String movieName, Category movieCategory, Map<Day,ArrayList<Hour>> movieShowTimes, Double price) {
        hourHallMap = new LinkedHashMap<>();
        movieHalls = new HashMap<>();
        dayHourHallCharisMap = new HashMap<>();
        ID = GlobalID++;
        this.movieName = movieName;
        this.movieCategory=movieCategory;
        this.movieShowTimes=movieShowTimes;
        this.price = price;
        this.hourHallMap = new LinkedHashMap<>();

    }

    public void setMovieChairAvailable(int dayIndex, int hourIndex, int chairIndex, boolean isTaken) {
        dayHourHallCharisMap.get(dayIndex).get(hourIndex).set(chairIndex, isTaken);
    }

    public ArrayList<Boolean> getAvailableChairs(int dayIndex, int hourIndex) {
        return dayHourHallCharisMap.get(dayIndex).get(hourIndex);
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public Map<Integer, Hall> getHourHallMap() {
        return hourHallMap;
    }

    public void setHourHallMap(Map<Integer, Hall> hourHallMap) {
        this.hourHallMap = hourHallMap;
    }

    public int getID() {
        return ID;
    }

    public Map<Day, ArrayList<Hour>> getMovieShowTimes() {
        return movieShowTimes;
    }
    public ArrayList<Hour> getMovieHours(Day day){
        ArrayList<Hour> hours=new ArrayList<>();
        for(Day setDay:getMovieShowTimes().keySet()){
            if(day.equals(setDay)){
                for(Hour hour:getMovieShowTimes().get(day)){
                    hours.add(hour);
                }
            }
        }
        return hours;

    }

    public void setMovieShowTimes(Map<Day, ArrayList<Hour>> movieShowTimes) {
        this.movieShowTimes = movieShowTimes;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public Category getMovieCategory() {
        return movieCategory;
    }
    public void setMovieCategory(Category movieCategory) {
        this.movieCategory = movieCategory;
    }
    @Override
    public String toString(){
        return this.getMovieName();
    }
}
