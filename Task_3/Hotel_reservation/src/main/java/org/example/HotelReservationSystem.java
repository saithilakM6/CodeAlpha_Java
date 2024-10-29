package org.example;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

class Room {
    int roomNumber;
    String roomType;
    boolean isBooked;
    double price;

    public Room(int roomNumber, String roomType, double price) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.isBooked = false;
        this.price = price;
    }

    public void bookRoom() {
        isBooked = true;
    }

    public void releaseRoom() {
        isBooked = false;
    }

    public String getDetails() {
        return "Room " + roomNumber + " (" + roomType + ") - Rs." + price + " - " + (isBooked ? "Booked" : "Available");
    }

    public boolean isAvailable() {
        return !isBooked;
    }
}

class Reservation {
    Room room;
    String guestName;
    String paymentMethod;

    public Reservation(Room room, String guestName, String paymentMethod) {
        this.room = room;
        this.guestName = guestName;
        this.paymentMethod = paymentMethod;
        room.bookRoom();
    }

    public String getDetails() {
        return "Guest: " + guestName + " - Room " + room.roomNumber + " (" + room.roomType + ") - Paid via: " + paymentMethod;
    }
}

public class HotelReservationSystem extends JFrame {
    private final ArrayList<Room> rooms = new ArrayList<>();
    private final ArrayList<Reservation> reservations = new ArrayList<>();
    private DefaultListModel<String> roomListModel;
    private JTextField nameField, roomNumberField, paymentField;
    private JTextArea bookingDetailsArea;
    private final JPanel contentPane;

    public HotelReservationSystem() {
        setTitle("Hotel Reservation System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(false); // Remove title bar for full-screen effect
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Make window full screen
        setLookAndFeel();

        // Set up main content pane with layout
        contentPane = new JPanel(new BorderLayout());
        contentPane.setBackground(new Color(255, 250, 240)); // Light background color
        setContentPane(contentPane);

        initializeRooms();
        initializeUI();
    }

    @SuppressWarnings("CallToPrintStackTrace")
    private void setLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initializeRooms() {
        rooms.add(new Room(101, "Single", 1000.0));
        rooms.add(new Room(102, "Double", 1500.0));
        rooms.add(new Room(103, "Suite", 2500.0));
        rooms.add(new Room(201, "Single", 1200.0));
        rooms.add(new Room(202, "Double", 1700.0));
        rooms.add(new Room(203, "Suite", 3000.0));
    }

    private void initializeUI() {
        // Title Label
        JLabel title = new JLabel("Hotel Reservation System", SwingConstants.CENTER);
        title.setFont(new Font("Serif", Font.BOLD, 48));
        title.setBorder(new EmptyBorder(20, 0, 20, 0));
        contentPane.add(title, BorderLayout.NORTH);

        // Main Panel
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20); // Padding around components
        gbc.fill = GridBagConstraints.BOTH; // Fill both horizontally and vertically
        gbc.weightx = 1.0; // Expand to fill space horizontally
        gbc.weighty = 1.0; // Expand to fill space vertically

        // Room List
        roomListModel = new DefaultListModel<>();
        JList<String> roomList = new JList<>(roomListModel);
        roomList.setBorder(BorderFactory.createTitledBorder("Available Rooms"));
        refreshRoomList();
        JScrollPane roomScrollPane = new JScrollPane(roomList);
        roomScrollPane.setPreferredSize(new Dimension(300, 300));
        roomScrollPane.setBorder(new EmptyBorder(10, 10, 10, 10)); // Padding inside the scroll pane
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 2; // Occupy two rows
        mainPanel.add(roomScrollPane, gbc);

        // Booking Form
        JPanel bookingPanel = createBookingPanel();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 1; // Reset grid height
        mainPanel.add(bookingPanel, gbc);

        // Booking Details Area
        bookingDetailsArea = new JTextArea();
        bookingDetailsArea.setBorder(BorderFactory.createTitledBorder("Booking Details"));
        bookingDetailsArea.setEditable(false);
        JScrollPane bookingScrollPane = new JScrollPane(bookingDetailsArea);
        bookingScrollPane.setPreferredSize(new Dimension(300, 300));
        bookingScrollPane.setBorder(new EmptyBorder(10, 10, 10, 10)); // Padding inside the scroll pane
        gbc.gridx = 1;
        gbc.gridy = 1;
        mainPanel.add(bookingScrollPane, gbc);

        contentPane.add(mainPanel, BorderLayout.CENTER);
    }

