import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ButtonFactoryTest {
  private Button[] buttonsList = new Button[37];

  @Test
  void createSingleButton() {
    Button blackButton = ButtonFactory.createButton("black");
    assertNotNull(blackButton);
  }

  @Test
  void createMultipleButtons() {
    for(int i=0;i<10;i++) {
      buttonsList[i] = ButtonFactory.createButton("black");
      assertNotNull(buttonsList[i]);
    }
  }


}