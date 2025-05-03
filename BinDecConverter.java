import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BinDecConverter extends JFrame {

    private JTextField inputField, outputField;
    private JRadioButton binToDecBtn, decToBinBtn;
    private JButton convertBtn, clearBtn;
    private ButtonGroup group;

    public BinDecConverter() {
        setTitle("Bin/Dec Converter");
        setSize(350, 180);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        add(new JLabel("Input:"));
        inputField = new JTextField(20);
        add(inputField);

        binToDecBtn = new JRadioButton("Binary to Decimal", true);
        decToBinBtn = new JRadioButton("Decimal to Binary");
        group = new ButtonGroup();
        group.add(binToDecBtn);
        group.add(decToBinBtn);
        add(binToDecBtn);
        add(decToBinBtn);

        add(new JLabel("Output:"));
        outputField = new JTextField(20);
        outputField.setEditable(false);
        add(outputField);

        convertBtn = new JButton("Convert");
        clearBtn = new JButton("Clear");
        add(convertBtn);
        add(clearBtn);

        convertBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (binToDecBtn.isSelected()) {
                        String binary = inputField.getText();
                        int decimal = Integer.parseInt(binary, 2);
                        outputField.setText(String.valueOf(decimal));
                    } else {
                        int decimal = Integer.parseInt(inputField.getText());
                        String binary = Integer.toBinaryString(decimal);
                        outputField.setText(binary);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid input!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        clearBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                inputField.setText("");
                outputField.setText("");
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new BinDecConverter();
    }
}