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
      ButtonFactory.createButton("black", i);
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
    roulette.setNpcSelectedNumber(40); // No match
    roulette.spinRoulette(); // Play the game
    /* NPC starting with 10000, he will use the Medium strategy and bet 1500, which he will lose */
    assertEquals(8500, roulette.getNpc().getPlayerCredits());
  }

  @Test
  // NPC
  void npcMakeWinningBet() {
    roulette.setNpcSelectedNumber(20); // Winning number
    roulette.spinRoulette(); // Play the game
    /* NPC starting with 10000, he will use the Medium strategy and bet 1500, which he will win */
    assertEquals(11500, roulette.getNpc().getPlayerCredits());
  }

  @Test
  void npcStrategicBets() {
    roulette.setNpcSelectedNumber(20); // winning number
    roulette.spinRoulette(); // play the game
    assertEquals(11500, roulette.getNpc().getPlayerCredits()); // check if you won 1500 (Medium bet)
    roulette.spinRoulette(); // play the game again, NPC now bets 2500 since his credits are above 10 000
    assertEquals(14000, roulette.getNpc().getPlayerCredits());
  }

}