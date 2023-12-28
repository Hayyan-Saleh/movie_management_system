package Frames;

import Crew.User;
import Domain.Hour;
import Domain.Movie;
import Domain.Ticket;
import Files.Data;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.List;
import java.util.*;
import java.util.stream.Collectors;

public class StatisticsFrame extends JFrame {
    Data data;
    DefaultTableModel mostCrowdedMoviesTableModel=new DefaultTableModel();
    DefaultTableModel mostCrowdedTimesTableModel=new DefaultTableModel();
    JTable mostCrowdedMoviesTable,mostCrowdedTimesTable;
    JScrollPane mostCrowdedMoviesTableScrollPane,mostCrowdedTimesTableScrollPane;
    ArrayList<Ticket> totalTickets;
    Map<Movie, Integer> movieTotalTicketsMap;
    Map<Hour,Integer> hourTotalTicketsMap;
    public StatisticsFrame(Data data){
        this.data=data;


        setTitle("Statistics Frame");
        setBounds((Toolkit.getDefaultToolkit().getScreenSize().getSize().width-400)/2,(Toolkit.getDefaultToolkit().getScreenSize().getSize().height-270)/2,400,270);
        setLayout(new FlowLayout());
        setResizable(false);
        getContentPane().setBackground(new Color(160, 0, 50));


        initializeTotalTickets();
        initializeMovieTotalTicketsMap();

        mostCrowdedMoviesTableModel.addColumn("Most Popular Movies");
        mostCrowdedMoviesTableModel.addColumn("Sold Tickets Num");

        initializeMostCrowdedMoviesTableModel();

        mostCrowdedMoviesTable = new JTable(mostCrowdedMoviesTableModel);
        mostCrowdedMoviesTable.setFont(new Font("Arial", Font.PLAIN, 14));
        mostCrowdedMoviesTable.setForeground(Color.white);
        mostCrowdedMoviesTable.setBackground(new Color(50, 50, 50));
        mostCrowdedMoviesTable.setSelectionBackground(Color.red);
        mostCrowdedMoviesTable.setDefaultEditor(Object.class,null);
        mostCrowdedMoviesTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        mostCrowdedMoviesTableScrollPane = new JScrollPane(mostCrowdedMoviesTable);
        mostCrowdedMoviesTableScrollPane.setPreferredSize(new Dimension(350,103));
        add(mostCrowdedMoviesTableScrollPane);


        initializeHourTotalTicketsMap();
        mostCrowdedTimesTableModel.addColumn("Most Crowded Times");
        initializeMostCrowdedTimesTableModel();

        mostCrowdedTimesTable = new JTable(mostCrowdedTimesTableModel);
        mostCrowdedTimesTable.setFont(new Font("Arial", Font.PLAIN, 14));
        mostCrowdedTimesTable.setForeground(Color.white);
        mostCrowdedTimesTable.setBackground(new Color(50, 50, 50));
        mostCrowdedTimesTable.setSelectionBackground(Color.red);
        mostCrowdedTimesTable.setDefaultEditor(Object.class,null);
        mostCrowdedTimesTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        mostCrowdedTimesTableScrollPane = new JScrollPane(mostCrowdedTimesTable);
        mostCrowdedTimesTableScrollPane.setPreferredSize(new Dimension(350,105));
        add(mostCrowdedTimesTableScrollPane);

        setVisible(true);

      sortTheMovieMapToModule();
      sortTheTimeMapToModule();
    }

