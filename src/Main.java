import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import infbyte.pysebal.PySEBAL;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::Frame);
    }

    private static void Frame() {
        JFrame frame = new JFrame();
        JLabel label = InputLabel();
        JLabel label1 = InputLastRowLabel();
        JTextField lastRowField = InputLastRowField();
        JTextField pathField = InputFileField();
        ActionListener listener = actionEvent -> {
            String path = pathField.getText();
            String lastRow = lastRowField.getText();
            PySEBAL.run(path, lastRow);
        };

        JButton runButton = RunButton(listener);

        GroupLayout layout = new GroupLayout(frame.getContentPane());

        frame.setLayout(layout);
        frame.add(label);
        frame.add(label1);
        frame.add(lastRowField);
        frame.add(pathField);
        frame.add(runButton);

        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setTitle("PySEBAL Client");
        frame.setSize(400, 300);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static JLabel InputLabel() {
        JLabel label = new JLabel("Input Excel File Path");
        label.setBounds(10, 30, 200, 30);
        return label;
    }

    private static JTextField InputFileField() {
        JTextField textField = new JTextField();
        textField.setBounds(10, 70, 380, 30);
        textField.setFont(new Font("Arial", Font.PLAIN, 16));
        return textField;
    }

    private static JLabel InputLastRowLabel() {
        JLabel label = new JLabel("Last Row Index");
        label.setBounds(10, 120, 200, 30);
        return label;
    }

    private static JTextField InputLastRowField() {
        JTextField textField = new JTextField();
        textField.setBounds(10, 160, 380, 30);
        textField.setFont(new Font("Arial", Font.PLAIN, 16));
        return textField;
    }

    private static JButton RunButton(ActionListener listener) {
        JButton button = new JButton("Run");
        button.setBounds(290, 215, 100, 50);
        button.addActionListener(listener);
        return button;
    }
}