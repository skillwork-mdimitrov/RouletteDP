public class NpcMediumBetStrategy implements NpcBetStrategy {
  private int betAmount;

  NpcMediumBetStrategy() {
    setBetAmount(1500);
  }

  // Set bet amount
  public int getBetAmount() {
    return betAmount;
  }

  // Get bet amount
  public void setBetAmount(int betAmount) {
    this.betAmount = betAmount;
  }
}
