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
  private JLabel selecteNumberLbl;
  // ~~~ Buttons ~~~
  private JButton increaseBetBtn;
  private JButton decreaseBetBtn;
  private JButton numberBtn;
  private JButton lockNumberBtn;
  private JButton spinRouletteBtn;

  RouletteGUI() {
    roulette = new Roulette();
    roulette.register(this);
    roulette.getUser().getBetObject().register(this);

    // ~~~ Labels ~~~
    rouletteLabel = new JLabel();
    rouletteLabel.setText("Spin the roulette!");
    rouletteLabel.setHorizontalAlignment(SwingConstants.CENTER);

    yourAmountLbl = new JLabel();
    yourAmountLbl.setText("|| Your amount: " + roulette.getUser().getPlayerCredits() + "||");

    npcAmountLbl = new JLabel();
    npcAmountLbl.setText("|| NPC amount: " + roulette.getNpc().getPlayerCredits() + "||");

    betAmountLbl = new JLabel();
    betAmountLbl.setText("|| Bet: " + roulette.getUser().getBetObject().getBet() + "||");

    selecteNumberLbl = new JLabel();
    selecteNumberLbl.setText("|| Choose a number ||");

    // ~~~ Buttons ~~
    increaseBetBtn = new JButton("+");
    decreaseBetBtn = new JButton("-");
    lockNumberBtn = new JButton("Lock");
    spinRouletteBtn = new JButton("Spin roulette!");

    makeFrame(); // don't move upwards
  }

  private void makeFrame() {
    frame = new JFrame("Roulette - the game");

    // set the initial frame size
    frame.setSize(1100, 300);

    // center the frame and make it visible
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    frame.setLocation(d.width/2 - frame.getWidth()/2, d.height/2 - frame.getHeight()/2);
    frame.setVisible(true);

    Container contentPane = frame.getContentPane();

    // Border layout for container
    contentPane.setLayout(new BorderLayout());

    // cosmetic section
    contentPane.setBackground(Color.LIGHT_GRAY);

    // Spin the roulette title
    contentPane.add(rouletteLabel, BorderLayout.PAGE_START);

    // Number buttons
    makeButtonGrid(contentPane);

    // TODO Make rows and columns dynamic?


    // TODO Numnber buttons
    // TODO Amount + bet
    // TODO Spint it button

//    // add labels to the content panel
//    contentPane.add(rouletteLabel, contentPane); // mby redundant
//    contentPane.add(yourAmountLbl, contentPane);
//    contentPane.add(npcAmountLbl, contentPane);
//    contentPane.add(betAmountLbl, contentPane);
//    contentPane.add(selecteNumberLbl, contentPane);
//
//    // Buttons
//    contentPane.add(increaseBetBtn);
//    contentPane.add(decreaseBetBtn);
//    contentPane.add(lockNumberBtn);
//    contentPane.add(spinRouletteBtn);
//    increaseBetBtn.addActionListener(this);
//    decreaseBetBtn.addActionListener(this);
//    lockNumberBtn.addActionListener(this);
//    spinRouletteBtn.addActionListener(this);
//
//    // The 37 buttons you can pick the number you want from
//    for(Button element : roulette.getButtonsList()) {
//      contentPane.add(createButton(numberBtn, element.getNumber() + ""));
//    }


  }

  // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ GETTERS ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

  // Get roulette
  public Roulette getRoulette() {
    return roulette;
  }

  public void actionPerformed(ActionEvent e) {
    if(e.getSource() == increaseBetBtn) {
      roulette.getUser().getBetObject().increaseBet();
    }
    if(e.getSource() == decreaseBetBtn) {
      roulette.getUser().getBetObject().decreaseBet();
    }
    if(e.getSource() == lockNumberBtn) {
      roulette.setNumberLocked(true);
      roulette.getUser().getBetObject().confirmBet();
    }
    if(e.getSource() == spinRouletteBtn) {
      roulette.spinRoulette();
    }

    if(!roulette.isNumberLocked()) {
      try {
        roulette.setSelectedNumber(Integer.parseInt(e.getActionCommand()));
        selecteNumberLbl.setText("Your number: " + roulette.getSelectedNumber() + "");
      } catch (Exception ex) { }
    }
  }

  @Override
  public void update(Object obj) {
    if(obj instanceof Roulette) {
      yourAmountLbl.setText("|| Your amount: " + roulette.getUser().getPlayerCredits() + "||");
      npcAmountLbl.setText("|| Npc amount: " + roulette.getNpc().getPlayerCredits() + "||");
    }
    if(obj instanceof Bet) {
      betAmountLbl.setText("|| Bet: " + roulette.getUser().getBetObject().getBet() + "||");
    }
  }

  private void makeButtonGrid(Container contentPane)
  {
    JPanel buttonsPanel = new JPanel(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    c.insets = new Insets(5, 5, 5, 5);
    c.weightx = 0.5;
    c.ipady = 30;

    int x = 0;
    int y = 0;
    for(Button element : roulette.getButtonsList()) {
      if (element.getNumber() == 0)
      {
        c.gridheight = 3;
        c.fill = GridBagConstraints.VERTICAL;
      }
      else
      {
        c.gridheight = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
      }

      c.gridx = x;
      c.gridy = y;

      buttonsPanel.add((JButton)element, c);

      x++;
      if (element.getNumber() % 12 == 0 && element.getNumber() > 1)
      {
        x = 1;
        y++;
      }
    }

    contentPane.add(buttonsPanel, BorderLayout.CENTER);
  }
}
