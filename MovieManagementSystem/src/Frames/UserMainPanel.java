package Frames;

import Crew.User;
import Domain.Day;
import Domain.Movie;
import Files.Data;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class UserMainPanel extends JPanel implements ActionListener {
    UserFrame userFrame;
    Data data;
    User user;
    JLabel userNameLabel, userTotalTicketsLabel;
    JButton addTicketButton,removeTicketButton,newMovieBookerButton;

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
        userNameLabel.setBounds(30,20,250,40);
        userNameLabel.setFont(font);
        userNameLabel.setForeground(Color.white);
        add(userNameLabel);

        userTotalTicketsLabel =new JLabel("User Total Tickets : "+user.getUserTickets().size());
        userTotalTicketsLabel.setBounds(30,60,250,40);
        userTotalTicketsLabel.setFont(font);
        userTotalTicketsLabel.setForeground(Color.white);
        add(userTotalTicketsLabel);

        JLabel newMovieHintLabel=new JLabel("Watch Newest Movie ");
        newMovieHintLabel.setBounds(35,230,300,30);
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
        addTicketButton.setBounds(30,130,200,30);
        addTicketButton.setFont(font);
        addTicketButton.setFocusPainted(false);
        addTicketButton.setBorder(null);
        addTicketButton.setForeground(Color.white);
        addTicketButton.setBackground(Color.darkGray);
        addTicketButton.addActionListener(this);
        add(addTicketButton);

        removeTicketButton =new JButton("Remove Ticket");
        removeTicketButton.setBounds(30,170,200,30);
        removeTicketButton.setFont(font);
        removeTicketButton.setFocusPainted(false);
        removeTicketButton.setBorder(null);
        removeTicketButton.setForeground(Color.white);
        removeTicketButton.setBackground(Color.darkGray);
        removeTicketButton.addActionListener(this);
        add(removeTicketButton);


        newMovieBookerButton =new JButton(data.getAppMovies().get(data.getAppMovies().size()-1).getMovieName());
        newMovieBookerButton.setBounds(30,270,200,30);
        newMovieBookerButton.setFont(new Font("Serif",Font.LAYOUT_LEFT_TO_RIGHT,20));
        newMovieBookerButton.setFocusPainted(false);
        newMovieBookerButton.setBorder(null);
        newMovieBookerButton.setForeground(Color.white);
        newMovieBookerButton.setBackground(new Color(150,50,50));
        newMovieBookerButton.addActionListener(this);
        add(newMovieBookerButton);

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
        moviesTable.setEnabled(false);
        moviesTableScrollPane = new JScrollPane(moviesTable);
        moviesTableScrollPane.setBounds(350, 60, 400, 200);
        add(moviesTableScrollPane);

        //creating refresherThread to refresh labels data
        new Thread(()->{
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
        }
    }
}
