public class Bet {
  private int bet;
  private boolean confirmedBet = false;

  /// Manipulate Bet
  public void increaseBet() {
    bet += 500;
  }
  public void decreaseBet() {
    bet -= 500;
  }

  // Finalize the bet
  public void confirmBet() {
    confirmedBet = true;
  }

  // Retrieve how much is the bet
  public int getBet() {
    return bet;
  }
}
