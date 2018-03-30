import javax.swing.*;

public class SpinRouletteState implements GameState {
    @Override
    public void selectNumber(Roulette roulette) {
        JOptionPane.showMessageDialog(null, "Please spin the roulette");
    }

    @Override
    public void placeBet(Roulette roulette) {
        roulette.setState(new PlaceBetState());
        JOptionPane.showMessageDialog(null, "Back to Place Bet State!");
    }

    @Override
    public void spinRoulette(Roulette roulette) {
        roulette.setState(new SelectNumberState());
        JOptionPane.showMessageDialog(null, "Spin to win!");
    }
}
