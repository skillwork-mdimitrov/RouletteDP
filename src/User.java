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
  public void update() {

  }

}