    private JPanel createBookingPanel() {
        JPanel bookingPanel = new JPanel();
        bookingPanel.setLayout(new BoxLayout(bookingPanel, BoxLayout.Y_AXIS));
        bookingPanel.setBorder(BorderFactory.createTitledBorder("Reservation Details"));
        bookingPanel.setBackground(new Color(255, 255, 255)); // Light peach color
        bookingPanel.setBorder(new EmptyBorder(50, 50, 50, 50)); // Add padding around the panel

        JLabel nameLabel = new JLabel("Guest Name:");
        nameField = new JTextField(15);
        nameField.setMaximumSize(new Dimension(Integer.MAX_VALUE, nameField.getPreferredSize().height));
        bookingPanel.add(nameLabel);
        bookingPanel.add(nameField);

        JLabel roomNumberLabel = new JLabel("Room Number:");
        roomNumberField = new JTextField(15);
        roomNumberField.setMaximumSize(new Dimension(Integer.MAX_VALUE, roomNumberField.getPreferredSize().height));
        bookingPanel.add(roomNumberLabel);
        bookingPanel.add(roomNumberField);

        JLabel paymentLabel = new JLabel("Payment Method:");
        paymentField = new JTextField(15);
        paymentField.setMaximumSize(new Dimension(Integer.MAX_VALUE, paymentField.getPreferredSize().height));
        bookingPanel.add(paymentLabel);
        bookingPanel.add(paymentField);

        JButton bookButton = new JButton("Book Room");
        bookButton.addActionListener(new BookRoomActionListener());
        bookingPanel.add(bookButton);

        JButton checkoutButton = new JButton("Check Out");
        checkoutButton.addActionListener(new CheckOutActionListener());
        bookingPanel.add(checkoutButton);

        JButton viewBookingButton = new JButton("View Booking");
        viewBookingButton.addActionListener(new ViewBookingActionListener());
        bookingPanel.add(viewBookingButton);

        // Adding Key Listener for Enter Key
        KeyAdapter enterKeyListener = new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    // Trigger the book action if the book button is enabled
                    if (bookButton.isEnabled()) {
                        bookButton.doClick();
                    } else if (checkoutButton.isEnabled()) {
                        checkoutButton.doClick();
                    } else if (viewBookingButton.isEnabled()) {
                        viewBookingButton.doClick();
                    }
                }
            }
        };

        // Add key listeners to input fields
        nameField.addKeyListener(enterKeyListener);
        roomNumberField.addKeyListener(enterKeyListener);
        paymentField.addKeyListener(enterKeyListener);

        return bookingPanel;
    }

    private void refreshRoomList() {
        roomListModel.clear();
        for (Room room : rooms) {
            if (room.isAvailable()) {
                roomListModel.addElement(room.getDetails());
            }
        }
    }

    private class BookRoomActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String guestName = nameField.getText().trim();
            String roomNumberStr = roomNumberField.getText().trim();
            String paymentMethod = paymentField.getText().trim();

            if (guestName.isEmpty() || roomNumberStr.isEmpty() || paymentMethod.isEmpty()) {
                JOptionPane.showMessageDialog(HotelReservationSystem.this, "Please fill in all fields.");
                return;
            }

            int roomNumber;
            try {
                roomNumber = Integer.parseInt(roomNumberStr);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(HotelReservationSystem.this, "Invalid room number.");
                return;
            }

            Room selectedRoom = null;
            for (Room room : rooms) {
                if (room.roomNumber == roomNumber) {
                    selectedRoom = room;
                    break;
                }
            }

            if (selectedRoom == null) {
                JOptionPane.showMessageDialog(HotelReservationSystem.this, "Room number " + roomNumber + " does not exist.");
                return;
            }

            if (selectedRoom.isAvailable()) {
                reservations.add(new Reservation(selectedRoom, guestName, paymentMethod));
                JOptionPane.showMessageDialog(HotelReservationSystem.this, "Room " + roomNumber + " booked successfully!");
                refreshRoomList();
            } else {
                JOptionPane.showMessageDialog(HotelReservationSystem.this, "Room " + roomNumber + " is already booked.");
            }
        }
    }

    private class CheckOutActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String roomNumberStr = roomNumberField.getText().trim();

            if (roomNumberStr.isEmpty()) {
                JOptionPane.showMessageDialog(HotelReservationSystem.this, "Please enter a room number.");
                return;
            }

            int roomNumber;
            try {
                roomNumber = Integer.parseInt(roomNumberStr);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(HotelReservationSystem.this, "Invalid room number.");
                return;
            }

            for (Reservation reservation : reservations) {
                if (reservation.room.roomNumber == roomNumber) {
                    reservation.room.releaseRoom();
                    reservations.remove(reservation);
                    JOptionPane.showMessageDialog(HotelReservationSystem.this, "Successfully checked out of room " + roomNumber + ".");
                    refreshRoomList();
                    return;
                }
            }

            JOptionPane.showMessageDialog(HotelReservationSystem.this, "No reservation found for room " + roomNumber + ".");
        }
    }

    private class ViewBookingActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String guestName = nameField.getText().trim();

            if (guestName.isEmpty()) {
                JOptionPane.showMessageDialog(HotelReservationSystem.this, "Please enter the guest name.");
                return;
            }

            StringBuilder bookingDetails = new StringBuilder();
            for (Reservation reservation : reservations) {
                if (reservation.guestName.equalsIgnoreCase(guestName)) {
                    bookingDetails.append(reservation.getDetails()).append("\n");
                }
            }

            if (bookingDetails.length() > 0) {
                bookingDetailsArea.setText(bookingDetails.toString());
            } else {
                JOptionPane.showMessageDialog(HotelReservationSystem.this, "No booking found for " + guestName + ".");
                bookingDetailsArea.setText("");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            HotelReservationSystem frame = new HotelReservationSystem();
            frame.setVisible(true);
        });
    }
}
