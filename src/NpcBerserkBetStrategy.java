public class NpcBerserkBetStrategy implements NpcBetStrategy {
  private int betAmount;

  NpcBerserkBetStrategy() {
    setBetAmount(2500);
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
