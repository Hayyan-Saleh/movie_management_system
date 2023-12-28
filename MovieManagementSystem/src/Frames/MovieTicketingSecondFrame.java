package Frames;

import javax.swing.*;

import Domain.*;
import Crew.*;
import Files.*;

import java.awt.*;
import java.awt.event.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MovieTicketingSecondFrame extends JFrame implements ActionListener {
    Data data;
    User user;
    Movie movie;
    Day day;
    Hour hour;
    JPanel northPanel;
    JPanel chairsPanel;
    JScrollPane chairsPanelScrollPane;
    JButton bookButton;
    ArrayList<Boolean> totalChairsBooleanArr;
    ArrayList<Integer> takenChairsIndexes;
    ArrayList<JButton> totalChairsButtonArr;

    public MovieTicketingSecondFrame(Data data, User user, Movie movie, Day day, Hour hour) {
        super("Movie Chairs Selection");
        this.data = data;
        this.user = user;
        this.movie = movie;
        this.day = day;
        this.hour = hour;


        //creating the Center panel and adding it to scroll pane then adding it to the frame
        createPanel();
        chairsPanel.setBackground(new Color(50, 50, 50));
        chairsPanelScrollPane = new JScrollPane(chairsPanel);
        getContentPane().add(chairsPanelScrollPane, BorderLayout.CENTER);

        //creating the North Panel and adding it to the frame
        northPanel = new JPanel();
        northPanel.setSize(1000, 500);
        northPanel.setLayout(new FlowLayout());
        northPanel.setBackground(new Color(50, 50, 50));
        add(northPanel, BorderLayout.NORTH);

        bookButton = new JButton("             Book Seats             ");
        bookButton.setSize(200, 50);
        bookButton.setBackground(new Color(100, 0, 150));
        bookButton.setFont(new Font("Serif", Font.BOLD, 22));
        bookButton.setForeground(Color.white);
        bookButton.setBorder(BorderFactory.createLineBorder(Color.yellow));
        bookButton.setFocusPainted(false);
        bookButton.addActionListener(this);
        northPanel.add(bookButton, SwingConstants.CENTER);

        //creating the Frame

        getContentPane().setBackground(new Color(50, 50, 50));
        setBounds((Toolkit.getDefaultToolkit().getScreenSize().width - 1000) / 2, (Toolkit.getDefaultToolkit().getScreenSize().height - 600) / 2, 1000, 600);
        setSize(1000, 600);
        setVisible(true);

    }

    Icon availableIcon = new ImageIcon("SmallTrGreenSeat.png");
    Icon hoverIcon = new ImageIcon("SmallTrYellowSeat.png");
    Icon unAvailableIcon = new ImageIcon("smallTrRedSeat.png");
    Map<JButton,Integer> c = new HashMap<>();
    public void createPanel() {

        chairsPanel = new JPanel();
        chairsPanel.setPreferredSize(new Dimension(600, 100));
        chairsPanel.setLayout(new FlowLayout());
        JButton tempButton;
        totalChairsBooleanArr = movie.getAvailableChairs(day.ordinal(), hour.ordinal());
        totalChairsButtonArr = new ArrayList<>(totalChairsBooleanArr.size());

        for (int i = 0; i < totalChairsBooleanArr.size(); i++) {
            tempButton = new JButton();
            tempButton.setPreferredSize(new Dimension(50, 50));
            tempButton.setBorder(null);
            tempButton.setContentAreaFilled(false);
            c.put(tempButton,i);

            tempButton.setFocusPainted(false);
            tempButton.addActionListener(this);
            if (totalChairsBooleanArr.get(i)) {
                tempButton.setIcon(unAvailableIcon);
            } else {
                tempButton.setIcon(availableIcon);
            }
            totalChairsButtonArr.add(tempButton);
            chairsPanel.add(tempButton);
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bookButton) {

            takenChairsIndexes=new ArrayList<>();
            for(JButton button:totalChairsButtonArr){
                if(button.getIcon()==hoverIcon){
                    takenChairsIndexes.add(c.get(button)+1);
                }
            }
            if (takenChairsIndexes.size() != 0) {
                int res = JOptionPane.showConfirmDialog(this, "Are you sure you want to take tickets for these seats ? \n" + takenChairsIndexes);
                if (res == JOptionPane.OK_OPTION) {
                    ArrayList<Ticket> tickets = Ticketing.addTicket(this.user, this.movie, this.day, this.hour, takenChairsIndexes);
                    String resultString = "Success ! these are your tickets : \n";
                    for (int i = 0; i < tickets.size(); i++) {
                        resultString += "Ticket " + (i + 1) + " : Movie Name : " + tickets.get(i).getMovie() + " ,Movie Day : " + this.day + " ,Movie Hour : " + this.hour + " ,Movie Hall :" + tickets.get(i).getHall().getName() + " ,Chair Number :" + tickets.get(i).getChairNumber() + "\n";
                    }
                    JOptionPane.showMessageDialog(this, resultString);
                    this.dispose();

                }
            } else {
                JOptionPane.showMessageDialog(this, "Please pick a seat(s) in order to get ticket(s) ");
            }

        }else{
            for(JButton button :totalChairsButtonArr){
                if(e.getSource()==button){
                if(button.getIcon()==availableIcon){
                    button.setIcon(hoverIcon);
                }else if(button.getIcon()==hoverIcon){
                    button.setIcon(availableIcon);
                }
                }
            }
        }
    }



}