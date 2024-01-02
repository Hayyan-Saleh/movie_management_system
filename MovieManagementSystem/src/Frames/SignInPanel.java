package Frames;


import Crew.User;
import Files.Data;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignInPanel extends JPanel implements ActionListener {
    Data data;
    JRadioButton userRadioButton,managerRadioButton;
    JLabel hintTypeLabel,hintNameLabel,hintPasswordLabel,hintSingUpLabel;
    ButtonGroup radioButtonsGrouper;
    JTextField nameTextField;
    JPasswordField passwordField;
    JButton signInButton, signUpButton;
    Color backgroundColor=new Color(140,0,40);
    Font labelsFont=new Font("Arial",Font.BOLD,18);
    SignInFrame signInFrame;

    ImageIcon imageIcon = new ImageIcon("SignBackground.png");
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(imageIcon.getImage(),0,0,this);
    }

    public SignInPanel(Data data, SignInFrame signInFrame){
        this.data=data;
        this.signInFrame=signInFrame;
        setBounds((Toolkit.getDefaultToolkit().getScreenSize().width-750)/2,(Toolkit.getDefaultToolkit().getScreenSize().height-500)/2,750,500);
        setLayout(null);


        //creating and adding the radio buttons to the frame along with the button group
        userRadioButton=new JRadioButton(" User ",true);
        managerRadioButton=new JRadioButton(" Manager ");

        managerRadioButton.setContentAreaFilled(false);
        userRadioButton.setContentAreaFilled(false);
        radioButtonsGrouper=new ButtonGroup();

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
        hintTypeLabel=new JLabel("Which person are You ?");
        hintTypeLabel.setForeground(Color.white);
        hintTypeLabel.setFont(labelsFont);
        hintTypeLabel.setBounds(300, 10, 300, 50);

        //add(hintTypeLabel);

        //creating and adding the text field , password field and labels for sign in frame
        hintNameLabel=new JLabel("Name ");
        hintPasswordLabel=new JLabel("Password");

        hintNameLabel.setForeground(Color.white);
        hintPasswordLabel.setForeground(Color.white);
        hintNameLabel.setFont(labelsFont);
        hintPasswordLabel.setFont(labelsFont);

        hintNameLabel.setBounds(240,205,280,30);
        hintPasswordLabel.setBounds(240,275,280,30);

        nameTextField=new JTextField();
        passwordField=new JPasswordField();

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
        signInButton =new JButton("Sign in");
        signUpButton =new JButton("Don't have an account ?   Sign up !");
        signInButton.setFont(labelsFont);
        signInButton.setForeground(Color.white);
        signInButton.setBounds(290,380,150,30);
        signInButton.setBackground(Color.gray);
        signUpButton.setForeground(Color.white);
        signUpButton.setBounds(240,420,250,30);
        signUpButton.setBackground(backgroundColor);
        signUpButton.setFocusPainted(false);
        signInButton.setFocusPainted(false);
        signInButton.setBorder(BorderFactory.createLineBorder(Color.red));
        signInButton.setContentAreaFilled(false);
        signUpButton.setBorder(BorderFactory.createLineBorder(Color.red));
        signUpButton.setContentAreaFilled(false);


        signInButton.addActionListener(this);
        signUpButton.addActionListener(this);

        add(signUpButton);
        add(signInButton);


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
                        signInFrame.dispose();

                    }else{
                        JOptionPane.showMessageDialog(this, "please provide correct data to sign in as user or sign up instead");
                    }
                } else {
                    if (data.getAppManager() != null) {
                        if(data.getAppManager().getManagerName().equalsIgnoreCase(nameString)&&data.getAppManager().getManagerPassword().equals(passwordString))
                        {
                            JOptionPane.showMessageDialog(this, "Hello Manager " + data.getAppManager().getManagerName() + " \n  You are signed in !");
                            signInFrame.dispose();
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
            signInFrame.dispose();
            new SignUpFrame(data);
        }
    }

}
