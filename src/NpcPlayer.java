public class NpcPlayer implements Player{
  private int playerCredits; // react to changes, observer
  private Bet bet;

  NpcPlayer() {
    playerCredits = 10000; // initial credits
    bet = new Bet();
  }

  /* Set player credits */
  public void adjustPlayerCredits(int byHowMuch, boolean increase) {
    if(increase) {
      this.playerCredits += byHowMuch;
    }
    // Decrease
    else {
      this.playerCredits -= byHowMuch;
    }
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
        npc.adjustPlayerCredits(npc.getBetObject().getBet(), true);
      }
      else {
        npc.adjustPlayerCredits(npc.getBetObject().getBet(), false);
      }
    }
  }
}
