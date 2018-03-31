public class YouWinState implements GameState
{

    @Override
    public void selectNumber(Roulette roulette) {
        roulette.setState(new SelectNumberState());
    }

    @Override
    public void placeBet(Roulette roulette) {

    }

    @Override
    public void spinRoulette(Roulette roulette) {

    }

    @Override
    public void toGameOver(Roulette roulette) {

    }

    @Override
    public void toYouWin(Roulette roulette) {

    }
}