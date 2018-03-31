public interface GameState
{
    public void selectNumber(Roulette roulette);
    public void placeBet(Roulette roulette);
    public void spinRoulette(Roulette roulette);
    public void toGameOver(Roulette roulette);
    public void toYouWin(Roulette roulette);
}
