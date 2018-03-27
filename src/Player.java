public interface Player extends Observer {
  public int getPlayerCredits();
  public Bet getBetObject();
  public void update(Object obj);
  public void setBetNumber(int newBetNumber);
  public void increasePlayerCredits(int byHowMuch);
  public void decreasePlayerCredits(int byHowMuch);
}
