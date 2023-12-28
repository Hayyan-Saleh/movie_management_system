package Frames;

import Crew.User;
import Domain.Ticket;
import Domain.Ticketing;
import Files.Data;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class RemoveUserTicketFrame extends JFrame {
    Data data;
    User user;
    JLabel currentTicketLabel;
    Ticket removedTicket;
    JButton removeTicketButton;
    DefaultTableModel ticketsModel = new DefaultTableModel();
    JTable ticketsTable;
    JScrollPane ticketsTableScrollPane;

    public RemoveUserTicketFrame(User user, Data data) {
        this.data = data;
        this.user = user;

        //creating the frame
        setTitle("Remove Ticket");
        getContentPane().setBackground(new Color(120, 10, 50));
        setBounds((Toolkit.getDefaultToolkit().getScreenSize().width - 400) / 2, (Toolkit.getDefaultToolkit().getScreenSize().height - 600) / 2, 400, 600);
        setResizable(false);
        setLayout(null);

        //creating the tickets table
        ticketsModel.addColumn("Movie Name");
        ticketsModel.addColumn("Hall Name ");
        ticketsModel.addColumn("Day ");
        ticketsModel.addColumn("Hour ");
        ticketsModel.addColumn("Seat Number");
        String[] movieRow;
        if (user.getUserTickets() != null) {
            for (Ticket ticket :
                    user.getUserTickets()) {
                movieRow = new String[]{ticket.getMovie().getMovieName(), ticket.getHall().getName(), ticket.getDay() + "", ticket.getHour() + "", ticket.getChairNumber() + ""};
                ticketsModel.addRow(movieRow);
            }
        }
        ticketsTable = new JTable(ticketsModel);
        ticketsTable.setFont(new Font("Arial", Font.PLAIN, 14));
        ticketsTable.setForeground(Color.white);
        ticketsTable.setBackground(new Color(50, 50, 50));
        ticketsTable.setSelectionBackground(Color.red);
        ticketsTable.setDefaultEditor(Object.class,null);
        ticketsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        ticketsTable.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                int index = ticketsTable.getSelectedRow();
                String movieName = (String) ticketsModel.getValueAt(index, 0);
                String hallName = (String) ticketsModel.getValueAt(index, 1);
                String day = (String) ticketsModel.getValueAt(index, 2);
                String hour = (String) ticketsModel.getValueAt(index, 3);
                int chairNumber = Integer.parseInt((String) ticketsModel.getValueAt(index, 4));
                for (Ticket ticket : user.getUserTickets()) {
                    if (ticket.getMovie().getMovieName().equals(movieName) && ticket.getHall().getName().equals(hallName) && ticket.getDay().toString().equals(day) && ticket.getHour().toString().equals(hour) && ticket.getChairNumber() == chairNumber) {
                        removedTicket = ticket;
                        currentTicketLabel.setText("Current Ticket : " + movieName + " , " + hallName + " , " + day + " , " + hour + " , " + chairNumber);
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        ticketsTableScrollPane = new JScrollPane(ticketsTable);
        ticketsTableScrollPane.setBounds(0, 0, 380, 450);
        add(ticketsTableScrollPane);


        currentTicketLabel = new JLabel("Current Ticket : ");
        currentTicketLabel.setForeground(Color.white);
        currentTicketLabel.setBounds(10, 460, this.getWidth()-10, 30);
        currentTicketLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        add(currentTicketLabel);


        //creating the remove ticket button and adding it to the frame
        removeTicketButton = new JButton("Remove Selected Ticket");
        removeTicketButton.setBounds(75, 500, 250, 40);
        removeTicketButton.setBorder(BorderFactory.createLineBorder(Color.yellow));
        removeTicketButton.setFont(new Font("Serif", Font.BOLD, 20));
        removeTicketButton.setForeground(Color.white);
        removeTicketButton.setBackground(new Color(100, 0, 100));
        removeTicketButton.setFocusPainted(false);
        removeTicketButton.addActionListener(e -> {
            if (removedTicket != null) {
                int response=JOptionPane.showConfirmDialog(this,"Are you sure you want to remove this \n"+currentTicketLabel.getText());
                if(response==JOptionPane.YES_OPTION) {
                    Ticketing.removeTicket(this.user, this.removedTicket, this.removedTicket.getDay().ordinal(), this.removedTicket.getHour().ordinal());
                    data.getAppManager().setManagerIncome(data.getAppManager().getManagerIncome()-this.removedTicket.getPrice());
                    JOptionPane.showMessageDialog(this, "Successfully removed selected Ticket from your tickets");
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Please pick a ticket at first in order to remove it !");
                }
            }
        });
        add(removeTicketButton);

        setVisible(true);
    }


}
