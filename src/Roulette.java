public class Roulette implements Subject {
  private Button[] buttonsList;
  private final int buttonLimit = 37; // including the 0
  private User user;

  @Override
  public void notifyObservers() {
    // to implement
  }

  Roulette() {
    // set the size of the button list
    buttonsList = new Button[buttonLimit];
    makeButtons();

    // Testing
    for (Button element:buttonsList) {
      System.out.println(element);
    }

  }

  // Make buttons
  public void makeButtons() {
    for(int i=0; i<buttonLimit; i++) {
      if(i==0) {
        buttonsList[0] = ButtonFactory.createButton("green");
      }
      else if(i%2 == 0) {
        buttonsList[i] = ButtonFactory.createButton("black");
      }
      else {
        buttonsList[i] = ButtonFactory.createButton("red");
      }
    }
  }

  // Get buttons

}
