import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class RouletteGUI implements ActionListener{
  private Roulette roulette;
  private JFrame frame;
  private JLabel rouletteLabel;
  // ~~~ Buttons ~~~
  private JButton increaseBetBtn;
  private JButton decreaseBetBtn;
  private JButton numberBtn;

  RouletteGUI() {
    roulette = new Roulette();
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
    FlowLayout rouletteLayout = new FlowLayout();
    contentPane.setLayout(rouletteLayout);
    contentPane.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

    // cosmetic section
    contentPane.setBackground(Color.LIGHT_GRAY);

    // add text/labels to the content panel
    contentPane.add(rouletteLabel, contentPane);

    // add buttons and their listeners

    // increase and decrease buttons
    contentPane.add(increaseBetBtn);
    contentPane.add(decreaseBetBtn);
    increaseBetBtn.addActionListener(this);
    decreaseBetBtn.addActionListener(this);

    // Number buttons you can pick
    for(Button element : roulette.getButtonsList()) {
      contentPane.add(createButton(numberBtn, element.getNumber() + ""));
    }

    // contentPane.add(button);
    // button.addActionListener(this);

    // set the initial frame size
    frame.setSize(1100, 560);

    // center the frame and make it visible
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    frame.setLocation(d.width/2 - frame.getWidth()/2, d.height/2 - frame.getHeight()/2);
    frame.setVisible(true);
  }

  private JButton createButton(JButton btn, String text) {
    btn = new JButton(text);
    return btn;
  }

  // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ GETTERS ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

  // Get roulette
  public Roulette getRoulette() {
    return roulette;
  }

  public void actionPerformed(ActionEvent e) {
    if(e.getSource() == increaseBetBtn) {
      System.out.println("Increase btn pressed");
    }
    if(e.getSource() == decreaseBetBtn) {
      System.out.println("Decrease btn pressed");
    }
    if(e.getSource() == numberBtn) {
      System.out.println("Number btn pressed");
    }
  }
}
