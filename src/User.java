import javax.swing.*;

public class User implements Player{
  private int playerCredits; // react to changes, observer
  private Bet bet;

  User() {
    playerCredits = 10000; // initial credits
    bet = new Bet();
  }

  @Override
  /* Get player credits */
  public int getPlayerCredits() {
    return playerCredits;
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
  public Bet getBetObject() {
    return bet;
  }

  @Override
  public void update(Object obj) {

    // Roulette pushing notifications
    Roulette rouletteObj = (Roulette)obj;

    // Check if you have won from that spin
    if (rouletteObj.getState() instanceof SpinRouletteState && rouletteObj.getIsSpinning())
    {
      if(rouletteObj.didYouWin()) {
        // You won, increase credits
        increasePlayerCredits(getBetObject().getBet());
        JOptionPane.showMessageDialog(null, "Congratulations! You won " + getBetObject().getBet() + "!");
      }
      else {
        // You lost, deduct credits
        decreasePlayerCredits(getBetObject().getBet());
        JOptionPane.showMessageDialog(null, "Unfortunately you lost " + getBetObject().getBet() + "...");
      }

      getBetObject().resetBet();
    }
  }

  @Override
  public void setBetNumber(int newBetNumber) {
    bet.setBetNumber(newBetNumber);
  }

}
