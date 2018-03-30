public class User implements Player{
  private int playerCredits; // react to changes, observer
  private Bet bet;

  User() {
    playerCredits = 10000; // initial credits
    bet = new Bet();
  }

  @Override
  /* Get player credits */
  public int getPlayerCredits() {
    return playerCredits;
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
  public Bet getBetObject() {
    return bet;
  }

  @Override
  public void update(Object obj) {

    // Roulette is pushing the notifications
    if(obj instanceof Roulette) {
      User user = ((Roulette) obj).getUser();

      // Check if you have won from that spin
      if(((Roulette) obj).didYouWin()) {
        // You won, increase credits
        user.increasePlayerCredits(user.getBetObject().getBet());
      }
      else {
        // You lost, deduct credits
        user.decreasePlayerCredits(user.getBetObject().getBet());
      }
    }
  }

  @Override
  public void setBetNumber(int newBetNumber) {
    bet.setBetNumber(newBetNumber);
  }

}
