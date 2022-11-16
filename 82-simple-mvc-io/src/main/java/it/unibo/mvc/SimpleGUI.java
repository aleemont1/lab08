package it.unibo.mvc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUI {

    private final JFrame frame = new JFrame();

    public SimpleGUI(final Controller control) {
        final JPanel canvas = new JPanel();
        canvas.setLayout(new BorderLayout());
        final JTextArea textArea = new JTextArea();
        canvas.add(textArea);
        final JButton save = new JButton("Save");
        canvas.add(textArea, BorderLayout.NORTH);
        canvas.add(save, BorderLayout.SOUTH);
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                try {
                    control.setFileContent(textArea.getText());
                } catch (IOException ioe) {
                    JOptionPane.showMessageDialog(null, ioe.getMessage(), "ERROR occurred due to: " + ioe.getCause(), JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        frame.setContentPane(canvas);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    private void display() {
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        final SimpleGUI app = new SimpleGUI(new Controller());
        app.display();
    }
}
