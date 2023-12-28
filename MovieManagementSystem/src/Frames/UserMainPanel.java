package Frames;

import Crew.User;
import Domain.Day;
import Domain.Movie;
import Files.Data;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class UserMainPanel extends JPanel implements ActionListener {
    UserFrame userFrame;
    Data data;
    User user;
    JLabel userNameLabel, userTotalTicketsLabel,discountDetailsLabel;
    JButton addTicketButton,removeTicketButton,newMovieBookerButton, showStaticsButton,rateMovieButton;

    DefaultTableModel moviesModel = new DefaultTableModel();
    JTable  moviesTable;
    JScrollPane moviesTableScrollPane;
    Font font =new Font("Arial",Font.ROMAN_BASELINE,24);
    ImageIcon imageIcon=new ImageIcon("userBackground.png");
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(imageIcon.getImage(),0,0,this);
    }
    public UserMainPanel(Data data, User user,UserFrame userFrame){
        this.data=data;
        this.user=user;
        this.userFrame=userFrame;

        setBounds(0,0,800,600);
        setLayout(null);
        repaint();
        revalidate();

        //creating the labels and adding them to panel
        userNameLabel =new JLabel("User Name : "+user.getUserName());
        userNameLabel.setBounds(10,20,250,40);
        userNameLabel.setFont(font);
        userNameLabel.setForeground(Color.white);
        add(userNameLabel);

        userTotalTicketsLabel =new JLabel("User Total Tickets : "+user.getUserTickets().size());
        userTotalTicketsLabel.setBounds(10,60,250,40);
        userTotalTicketsLabel.setFont(font);
        userTotalTicketsLabel.setForeground(Color.white);
        add(userTotalTicketsLabel);

        discountDetailsLabel =new JLabel(data.getAppManager().getManagerDiscountDay()==null ? "Discount :No Day Discounts":"Discount :At "+data.getAppManager().getManagerDiscountDay()+" for "+data.getAppManager().getDiscountAmount()+" %");
        discountDetailsLabel.setBounds(10,100,365,40);
        discountDetailsLabel.setFont(new Font("Arial",Font.PLAIN,20));
        discountDetailsLabel.setForeground(Color.white);
        add(discountDetailsLabel);

        JLabel newMovieHintLabel=new JLabel("Watch Newest Movie ");
        newMovieHintLabel.setBounds(55,230,300,30);
        newMovieHintLabel.setFont(new Font("Arial",Font.LAYOUT_LEFT_TO_RIGHT,20));
        newMovieHintLabel.setForeground(Color.white);
        add(newMovieHintLabel);

        JLabel moviesHintLabel=new JLabel(data.getCinema().getCinemaName()+" Cinema Movies ");
        moviesHintLabel.setBounds(430,20,300,30);
        moviesHintLabel.setFont(new Font("Arial",Font.LAYOUT_LEFT_TO_RIGHT,20));
        moviesHintLabel.setForeground(Color.white);
        add(moviesHintLabel);

        //creating and adding the buttons to the panel
        addTicketButton =new JButton("Add Ticket");
        addTicketButton.setBounds(50,150,200,30);
        addTicketButton.setFont(font);
        addTicketButton.setFocusPainted(false);
        addTicketButton.setBorder(null);
        addTicketButton.setForeground(Color.white);
        addTicketButton.setBackground(Color.darkGray);
        addTicketButton.addActionListener(this);
        add(addTicketButton);

        removeTicketButton =new JButton("Remove Ticket");
        removeTicketButton.setBounds(50,190,200,30);
        removeTicketButton.setFont(font);
        removeTicketButton.setFocusPainted(false);
        removeTicketButton.setBorder(null);
        removeTicketButton.setForeground(Color.white);
        removeTicketButton.setBackground(Color.darkGray);
        removeTicketButton.addActionListener(this);
        add(removeTicketButton);


        newMovieBookerButton =new JButton(data.getAppMovies().get(data.getAppMovies().size()-1).getMovieName());
        newMovieBookerButton.setBounds(50,270,200,30);
        newMovieBookerButton.setFont(new Font("Serif",Font.LAYOUT_LEFT_TO_RIGHT,20));
        newMovieBookerButton.setFocusPainted(false);
        newMovieBookerButton.setBorder(null);
        newMovieBookerButton.setForeground(Color.white);
        newMovieBookerButton.setBackground(new Color(150,50,50));
        newMovieBookerButton.addActionListener(this);
        add(newMovieBookerButton);

        showStaticsButton =new JButton("Show Statistics");
        showStaticsButton.setBounds(350,270,195,30);
        showStaticsButton.setFont(font);
        showStaticsButton.setFocusPainted(false);
        showStaticsButton.setBorder(null);
        showStaticsButton.setForeground(Color.white);
        showStaticsButton.setBackground(new Color(50,50,50));
        showStaticsButton.addActionListener(this);
        add(showStaticsButton);

        rateMovieButton = new JButton("Rate Movies");
        rateMovieButton.setBounds(555, 270, 195, 30);
        rateMovieButton.setFont(font);
        rateMovieButton.setFocusPainted(false);
        rateMovieButton.setBorder(null);
        rateMovieButton.setForeground(Color.white);
        rateMovieButton.setBackground(new Color(50, 50, 50));
        rateMovieButton.addActionListener(this);
        add(rateMovieButton);


        //creating movies Table and adding it to panel
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
        moviesTable.setFont(new Font("Arial",Font.PLAIN,18));
        moviesTable.setForeground(Color.white);
        moviesTable.setBackground(new Color(150,50,50));
        moviesTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        moviesTable.setDefaultEditor(Object.class,null);
        moviesTable.addMouseListener(
                new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        Movie buttonMovie=null;
                        String movieName=(String)moviesModel.getValueAt(moviesTable.getSelectedRow(),0);
                        for(Movie movie:data.getAppMovies()){
                            if(movie.getMovieName().equals(movieName)){
                                buttonMovie=movie;
                            }
                        }
                        Day day=null;
                        for(Day day1:buttonMovie.getMovieShowTimes().keySet()){
                            day=day1;
                            break;
                        }
                        MovieTicketingSecondFrame mtsf=new MovieTicketingSecondFrame(data,user,buttonMovie,day,buttonMovie.getMovieHours(day).get(0));
                        userFrame.setEnabled(false);
                        new Thread(()->{
                            while(!Thread.currentThread().isInterrupted()){
                                if(!mtsf.isVisible()){
                                    try {
                                        ObjectOutputStream applicationDataSaver = new ObjectOutputStream(new FileOutputStream("data.txt"));
                                        applicationDataSaver.writeObject(data);

                                    } catch (IOException ex) {
                                        JOptionPane.showMessageDialog(null, "There is an IOEXCEPTION \n" + ex.getMessage());
                                    }
                                    userFrame.setEnabled(true);
                                    Thread.currentThread().interrupt();
                                }
                            }
                        }).start();
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {

                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {

                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {

                    }

                    @Override
                    public void mouseExited(MouseEvent e) {

                    }
                }
        );
        moviesTableScrollPane = new JScrollPane(moviesTable);
        moviesTableScrollPane.setBounds(350, 60, 400, 200);
        add(moviesTableScrollPane);

        //creating refresherThread to refresh labels data
        new Thread(()->{
            while(!Thread.currentThread().isInterrupted())
            userTotalTicketsLabel.setText("User Total Tickets : "+user.getUserTickets().size());
        }).start();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==addTicketButton){
            MovieTicketingFirstFrame mtff=  new MovieTicketingFirstFrame(this.user,this.data);
            userFrame.setEnabled(false);
            new Thread(() -> {
                while (!Thread.currentThread().isInterrupted()){
                    if(!mtff.isVisible()){
                        userFrame.setEnabled(true);
                        Thread.currentThread().interrupt();
                    }
                }
            }).start();
        }else if(e.getSource()==newMovieBookerButton){
            Movie buttonMovie=null;
            for(Movie movie:data.getAppMovies()){
                if(movie.getMovieName().equals(newMovieBookerButton.getText())){
                    buttonMovie=movie;
                }
            }
            Day day=null;
            for(Day day1:buttonMovie.getMovieShowTimes().keySet()){
                day=day1;
                break;
            }
            MovieTicketingSecondFrame mtsf=new MovieTicketingSecondFrame(this.data,this.user,buttonMovie,day,buttonMovie.getMovieHours(day).get(0));
            userFrame.setEnabled(false);
            new Thread(()->{
                while(!Thread.currentThread().isInterrupted()){
                    if(!mtsf.isVisible()){
                        try {
                            ObjectOutputStream applicationDataSaver = new ObjectOutputStream(new FileOutputStream("data.txt"));
                            applicationDataSaver.writeObject(data);

                        } catch (IOException ex) {
                            JOptionPane.showMessageDialog(null, "There is an IOEXCEPTION \n" + ex.getMessage());
                        }
                        userFrame.setEnabled(true);
                        Thread.currentThread().interrupt();
                    }
                }
            }).start();
        }else if(e.getSource()==removeTicketButton){
            RemoveUserTicketFrame rutf=new RemoveUserTicketFrame(this.user,this.data);
            userFrame.setEnabled(false);
            new Thread(()->{
                while(!Thread.currentThread().isInterrupted()){
                    if(!rutf.isVisible()){
                        try {
                            ObjectOutputStream applicationDataSaver = new ObjectOutputStream(new FileOutputStream("data.txt"));
                            applicationDataSaver.writeObject(data);

                        } catch (IOException ex) {
                            JOptionPane.showMessageDialog(null, "There is an IOEXCEPTION \n" + ex.getMessage());
                        }
                        userFrame.setEnabled(true);
                        Thread.currentThread().interrupt();
                    }
                }
            }).start();
        } else if (e.getSource()==showStaticsButton) {
            StatisticsFrame scs=new StatisticsFrame(this.data);
            userFrame.setEnabled(false);
            new Thread(()->{
                while(!Thread.currentThread().isInterrupted()){
                    if(!scs.isVisible()){
                        try {
                            ObjectOutputStream applicationDataSaver = new ObjectOutputStream(new FileOutputStream("data.txt"));
                            applicationDataSaver.writeObject(data);

                        } catch (IOException ex) {
                            JOptionPane.showMessageDialog(null, "There is an IOEXCEPTION \n" + ex.getMessage());
                        }
                        userFrame.setEnabled(true);
                        Thread.currentThread().interrupt();
                    }
                }
            }).start();

        }else if(e.getSource()==rateMovieButton){
            RateMoviesFrame rmf=new RateMoviesFrame(this.user,this.data);
            userFrame.setEnabled(false);
            new Thread(()->{
                while(!Thread.currentThread().isInterrupted()){
                    if(!rmf.isVisible()){
                        try {
                            ObjectOutputStream applicationDataSaver = new ObjectOutputStream(new FileOutputStream("data.txt"));
                            applicationDataSaver.writeObject(data);

                        } catch (IOException ex) {
                            JOptionPane.showMessageDialog(null, "There is an IOEXCEPTION \n" + ex.getMessage());
                        }
                        userFrame.setEnabled(true);
                        Thread.currentThread().interrupt();
                    }
                }
            }).start();
        }
    }
}
