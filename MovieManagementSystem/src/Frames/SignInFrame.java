package Frames;


import Crew.User;
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

public class SignInFrame extends JFrame implements ActionListener, WindowListener {
    Data data;
    JRadioButton userRadioButton,managerRadioButton;
    JLabel hintTypeLabel,hintNameLabel,hintPasswordLabel,hintSingUpLabel;
    ButtonGroup radioButtonsGrouper;
    JTextField nameTextField;
    JPasswordField passwordField;
    JButton signInButton, signUpButton;
    Color backgroundColor=new Color(140,0,40);
    Font labelsFont=new Font("Arial",Font.BOLD,18);
    public SignInFrame(Data data){
        this.data=data;
        setTitle("Movie Cinema");
        setBounds((Toolkit.getDefaultToolkit().getScreenSize().width-600)/2,(Toolkit.getDefaultToolkit().getScreenSize().height-400)/2,600,400);
        getContentPane().setBackground(backgroundColor);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //creating and adding the radio buttons to the frame along with the button group
        userRadioButton=new JRadioButton(" User ",true);
        managerRadioButton=new JRadioButton(" Manager ");
        radioButtonsGrouper=new ButtonGroup();

        radioButtonsGrouper.add(userRadioButton);
        radioButtonsGrouper.add(managerRadioButton);

        userRadioButton.setForeground(Color.white);
        managerRadioButton.setForeground(Color.white);
        userRadioButton.setBackground(backgroundColor);
        managerRadioButton.setBackground(backgroundColor);
        userRadioButton.setFocusPainted(false);
        managerRadioButton.setFocusPainted(false);

        userRadioButton.setBounds(300,60,300,30);
        managerRadioButton.setBounds(300,95,300,30);


        add(userRadioButton);
        add(managerRadioButton);

        //creating and adding the labels to the frame
        hintTypeLabel=new JLabel("Which person are You ?");
        hintTypeLabel.setForeground(Color.white);
        hintTypeLabel.setFont(labelsFont);
        hintTypeLabel.setBounds(300,10,300,50);

        add(hintTypeLabel);

        //creating and adding the text field , password field and labels for sign in frame
        hintNameLabel=new JLabel("Name ");
        hintPasswordLabel=new JLabel("Password");

        hintNameLabel.setForeground(Color.white);
        hintPasswordLabel.setForeground(Color.white);
        hintNameLabel.setFont(labelsFont);
        hintPasswordLabel.setFont(labelsFont);

        hintNameLabel.setBounds(10,10,280,30);
        hintPasswordLabel.setBounds(10,100,280,30);

        nameTextField=new JTextField();
        passwordField=new JPasswordField();

        nameTextField.setFont(labelsFont);
        passwordField.setFont(labelsFont);
        nameTextField.setForeground(backgroundColor);
        passwordField.setForeground(backgroundColor);
        nameTextField.setBackground(Color.lightGray);
        passwordField.setBackground(Color.lightGray);
        nameTextField.setBounds(10,45,280,30);
        passwordField.setBounds(10,135,280,30);

        add(hintNameLabel);
        add(hintPasswordLabel);
        add(nameTextField);
        add(passwordField);

        //creating and adding the sign in & up buttons to the frame
        signInButton =new JButton("Sign in");
        signUpButton =new JButton("Don't have an account ?   Sign up !");
        signInButton.setFont(labelsFont);
        signInButton.setForeground(Color.white);
        signInButton.setBounds(220,220,150,30);
        signInButton.setBackground(Color.gray);
        signUpButton.setForeground(Color.white);
        signUpButton.setBounds(170,300,250,30);
        signUpButton.setBackground(backgroundColor);
        signUpButton.setFocusPainted(false);
        signUpButton.setBorder(null);
        signInButton.setBorder(null);
        signInButton.setFocusPainted(false);

        signInButton.addActionListener(this);
        signUpButton.addActionListener(this);

        add(signUpButton);
        add(signInButton);

        addWindowListener(this);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==signInButton){
            String nameString, passwordString;
            nameString = nameTextField.getText();
            passwordString = passwordField.getText();
            boolean matched=false;
            if (nameString == null || nameString.equals("") || passwordString == null || passwordString.equals("")) {
                JOptionPane.showMessageDialog(this, "Please make sure to put your name and your password");
            } else {
                if (userRadioButton.isSelected()) {
                    User matchedUser = null;
                    for(User user:data.getAppUsers()){
                        if(user.getUserName().equalsIgnoreCase(nameString)){
                            if(user.getUserPassword().equals(passwordString)){
                                nameString=user.getUserName();
                                matchedUser=user;
                                matched=true;
                                break;
                            }
                        }
                    }
                    if(matched){
                        JOptionPane.showMessageDialog(this, "Hello User " + nameString + " \n  You are signed in !");
                        new UserFrame(data,matchedUser);
                        dispose();
                    }else{
                        JOptionPane.showMessageDialog(this, "please provide correct data to sign in as user or sign up instead");
                    }
                } else {
                    if (data.getAppManager() != null) {
                        if(data.getAppManager().getManagerName().equalsIgnoreCase(nameString)&&data.getAppManager().getManagerPassword().equals(passwordString))
                        {
                            JOptionPane.showMessageDialog(this, "Hello Manager " + data.getAppManager().getManagerName() + " \n  You are signed in !");
                            this.dispose();
                            new ManagerFrame(data);
                        }else{
                            JOptionPane.showMessageDialog(this, "please provide correct data to sign in as manager or sign up instead");
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "there is no manager for this application");
                    }
                }
            }
    }else{
            this.dispose();
            new SignUpFrame(data);
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        try {
            ObjectOutputStream applicationDataSaver=new ObjectOutputStream(new FileOutputStream("data.txt"));
            applicationDataSaver.writeObject(data);
        }catch (IOException ex) {
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
}
