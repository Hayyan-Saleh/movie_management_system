package Frames;

import Domain.*;
import Files.Data;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class AddMovieFrame extends JFrame implements ActionListener {
    Data data;
    JComboBox hallsComboBox, movieCategoryComboBox, daysComboBox, hoursComboBox;
    Hall dialogHall;
    JTextField movieNameTextField,moviePriceTextField;
    JLabel hallHintLabel, movieNameHintLabel, movieCategoryHintLabel, availableDaysHintLabel, availableHoursHintLabel,priceHintLabel;
    String frameMovieName;
    Category frameMovieCategory;
    Day frameMovieDay;
    Hour frameMovieHour;
    Map<Day,ArrayList<Hour>> frameMovieShowTimes;
    ArrayList<Hour> frameMovieHourShowTimes;
    ArrayList<Day> frameMovieShowDays;

    boolean hourComboBoxInitialized=false;
    String frameMoviePrice;

    JButton addMovieButton;
    public AddMovieFrame(Data data) {
        this.data = data;
        setTitle("Add Movie");
        getContentPane().setBackground(new Color(180, 20, 20));
        setBounds((Toolkit.getDefaultToolkit().getScreenSize().width - 500) / 2, (Toolkit.getDefaultToolkit().getScreenSize().height - 400) / 2, 500, 400);
        setResizable(false);
        setLayout(null);

        //creating labels
        hallHintLabel = new JLabel("Select Hall");
        hallHintLabel.setBounds(10, 10, 200, 30);
        hallHintLabel.setForeground(Color.white);
        hallHintLabel.setFont(new Font("Serif", Font.BOLD, 18));
        add(hallHintLabel);

        movieNameHintLabel = new JLabel("Enter the Movie Name");
        movieNameHintLabel.setBounds(10, 90, 200, 30);
        movieNameHintLabel.setForeground(Color.white);
        movieNameHintLabel.setFont(new Font("Serif", Font.BOLD, 18));
        add(movieNameHintLabel);

        movieCategoryHintLabel = new JLabel("Select the Movie Category");
        movieCategoryHintLabel.setBounds(10, 170, 210, 30);
        movieCategoryHintLabel.setForeground(Color.white);
        movieCategoryHintLabel.setFont(new Font("Serif", Font.BOLD, 18));
        add(movieCategoryHintLabel);

        availableDaysHintLabel = new JLabel("Select the Movie show Days");
        availableDaysHintLabel.setBounds(10, 250, 230, 30);
        availableDaysHintLabel.setForeground(Color.white);
        availableDaysHintLabel.setFont(new Font("Serif", Font.BOLD, 18));
        add(availableDaysHintLabel);

        availableHoursHintLabel = new JLabel("Select the Movie show Hours");
        availableHoursHintLabel.setBounds(250, 10, 250, 30);
        availableHoursHintLabel.setForeground(Color.white);
        availableHoursHintLabel.setFont(new Font("Serif", Font.BOLD, 18));
        add(availableHoursHintLabel);

        priceHintLabel = new JLabel("Movie Price");
        priceHintLabel.setBounds(250, 90, 150, 30);
        priceHintLabel.setForeground(Color.white);
        priceHintLabel.setFont(new Font("Serif", Font.BOLD, 18));
        add(priceHintLabel);

        //creating halls combo box and adding it to the dialog

        hallsComboBox = new JComboBox(data.getCinema().getHalls().toArray());
        hallsComboBox.setBackground(Color.darkGray);
        hallsComboBox.setForeground(Color.white);
        hallsComboBox.setFont(new Font("Serif", Font.BOLD, 20));
        hallsComboBox.setBounds(10, 50, 150, 30);
        hallsComboBox.addActionListener(this);
        add(hallsComboBox);

        movieCategoryComboBox = new JComboBox(Category.values());
        movieCategoryComboBox.setBackground(Color.darkGray);
        movieCategoryComboBox.setForeground(Color.white);
        movieCategoryComboBox.setFont(new Font("Serif", Font.BOLD, 20));
        movieCategoryComboBox.setBounds(10, 210, 150, 30);
        movieCategoryComboBox.addActionListener(this);
        add(movieCategoryComboBox);

        daysComboBox = new JComboBox(Day.values());
        daysComboBox.setBackground(Color.darkGray);
        daysComboBox.setForeground(Color.white);
        daysComboBox.setFont(new Font("Serif", Font.BOLD, 20));
        daysComboBox.setBounds(10, 290, 150, 30);
        daysComboBox.addActionListener(this);
        daysComboBox.setEnabled(false);
        add(daysComboBox);

        hoursComboBox = new JComboBox(Hour.values());
        hoursComboBox.setBackground(Color.darkGray);
        hoursComboBox.setForeground(Color.white);
        hoursComboBox.setFont(new Font("Serif", Font.BOLD, 20));
        hoursComboBox.setBounds(250, 50, 150, 30);
        hoursComboBox.addActionListener(this);
        hoursComboBox.setEnabled(false);
        add(hoursComboBox);

        //creating and adding movie name text field
        movieNameTextField = new JTextField();
        movieNameTextField.setFont(new Font("Serif", Font.BOLD, 20));
        movieNameTextField.setBounds(10, 130, 200, 30);
        movieNameTextField.setForeground(Color.white);
        movieNameTextField.setBackground(Color.darkGray);
        movieNameTextField.addActionListener(this);
        add(movieNameTextField);

        moviePriceTextField = new JTextField();
        moviePriceTextField.setFont(new Font("Serif", Font.BOLD, 20));
        moviePriceTextField.setBounds(250, 130, 100, 30);
        moviePriceTextField.setForeground(Color.white);
        moviePriceTextField.setBackground(Color.darkGray);
        moviePriceTextField.addActionListener(this);
        add(moviePriceTextField);

        //creating add movie button and adding it to the frame
        addMovieButton=new JButton("Add Movie to Cinema");
        addMovieButton.setBounds(250,285,220,40);
        addMovieButton.setBorder(BorderFactory.createLineBorder(Color.red));
        addMovieButton.setFont(new Font("Serif",Font.BOLD,20));
        addMovieButton.setForeground(Color.white);
        addMovieButton.setBackground(new Color(0, 190, 100));
        addMovieButton.setFocusPainted(false);
        addMovieButton.addActionListener(this);
        setEnabled(true);
        add(addMovieButton);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == hallsComboBox) {
            dialogHall = (Hall) hallsComboBox.getSelectedItem();
            frameMovieShowTimes=new HashMap<>();
            frameMovieShowDays=new ArrayList<>();
            if(frameMovieDay!=null){
                refreshHoursComboBox();
            }
            daysComboBox.setEnabled(true);
        } else if (e.getSource() == movieNameTextField) {
            frameMovieName = movieNameTextField.getText();
        } else if (e.getSource()==movieCategoryComboBox) {
            frameMovieCategory=(Category)movieCategoryComboBox.getSelectedItem();
        }else if(e.getSource()==daysComboBox){
            if(frameMovieHourShowTimes!=null){
                frameMovieShowTimes.put(frameMovieDay,frameMovieHourShowTimes);
            }
            frameMovieDay=(Day)daysComboBox.getSelectedItem();
                hoursComboBox.removeAllItems();
                hourComboBoxInitialized=false;
                frameMovieHourShowTimes=new ArrayList<>();
            for(Hour hour:dialogHall.getAvailableHours(frameMovieDay))
                hoursComboBox.addItem(hour);
            hourComboBoxInitialized=true;
            hoursComboBox.setEnabled(true);
        } else if (e.getSource()==hoursComboBox){
                frameMovieHour = (Hour) hoursComboBox.getSelectedItem();
                if(hourComboBoxInitialized)
                hoursComboBox.removeItem(frameMovieHour);
                if (frameMovieHourShowTimes != null)
                    frameMovieHourShowTimes.add(frameMovieHour);
            if(frameMovieHourShowTimes!=null){
                frameMovieHourShowTimes.remove(null);
            }
        }else if(e.getSource()==moviePriceTextField){
            frameMoviePrice=movieNameTextField.getText();
        }else if(e.getSource()==addMovieButton){
            frameMoviePrice=moviePriceTextField.getText();
            frameMovieName=movieNameTextField.getText();
            if(frameMovieName.equals("")||frameMovieCategory==null||frameMoviePrice.equals("")||frameMovieHourShowTimes==null){
                JOptionPane.showMessageDialog(this,"You need to enter all the data in order to add movie !","Error",JOptionPane.ERROR_MESSAGE);
            }else {
                frameMovieShowTimes.put(frameMovieDay,frameMovieHourShowTimes);
                Set<Day> set=frameMovieShowTimes.keySet();
                for (Day day :
                        set) {
                    frameMovieShowDays.add(day);
                    for (Hour hour:
                         frameMovieShowTimes.get(day)) {
                        dialogHall.setTimeTaken(day,hour);
                    }
                }
                if(data.getAppMovies()==null){
                    data.setAppMovies(new ArrayList<>());
                }else{
                    if(!data.getAppMovies().isEmpty())
                    Movie.setGlobalID(data.getAppMovies().get(data.getAppMovies().size()-1).getID()+1);
                }
                Movie movie=new Movie(frameMovieName,frameMovieCategory,frameMovieShowTimes,Double.parseDouble(frameMoviePrice));
                dialogHall.getMovies().add(movie);

                data.getAppMovies().add(movie);
                for (Day day :
                        set) {
                    ArrayList<Integer> hourIndexes=new ArrayList<>();
                    for (Hour hour:
                            frameMovieShowTimes.get(day)) {
                            hourIndexes.add(hour.ordinal());
                    }
                    movie.insertInHallMap(day.ordinal(),hourIndexes,dialogHall);
                }
                JOptionPane.showMessageDialog(this,"Successfully added movie "+movie.getMovieName()+" to the "+dialogHall.getName());
                this.dispose();
            }
        }
    }
    public void refreshHoursComboBox(){
        frameMovieDay=(Day)daysComboBox.getSelectedItem();
        dialogHall.getAvailableHours(frameMovieDay);
        hoursComboBox.removeAllItems();
        for(Hour hour:dialogHall.getAvailableHours(frameMovieDay))
            hoursComboBox.addItem(hour);
    }
}
