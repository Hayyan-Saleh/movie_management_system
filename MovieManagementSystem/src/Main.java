import Crew.Manager;
import Files.Data;
import Frames.ManagerFrame;
import Frames.SignInFrame;
import Frames.UserFrame;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Map;

public class Main {
    Data data;

    public Main() {
        File file = new File("data.txt");
        try {
            if (file.exists()) {
                ObjectInputStream applicationDataGetter = new ObjectInputStream(new FileInputStream(file));
                data = (Data) applicationDataGetter.readObject();
            } else {
                file.createNewFile();
                data = new Data();
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "There is an IOEXCEPTION \n" + ex.getMessage());
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "There is a CLASS NOT FOUND EXCEPTION \n" + e.getMessage());
        }
        new SignInFrame(data);

    }

    public static void main(String args[]) {
            new Main();


//        Hall hall = new Hall("hall1", 30, null);
//        ArrayList<Day> days1 = new ArrayList<>();
//        days1.add(Day.SUNDAY);
//        days1.add(Day.MONDAY);
//        ArrayList<Hour> hours1 = new ArrayList<>();
//        hours1.add(Hour.TWELVE);
//        hours1.add(Hour.TEN);
//        Movie movie1 = new Movie("movie1", "comedy", days1, hours1, 30.0);
//        hall.setTimeTaken(Day.SUNDAY, Hour.TWELVE);
//        hall.setTimeTaken(Day.SUNDAY, Hour.TEN);
//        hall.setTimeTaken(Day.MONDAY, Hour.TWELVE);
//        hall.setTimeTaken(Day.MONDAY, Hour.TEN);
//        ArrayList<Day> days2 = new ArrayList<>();
//        days1.add(Day.FRIDAY);
//        days1.add(Day.MONDAY);
//        ArrayList<Hour> hours2 = new ArrayList<>();
//        hours1.add(Hour.EIGHT);
//        hours1.add(Hour.SIX);
//        Movie movie2 = new Movie("movie2", "scary", days2, hours2, 50.0);
//        hall.setTimeTaken(Day.FRIDAY, Hour.EIGHT);
//        hall.setTimeTaken(Day.FRIDAY, Hour.SIX);
//        hall.setTimeTaken(Day.MONDAY, Hour.EIGHT);
//        hall.setTimeTaken(Day.MONDAY, Hour.SIX);
//        ArrayList<Movie> movies = new ArrayList<>();
//        movies.add(movie1);
//        movies.add(movie2);
//        movie1.insertInHallMap(Day.SUNDAY.ordinal(), new ArrayList<>(Arrays.asList(Hour.TWELVE.ordinal(), Hour.TEN.ordinal())), hall);
//        movie1.insertInHallMap(Day.MONDAY.ordinal(), new ArrayList<>(Arrays.asList(Hour.TWELVE.ordinal(), Hour.TEN.ordinal())), hall);
//        movie2.insertInHallMap(Day.FRIDAY.ordinal(), new ArrayList<>(Arrays.asList(Hour.EIGHT.ordinal(), Hour.SIX.ordinal())), hall);
//        movie2.insertInHallMap(Day.MONDAY.ordinal(), new ArrayList<>(Arrays.asList(Hour.EIGHT.ordinal(), Hour.SIX.ordinal())), hall);
//        hall.setMovies(movies);
//        Ticket ticket1 = new Ticket(1, movie1, movie1.getHallAt(Day.SUNDAY.ordinal(), Hour.TWELVE.ordinal()));
//        Ticket ticket2 = new Ticket(3, movie1, movie1.getHallAt(Day.SUNDAY.ordinal(), Hour.TWELVE.ordinal()));
//        Ticket ticket3 = new Ticket(2, movie2, movie2.getHallAt(Day.MONDAY.ordinal(), Hour.EIGHT.ordinal()));
//        Ticket ticket4 = new Ticket(5, movie2, movie2.getHallAt(Day.MONDAY.ordinal(), Hour.EIGHT.ordinal()));
//        ArrayList<Ticket> tickets = new ArrayList<>();
//        tickets.add(ticket1);
//        tickets.add(ticket2);
//        tickets.add(ticket3);
//        tickets.add(ticket4);
//        User user = new User("Hayyan", "0000", new ArrayList<>());
//        System.out.println("------------------------------------");
//        System.out.println("available seats in hall 1 for movie 1 at day sunday 12 am before booking : ");
//        System.out.println(movie1.getAvailableChairs(Day.SUNDAY.ordinal(), Hour.TWELVE.ordinal()));
//        System.out.println("------------------------------------");
//        Ticketing.addTicket(user, movie1, Day.SUNDAY, Hour.TWELVE, new ArrayList<>(Arrays.asList(1, 3)));
//        Ticketing.addTicket(user, movie2, Day.MONDAY, Hour.SIX, new ArrayList<>(Arrays.asList(2, 5)));
//        System.out.println("before removing");
//        user.printTickets();
//        System.out.println("------------------------------------");
//        System.out.println("available seats in hall 1 for movie 1 at day sunday at 12 pm after booking 1 3: ");
//        System.out.println(movie1.getAvailableChairs(Day.SUNDAY.ordinal(), Hour.TWELVE.ordinal()));
//        System.out.println("------------------------------------");
//        Ticketing.removeTicket(user, user.getUserTickets().get(0), Day.SUNDAY.ordinal(), Hour.TWELVE.ordinal());
//        System.out.println("after removing seat 3 movie 1 day sunday at 12 am");
//        user.printTickets();
//        System.out.println("------------------------------------");
//        System.out.println("available seats in hall 1 for movie 1 at 12 am after booking 1 3 and removing 1: ");
//        System.out.println(movie1.getAvailableChairs(Day.SUNDAY.ordinal(), Hour.TWELVE.ordinal()));
//        System.out.println("------------------------------------");
//        System.out.println("All the  sunday for movie 2 and movie 1 hour with boolean is taken : ");
//        System.out.println(hall.getIsTimeTaken(Day.SUNDAY));
//        System.out.println("------------------------------------");
//        System.out.println("available hours for monday ");
//        System.out.println(hall.getIsTimeTaken(Day.MONDAY));
//        System.out.println("available seats for movie 2 day monday hour 6 with boolean is taken : ");
//        System.out.println(movie2.getAvailableChairs(Day.MONDAY.ordinal(), Hour.SIX.ordinal()));

    }
}









