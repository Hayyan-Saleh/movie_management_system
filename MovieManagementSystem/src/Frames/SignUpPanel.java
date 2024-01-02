package Frames;

import Crew.Manager;
import Crew.User;
import Files.Data;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SignUpPanel extends JPanel implements ActionListener {
    Data data;
    JRadioButton userRadioButton, managerRadioButton;
    JLabel hintTypeLabel, hintNameLabel, hintPasswordLabel, hintSingUpLabel;
    ButtonGroup radioButtonsGrouper;
    JTextField nameTextField;
    JPasswordField passwordField;
    JButton signUpButton, goBackButton;
    Color backgroundColor = new Color(140, 0, 40);
    Font labelsFont = new Font("Arial", Font.BOLD, 18);
    SignUpFrame signUpFrame ;

    ImageIcon imageIcon = new ImageIcon("SignBackground.png");
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(imageIcon.getImage(),0,0,this);
    }


    public SignUpPanel(Data data, SignUpFrame signUpFrame) {
        this.data = data;
        this.signUpFrame=signUpFrame;
        setBounds((Toolkit.getDefaultToolkit().getScreenSize().width - 750) / 2, (Toolkit.getDefaultToolkit().getScreenSize().height - 500) / 2, 750, 500);
        setLayout(null);

        //creating and adding the radio buttons to the frame along with the button group
        userRadioButton = new JRadioButton(" User ", true);
        managerRadioButton = new JRadioButton(" Manager ");
        radioButtonsGrouper = new ButtonGroup();

        managerRadioButton.setContentAreaFilled(false);
        userRadioButton.setContentAreaFilled(false);

        radioButtonsGrouper.add(userRadioButton);
        radioButtonsGrouper.add(managerRadioButton);

        userRadioButton.setForeground(Color.white);
        managerRadioButton.setForeground(Color.white);
        userRadioButton.setBackground(backgroundColor);
        managerRadioButton.setBackground(backgroundColor);
        userRadioButton.setFocusPainted(false);
        managerRadioButton.setFocusPainted(false);

        userRadioButton.setBounds(285,345,60,30);
        managerRadioButton.setBounds(365,345,100,30);


        add(userRadioButton);
        add(managerRadioButton);

        //creating and adding the labels to the frame
        hintTypeLabel = new JLabel("Which person are You ?");
        hintTypeLabel.setForeground(Color.white);
        hintTypeLabel.setFont(labelsFont);
        hintTypeLabel.setBounds(300, 10, 300, 50);

        //add(hintTypeLabel);

        //creating and adding the text field , password field and labels for sign in frame
        hintNameLabel = new JLabel("Name ");
        hintPasswordLabel = new JLabel("Password");

        hintNameLabel.setForeground(Color.white);
        hintPasswordLabel.setForeground(Color.white);
        hintNameLabel.setFont(labelsFont);
        hintPasswordLabel.setFont(labelsFont);

        hintNameLabel.setBounds(240,205,280,30);
        hintPasswordLabel.setBounds(240,275,280,30);

        nameTextField = new JTextField();
        passwordField = new JPasswordField();

        nameTextField.setFont(labelsFont);
        passwordField.setFont(labelsFont);
        nameTextField.setForeground(backgroundColor);
        passwordField.setForeground(backgroundColor);
        nameTextField.setBackground(Color.lightGray);
        passwordField.setBackground(Color.lightGray);
        nameTextField.setBounds(240,240,250,30);
        passwordField.setBounds(240,310,250,30);

        add(hintNameLabel);
        add(hintPasswordLabel);
        add(nameTextField);
        add(passwordField);

        //creating and adding the sign in & up buttons to the frame
        signUpButton = new JButton("Sign Up");
        goBackButton = new JButton("Go back to sign in window ");
        signUpButton.setFont(labelsFont);
        signUpButton.setForeground(Color.white);
        signUpButton.setBounds(290,380,150,30);
        signUpButton.setBackground(Color.gray);
        goBackButton.setForeground(Color.white);
        goBackButton.setBounds(240,420,250,30);
        goBackButton.setBackground(backgroundColor);
        goBackButton.setFocusPainted(false);
        goBackButton.setBorder(null);
        signUpButton.setBorder(null);
        signUpButton.setFocusPainted(false);
        goBackButton.setBorder(BorderFactory.createLineBorder(Color.red));
        goBackButton.setContentAreaFilled(false);
        signUpButton.setBorder(BorderFactory.createLineBorder(Color.red));
        signUpButton.setContentAreaFilled(false);

        signUpButton.addActionListener(this);
        goBackButton.addActionListener(this);

        add(goBackButton);
        add(signUpButton);


        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == signUpButton) {
            String nameString, passwordString;
            nameString = nameTextField.getText();
            passwordString = passwordField.getText();
            if (nameString == null || nameString.equals("") || passwordString == null || passwordString.equals("")) {
                JOptionPane.showMessageDialog(this, "Please make sure to put your name and your password");
            } else {
                if (userRadioButton.isSelected()) {
                    data.getAppUsers().add(new User(nameString, passwordString, new ArrayList<>()));
                    JOptionPane.showMessageDialog(this, "Hello User " + nameString + " \n  You are signed up !");
                } else {
                    if (data != null) {
                        if (data.getAppManager() == null) {
                            data.setAppManager(new Manager(nameString, passwordString));
                            JOptionPane.showMessageDialog(this, "Hello Manager " + nameString + " \n  You are signed up !");
                        } else {
                            JOptionPane.showMessageDialog(this, "there is already a manager for this application");
                        }
                    } else {
                        data = new Data();
                        data.setAppManager(new Manager(nameString, passwordString));
                        JOptionPane.showMessageDialog(this, "Hello Manager " + nameString + " \n  You are signed up !");

                    }
                }
            }
        } else {
            signUpFrame.dispose();
            new SignInFrame(data);
        }
    }
}