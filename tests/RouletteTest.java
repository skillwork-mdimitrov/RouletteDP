import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RouletteTest {
  private Button[] buttonsList = new Button[37];
  private final int buttonLimit = 37; // including the 0

  @Test
  void notifyObservers() {
  }

  @Test
  void makeButtons() {
    for(int i=0;i<buttonLimit;i++) {
      ButtonFactory.createButton("black");
    }
  }
}