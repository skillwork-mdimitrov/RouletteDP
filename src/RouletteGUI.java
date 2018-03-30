import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class RouletteGUI implements Observer{
  private Roulette roulette;
  private JFrame frame;

  // ~~ Labels
  private JLabel rouletteLabel;
  private JLabel yourAmountLbl;
  private JLabel npcAmountLbl;
  private JLabel betAmountLbl;
  private JLabel selectNumberLbl;

  // ~~~ Buttons ~~~
  private JButton increaseBetBtn;
  private JButton decreaseBetBtn;
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
    rouletteLabel.setFont(new Font("Helvetica", Font.BOLD, 25));
    rouletteLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

    yourAmountLbl = new JLabel();
    yourAmountLbl.setText("|| Your amount: " + roulette.getUser().getPlayerCredits() + "||");

    npcAmountLbl = new JLabel();
    npcAmountLbl.setText("|| NPC amount: " + roulette.getNpc().getPlayerCredits() + "||");

    betAmountLbl = new JLabel();
    betAmountLbl.setText("|| Bet: " + roulette.getUser().getBetObject().getBet() + "||");

    selectNumberLbl = new JLabel();
    selectNumberLbl.setText("|| Choose a number ||");

    // ~~~ Buttons ~~
    increaseBetBtn = new JButton("+");
    increaseBetBtn.setBorder(BorderFactory.createEmptyBorder(10, 12, 10, 12));
    increaseBetBtn.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        roulette.getUser().getBetObject().increaseBet();
      }
    });

    decreaseBetBtn = new JButton("-");
    decreaseBetBtn.setBorder(BorderFactory.createEmptyBorder(10, 12, 10, 12));
    decreaseBetBtn.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        roulette.getUser().getBetObject().decreaseBet();
      }
    });

    lockNumberBtn = new JButton("Lock");
    lockNumberBtn.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        roulette.setNumberLocked(true);
        roulette.getUser().getBetObject().confirmBet();
        roulette.placeBet();
      }
    });

    spinRouletteBtn = new JButton("Spin roulette!");
    spinRouletteBtn.setFont(new Font("Helvetica", Font.BOLD, 18));
    spinRouletteBtn.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
    // TODO Implement spin roulette logic
    spinRouletteBtn.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        roulette.spinRoulette();
      }
    });

    makeFrame(); // don't move upwards
  }

  private void makeFrame() {
    frame = new JFrame("Roulette - the game");

    // set the initial frame size
    frame.setSize(1100, 600);

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

    // Center (number buttons and amounts/bets)
    JPanel centerPanel = new JPanel(new BorderLayout());
    makeButtonGrid(centerPanel);
    makeAmountAndBet(centerPanel);
    contentPane.add(centerPanel, BorderLayout.CENTER);

    // Spin it button
    contentPane.add(spinRouletteBtn, BorderLayout.PAGE_END);
  }

  // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ GETTERS ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

  // Get roulette
  public Roulette getRoulette() {
    return roulette;
  }

  // scrap this POTENTIALLY
//  public void actionPerformed(ActionEvent e) {
//    if(!roulette.isNumberLocked()) {
//      try {
//        roulette.setSelectedNumber(Integer.parseInt(e.getActionCommand()));
//        selectNumberLbl.setText("Your number: " + roulette.getSelectedNumber() + "");
//      } catch (Exception ex) { }
//    }
//  }

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

  /***
   * Creates the grid with the number buttons
   * @param panel The total container to store the grid in
   */
  private void makeButtonGrid(JPanel panel)
  {
    JPanel buttonsPanel = new JPanel(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    c.insets = new Insets(5, 5, 5, 5);
    c.weightx = 0.5;
    c.ipady = 30;

    // TODO Make rows and columns dynamic?
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

    panel.add(buttonsPanel, BorderLayout.PAGE_START);
  }

  /***
   * Creates the amount and bet section (for the player and the NPC)
   * @param panel The total container to store the section in
   */
  private void makeAmountAndBet(JPanel panel)
  {
    JPanel amountAndBetPanel = new JPanel(new BorderLayout());
    amountAndBetPanel.setBorder(BorderFactory.createEmptyBorder(0, 50, 50, 50));

    JPanel yourAmountAndBet = new JPanel(new BorderLayout());
    JPanel npcAmountAndBet = new JPanel(new BorderLayout());

    // Your amount and bet
    yourAmountAndBet.add(yourAmountLbl, BorderLayout.PAGE_START);
    JPanel yourBetPanel = new JPanel(new BorderLayout());
    yourBetPanel.add(increaseBetBtn, BorderLayout.LINE_START);
    betAmountLbl.setHorizontalAlignment(SwingConstants.CENTER);
    yourBetPanel.add(betAmountLbl, BorderLayout.CENTER);
    yourBetPanel.add(decreaseBetBtn, BorderLayout.LINE_END);
    yourBetPanel.add(lockNumberBtn, BorderLayout.PAGE_END);
    yourAmountAndBet.add(yourBetPanel, BorderLayout.PAGE_END);

    // NPC amount and bet
    npcAmountAndBet.add(npcAmountLbl, BorderLayout.PAGE_START);
    npcAmountAndBet.add(new JLabel("A very nice bet"), BorderLayout.PAGE_END);

    amountAndBetPanel.add(yourAmountAndBet, BorderLayout.LINE_START);
    amountAndBetPanel.add(npcAmountAndBet, BorderLayout.LINE_END);

    panel.add(amountAndBetPanel, BorderLayout.PAGE_END);
  }
}