package Frames;

import Crew.User;
import Domain.Hour;
import Files.Data;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.*;

public class StatisticsFrame extends JFrame{
    Data data;
    JLabel mostPopularMovies,mostCrowdedTimes,movieName,soldTicket,time;
    DefaultTableModel mpmModel = new DefaultTableModel();
    DefaultTableModel mctModel = new DefaultTableModel();
    JTable mpmTable,mctTable;



    public StatisticsFrame(Data data){
        this.data=data;
        setTitle("Statistics");
        getContentPane().setBackground(new Color(160, 0, 50));
        setBounds((Toolkit.getDefaultToolkit().getScreenSize().width - 500) / 2, (Toolkit.getDefaultToolkit().getScreenSize().height - 400) / 2, 500, 400);
        setResizable(false);
        setLayout(null);

        mostPopularMovies = new JLabel("Most Popular Movies:");
        mostPopularMovies.setForeground(Color.white);
        mostPopularMovies.setBounds(30, 10, this.getWidth()-10, 30);
        mostPopularMovies.setFont(new Font("Arial", Font.PLAIN, 20));
        mostCrowdedTimes = new JLabel("Most Crowded Times:");
        mostCrowdedTimes.setForeground(Color.white);
        mostCrowdedTimes.setBounds(250, 10, this.getWidth()-10, 30);
        mostCrowdedTimes.setFont(new Font("Arial", Font.PLAIN, 20));

        movieName = new JLabel("Movie Name");
        movieName.setForeground(Color.white);
        movieName.setBounds(30, 55, this.getWidth()-10, 30);
        movieName.setFont(new Font("Arial", Font.ITALIC, 10));

        soldTicket = new JLabel("Sold Tickets");
        soldTicket.setForeground(Color.white);
        soldTicket.setBounds(130, 55, this.getWidth()-10, 30);
        soldTicket.setFont(new Font("Arial", Font.PLAIN, 10));

        time = new JLabel("Time");
        time.setForeground(Color.white);
        time.setBounds(250, 55, this.getWidth()-10, 30);
        time.setFont(new Font("Arial", Font.PLAIN, 10));



        mpmModel.addColumn("Movie Name");
        mpmModel.addColumn("Num Of sold Tickets");
        ArrayList<String> moMovies=new ArrayList();
        for(int i=0;i<data.getAppUsers().size();i++){
            for(int j=0;j<data.getAppUsers().get(i).getUserTickets().size();j++){
                moMovies.add(data.getAppUsers().get(i).getUserTickets().get(j).getMovie().getMovieName());
            }
        }
        HashMap<String, Integer> frequencyMap = new HashMap<>();
        for (String str : moMovies) {
            if (frequencyMap.containsKey(str)) {
                frequencyMap.put(str, frequencyMap.get(str) + 1);
            } else {
                frequencyMap.put(str, 1);
            }
        }
        SortedSet<Map.Entry<String, Integer>> sortedEntries = new TreeSet<>((o1, o2) -> (o2.getValue() - o1.getValue()));
        sortedEntries.addAll(frequencyMap.entrySet());

        String[] movieRow;
        for (Map.Entry<String,Integer>entryk : sortedEntries){
            movieRow = new String[]{entryk.getKey(),entryk.getValue().toString()};
            mpmModel.addRow(movieRow);}
        mpmTable=new JTable(mpmModel);
        mpmTable.setDefaultEditor(Object.class, null);
        mpmTable.setFont(new Font("Arial", Font.PLAIN, 14));
        mpmTable.setForeground(Color.white);
        mpmTable.setBackground(new Color(50, 50, 50));
        mpmTable.setSelectionBackground(Color.red);
        mpmTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        mpmTable.setBounds(30, 80, 200, 100);



        mctModel.addColumn("Time");
        ArrayList<Hour> crowdedHours = new ArrayList<>();
        for(int i=0;i<data.getAppUsers().size();i++){
            for(int j=0;j<data.getAppUsers().get(i).getUserTickets().size();j++){
                crowdedHours.add(data.getAppUsers().get(i).getUserTickets().get(j).getHour());
            }
        }
        HashMap<Hour, Integer> frequencyHourMap = new HashMap<>();
        for (Hour hour : crowdedHours) {
            if (frequencyHourMap.containsKey(hour)) {
                frequencyHourMap.put(hour, frequencyHourMap.get(hour) + 1);
            } else {
                frequencyHourMap.put(hour, 1);
            }
        }
        SortedSet<Map.Entry<Hour, Integer>> sortedHourEntries = new TreeSet<>((o1, o2) -> (o2.getValue() - o1.getValue()));
        sortedHourEntries.addAll(frequencyHourMap.entrySet());


        Hour[] HourRow;

        for (Map.Entry<Hour,Integer>entryh : sortedHourEntries){
            HourRow = new Hour[]{entryh.getKey()};
            mctModel.addRow(HourRow);}
        mctTable=new JTable(mctModel);
        mctTable.setDefaultEditor(Object.class, null);
        mctTable.setFont(new Font("Arial", Font.PLAIN, 14));
        mctTable.setForeground(Color.white);
        mctTable.setBackground(new Color(50, 50, 50));
        mctTable.setSelectionBackground(Color.red);
        mctTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        mctTable.setBounds(250, 80, 200, 100);





        add(movieName);
        add(soldTicket);
        add(time);
        add(mostCrowdedTimes);
        add(mostPopularMovies);
        add(mpmTable);
        add(mctTable);
        setVisible(true);


    }


}
