public class NpcPlayer implements Player{
  private int playerCredits; // react to changes, observer
  private Bet bet;

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
    if(obj instanceof Roulette) {
      NpcPlayer npc = ((Roulette) obj).getNpc();

      if(((Roulette) obj).didNPCWon()) {
        npc.increasePlayerCredits(npc.getBetObject().getBet());
      }
      else {
        npc.decreasePlayerCredits(npc.getBetObject().getBet());
      }
      // Reset bet needed
      npc.getBetObject().resetBet();
    }
  }
}
