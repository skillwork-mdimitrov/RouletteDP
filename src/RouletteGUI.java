import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class RouletteGUI implements ActionListener{
  private Roulette roulette;
  private JFrame frame;
  private JLabel rouletteLabel;
  private JButton increaseBetBtn;
  private JButton decreaseBetBtn;

  RouletteGUI() {
    rouletteLabel = new JLabel();
    increaseBetBtn = new JButton("+");
    decreaseBetBtn = new JButton("-");

    // Add text to the labels
    this.rouletteLabel.setText("Roulette");
    makeFrame(); // don't move upwards
  }

  private void makeFrame() {
    frame = new JFrame("Roulette - the game");
    Container contentPane = frame.getContentPane();

    // set the layout
    contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

    // cosmetic section
    contentPane.setBackground(Color.LIGHT_GRAY);

    // add text/labels to the content panel
    contentPane.add(rouletteLabel, contentPane);

    // add buttons and their listeners
    // contentPane.add(button);
    // button.addActionListener(this);

    // set the initial frame size
    frame.setSize(1100, 560);

    // center the frame and make it visible
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    frame.setLocation(d.width/2 - frame.getWidth()/2, d.height/2 - frame.getHeight()/2);
    frame.setVisible(true);
  }

  // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ GETTERS ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

  // Get roulette
  public Roulette getRoulette() {
    return roulette;
  }

  public void actionPerformed(ActionEvent e)
  {

  }
}
