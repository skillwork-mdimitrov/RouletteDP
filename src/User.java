public class User implements Player{
  private int playerCredits; // react to changes, observer
  private Bet bet;

  User() {
    playerCredits = 10000; // initial credits
    bet = new Bet();
  }

  /* Get player credits */
  public int getPlayerCredits() {
    return playerCredits;
  }

  /* Set player credits */
  public void adjustPlayerCredits(int howMuch, boolean increase) {
    if(increase) {
      this.playerCredits += howMuch;
    }
    else {
      this.playerCredits -= howMuch;
    }
  }

  @Override
  public Bet getBetObject() {
    return bet;
  }

  @Override
  public void update(Object obj) {
    // if it's winning +
    // if it's losing -

    // When notifications are coming from the Roulette
    if(obj instanceof Roulette) {
      // obj.something
      // adjustPlayerCredits() accordingly
    }

    playerCredits -= getBetObject().getBet();
  }

}
