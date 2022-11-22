package it.unibo.mvc;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * A very simple program using a graphical interface.
 *
 */
public final class SimpleGUI {

  private final JFrame frame = new JFrame();

  /**
   * @param control the controller of the view
   */
  public SimpleGUI(final Controller control) {
    final JPanel canvas = new JPanel();
    canvas.setLayout(new BorderLayout());
    final JTextArea textArea = new JTextArea();
    canvas.add(textArea);
    final JButton save = new JButton("Save");
    canvas.add(textArea, BorderLayout.NORTH);
    canvas.add(save, BorderLayout.SOUTH);
    save.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(final ActionEvent e) {
          try {
            control.setFileContent(textArea.getText());
          } catch (IOException ioe) {
            JOptionPane.showMessageDialog(
              null,
              ioe.getMessage(),
              "ERROR occurred due to: " + ioe.getCause(),
              JOptionPane.ERROR_MESSAGE
            );
          }
        }
      }
    );
    frame.setContentPane(canvas);
    frame.pack();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  /**
   * Method for displaying the GUI.
   */
  private void display() {
    frame.setVisible(true);
  }

  /**
   * @param a Params
   * Main method.
   */
  public static void main(final String... a) {
    final SimpleGUI app = new SimpleGUI(new Controller());
    app.display();
  }
}
