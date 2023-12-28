package Files;

import Crew.Manager;
import Crew.User;
import Domain.Cinema;
import Domain.Hall;
import Domain.Movie;

import java.io.Serializable;
import java.util.ArrayList;

public class Data implements Serializable {
     public Cinema cinema;

     public Cinema getCinema() {
          return cinema;
     }

     public void setCinema(Cinema cinema) {
          this.cinema = cinema;
     }

     public  ArrayList<User> appUsers;
     public  Manager appManager;
     public  ArrayList<Movie>appMovies;
     public  ArrayList<Hall>appHalls;

     public Data() {
          appUsers=new ArrayList<>();
          appMovies=new ArrayList<>();
          appHalls=new ArrayList<>();
     }

     public ArrayList<User> getAppUsers() {
          return appUsers;
     }

     public void setAppUsers(ArrayList<User> appUsers) {
          this.appUsers = appUsers;
     }

     public Manager getAppManager() {
          return appManager;
     }

     public void setAppManager(Manager appManager) {
          this.appManager = appManager;
     }

     public ArrayList<Movie> getAppMovies() {
          return appMovies;
     }

     public void setAppMovies(ArrayList<Movie> appMovies) {
          this.appMovies = appMovies;
     }

     public ArrayList<Hall> getAppHalls() {
          return appHalls;
     }

     public void setAppHalls(ArrayList<Hall> appHalls) {
          this.appHalls = appHalls;
     }

     public void print(){
          for(Hall hall:appHalls){
               System.out.println(hall.toString());
          }
     }



}
