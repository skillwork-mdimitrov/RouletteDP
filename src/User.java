public class User implements Player{
  private int playerCredits; // react to changes, observer
  private Bet bet;

  User() {
    playerCredits = 10000; // initial credits
    bet = new Bet();
  }

  public int getPlayerCredits() {
    return playerCredits;
  }

  @Override
  public Bet getBetObject() {
    return bet;
  }

  @Override
  public void update(Object obj) {
    // if it's winning +
    // if it's losing -
    if(obj instanceof Roulette) {
      System.out.println("fuck you");
    }
    playerCredits -= getBetObject().getBet();
  }

}
