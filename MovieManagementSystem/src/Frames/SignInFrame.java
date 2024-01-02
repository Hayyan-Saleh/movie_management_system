package Frames;

import Files.Data;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SignInFrame extends JFrame implements WindowListener {
    Data data;
    public SignInFrame(Data data){
        this.data=data;
        //creating the JFrame with its properties
            setTitle("Movie Cinema");
        setBounds((Toolkit.getDefaultToolkit().getScreenSize().width - 750) / 2, (Toolkit.getDefaultToolkit().getScreenSize().height - 500) / 2, 750, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        add(new SignInPanel(this.data,this));



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
}