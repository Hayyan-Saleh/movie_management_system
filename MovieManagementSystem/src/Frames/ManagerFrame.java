package Frames;

import Domain.Hall;
import Domain.Movie;
import Files.Data;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ManagerFrame extends JFrame implements WindowListener, ActionListener {
    Data data;
    JPanel mainPanel, sidePanel;
    JButton homeButton, addHallButton, addMovieButton, removeAllMoviesRatingsButton,goBackToSignInWindowButton, aboutButton;
    Font font = new Font("Serif", Font.BOLD, 18);

    public ManagerFrame(Data data) {
        this.data = data;

        //creating the JFrame with its properties
        setTitle("Manager " + data.getAppManager().getManagerName());
        setBounds((Toolkit.getDefaultToolkit().getScreenSize().width - 1015) / 2, (Toolkit.getDefaultToolkit().getScreenSize().height - 600) / 2, 1015, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        //creating and adding the panels to the frame
        sidePanel = new JPanel();
        sidePanel.setLayout(new FlowLayout());
        sidePanel.setBounds(0, 0, 300, 600);
        sidePanel.setBackground(new Color(160, 0, 20));
        add(sidePanel);

        mainPanel = new ManagerMainPanel(data);
        add(mainPanel);

        //initialize the buttons and adding them to side panel
        homeButton = new JButton("Home");
        homeButton.setFocusPainted(false);
        homeButton.setBorderPainted(false);
        homeButton.setFont(font);
        homeButton.setForeground(Color.white);
        homeButton.setBorder(null);
        homeButton.setBackground(Color.darkGray);
        homeButton.setPreferredSize(new Dimension(300, 40));
        sidePanel.add(homeButton);

        addHallButton = new JButton("Add Hall");
        addHallButton.setFocusPainted(false);
        addHallButton.setBorderPainted(false);
        addHallButton.setFont(font);
        addHallButton.setForeground(Color.white);
        addHallButton.setBackground(new Color(0, 180, 100));
        addHallButton.setPreferredSize(new Dimension(300, 40));
        addHallButton.addActionListener(this);
        sidePanel.add(addHallButton);

        addMovieButton = new JButton("Add Movie");
        addMovieButton.setFocusPainted(false);
        addMovieButton.setBorderPainted(false);
        addMovieButton.setFont(font);
        addMovieButton.setForeground(Color.white);
        addMovieButton.setBackground(new Color(0, 180, 100));
        addMovieButton.setPreferredSize(new Dimension(300, 40));
        addMovieButton.addActionListener(this);
        sidePanel.add(addMovieButton);

        removeAllMoviesRatingsButton = new JButton("Remove All Movies Ratings");
        removeAllMoviesRatingsButton.setFocusPainted(false);
        removeAllMoviesRatingsButton.setBorderPainted(false);
        removeAllMoviesRatingsButton.setFont(new Font("Serif", Font.BOLD, 18));
        removeAllMoviesRatingsButton.setForeground(Color.white);
        removeAllMoviesRatingsButton.setBackground(new Color(0, 180, 100));
        removeAllMoviesRatingsButton.setPreferredSize(new Dimension(300, 40));
        removeAllMoviesRatingsButton.addActionListener(this);
        sidePanel.add(removeAllMoviesRatingsButton);

        goBackToSignInWindowButton = new JButton("Go Back To Sign in Window");
        goBackToSignInWindowButton.setFocusPainted(false);
        goBackToSignInWindowButton.setBorderPainted(false);
        goBackToSignInWindowButton.setFont(new Font("Serif", Font.BOLD, 16));
        goBackToSignInWindowButton.setForeground(Color.white);
        goBackToSignInWindowButton.setBackground(new Color(0, 180, 100));
        goBackToSignInWindowButton.setPreferredSize(new Dimension(300, 40));
        goBackToSignInWindowButton.addActionListener(this);
        sidePanel.add(goBackToSignInWindowButton);

        aboutButton = new JButton("About");
        aboutButton.setFocusPainted(false);
        aboutButton.setBorderPainted(false);
        aboutButton.setFont(font);
        aboutButton.setForeground(Color.white);
        aboutButton.setBackground(new Color(0, 180, 100));
        aboutButton.setPreferredSize(new Dimension(300, 40));
        aboutButton.addActionListener(this);
        sidePanel.add(aboutButton);

        addWindowListener(this);
        setVisible(true);
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        try {
            ObjectOutputStream applicationDataSaver = new ObjectOutputStream(new FileOutputStream("data.txt"));
            applicationDataSaver.writeObject(data);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "There is an IOEXCEPTION \n" + ex.getMessage());
        }
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addHallButton) {
            String hallName = "", totalChairs = "";
            boolean flag = true;
            while (hallName.equals("") && totalChairs.equals("")) {
                hallName = JOptionPane.showInputDialog(this, "Please Enter The Hall Name :", "Hall Name");
                if (hallName == null) {
                    flag = false;
                    break;
                }
                totalChairs = JOptionPane.showInputDialog(this, "Please Enter The Hall Total chairs Number :", "Hall total chairs number");
                if (totalChairs == null) {
                    flag = false;
                    break;
                }
                if (hallName.equals("") && totalChairs.equals("")) {
                    JOptionPane.showMessageDialog(this, "You must fill all the required inputs !", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            if (flag) {
                Hall hall = new Hall(hallName, Integer.parseInt(totalChairs), new ArrayList<>());
                if (data.getCinema().getHalls() == null) {
                    data.getCinema().setHalls(new ArrayList<>());
                }
                data.getCinema().getHalls().add(hall);
                JOptionPane.showMessageDialog(this, "Succeeded !\nAdded Hall " + hallName + " to the " + data.getCinema().getCinemaName());
            }
        } else if (e.getSource() == addMovieButton) {
            if (data.getCinema().getHalls() == null || data.getCinema().getHalls().size() == 0) {
                JOptionPane.showMessageDialog(this, "You must first add Halls to your Cinema then add movies to a Hall !", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                AddMovieFrame amf = new AddMovieFrame(data);
                this.setEnabled(false);
                new Thread(() -> {
                    while (!Thread.currentThread().isInterrupted()) {
                        if (!amf.isVisible()) {
                            this.setEnabled(true);
                            Thread.currentThread().interrupt();
                        }
                    }
                }).start();

            }
        } else if (e.getSource() == removeAllMoviesRatingsButton) {
            int res =JOptionPane.showConfirmDialog(this,"Are you sure you want to DELETE All the Movies RATINGS ?");
            if (res == JOptionPane.YES_OPTION) {
                    for(Movie movie:data.getAppMovies()){
                        movie.clearMovieRatings();
                    }
                    JOptionPane.showMessageDialog(this,"Successfully Removed All Ratings ");
            }
        } else if (e.getSource() == goBackToSignInWindowButton) {
            try {
                ObjectOutputStream applicationDataSaver = new ObjectOutputStream(new FileOutputStream("data.txt"));
                applicationDataSaver.writeObject(data);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "There is an IOEXCEPTION \n" + ex.getMessage());
            }
            new SignInFrame(data);
            this.dispose();

        } else if (e.getSource() == aboutButton) {
            JOptionPane.showMessageDialog(this, "This work is presented by this ITE Damascus University Student\n\n                                        Hayyan Saleh\n\n");
        }
    }
}
