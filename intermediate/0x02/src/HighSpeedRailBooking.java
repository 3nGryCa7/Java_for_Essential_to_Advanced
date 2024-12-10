import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HighSpeedRailBooking extends JFrame {

    public HighSpeedRailBooking() {
        super("High-Speed Rail Ticket Booking System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 300);
        setLayout(new GridLayout(5, 2));

        JLabel startLabel = new JLabel("Departure:");
        String[] stations = {"None", "Taipei", "Hsinchu", "Taichung", "Tainan", "Kaohsiung"};
        JComboBox<String> startComboBox = new JComboBox<>(stations);
        JLabel endLabel = new JLabel("Arrival:");
        JComboBox<String> endComboBox = new JComboBox<>(stations);

        JLabel ticketTypeLabel = new JLabel("Ticket Type:");
        JRadioButton noneFare = new JRadioButton("None");
        JRadioButton fullFare = new JRadioButton("Full-fare");
        JRadioButton halfFare = new JRadioButton("Half-fare");
        ButtonGroup ticketGroup = new ButtonGroup();
        ticketGroup.add(fullFare);
        ticketGroup.add(halfFare);
        ticketGroup.add(noneFare);
        noneFare.setSelected(true);

        JLabel ticketCountLabel = new JLabel("Number of Tickets:");
        JRadioButton noneTicket = new JRadioButton("None");
        JRadioButton oneTicket = new JRadioButton("1 ticket");
        JRadioButton twoTickets = new JRadioButton("2 tickets");
        JRadioButton threeTickets = new JRadioButton("3 tickets");
        ButtonGroup countGroup = new ButtonGroup();
        countGroup.add(oneTicket);
        countGroup.add(twoTickets);
        countGroup.add(threeTickets);
        countGroup.add(noneTicket);
        noneTicket.setSelected(true);

        JButton bookButton = new JButton("Book");

        add(startLabel);
        add(startComboBox);

        add(endLabel);
        add(endComboBox);

        add(ticketTypeLabel);
        JPanel ticketTypePanel = new JPanel();
        ticketTypePanel.add(noneFare);
        ticketTypePanel.add(fullFare);
        ticketTypePanel.add(halfFare);
        add(ticketTypePanel);

        add(ticketCountLabel);
        JPanel countPanel = new JPanel();
        countPanel.add(noneTicket);
        countPanel.add(oneTicket);
        countPanel.add(twoTickets);
        countPanel.add(threeTickets);
        add(countPanel);

        add(new JLabel()); // blank
        add(bookButton);

        bookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String startStation = (String) startComboBox.getSelectedItem();
                String endStation = (String) endComboBox.getSelectedItem();
                String ticketType = noneFare.isSelected() ? "None" : (fullFare.isSelected() ? "Full Fare" : "Half Fare");
                String ticketCount = noneTicket.isSelected() ? "None" : (oneTicket.isSelected() ? "1 Ticket" :
                        (twoTickets.isSelected() ? "2 Tickets" : "3 Tickets"));

                // Validation
                if (startStation.equals("None") || 
                    endStation.equals("None") || 
                    ticketType.equals("None") || 
                    ticketCount.equals("None")
                ) {
                    JOptionPane.showMessageDialog(null, "Please select all options.");
                } else if (startStation.equals(endStation)) {
                    JOptionPane.showMessageDialog(null, "Departure and arrival stations cannot be the same.");
                } else {
                    // Display booking info
                    JOptionPane.showMessageDialog(null, "You have booked " + ticketCount +
                            " from " + startStation + " to " + endStation + " with " + ticketType + ".");
                }
            }
        });
        setVisible(true);
    }

    public static void main(String[] args) {
        new HighSpeedRailBooking();
    }
}
