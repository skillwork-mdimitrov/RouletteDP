import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class RouletteGUI implements ActionListener, Observer{
  private Roulette roulette;
  private JFrame frame;
  // ~~ Labels
  private JLabel rouletteLabel;
  private JLabel yourAmountLbl;
  private JLabel npcAmountLbl;
  private JLabel betAmountLbl;
  // ~~~ Buttons ~~~
  private JButton increaseBetBtn;
  private JButton decreaseBetBtn;
  private JButton numberBtn;
  private JButton lockNumberBtn;
  private JButton spinRouletteBtn;

  RouletteGUI() {
    roulette = new Roulette();
    roulette.register(this);

    // ~~~ Labels ~~~
    rouletteLabel = new JLabel();
    rouletteLabel.setText("Roulette");

    yourAmountLbl = new JLabel();
    yourAmountLbl.setText("Your amount: " + roulette.getUser().getPlayerCredits());

    npcAmountLbl = new JLabel();
    npcAmountLbl.setText("NPC amount: " + roulette.getNpc().getPlayerCredits());

    betAmountLbl = new JLabel();
    betAmountLbl.setText("Bet: " + roulette.getUser().getBetObject().getBet());

    // ~~~ Buttons ~~
    increaseBetBtn = new JButton("+");
    decreaseBetBtn = new JButton("-");
    lockNumberBtn = new JButton("Lock");
    spinRouletteBtn = new JButton("Spin roulette!");
    numberBtn = new JButton();

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

    // add labels to the content panel
    contentPane.add(rouletteLabel, contentPane); // mby redundant
    contentPane.add(yourAmountLbl, contentPane);
    contentPane.add(npcAmountLbl, contentPane);
    contentPane.add(betAmountLbl, contentPane);

    // Buttons
    contentPane.add(increaseBetBtn);
    contentPane.add(decreaseBetBtn);
    contentPane.add(lockNumberBtn);
    contentPane.add(spinRouletteBtn);
    increaseBetBtn.addActionListener(this);
    decreaseBetBtn.addActionListener(this);
    lockNumberBtn.addActionListener(this);
    spinRouletteBtn.addActionListener(this);

    // Number buttons you can pick
    for(Button element : roulette.getButtonsList()) {
      contentPane.add(createButton(numberBtn, element.getNumber() + ""));
    }

    // set the initial frame size
    frame.setSize(1100, 560);

    // center the frame and make it visible
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    frame.setLocation(d.width/2 - frame.getWidth()/2, d.height/2 - frame.getHeight()/2);
    frame.setVisible(true);
  }

  // Create buttons with their action listeners
  private JButton createButton(JButton btn, String text) {
    numberBtn = new JButton(text);
    numberBtn.addActionListener(this);
    return numberBtn;
  }

  // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ GETTERS ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

  // Get roulette
  public Roulette getRoulette() {
    return roulette;
  }

  public void actionPerformed(ActionEvent e) {
    if(e.getSource() == increaseBetBtn) {
      System.out.println("Increase btn pressed");
      roulette.getUser().getBetObject().increaseBet();
      System.out.println(roulette.getUser().getBetObject().getBet());
    }
    if(e.getSource() == decreaseBetBtn) {
      System.out.println("Decrease btn pressed");
    }
    if(e.getSource() == lockNumberBtn) {
      System.out.println("Lock btn pressed");
      roulette.setNumberLocked(true);
    }
    if(e.getSource() == spinRouletteBtn) {
      System.out.println("Spinning the roulette!");
      roulette.spinRoulette();
    }

    if(!roulette.isNumberLocked()) {
      try {
        roulette.setSelectedNumber(Integer.parseInt(e.getActionCommand()));
        System.out.println("Selected number is " + roulette.getSelectedNumber());
      } catch (Exception ex) { }
    }
  }

  @Override
  public void update(Object obj) {
    if(obj instanceof Roulette) {
      betAmountLbl.setText(9999 + "");
    }
  }
}
