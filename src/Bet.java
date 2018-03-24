// Who handles if bet goes below 0?
// Remove confirmBet or do smth with it

public class Bet {
  private int bet = 0; // initially 0
  private boolean confirmedBet = false;

  /// Manipulate Bet
  public void increaseBet() {
    // Possibly add if confirmed bet = false check
    bet += 500;
  }
  public void decreaseBet() {
    // Possibly add if confirmed bet = false check
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
