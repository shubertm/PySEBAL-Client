/**
 *  @Author: Shubert Munthali
 *  @Copyright: 2024 Shubert Munthali
 *  @Licence: Apache License
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import infbyte.pysebal.PySEBAL;
import infbyte.pysebal.listeners.ImageAnalysisListener;

public class Main implements ImageAnalysisListener {

    JProgressBar progressBar = ProgressBar();
    JButton runButton;
    private String lastRow = "";
    private String path = "";

    public Main() {
        Frame();
    }

    public static void main(String[] args) {
        new Main();
    }

    private void Frame() {
        JFrame frame = new JFrame();
        JLabel label = InputLabel();
        JLabel label1 = InputLastRowLabel();
        JTextField lastRowField = InputLastRowField();
        JTextField pathField = InputFileField();
        ActionListener listener = actionEvent -> {
            path = pathField.getText();
            lastRow = lastRowField.getText();
            onStart();
            SwingUtilities.invokeLater(() -> {
                PySEBAL.run(path, lastRow, Main.this);
                onFinish();
            });
        };

        GroupLayout layout = new GroupLayout(frame.getContentPane());

        runButton = RunButton(listener);

        frame.setLayout(layout);
        frame.add(label);
        frame.add(label1);
        frame.add(lastRowField);
        frame.add(pathField);
        frame.add(runButton);
        frame.add(progressBar);

        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setTitle("PySEBAL Client");
        frame.setSize(400, 330);
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

    private static JProgressBar ProgressBar() {
        JProgressBar progressBar = new JProgressBar();
        progressBar.setBounds(40, 205, 320, 20);
        return progressBar;
    }

    private static JButton RunButton(ActionListener listener) {
        JButton button = new JButton("Run");
        button.setBounds(290, 240, 100, 50);
        button.addActionListener(listener);
        return button;
    }

    private void updateProgress(int progress) {
        int numberOfImages = Integer.parseInt(lastRow) - 1;
        int value = (progress / numberOfImages) * 100;
        progressBar.setValue(value);
    }

    @Override
    public void onStart() {
        updateProgress(0);
        runButton.setEnabled(false);
    }

    @Override
    public void onNextImage(int number) {
        updateProgress(number);
    }

    @Override
    public void onFinish() {
        runButton.setEnabled(true);
    }
}