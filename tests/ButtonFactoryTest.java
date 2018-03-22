import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ButtonFactoryTest {

  @Test
  void createButton() {
    Button blackButton = ButtonFactory.createButton("black");
    assertNotNull(blackButton);
  }
}