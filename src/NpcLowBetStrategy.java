public class NpcLowBetStrategy implements NpcBetStrategy {
  private int betAmount;

  NpcLowBetStrategy() {
    setBetAmount(1000);
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
