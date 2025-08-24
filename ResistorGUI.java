import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

//The Resistor class contains the logic for calculating resistance values based on color codes.

class Resistor {
    // METHOD ONE: Calculates the value for the first band.
    public static double Colour1(String col1){
        int a = 0;
        if (col1.equalsIgnoreCase("Black")){ a= 0; }
        else if (col1.equalsIgnoreCase("Brown")) { a= 10; }
        else if (col1.equalsIgnoreCase("Red")){ a= 20; }
        else if (col1.equalsIgnoreCase("Orange")){ a= 30; }
        else if (col1.equalsIgnoreCase("Yellow")){ a= 40; }
        else if (col1.equalsIgnoreCase("Green")){ a= 50; }
        else if (col1.equalsIgnoreCase("Blue")){ a= 60; }
        else if (col1.equalsIgnoreCase("Violet")){ a=70; }
        else if(col1.equalsIgnoreCase("White")){ a= 80; }
        else if (col1.equalsIgnoreCase("Gray")){ a= 90; }
        return a;
    }

    // METHOD TWO: Calculates the value for the second band.
    public static double Colour2(String col2){
        int b = 0;
        if (col2.equalsIgnoreCase("Black")){ b= 0; }
        else if (col2.equalsIgnoreCase("Brown")){ b= 1; }
        else if (col2.equalsIgnoreCase("Red")){ b= 2; }
        else if (col2.equalsIgnoreCase("Orange")){ b= 3; }
        else if (col2.equalsIgnoreCase("Yellow")){ b= 4; }
        else if (col2.equalsIgnoreCase("Green")){ b= 5; }
        else if (col2.equalsIgnoreCase("Blue")){ b= 6; }
        else if (col2.equalsIgnoreCase("Violet")){ b = 7; }
        else if(col2.equalsIgnoreCase("White")){ b= 8; }
        else if (col2.equalsIgnoreCase("Gray")){ b= 9; }
        return b;
    }

    // METHOD THREE: Calculates the multiplier value for the third band.
    public static double Colour3 (String col3){
        double c=0;
        if (col3.equalsIgnoreCase("Black")){ c= Math.pow(10,0); }
        else if (col3.equalsIgnoreCase("Brown")){ c= Math.pow(10,1); }
        else if (col3.equalsIgnoreCase("Red")){ c= Math.pow(10,2); }
        else if (col3.equalsIgnoreCase("Orange")){ c= Math.pow(10,3); }
        else if (col3.equalsIgnoreCase("Yellow")){ c= Math.pow(10,4); }
        else if (col3.equalsIgnoreCase("Green")){ c= Math.pow(10,5); }
        else if (col3.equalsIgnoreCase("Blue")){ c= Math.pow(10,6); }
        else if (col3.equalsIgnoreCase("Violet")){ c= Math.pow(10,7); }
        else if(col3.equalsIgnoreCase("White")){ c= Math.pow(10,8); }
        else if (col3.equalsIgnoreCase("Gray")){ c= Math.pow(10,9); }
        return c;
    }
}

//ResistorGUI class creates and manages an improved graphical user interface
public class ResistorGUI extends JFrame {

    private final ButtonGroup band1Group = new ButtonGroup();
    private final ButtonGroup band2Group = new ButtonGroup();
    private final ButtonGroup band3Group = new ButtonGroup();
    private JButton calculateButton;
    private JLabel resultLabel;
    private final Map<String, Color> colorMap = new HashMap<>();

    public ResistorGUI() {
        // Initialize Color Map
        initializeColorMap();

        // Frame Setup
        setTitle("Resistor Calculator");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Main Panel for Color Choices
        JPanel choicePanel = new JPanel(new GridLayout(1, 3, 10, 10));
        choicePanel.add(createColorBandPanel("1st Band", band1Group));
        choicePanel.add(createColorBandPanel("2nd Band", band2Group));
        choicePanel.add(createColorBandPanel("3rd Band (Multiplier)", band3Group));

        // --- Bottom Panel for Button and Result ---
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        calculateButton = new JButton("Calculate Resistance");
        resultLabel = new JLabel("Select colors and click calculate.");
        resultLabel.setFont(new Font("Arial", Font.BOLD, 14));
        bottomPanel.add(calculateButton);
        bottomPanel.add(resultLabel);

        // Add Panels to Frame
        add(choicePanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        // Action Listener for the Button
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String color1 = getSelectedButtonText(band1Group);
                String color2 = getSelectedButtonText(band2Group);
                String color3 = getSelectedButtonText(band3Group);

                if (color1 == null || color2 == null || color3 == null) {
                    resultLabel.setText("Please select a color for each band.");
                    return;
                }

                double val1 = Resistor.Colour1(color1);
                double val2 = Resistor.Colour2(color2);
                double multiplier = Resistor.Colour3(color3);
                double result = (val1 + val2) * multiplier;

                resultLabel.setText(String.format("Resistance: %.2f Ohms", result));
            }
        });
    }

    // Helper method to create a panel with colored radio buttons for a single band.
    private JPanel createColorBandPanel(String title, ButtonGroup group) {
        JPanel panel = new JPanel(new GridLayout(colorMap.size(), 1));
        panel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), title, TitledBorder.CENTER, TitledBorder.TOP));

        for (String colorName : colorMap.keySet()) {
            JRadioButton button = new JRadioButton(colorName);
            button.setBackground(colorMap.get(colorName));
            // Set text color to be readable against dark backgrounds
            if (colorName.equals("Black") || colorName.equals("Blue") || colorName.equals("Brown")) {
                button.setForeground(Color.WHITE);
            }
            group.add(button);
            panel.add(button);
        }
        // Select the first button by default
        if (panel.getComponent(0) instanceof JRadioButton) {
            ((JRadioButton) panel.getComponent(0)).setSelected(true);
        }
        return panel;
    }

    // Finds the text of the selected radio button in a given button group.

    public String getSelectedButtonText(ButtonGroup buttonGroup) {
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
                return button.getText();
            }
        }
        return null;
    }

    //Initializes the map of color names to Java AWT Color objects.

    private void initializeColorMap() {
        colorMap.put("Black", Color.BLACK);
        colorMap.put("Brown", new Color(139, 69, 19)); // SaddleBrown
        colorMap.put("Red", Color.RED);
        colorMap.put("Orange", Color.ORANGE);
        colorMap.put("Yellow", Color.YELLOW);
        colorMap.put("Green", Color.GREEN);
        colorMap.put("Blue", Color.BLUE);
        colorMap.put("Violet", new Color(138, 43, 226)); // BlueViolet
        colorMap.put("Gray", Color.GRAY);
        colorMap.put("White", Color.WHITE);
    }

    // Main method to run the application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ResistorGUI().setVisible(true));
    }
}