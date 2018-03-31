import javax.swing.*;

public class PlaceBetState implements GameState {
    @Override
    public void selectNumber(Roulette roulette) {
        roulette.setState(new SelectNumberState());
        JOptionPane.showMessageDialog(null, "Back to Select Number State!");
    }

    @Override
    public void placeBet(Roulette roulette) {
        roulette.setState(new SpinRouletteState());
    }

    @Override
    public void spinRoulette(Roulette roulette) {
        JOptionPane.showMessageDialog(null, "Please place a bet first");
    }
}
