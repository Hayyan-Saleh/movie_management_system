package Crew;

import Domain.Ticket;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
    private String userName;
    private String userPassword;
    private ArrayList<Ticket> userTickets;

    public User(String userName, String userPassword, ArrayList<Ticket> userTickets) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.userTickets = userTickets;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public ArrayList<Ticket> getUserTickets() {
        return userTickets;
    }
    public void printTickets(){
        for(Ticket ticket:getUserTickets()){
            System.out.println("=============================\nChair Number : "+
                    ticket.getChairNumber()+"\nMovie Name : "+
                    ticket.getMovie().getMovieName() +
                    "\nHall Name : "+ticket.getHall().getName()+
                    "\n=============================");
        }
    }
    public void setUserTickets(ArrayList<Ticket> userTickets) {
        this.userTickets = userTickets;
    }
}
