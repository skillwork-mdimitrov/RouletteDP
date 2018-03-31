import javax.swing.*;

public class SelectNumberState implements GameState
{

    @Override
    public void selectNumber(Roulette roulette) {
        roulette.setState(new PlaceBetState());
    }

    @Override
    public void placeBet(Roulette roulette) {
        JOptionPane.showMessageDialog(null, "Please select a number first");
    }

    @Override
    public void spinRoulette(Roulette roulette) {
        JOptionPane.showMessageDialog(null, "Please select a number first");
    }

    @Override
    public void toGameOver(Roulette roulette) {
        roulette.setState(new GameOverState());
    }

    @Override
    public void toYouWin(Roulette roulette) {
        roulette.setState(new YouWinState());
    }
}
