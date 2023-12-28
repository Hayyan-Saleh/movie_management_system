package Frames;

import Domain.Day;
import Files.Data;

import javax.swing.*;
import java.awt.*;

public class AddDiscountFrame extends JFrame {
    Data data;
    Day discountDay;
    double discountAmount;
    JLabel discountDayHintLabel, discountAmountHintLabel;
    JComboBox discountDaysComboBox;
    JTextField discountAmountTextField;
    JButton addDiscountButton;
    public AddDiscountFrame(Data data){
        this.data = data;
        setTitle("Add Movie");
        getContentPane().setBackground(new Color(180, 20, 20));
        setBounds((Toolkit.getDefaultToolkit().getScreenSize().width - 450) / 2, (Toolkit.getDefaultToolkit().getScreenSize().height - 160) / 2, 450, 160);
        setResizable(false);
        setLayout(null);

        //creating the frame labels and adding it to the frame

        discountDayHintLabel = new JLabel("Select discount Day :");
        discountDayHintLabel.setBounds(10, 10, 200, 30);
        discountDayHintLabel.setForeground(Color.white);
        discountDayHintLabel.setFont(new Font("Serif", Font.BOLD, 18));
        add(discountDayHintLabel);

        discountAmountHintLabel = new JLabel("Select discount Amount :");
        discountAmountHintLabel.setBounds(220, 10, 200, 30);
        discountAmountHintLabel.setForeground(Color.white);
        discountAmountHintLabel.setFont(new Font("Serif", Font.BOLD, 18));
        add(discountAmountHintLabel);


        //creating the combo box and adding it to the frame
        discountDaysComboBox=new JComboBox(Day.values());
        discountDaysComboBox.setBackground(Color.darkGray);
        discountDaysComboBox.setForeground(Color.white);
        discountDaysComboBox.setFont(new Font("Serif", Font.BOLD, 20));
        discountDaysComboBox.setBounds(10, 40, 150, 30);
        discountDaysComboBox.addActionListener((e)->{
            this.discountDay=(Day)discountDaysComboBox.getSelectedItem();
        });
        discountDaysComboBox.setEnabled(true);
        add(discountDaysComboBox);

        //creating discount amount text field
        discountAmountTextField = new JTextField();
        discountAmountTextField.setFont(new Font("Serif", Font.BOLD, 20));
        discountAmountTextField.setBounds(220, 40, 200, 30);
        discountAmountTextField.setForeground(Color.white);
        discountAmountTextField.setBackground(Color.darkGray);
        discountAmountTextField.addActionListener((e)->{
            try{
                this.discountAmount=Double.parseDouble(discountAmountTextField.getText());
            }catch (NumberFormatException ex){
                JOptionPane.showMessageDialog(this,"Please Enter a Valid Number in Discount Amount !!");
            }
        });
        add(discountAmountTextField);

        //creating the button and adding it to the frame
        addDiscountButton=new JButton("Add Discount");
        addDiscountButton.setBounds(120,80,200,35);
        addDiscountButton.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
        addDiscountButton.setFont(new Font("Serif",Font.BOLD,20));
        addDiscountButton.setForeground(Color.white);
        addDiscountButton.setBackground(new Color(0, 190, 150));
        addDiscountButton.setFocusPainted(false);
        addDiscountButton.addActionListener((e)->{
            if(this.discountDay!=null && this.discountAmount!=0){
                data.getAppManager().setManagerDiscountDay(this.discountDay);
                data.getAppManager().setDiscountAmount(this.discountAmount);
                JOptionPane.showMessageDialog(this,"Added Discount At "+this.discountDay+" for "+this.discountAmount+" %");
                this.dispose();
            }else{
                JOptionPane.showMessageDialog(this,"please provide all the needed data in order to add a Discount !!","missing input",JOptionPane.ERROR_MESSAGE);
            }
        });
        setEnabled(true);
        add(addDiscountButton);


        setVisible(true);

    }
}
