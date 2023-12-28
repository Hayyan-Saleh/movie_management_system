package Frames;

import Crew.User;
import Domain.Cinema;
import Domain.Movie;
import Domain.Ticket;
import Files.Data;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ManagerMainPanel extends JPanel {
    Data data;
    Font font = new Font("Serif", Font.BOLD, 18);
    JLabel cinemaNameLabel, managerNameLabel, timeLabel, statisticsHintLabel, moviesHintLabel, usersHintLabel;
    JLabel totalMoviesLabel, totalHallsLabel, totalUsersLabel, totalTicketsLabel, totalIncomeLabel;
    DefaultTableModel usersModel = new DefaultTableModel();
    DefaultTableModel moviesModel = new DefaultTableModel();
    JTable usersTable, moviesTable;
    JScrollPane usersTableScrollPane, moviesTableScrollPane;

    public ManagerMainPanel(Data data) {
        this.data = data;
        setLayout(null);
        setBounds(300, 0, 700, 600);
        setBackground(Color.darkGray);
        if (data.getCinema() == null) {
            data.setCinema(new Cinema());
            data.getCinema().setCinemaName(JOptionPane.showInputDialog("Please enter your cinema Name"));
        }
        //initialize the labels
        cinemaNameLabel = new JLabel(data.getCinema().getCinemaName());
        cinemaNameLabel.setBounds(550, 10, 450, 40);
        cinemaNameLabel.setFont(new Font("Serif", Font.BOLD, 28));
        cinemaNameLabel.setForeground(Color.yellow);
        add(cinemaNameLabel);

        managerNameLabel = new JLabel("Manager : " + data.getAppManager().getManagerName());
        managerNameLabel.setBounds(310, 50, 200, 30);
        managerNameLabel.setFont(font);
        managerNameLabel.setForeground(Color.white);
        add(managerNameLabel);


        timeLabel = new JLabel("00:00:00 ----");
        timeLabel.setBounds(310, 90, 350, 30);
        timeLabel.setFont(font);
        timeLabel.setForeground(Color.white);
        add(timeLabel);

        statisticsHintLabel = new JLabel("Statistics");
        statisticsHintLabel.setBounds(600, 115, 250, 40);
        statisticsHintLabel.setFont(new Font("Serif", Font.BOLD, 28));
        statisticsHintLabel.setForeground(Color.red);
        add(statisticsHintLabel);

        usersHintLabel = new JLabel("Users");
        usersHintLabel.setBounds(465, 240, 150, 30);
        usersHintLabel.setFont(new Font("Serif", Font.BOLD, 24));
        usersHintLabel.setForeground(Color.white);
        add(usersHintLabel);

        moviesHintLabel = new JLabel("movies");
        moviesHintLabel.setBounds(805, 240, 150, 30);
        moviesHintLabel.setFont(new Font("Serif", Font.BOLD, 24));
        moviesHintLabel.setForeground(Color.white);
        add(moviesHintLabel);

        totalMoviesLabel = new JLabel(data.getAppMovies() == null ? " Total Movies : 0" : " Total Movies : " + data.getAppMovies().size());
        totalMoviesLabel.setBounds(310, 160, 345, 30);
        totalMoviesLabel.setFont(new Font("Serif", Font.BOLD, 18));
        totalMoviesLabel.setForeground(Color.white);
        add(totalMoviesLabel);

        totalHallsLabel = new JLabel(data.getCinema().getHalls() == null ? " Total Halls : 0" : " Total Halls : " + data.getCinema().getHalls().size());
        totalHallsLabel.setBounds(310, 200, 345, 30);
        totalHallsLabel.setFont(new Font("Serif", Font.BOLD, 18));
        totalHallsLabel.setForeground(Color.white);
        add(totalHallsLabel);

        totalUsersLabel = new JLabel(data.getAppUsers() == null ? " Total Users : 0" : " Total Users : " + data.getAppUsers().size());
        totalUsersLabel.setBounds(480, 160, 345, 30);
        totalUsersLabel.setFont(new Font("Serif", Font.BOLD, 18));
        totalUsersLabel.setForeground(Color.white);
        add(totalUsersLabel);
        int tickets = 0;
        for (User user :
                data.getAppUsers()) {
                tickets+=user.getUserTickets().size();
        }
        totalTicketsLabel = new JLabel(" Total Tickets : " + tickets);
        totalTicketsLabel.setBounds(480, 200, 345, 30);
        totalTicketsLabel.setFont(new Font("Serif", Font.BOLD, 18));
        totalTicketsLabel.setForeground(Color.white);
        add(totalTicketsLabel);
        double totalIncome=0;
        for(User user:data.getAppUsers())
        for(Ticket ticket:user.getUserTickets()){
            totalIncome+=ticket.getMovie().getPrice();
        }
        totalTicketsLabel = new JLabel(" Total Income : " + totalIncome+" $ ");
        totalTicketsLabel.setBounds(650, 160, 345, 30);
        totalTicketsLabel.setFont(new Font("Serif", Font.BOLD, 18));
        totalTicketsLabel.setForeground(Color.white);
        add(totalTicketsLabel);

        //creating users table and adding it to panel

        usersModel.addColumn("User Name");
        usersModel.addColumn("User Password");
        usersModel.addColumn("Last Movie Name");
        String[] userRow;
        if (data.getAppUsers() != null) {
            for (User user :
                    data.getAppUsers()) {
                if (user.getUserTickets().size() == 0) {
                    userRow = new String[]{user.getUserName(), user.getUserPassword(), "No Tickets"};
                } else {
                    userRow = new String[]{user.getUserName(), user.getUserPassword(), user.getUserTickets().get(user.getUserTickets().size() - 1).getMovie().getMovieName()};
                }
                usersModel.addRow(userRow);
            }
        }
        usersTable = new JTable(usersModel);
        usersTable.setFont(font);
        usersTable.setForeground(Color.white);
        usersTable.setBackground(new Color(10, 100, 100));
        usersTable.setEnabled(false);
        usersTableScrollPane = new JScrollPane(usersTable);
        usersTableScrollPane.setBounds(310, 280, 350, 280);
        add(usersTableScrollPane);


        //creating movie table and adding it to panel
        moviesModel.addColumn("Movie Name");
        moviesModel.addColumn("Category");
        moviesModel.addColumn("Price");
        String[] movieRow;
        if (data.getAppMovies() != null) {
            for (Movie movie :
                    data.getAppMovies()) {
                movieRow = new String[]{movie.getMovieName(), movie.getMovieCategory().toString(), movie.getPrice().toString() + " $"};
                moviesModel.addRow(movieRow);
            }
        }
        moviesTable = new JTable(moviesModel);
        moviesTable.setFont(font);
        moviesTable.setForeground(Color.white);
        moviesTable.setBackground(new Color(130, 10, 10));
        moviesTable.setEnabled(false);
        moviesTableScrollPane = new JScrollPane(moviesTable);
        moviesTableScrollPane.setBounds(670, 280, 320, 280);
        add(moviesTableScrollPane);


        //creating time thread and starting it
          new Thread( () ->{
                while (!Thread.currentThread().isInterrupted()) {
                    timeLabel.setText("Time : " + new java.util.Date().toGMTString());
                    try {
                        Thread.currentThread().sleep(1000);
                    } catch (InterruptedException e) {
                        JOptionPane.showMessageDialog(null, "There is a Thread Exception !\n" + e.getMessage());
                    }
                }
            }
        ).start();

          //creating refresher Thread and starting it
        new Thread(()->{
            while (!Thread.currentThread().isInterrupted()){
                totalMoviesLabel.setText(data.getAppMovies() == null ? " Total Movies : 0" : " Total Movies : " + data.getAppMovies().size());
                totalHallsLabel.setText(data.getCinema().getHalls() == null ? " Total Halls : 0" : " Total Halls : " + data.getCinema().getHalls().size());
                totalUsersLabel.setText(data.getAppUsers() == null ? " Total Users : 0" : " Total Users : " + data.getAppUsers().size());
//                refreshMoviesTable();
            }
        }).start();

    }


    public void refreshMoviesTable(){
        moviesModel=null;
        moviesModel=new DefaultTableModel();
        moviesModel.addColumn("Movie Name");
        moviesModel.addColumn("Category");
        moviesModel.addColumn("Price");
        String[] movieRow;
        if (data.getAppMovies() != null) {
            for (Movie movie :
                    data.getAppMovies()) {
                movieRow = new String[]{movie.getMovieName(), movie.getMovieCategory().toString(), movie.getPrice().toString() + " $"};
                moviesModel.addRow(movieRow);
            }
        }
        moviesTable.repaint();
        moviesTable.revalidate();
        moviesTableScrollPane.repaint();
        moviesTableScrollPane.revalidate();
    }

}
