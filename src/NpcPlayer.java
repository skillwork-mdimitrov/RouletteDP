import javax.swing.*;

public class NpcPlayer implements Player{
  private int playerCredits; // react to changes, observer
  private Bet bet;
  private NpcBetStrategy strategy;

  NpcPlayer() {
    playerCredits = 10000; // initial credits
    bet = new Bet();
  }

  /* Increase player credits */
  public void increasePlayerCredits(int byHowMuch) {
    this.playerCredits += byHowMuch;
  }

  /* Decrease player credits */
  public void decreasePlayerCredits(int byHowMuch) {
    this.playerCredits -= byHowMuch;
  }

  @Override
  /* Get player credits */
  public int getPlayerCredits() {
    return playerCredits;
  }

  @Override
  /* Get bet */
  public Bet getBetObject() {
    return bet;
  }

  @Override
  public void update(Object obj) {

    // Roulette pushing notifications
    Roulette rouletteObj = (Roulette)obj;

    // Check if you have won from that spin
    if (rouletteObj.getState() instanceof SpinRouletteState && rouletteObj.getIsSpinning()) {

      // Make a bet, accordingly to credits
      getBetObject().setBet(strategicallyMakeBet());

      if (((Roulette) obj).didNPCWon()) {
        increasePlayerCredits(getBetObject().getBet());
        JOptionPane.showMessageDialog(null, "Uh oh... NPC won " + getBetObject().getBet() + "");
      } else {
        decreasePlayerCredits(getBetObject().getBet());
        JOptionPane.showMessageDialog(null, "NPC lost " + getBetObject().getBet() );
      }
      // Reset the bet
      getBetObject().resetBet();
    }
  }

  @Override
  public void setBetNumber(int newBetNumber) {
    bet.setBetNumber(newBetNumber);
  }

  private int strategicallyMakeBet() {
    int theBet = 500; // default bet
    // NPC is playing it safe
    if(getPlayerCredits() < 2000) {
      strategy = new NpcLowBetStrategy();
      theBet = strategy.getBetAmount();
    }
    // NPC is doing okay
    else if(getPlayerCredits() > 2000 && getPlayerCredits() <= 10000) {
      strategy = new NpcMediumBetStrategy();
      theBet = strategy.getBetAmount();
    }
    // NPC feels adventurous
    else {
      strategy = new NpcBerserkBetStrategy();
      theBet = strategy.getBetAmount();
    }
    return theBet;
  }
}
