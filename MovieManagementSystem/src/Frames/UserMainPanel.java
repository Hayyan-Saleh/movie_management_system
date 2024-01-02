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
    JLabel userNameLabel, userTotalTicketsLabel,discountDetailsLabel,announcementLabel,discount2;
    JButton addTicketButton,removeTicketButton,newMovieBookerButton, showStaticsButton,rateMovieButton,goBackToSignInWindowButton;

    DefaultTableModel moviesModel = new DefaultTableModel();
    JTable  moviesTable;
    JScrollPane moviesTableScrollPane;
    Font font =new Font("Arial",Font.ROMAN_BASELINE,24);
    Font font2 =new Font("Arial",Font.ROMAN_BASELINE,20);
    ImageIcon imageIcon=new ImageIcon("userBackground.png");
    boolean moviesExists=false;
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


        //setting the enable feature for the buttons
        setButtonsEnabled();


        //creating the labels and adding them to panel
        userNameLabel =new JLabel("Username : "+user.getUserName());
        userNameLabel.setBounds(10,20,350,40);
        userNameLabel.setFont(font);
        userNameLabel.setForeground(Color.white);
        add(userNameLabel);

        userTotalTicketsLabel =new JLabel("User Total Tickets : "+user.getUserTickets().size());
        userTotalTicketsLabel.setBounds(10,60,250,40);
        userTotalTicketsLabel.setFont(font);
        userTotalTicketsLabel.setForeground(Color.white);
        add(userTotalTicketsLabel);

        announcementLabel = new JLabel("Announcements:");
        announcementLabel.setBounds(10,100,200,40);
        announcementLabel.setFont(new Font("Arial",Font.ITALIC,24));
        announcementLabel.setForeground(Color.ORANGE);
        add(announcementLabel);

        discountDetailsLabel =new JLabel(data.getAppManager().getManagerDiscountDay()==null ? "No Day Discounts":"Day Discount :At "+data.getAppManager().getManagerDiscountDay()+" for "+data.getAppManager().getDiscountAmount()+"%");
        discountDetailsLabel.setBounds(10,165,350,20);
        discountDetailsLabel.setFont(new Font("Arial",Font.ROMAN_BASELINE,17));
        discountDetailsLabel.setForeground(Color.ORANGE);
        add(discountDetailsLabel);

        discount2 = new JLabel("10% Discount When Buying 5 Tickets Or More");
        discount2.setBounds(10,140,350,20);
        discount2.setFont(new Font("Arial",Font.ROMAN_BASELINE,16));
        discount2.setForeground(Color.ORANGE);
        add(discount2);


        JLabel newMovieHintLabel=new JLabel("Watch Newest Movie ");
        newMovieHintLabel.setBounds(55,230,300,30);
        newMovieHintLabel.setFont(new Font("Arial",Font.ITALIC,20));
        newMovieHintLabel.setForeground(Color.orange);
        add(newMovieHintLabel);

        JLabel moviesHintLabel=new JLabel(data.getCinema().getCinemaName()+" Cinema Movies ");
        moviesHintLabel.setBounds(400,20,350,30);
        moviesHintLabel.setFont(new Font("Arial",Font.LAYOUT_LEFT_TO_RIGHT,20));
        moviesHintLabel.setForeground(Color.white);
        add(moviesHintLabel);



        //creating and adding the buttons to the panel
        addTicketButton =new JButton("Add Ticket");
        addTicketButton.setBounds(175,190,150,30);
        addTicketButton.setFont(font2);
        addTicketButton.setFocusPainted(false);
        addTicketButton.setBorder(null);
        addTicketButton.setForeground(Color.white);
        addTicketButton.setBackground(new Color(100, 0, 100));
        addTicketButton.setBorder(BorderFactory.createLineBorder(Color.yellow));
        addTicketButton.addActionListener(this);
        addTicketButton.setEnabled(moviesExists);
        add(addTicketButton);

        removeTicketButton =new JButton("Remove Ticket");
        removeTicketButton.setBounds(10,190,150,30);
        removeTicketButton.setFont(font2);
        removeTicketButton.setFocusPainted(false);
        removeTicketButton.setBorder(null);
        removeTicketButton.setEnabled(moviesExists);
        removeTicketButton.setForeground(Color.white);
        removeTicketButton.setBackground(new Color(100, 0, 100));
        removeTicketButton.setBorder(BorderFactory.createLineBorder(Color.yellow));
        removeTicketButton.addActionListener(this);
        add(removeTicketButton);


        newMovieBookerButton =new JButton();
        String newestMovieString="";
        if(data.getAppMovies()!=null){
            if(!data.getAppMovies().isEmpty())
                newestMovieString = data.getAppMovies().get(data.getAppMovies().size() - 1).getMovieName();
            else
                newestMovieString="No Movies In Cinema";
        }
        newMovieBookerButton.setText(newestMovieString);
        newMovieBookerButton.setBounds(50,270,200,30);
        newMovieBookerButton.setFont(new Font("Serif",Font.LAYOUT_LEFT_TO_RIGHT,20));
        newMovieBookerButton.setFocusPainted(false);
        newMovieBookerButton.setBorder(null);
        newMovieBookerButton.setBorder(BorderFactory.createLineBorder(Color.yellow));
        newMovieBookerButton.setBackground(new Color(150, 50, 50));
        newMovieBookerButton.setForeground(Color.white);
        newMovieBookerButton.setEnabled(moviesExists);
        newMovieBookerButton.addActionListener(this);
        add(newMovieBookerButton);

        showStaticsButton =new JButton("Show Statistics");
        showStaticsButton.setBounds(350,270,195,30);
        showStaticsButton.setFont(font);
        showStaticsButton.setFocusPainted(false);
        showStaticsButton.setBorder(null);
        showStaticsButton.setForeground(Color.white);
        showStaticsButton.setBackground(new Color(100, 0, 100));
        showStaticsButton.setBorder(BorderFactory.createLineBorder(Color.yellow));
        showStaticsButton.setEnabled(moviesExists);
        showStaticsButton.addActionListener(this);
        add(showStaticsButton);

        rateMovieButton = new JButton("Rate Movies");
        rateMovieButton.setBounds(555, 270, 195, 30);
        rateMovieButton.setFont(font);
        rateMovieButton.setFocusPainted(false);
        rateMovieButton.setBorder(null);
        rateMovieButton.setForeground(Color.white);
        rateMovieButton.setBackground(new Color(100, 0, 100));
        rateMovieButton.setBorder(BorderFactory.createLineBorder(Color.yellow));
        rateMovieButton.setEnabled(moviesExists);
        rateMovieButton.addActionListener(this);
        add(rateMovieButton);

        goBackToSignInWindowButton = new JButton("Back To Sign In Window");
        goBackToSignInWindowButton.setBounds(5,520,200,40);
        goBackToSignInWindowButton.setFont(new Font("Serif", Font.BOLD, 16));
        goBackToSignInWindowButton.setFocusPainted(false);
        goBackToSignInWindowButton.setBorder(null);
        goBackToSignInWindowButton.setForeground(Color.white);
        goBackToSignInWindowButton.setBackground(new Color(100, 0, 100));
        goBackToSignInWindowButton.setBorder(BorderFactory.createLineBorder(Color.yellow));
        goBackToSignInWindowButton.addActionListener(this);
        add(goBackToSignInWindowButton);


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
        }else if (e.getSource() == goBackToSignInWindowButton) {
            try {
                ObjectOutputStream applicationDataSaver = new ObjectOutputStream(new FileOutputStream("data.txt"));
                applicationDataSaver.writeObject(data);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "There is an IOEXCEPTION \n" + ex.getMessage());
            }
            new SignInFrame(data);
            userFrame.dispose();

        }
    }

    public void setButtonsEnabled(){
        if(data.getAppMovies()!=null)
            if(!data.getAppMovies().isEmpty())
                moviesExists=true;
    }

}
