import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
  private User user = new User();

  @Test
  void getPlayerCredits() {
    assertEquals(10000, user.getPlayerCredits());
  }

  @Test
  void getBetObject() {
    assertNotNull(user.getBetObject());
  }

  @Test
  void makeABet() {
    user.getBetObject().increaseBet();
    assertEquals(500, user.getBetObject().getBet());
  }
}