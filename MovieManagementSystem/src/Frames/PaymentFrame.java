package Frames;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PaymentFrame extends JFrame implements ActionListener {
    JLabel payMethodType,chosenMethod,label1,label2;
    JRadioButton cash,syriatelCash,creditCard;
    ButtonGroup radioButtonsGroup;
    Color backgroundColor = new Color(0x424242);
    Font radioButtonsFont = new Font("Areal",Font.ITALIC,16);
    JButton continueButton;
    JTextField tf1;
    JPasswordField tf2;

    public PaymentFrame(){
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setTitle("Payment Methods");
        getContentPane().setBackground(backgroundColor);
        setBounds((Toolkit.getDefaultToolkit().getScreenSize().width - 400) / 2, (Toolkit.getDefaultToolkit().getScreenSize().height - 350) / 2, 400, 350);
        setLayout(null);

        payMethodType = new JLabel("Choose The Preferred Payment Method:");
        payMethodType.setBounds(10,10,350,30);
        payMethodType.setFont(new Font("Areal",Font.ITALIC,18));
        payMethodType.setForeground(Color.orange);

        cash = new JRadioButton("Cash");
        cash.setBounds(20,50,70,40);
        cash.setContentAreaFilled(false);
        cash.setBorderPainted(false);
        cash.setFocusPainted(false);
        cash.setForeground(Color.white);
        cash.setFont(radioButtonsFont);
        cash.addActionListener(this);

        syriatelCash = new JRadioButton("Syriatel Cash");
        syriatelCash.setBounds(100,50,130,40);
        syriatelCash.setContentAreaFilled(false);
        syriatelCash.setBorderPainted(false);
        syriatelCash.setFocusPainted(false);
        syriatelCash.setForeground(Color.white);
        syriatelCash.setFont(radioButtonsFont);
        syriatelCash.addActionListener(this);

        creditCard = new JRadioButton("Credit Card");
        creditCard.setBounds(240,50,120,40);
        creditCard.setContentAreaFilled(false);
        creditCard.setBorderPainted(false);
        creditCard.setFocusPainted(false);
        creditCard.setForeground(Color.white);
        creditCard.setFont(radioButtonsFont);
        creditCard.addActionListener(this);

        radioButtonsGroup = new ButtonGroup();
        radioButtonsGroup.add(cash);
        radioButtonsGroup.add(syriatelCash);
        radioButtonsGroup.add(creditCard);

        continueButton = new JButton("Continue");
        continueButton.setBounds(120,270,140,40);
        continueButton.setBorder(null);
        continueButton.setFocusPainted(false);
        continueButton.setForeground(Color.white);
        continueButton.setBackground(new Color(100, 0, 100));
        continueButton.setBorder(BorderFactory.createLineBorder(Color.yellow));
        continueButton.setFont(new Font("Areal",Font.BOLD,20));
        continueButton.setEnabled(false);
        continueButton.addActionListener(this);

        chosenMethod = new JLabel("Chosen Method: None");
        chosenMethod.setBounds(20,230,230,30);
        chosenMethod.setFont(new Font("Areal",Font.ITALIC,15));
        chosenMethod.setForeground(Color.orange);

        label1= new JLabel();
        label1.setFont(new Font("Areal",Font.ITALIC,16));
        label1.setForeground(Color.orange);
        label1.setBounds(20,100,140,30);

        tf1 = new JTextField();
        tf1.setBounds(20,130,100,20);
        tf1.setBackground(new Color(0x9A9797));
        tf1.setForeground(Color.black);
        tf1.setVisible(false);

        label2 = new JLabel();
        label2.setFont(new Font("Areal",Font.ITALIC,16));
        label2.setForeground(Color.orange);
        label2.setBounds(240,100,140,30);

        tf2 = new JPasswordField();
        tf2.setBounds(240,130,100,20);
        tf2.setBackground(new Color(0x9A9797));
        tf2.setForeground(Color.black);
        tf2.setVisible(false);










        add(tf1);
        add(tf2);
        add(label2);
        add(label1);
        add(chosenMethod);
        add(continueButton);
        add(creditCard);
        add(syriatelCash);
        add(cash);
        add(payMethodType);
        setVisible(true);
    }




    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==cash){
            chosenMethod.setText("Chosen Method: "+cash.getText());
            label1.setText("");
            label2.setText("");
            tf1.setVisible(false);
            tf2.setVisible(false);
            continueButton.setEnabled(true);
        }else if(e.getSource()==syriatelCash){
            tf1.setText("");
            tf2.setText("");
            chosenMethod.setText("Chosen Method: "+syriatelCash.getText());
            tf1.setVisible(true);
            label1.setText("Account Number");
            tf2.setVisible(true);
            label2.setText("Password");
            continueButton.setEnabled(true);

        }else if(e.getSource()==creditCard){
            tf1.setText("");
            tf2.setText("");
            label1.setText("Card Number");
            tf1.setVisible(true);
            label2.setText("PIN");
            tf2.setVisible(true);
            continueButton.setEnabled(true);
            chosenMethod.setText("Chosen Method: "+creditCard.getText());
        }else if(e.getSource()==continueButton){
            String paymentMethod="";
            if(syriatelCash.isSelected()){
                paymentMethod="Syriatel Cash";
            }else if(creditCard.isSelected()){
                paymentMethod="Credit Card";
            } else if (cash.isSelected()) {
                paymentMethod="Cash";
                }
            if(!paymentMethod.equals("")){
            if(syriatelCash.isSelected()||creditCard.isSelected()) {
                String pass = new String(tf2.getPassword());
                if (tf1.getText().equals("") || pass.equals("")) {
                    JOptionPane.showMessageDialog(null, "You Need To Enter All The Fields.", "Warning Message", JOptionPane.WARNING_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(this,"succeeded ! Now You can Take the tickets with payment : "+paymentMethod);
                    this.dispose();
                }
        }else if(cash.isSelected()){
                    JOptionPane.showMessageDialog(this,"succeeded ! Now You can Take the tickets with payment : "+paymentMethod);
                    this.dispose();
                }else
            JOptionPane.showMessageDialog(this,"please selected a Payment Method in order to continue !","missing payment selection",JOptionPane.WARNING_MESSAGE);
        }
    }

}}
