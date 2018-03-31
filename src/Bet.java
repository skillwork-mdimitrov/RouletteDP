// Who handles if bet goes below 0?

public class Bet{
  private int betNumber = 0;
  private int bet = 0; // initially 0
  private boolean confirmedBet = false;

  /// Manipulate Bet
  public void increaseBet() {
    // Possibly add if confirmed bet = false check
    if(!isConfirmedBet()) {
      bet += 500;
    }
  }
  public void decreaseBet() {
    // Possibly add if confirmed bet = false check
    if(!isConfirmedBet()) {
      bet -= 500;
    }
  }

  public int getBetNumber(){return betNumber;}
  public void setBetNumber(int newBetNumber){betNumber = newBetNumber;}

  public void setBet(int bet) {
    this.bet = bet;
  }

  // Finalize the bet
  public void confirmBet() {
    confirmedBet = true;
  }

  // Reset bet
  public void resetBet() {
    bet = 0;
    confirmedBet = false;
  }

  // Retrieve how much is the bet
  public int getBet() {
    return bet;
  }

  public boolean isConfirmedBet() {
    return confirmedBet;
  }
}
