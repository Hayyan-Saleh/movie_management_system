package Domain;

import java.io.Serializable;

public class Ticket implements Serializable {
    private int chairNumber;
    private double price;
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

    public Ticket(int chairNumber, Movie movie, Day day, Hour hour, Hall hall,double price) {
        this.chairNumber = chairNumber;
        this.movie = movie;
        this.day = day;
        this.hour = hour;
        this.hall = hall;
        this.price=price;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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
