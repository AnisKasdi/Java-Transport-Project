import vehicle.*;
import journey.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Transportation {
    private static JFrame frame;
    private static ArrayList<Vehicle> vehicles = new ArrayList<>();
    private static ArrayList<Journey> journeys = new ArrayList<>();
    private static JTextArea outputArea;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        frame = new JFrame("Transportation System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        // Panneau principal
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Boutons
        JPanel buttonPanel = new JPanel(new GridLayout(5, 1, 10, 10));
        JButton addVehicleButton = new JButton("Add Vehicle");
        JButton addJourneyButton = new JButton("Add Journey");
        JButton linkVehicleButton = new JButton("Add Vehicle to Journey");
        JButton showStatsButton = new JButton("Show Statistics");
        JButton exitButton = new JButton("Exit");

        buttonPanel.add(addVehicleButton);
        buttonPanel.add(addJourneyButton);
        buttonPanel.add(linkVehicleButton);
        buttonPanel.add(showStatsButton);
        buttonPanel.add(exitButton);

        // Zone de texte pour les résultats
        outputArea = new JTextArea(10, 40);
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        // Ajout au panneau principal
        mainPanel.add(buttonPanel, BorderLayout.WEST);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Actions des boutons
        addVehicleButton.addActionListener(e -> showAddVehicleDialog());
        addJourneyButton.addActionListener(e -> showAddJourneyDialog());
        linkVehicleButton.addActionListener(e -> showLinkVehicleDialog());
        showStatsButton.addActionListener(e -> showStats());
        exitButton.addActionListener(e -> System.exit(0));

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private static void showAddVehicleDialog() {
        JTextField nameField = new JTextField(10);
        JTextField capacityField = new JTextField(5);
        JTextField speedField = new JTextField(5);
        JTextField costField = new JTextField(5);
        String[] types = {"Train", "Bus"};
        JComboBox<String> typeBox = new JComboBox<>(types);

        JPanel dialogPanel = new JPanel(new GridLayout(5, 2, 5, 5));
        dialogPanel.add(new JLabel("Vehicle Type:"));
        dialogPanel.add(typeBox);
        dialogPanel.add(new JLabel("Name:"));
        dialogPanel.add(nameField);
        dialogPanel.add(new JLabel("Capacity:"));
        dialogPanel.add(capacityField);
        dialogPanel.add(new JLabel("Speed (km/h):"));
        dialogPanel.add(speedField);
        dialogPanel.add(new JLabel("Cost per km:"));
        dialogPanel.add(costField);

        int result = JOptionPane.showConfirmDialog(frame, dialogPanel, "Add Vehicle", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                String type = (String) typeBox.getSelectedItem();
                String name = nameField.getText();
                int capacity = Integer.parseInt(capacityField.getText());
                int speed = Integer.parseInt(speedField.getText());
                int cost = Integer.parseInt(costField.getText());

                if ("Train".equals(type)) {
                    vehicles.add(new Train(name, capacity, speed, cost));
                } else {
                    vehicles.add(new Bus(name, capacity, speed, cost));
                }
                outputArea.append("Added vehicle: " + name + " [Type: " + type + "]\n");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid input! Use numbers for capacity, speed, and cost.");
            }
        }
    }

    private static void showAddJourneyDialog() {
        JTextField city1Field = new JTextField(10);
        JCheckBox gare1Check = new JCheckBox("Has train station");
        JTextField city2Field = new JTextField(10);
        JCheckBox gare2Check = new JCheckBox("Has train station");
        JTextField distanceField = new JTextField(5);
        JTextField ticketField = new JTextField(5);

        JPanel dialogPanel = new JPanel(new GridLayout(6, 2, 5, 5));
        dialogPanel.add(new JLabel("City 1 Name:"));
        dialogPanel.add(city1Field);
        dialogPanel.add(new JLabel("City 1 Train Station:"));
        dialogPanel.add(gare1Check);
        dialogPanel.add(new JLabel("City 2 Name:"));
        dialogPanel.add(city2Field);
        dialogPanel.add(new JLabel("City 2 Train Station:"));
        dialogPanel.add(gare2Check);
        dialogPanel.add(new JLabel("Distance (km):"));
        dialogPanel.add(distanceField);
        dialogPanel.add(new JLabel("Ticket Price:"));
        dialogPanel.add(ticketField);

        int result = JOptionPane.showConfirmDialog(frame, dialogPanel, "Add Journey", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                String city1Name = city1Field.getText();
                boolean hasGare1 = gare1Check.isSelected();
                String city2Name = city2Field.getText();
                boolean hasGare2 = gare2Check.isSelected();
                int distance = Integer.parseInt(distanceField.getText());
                int ticketPrice = Integer.parseInt(ticketField.getText());

                City city1 = new City(city1Name, hasGare1);
                City city2 = new City(city2Name, hasGare2);
                journeys.add(new Journey(city1, city2, distance, ticketPrice, new Vehicle[Journey.MAX_VEHICLE], 0));
                outputArea.append("Added journey: " + city1Name + " to " + city2Name + " (" + distance + " km)\n");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid input! Use numbers for distance and ticket price.");
            }
        }
    }

    private static void showLinkVehicleDialog() {
        if (vehicles.isEmpty() || journeys.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Add at least one vehicle and one journey first!");
            return;
        }

        String[] vehicleNames = vehicles.stream().map(Vehicle::getVehicleName).toArray(String[]::new);
        JComboBox<String> vehicleBox = new JComboBox<>(vehicleNames);

        String[] journeyNames = journeys.stream()
                .map(j -> j.getCity1().getNameOfCity() + " to " + j.getCity2().getNameOfCity())
                .toArray(String[]::new);
        JComboBox<String> journeyBox = new JComboBox<>(journeyNames);

        JPanel dialogPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        dialogPanel.add(new JLabel("Select Vehicle:"));
        dialogPanel.add(vehicleBox);
        dialogPanel.add(new JLabel("Select Journey:"));
        dialogPanel.add(journeyBox);

        int result = JOptionPane.showConfirmDialog(frame, dialogPanel, "Link Vehicle to Journey", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            int vehicleIdx = vehicleBox.getSelectedIndex();
            int journeyIdx = journeyBox.getSelectedIndex();

            Vehicle v = vehicles.get(vehicleIdx);
            Journey j = journeys.get(journeyIdx);
            if (j.addVehicle(v)) {
                outputArea.append("Added " + v.getVehicleName() + " to " + j.getCity1().getNameOfCity() + " to " + j.getCity2().getNameOfCity() + "\n");
            } else {
                JOptionPane.showMessageDialog(frame, "Failed to add vehicle (incompatible or journey full)!");
            }
        }
    }

    private static void showStats() {
        if (journeys.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "No journeys available!");
            return;
        }

        outputArea.setText(""); // Efface la zone de texte
        int totalBenefit = 0;
        for (Journey j : journeys) {
            outputArea.append(j.getCity1().getNameOfCity() + " to " + j.getCity2().getNameOfCity() + ":\n");
            outputArea.append("  Passengers/day: " + j.passangersperday() + "\n");
            outputArea.append("  Income/day: " + j.incomePerDay() + "\n");
            outputArea.append("  Cost/day: " + j.costPerDay() + "\n");
            outputArea.append("  Benefit/day: " + j.benefitPerDay() + "\n\n");
            totalBenefit += j.benefitPerDay();
        }
        outputArea.append("Total Benefit: " + totalBenefit + "\n");
    }
}