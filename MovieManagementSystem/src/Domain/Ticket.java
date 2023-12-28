package Domain;

import java.io.Serializable;

public class Ticket implements Serializable {
    private int chairNumber;
    private Movie movie;
    private Day day;
    private Hour hour;
    private Hall hall;

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    public Hour getHour() {
        return hour;
    }

    public void setHour(Hour hour) {
        this.hour = hour;
    }

    public Ticket(int chairNumber, Movie movie, Day day, Hour hour, Hall hall) {
        this.chairNumber = chairNumber;
        this.movie = movie;
        this.day = day;
        this.hour = hour;
        this.hall = hall;
    }

    public int getChairNumber() {
        return chairNumber;
    }

    public void setChairNumber(int chairNumber) {
        this.chairNumber = chairNumber;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Hall getHall() {
        return hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }


}
