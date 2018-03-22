public interface Player extends Observer {
  public int getPlayerCredits();
  public Bet getBetObject();
  public void update();
}
