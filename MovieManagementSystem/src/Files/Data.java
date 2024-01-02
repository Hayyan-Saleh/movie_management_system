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

     public Data() {
          appUsers=new ArrayList<>();
          appMovies=new ArrayList<>();
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
     public void saveRating(String username, int rating, String comment) {
          for (User user : appUsers) {
               if (user.getUserName().equals(username)) {
                    user.addRating(new Rating(rating, comment));
                    break;
               }
          }
     }



     public class Rating {
          private int rating;
          private String comment;

          public Rating(int rating, String comment) {
               this.rating = rating;
               this.comment = comment;
          }

          public int getRating() {
               return rating;
          }

          public void setRating(int rating) {
               this.rating = rating;
          }

          public String getComment() {
               return comment;
          }

          public void setComment(String comment) {
               this.comment = comment;
          }
     }



}
