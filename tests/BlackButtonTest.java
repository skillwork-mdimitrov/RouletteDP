import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BlackButtonTest {
  private BlackButton button = new BlackButton(5);

  @Test
  void addActionListener() {
  }

  @Test
  void getColour() {
    assertEquals("black", button.getColour());
  }

  @Test
  void getNumber() {
    assertEquals(5, button.getNumber());
  }

}