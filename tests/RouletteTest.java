import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RouletteTest {
  private Button[] buttonsList = new Button[37];
  private final int buttonLimit = 37; // including the 0
  private Roulette roulette;

  RouletteTest() {
    roulette = new Roulette();
  }

  @Test
  void makeButtons() {
    for(int i=0;i<buttonLimit;i++) {
      ButtonFactory.createButton("black");
    }
  }

  // User
  @Test
  void makeALosingBet() {
    roulette.getUser().getBetObject().increaseBet(); // bet 500;
    roulette.setSelectedNumber(50); // Never gonna win with that number hoe
    roulette.spinRoulette(); // play the game
    assertEquals(9500, roulette.getUser().getPlayerCredits()); // he lost 500, so total is now 9500
  }

  // User
  @Test
  void makeWinningBet() {
    roulette.getUser().getBetObject().increaseBet(); // bet 500;
    roulette.setSelectedNumber(20); // Is the correct hardcoded number
    roulette.spinRoulette(); // play the game
    assertEquals(10500, roulette.getUser().getPlayerCredits()); // he lost 500, so total is now 10500
  }

  @Test
  // NPC
  void npcMakeLosingBet() {
    roulette.getNpc().getBetObject().increaseBet(); // bet 500
    roulette.getNpc().getBetObject().increaseBet(); // bet 500 more
    roulette.setNpcSelectedNumber(40); // No match
    roulette.spinRoulette();
    assertEquals(9000, roulette.getNpc().getPlayerCredits());
  }

  @Test
  // NPC
  void npcMakeWinningBet() {
    roulette.getNpc().getBetObject().increaseBet(); // bet 500
    roulette.getNpc().getBetObject().increaseBet(); // bet 500 more
    roulette.getNpc().getBetObject().increaseBet(); // more
    roulette.setNpcSelectedNumber(20); // Winning number
    roulette.spinRoulette();
    assertEquals(11500, roulette.getNpc().getPlayerCredits());
  }

  @Test
  void makeBetAndResetBet() {
    roulette.getNpc().getBetObject().increaseBet(); // bet 500
    roulette.setNpcSelectedNumber(20); // winning number
    roulette.spinRoulette(); // play the game
    assertEquals(10500, roulette.getNpc().getPlayerCredits()); // check if you won 500
    roulette.spinRoulette(); // play the game again
    assertEquals(10500, roulette.getNpc().getPlayerCredits()); // check if you have same credits, since bet was reset
  }

}