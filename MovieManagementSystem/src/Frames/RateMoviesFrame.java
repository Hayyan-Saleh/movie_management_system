
package Frames;

import Crew.User;
import Domain.Movie;

import Files.Data;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class RateMoviesFrame extends JFrame {


    private Data data;
    private User user;

    JLabel movieHintLabel,awful,ok,good,excellent,awesome,ratingsHintLabel;
    JComboBox moviesComboBox;
    Movie movie;
    JSlider ratingSlider;
    JTextArea comment,ratingsArea;
    JScrollPane commentScroll,ratingsAreaScroller;
    JButton addRating;
    Font font = new Font("Arial", Font.BOLD, 20);
    Font commentFont=new Font("Arial",Font.ITALIC,16);
    public RateMoviesFrame(User user, Data data) {
        this.user = user;
        this.data = data;

        setTitle("Rate Movies");
        getContentPane().setBackground(new Color(160, 0, 50));
        setBounds((Toolkit.getDefaultToolkit().getScreenSize().width - 500) / 2, (Toolkit.getDefaultToolkit().getScreenSize().height - 400) / 2, 500, 400);
        setResizable(false);
        setLayout(null);

        movieHintLabel = new JLabel("Select The Movie");
        movieHintLabel.setBounds(10, 10, 180, 30);
        movieHintLabel.setForeground(Color.white);
        movieHintLabel.setFont(font);

        ratingsHintLabel=new JLabel("Users ratings for selected Movie");
        ratingsHintLabel.setBounds(20,165,440,30);
        ratingsHintLabel.setForeground(Color.white);
        ratingsHintLabel.setFont(new Font("Arial",Font.PLAIN,16));

        moviesComboBox = new JComboBox(data.getAppMovies().toArray(new Movie[0]));
        moviesComboBox.setBackground(Color.darkGray);
        moviesComboBox.setForeground(Color.white);
        moviesComboBox.setFont(new Font("Serif", Font.BOLD, 20));
        moviesComboBox.setBounds(10, 40, 180, 30);
        ratingSlider = new JSlider(JSlider.VERTICAL,0,4,0);

        ratingSlider.setBounds(200,10,20,135);
        ratingSlider.setPaintTicks(false);
        ratingSlider.setBackground(new Color(160, 0, 50));
        ratingSlider.setPaintTrack(false);
        ratingSlider.setPaintLabels(true);

        awesome = new JLabel("AWESOME");
        awesome.setBounds(220, 8, 70, 20);
        awesome.setForeground(Color.white);
        awesome.setFont(new Font("Arial",Font.PLAIN,13));

        excellent = new JLabel("EXCELLENT");
        excellent.setBounds(220, 38, 80, 20);
        excellent.setForeground(Color.white);
        excellent.setFont(new Font("Arial",Font.PLAIN,13));

        good = new JLabel("GOOD");
        good.setBounds(220, 68, 80, 20);
        good.setForeground(Color.white);
        good.setFont(new Font("Arial",Font.PLAIN,13));

        ok = new JLabel("OK");
        ok.setBounds(220, 98, 80, 20);
        ok.setForeground(Color.white);
        ok.setFont(new Font("Arial",Font.PLAIN,13));

        awful = new JLabel("AWFUL");
        awful.setBounds(220, 128, 80, 20);
        awful.setForeground(Color.white);
        awful.setFont(new Font("Arial",Font.PLAIN,13));

        comment = new JTextArea();
        comment.setLineWrap(true);
        comment.setWrapStyleWord(true);
        comment.setForeground(Color.black);
        comment.setFont(commentFont);
        comment.setEnabled(false);
        commentScroll = new JScrollPane(comment);
        commentScroll.setBounds(300,10,180,100);

        addRating = new JButton("Select a Movie");
        addRating.setBounds(325,115,120,30);
        addRating.setFont(new Font("Arial", Font.BOLD, 15));
        addRating.setFocusPainted(false);
        addRating.setBorder(null);
        addRating.setForeground(Color.white);
        addRating.setBackground(new Color(50,50,50));
        addRating.setEnabled(false);

        ratingsArea = new JTextArea();
        ratingsArea.setLineWrap(true);
        ratingsArea.setWrapStyleWord(true);
        ratingsArea.setForeground(Color.black);
        ratingsArea.setFont(commentFont);
        ratingsArea.setEnabled(false);
        ratingsArea.setText("Choose A Movie To See It's Ratings");
        ratingsAreaScroller = new JScrollPane(ratingsArea);
        ratingsAreaScroller.setBounds(20,200,440,150);



        moviesComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                movie = (Movie) moviesComboBox.getSelectedItem();
                ratingsHintLabel.setText("Users ratings for "+movie.getMovieName());
                addRating.setEnabled(true);
                comment.setEnabled(true);
                addRating.setFont(new Font("Arial", Font.BOLD, 17));
                addRating.setText("Add Rating");
                addRating.setForeground(new Color(208, 48, 20));
                String MovieRatingsString ="";
                ratingsArea.setText("No Ratings");

                for(int i=0;i<data.appMovies.size();i++){
                    if(movie==data.appMovies.get(i)){
                        if(!movie.getMovieRating().isEmpty()){
                        for(int j=0;j<movie.getMovieRating().size();j++){
                            MovieRatingsString+=movie.getMovieRating().get(j)+"\n";
                        }
                        ratingsArea.setForeground(Color.black);
                        ratingsArea.setText(MovieRatingsString);}
                        break;
                    }
                }

            }
        });
        addRating.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                int r = ratingSlider.getValue();
                String rate="";
                if(r==0)
                    rate="AWFUL\n";
                if(r==1)
                    rate="OK\n";
                if(r==2)
                    rate="GOOD\n";
                if(r==3)
                    rate="EXCELLENT\n";
                if(r==4)
                    rate="AWESOME\n";
                String userName=user.getUserName()+" :\n";
                String Comment =userName+ rate+comment.getText()+"\n____________________________";
                movie.addMovieRating(Comment);
                ratingSlider.setValue(0);
                comment.setText("");

                    String tempRatings="";
                    for(int j=0;j<movie.getMovieRating().size();j++){
                        tempRatings+=movie.getMovieRating().get(j)+"\n";
                    }
                ratingsArea.setText(tempRatings);

                    JOptionPane.showMessageDialog(null, "Movie Has Been Rated Successfully", "Plain Message", JOptionPane.PLAIN_MESSAGE);
                }catch(NullPointerException ex){
                    JOptionPane.showMessageDialog(null, "Choose the Movie You Want To Rate Again.", "Information Message", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });





        add(awesome);
        add(excellent);
        add(good);
        add(ok);
        add(awful);
        add(commentScroll);
        add(ratingsAreaScroller);
        add(addRating);
        add(movieHintLabel);
        add(ratingsHintLabel);
        add(moviesComboBox);
        add(ratingSlider);
        setVisible(true);
    }
}
