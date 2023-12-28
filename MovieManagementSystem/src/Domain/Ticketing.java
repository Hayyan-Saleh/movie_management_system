package Domain;

import Crew.User;
import Files.Data;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import static Files.Data.*;

public class Ticketing implements Serializable {
    private static ArrayList<Integer> availableChairsNumbers;
/*
    public ArrayList<Integer> getAvailableChairs(Movie movie,Hour hour){
        ArrayList<Boolean> availableChairs;
       availableChairsNumbers=new ArrayList<>();

       for(Hour h:hour.values()){

           if(h.compareTo(hour)==0){
               availableChairs=movie.getHallAt(hour.ordinal()).getIsChairTaken();
           for(int i=0;i<availableChairs.size();i++){
               if(!availableChairs.get(i)){
                   availableChairsNumbers.add(i+1);
               }
            }
       }}
       return availableChairsNumbers;
    }*/

    public static ArrayList<Movie> getAvailableMovies(Day day, Data data) {
        ArrayList<Movie> movies = new ArrayList<>();


        for (Movie i : data.getAppMovies()) {
            for (Day d : i.getMovieShowTimes().keySet()) {
                if (d.compareTo(day) == 0) {
                    movies.add(i);
                }
            }
        }
        return movies;
    }

    public static ArrayList<Ticket> addTicket(User user, Movie movie, Day day, Hour hour, ArrayList<Integer> chairsIndexes,double price) {
        Hall hall = movie.getHallAt(day.ordinal(), hour.ordinal());
        ArrayList<Ticket> tickets = new ArrayList<>();
        Ticket ticket;
        for (int i = 0; i < chairsIndexes.size(); i++) {
            ticket = new Ticket(chairsIndexes.get(i), movie,day,hour,hall,price);
            movie.getDayHourHallCharisMap().get(day.ordinal()).get(hour.ordinal()).set(chairsIndexes.get(i) - 1, true);
            tickets.add(ticket);
            user.getUserTickets().add(ticket);
        }
        return tickets;
    }

    public static void removeTicket(User user, Ticket removedTicket, int dayIndex, int hourIndex) {
        removedTicket.getMovie().getDayHourHallCharisMap().get(dayIndex).get(hourIndex).set(removedTicket.getChairNumber() - 1, false);
        user.getUserTickets().remove(removedTicket);

    }

}