    public void initializeTotalTickets(){
        this.totalTickets=new ArrayList<>();
        for(User user: data.getAppUsers()){
            totalTickets.addAll(user.getUserTickets());
        }
    }
    public void initializeMovieTotalTicketsMap(){
        movieTotalTicketsMap=new LinkedHashMap<>();
        for(Ticket ticket:totalTickets){
            if(movieTotalTicketsMap.containsKey(ticket.getMovie())){
                movieTotalTicketsMap.put(ticket.getMovie(),movieTotalTicketsMap.get(ticket.getMovie())+1);
            }else
                movieTotalTicketsMap.put(ticket.getMovie(),1);
        }
    }
    public void initializeMostCrowdedMoviesTableModel(){
        Map<Movie,Integer> biggestFiveMap=new LinkedHashMap<>();
        for(int i=0;i<5;i++){
            Movie topMovie = null;
            int max = 0;
            for (Movie movie : movieTotalTicketsMap.keySet()) {
                if (movieTotalTicketsMap.get(movie) >= max && !biggestFiveMap.containsKey(movie)) {
                    max = movieTotalTicketsMap.get(movie);
                    topMovie = movie;
                }
            }
            biggestFiveMap.put(topMovie, max);
        }
//        for(Movie movie:movieTotalTicketsMap.keySet()){
//            mostCrowdedMoviesTableModel.addRow(new String[]{movie.getMovieName(),movieTotalTicketsMap.get(movie).toString()});
//        }



    }

    public void initializeHourTotalTicketsMap(){
        hourTotalTicketsMap=new LinkedHashMap<>();
        for(Ticket ticket:totalTickets){
            if(hourTotalTicketsMap.containsKey(ticket.getHour())){
                hourTotalTicketsMap.put(ticket.getHour(),hourTotalTicketsMap.get(ticket.getHour())+1);
            }else{
                hourTotalTicketsMap.put(ticket.getHour(),1);
            }
        }
    }

    public void initializeMostCrowdedTimesTableModel(){
        Map<Hour,Integer> biggestFiveMap=new LinkedHashMap<>();
        for(int i=0;i<5;i++){
            Hour topHour = null;
            int max = 0;
            for (Hour hour : hourTotalTicketsMap.keySet()) {
                if (hourTotalTicketsMap.get(hour)>= max && !biggestFiveMap.containsKey(hour)) {
                    max =hourTotalTicketsMap.get(hour);
                    topHour=hour;
                }
            }
            biggestFiveMap.put(topHour, max);
        }
//        for(Hour hour:hourTotalTicketsMap.keySet()){
//                mostCrowdedTimesTableModel.addRow(new String[]{hour.toString()});
//        }
    }
        public void sortTheMovieMapToModule(){
            ArrayList<Integer> sortedSoldTickets=new ArrayList<>();
            for(int i:movieTotalTicketsMap.values()){
                sortedSoldTickets.add(i);
            }
            sortedSoldTickets.sort(Comparator.reverseOrder());

            ArrayList<Movie> sortedMoviesList=new ArrayList<>();
            for(int i=0;i<sortedSoldTickets.size();i++){
                for(Movie movie:movieTotalTicketsMap.keySet()){
                    if(movieTotalTicketsMap.get(movie)==sortedSoldTickets.get(i)){
                        sortedMoviesList.add(movie);
                    }
                }
            }
            for(int i=0;i<sortedMoviesList.size();i++){
                mostCrowdedMoviesTableModel.addRow(new String[]{sortedMoviesList.get(i).getMovieName(),sortedSoldTickets.get(i).toString()});
            }


        }

    public void sortTheTimeMapToModule(){
        ArrayList<Integer> sortedHourValues=new ArrayList<>();
        for(int i:hourTotalTicketsMap.values()){
            sortedHourValues.add(i);
        }
        sortedHourValues.sort(Comparator.reverseOrder());

        ArrayList<Hour> sortedHourKeys=new ArrayList<>();
        for(int i=0;i<sortedHourValues.size();i++){
            for(Hour hour:hourTotalTicketsMap.keySet()){
                if(hourTotalTicketsMap.get(hour)==sortedHourValues.get(i)){
                    sortedHourKeys.add(hour);
                }
            }
        }
        for(int i=0;i<sortedHourValues.size();i++){
            mostCrowdedTimesTableModel.addRow(new String[]{sortedHourKeys.get(i).toString(),sortedHourValues.get(i).toString()});
        }


    }

        }


