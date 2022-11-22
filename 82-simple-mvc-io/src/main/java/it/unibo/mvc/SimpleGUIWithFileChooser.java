package it.unibo.mvc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * A very simple program using a graphical interface.
 *
 */
public final class SimpleGUIWithFileChooser {

  private final JFrame frame = new JFrame();

  /**
   * @param control The controller of the view.
   */
  public SimpleGUIWithFileChooser(final Controller control) {
    final JPanel canvas = new JPanel();
    canvas.setLayout(new BorderLayout());
    final JPanel browsePanel = new JPanel();
    browsePanel.setLayout(new BorderLayout());
    final JTextField text = new JTextField(control.getCurrFilePath());
    text.setEditable(false);
    final JButton browser = new JButton("Browse...");
    browser.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(final ActionEvent e) {
          final JFileChooser fileChooser = new JFileChooser("Select directory");
          fileChooser.setSelectedFile(control.getCurrFile());
          final int res = fileChooser.showSaveDialog(frame);
          switch (res) {
            case JFileChooser.APPROVE_OPTION:
              final File dest = fileChooser.getSelectedFile();
              control.setCurrFile(dest);
              text.setText(dest.getPath());
              break;
            case JFileChooser.CANCEL_OPTION:
              break;
            default:
              JOptionPane.showMessageDialog(
                frame,
                res,
                "Error!",
                JOptionPane.ERROR_MESSAGE
              );
          }
        }
      }
    );

    frame.pack();
    browsePanel.add(text, BorderLayout.CENTER);
    browsePanel.add(browser, BorderLayout.LINE_END);
    canvas.add(browsePanel, BorderLayout.NORTH);
    frame.setContentPane(canvas);
    final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    final int w = (int) screen.getWidth();
    final int h = (int) screen.getHeight();
    frame.setSize(w / 4, h / 4);
    frame.setLocationByPlatform(true);
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
    final SimpleGUIWithFileChooser app = new SimpleGUIWithFileChooser(
      new Controller()
    );
    app.display();
  }
}
