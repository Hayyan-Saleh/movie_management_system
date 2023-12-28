package Frames;

import javax.swing.*;

import Crew.User;
import Domain.Category;
import Domain.Day;
import Domain.Hour;
import Domain.Movie;
import Files.Data;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

public class MovieTicketingFirstFrame extends JFrame implements ActionListener {
    User user;
    Data data;
    Movie movie;
    Day day;
    Hour hour;
    JLabel movieCategoryHintLabel, movieHintLabel, dayHintLabel, hourHintLabel;
    JComboBox movieCategoryComboBox, moviesComboBox, daysComboBox, hoursComboBox;

    JButton selectChairsButton;
    Font font = new Font("Arial", Font.BOLD, 20);
    boolean isArranged = false;

    public MovieTicketingFirstFrame(User user, Data data) {
        this.data = data;
        this.user = user;
        //creating the frame

        setTitle("Movie Selection");
        getContentPane().setBackground(new Color(150, 10, 50));
        setBounds((Toolkit.getDefaultToolkit().getScreenSize().width - 300) / 2, (Toolkit.getDefaultToolkit().getScreenSize().height - 450) / 2, 300, 450);
        setResizable(false);
        setLayout(null);

        //creating frame labels and adding them to the JFrame
        movieCategoryHintLabel = new JLabel("Select The Movie Category ");
        movieCategoryHintLabel.setBounds(10, 10, 280, 30);
        movieCategoryHintLabel.setForeground(Color.white);
        movieCategoryHintLabel.setFont(font);
        add(movieCategoryHintLabel);

        movieHintLabel = new JLabel("Select The Movie");
        movieHintLabel.setBounds(10, 90, 280, 30);
        movieHintLabel.setForeground(Color.white);
        movieHintLabel.setFont(font);
        add(movieHintLabel);

        dayHintLabel = new JLabel("Select The day");
        dayHintLabel.setBounds(10, 170, 280, 30);
        dayHintLabel.setForeground(Color.white);
        dayHintLabel.setFont(font);
        add(dayHintLabel);

        hourHintLabel = new JLabel("Select The hour");
        hourHintLabel.setBounds(10, 250, 280, 30);
        hourHintLabel.setForeground(Color.white);
        hourHintLabel.setFont(font);
        add(hourHintLabel);
        //creating movie categories and movies combo box
        movieCategoryComboBox = new JComboBox(Category.values());
        movieCategoryComboBox.setBackground(Color.darkGray);
        movieCategoryComboBox.setForeground(Color.white);
        movieCategoryComboBox.setFont(new Font("Serif", Font.BOLD, 20));
        movieCategoryComboBox.setBounds(10, 50, 250, 30);
        movieCategoryComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                Category category = (Category) movieCategoryComboBox.getSelectedItem();
                moviesComboBox.removeAllItems();
                for (Movie movie : data.getAppMovies()) {
                    if (movie.getMovieCategory().equals(category)) moviesComboBox.addItem(movie);
                }
                moviesComboBox.setEnabled(true);
            }
        });
        movieCategoryComboBox.setEnabled(true);
        add(movieCategoryComboBox);


        moviesComboBox = new JComboBox(data.getAppMovies().toArray(new Movie[0]));
        moviesComboBox.setBackground(Color.darkGray);
        moviesComboBox.setForeground(Color.white);
        moviesComboBox.setFont(new Font("Serif", Font.BOLD, 20));
        moviesComboBox.setBounds(10, 130, 250, 30);
        moviesComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                movie = (Movie) moviesComboBox.getSelectedItem();
                daysComboBox.removeAllItems();
                for (Day day : movie != null ? movie.getMovieShowTimes().keySet() : new ArrayList<Day>()) {
                    daysComboBox.addItem(day);
                }
                daysComboBox.setEnabled(true);
            }
        });
        moviesComboBox.setEnabled(false);
        add(moviesComboBox);

        daysComboBox = new JComboBox(Day.values());
        daysComboBox.setBackground(Color.darkGray);
        daysComboBox.setForeground(Color.white);
        daysComboBox.setFont(new Font("Serif", Font.BOLD, 20));
        daysComboBox.setBounds(10, 210, 250, 30);
        daysComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                try {
                    day = (Day) daysComboBox.getSelectedItem();
                    hoursComboBox.removeAllItems();
                    for (Hour hour : (movie != null && day != null) ? movie.getMovieHours(day) : new ArrayList<Hour>())
                        hoursComboBox.addItem(hour);
                    hoursComboBox.setEnabled(true);
                    isArranged = false;
                } catch (Exception ex) {
                }
            }
        });
        daysComboBox.setEnabled(false);
        add(daysComboBox);

        hoursComboBox = new JComboBox(Hour.values());
        hoursComboBox.setBackground(Color.darkGray);
        hoursComboBox.setForeground(Color.white);
        hoursComboBox.setFont(new Font("Serif", Font.BOLD, 20));
        hoursComboBox.setBounds(10, 290, 250, 30);
        hoursComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                hour = (Hour) hoursComboBox.getSelectedItem();
            }
        });
        hoursComboBox.setEnabled(false);
        add(hoursComboBox);


        //creating the next frame button and adding it to the frame
        selectChairsButton = new JButton("Go To Select Seats");
        selectChairsButton.setBounds(10, 350, 250, 40);
        selectChairsButton.setBorder(BorderFactory.createLineBorder(Color.yellow));
        selectChairsButton.setFont(new Font("Serif", Font.BOLD, 22));
        selectChairsButton.setForeground(Color.white);
        selectChairsButton.setBackground(new Color(100, 0, 100));
        selectChairsButton.setFocusPainted(false);
        selectChairsButton.addActionListener(this);
        add(selectChairsButton);


        //creating thread for removing multiple same items in the hour combo box
        new Thread(() -> {
            Set<Hour> limiterSet = null;
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    if (hoursComboBox.isEnabled() && !isArranged) {
                        limiterSet = new LinkedHashSet<>();
                        for (int i = 0; i < hoursComboBox.getItemCount(); i++) {
                            limiterSet.add((Hour) hoursComboBox.getItemAt(i));
                        }
                        hoursComboBox.removeAllItems();
                        if (limiterSet.size() != 0)
                            for (Hour hour : limiterSet) {
                                hoursComboBox.addItem(hour);
                            }
                        isArranged = true;

                    }
                }
            } catch (Exception ex) {
            }
        }).start();

        setVisible(true);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == selectChairsButton) {
            if (day != null && hour != null && movie != null) {
                MovieTicketingSecondFrame mtsf = new MovieTicketingSecondFrame(data, user, movie, day, hour);
                this.setEnabled(false);
                new Thread(() -> {
                    while (!Thread.currentThread().isInterrupted()) {
                        if (!mtsf.isVisible()) {
                            try {
                                ObjectOutputStream applicationDataSaver = new ObjectOutputStream(new FileOutputStream("data.txt"));
                                applicationDataSaver.writeObject(data);

                            } catch (IOException ex) {
                                JOptionPane.showMessageDialog(null, "There is an IOEXCEPTION \n" + ex.getMessage());
                            }
                            this.dispose();
                            Thread.currentThread().interrupt();
                        }
                    }
                }).start();

            } else {
                JOptionPane.showMessageDialog(this, "Pleas Fill All the required inputs in order to continue !");
            }
        }
    }
}
