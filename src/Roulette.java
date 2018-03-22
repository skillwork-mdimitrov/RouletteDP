public class Roulette implements Subject {
  private Button[] buttonsList;
  private final int buttonLimit = 36; // 37 buttons, including the 0
  //

  @Override
  public void notifyObservers() {
    // to implement
  }

  Roulette() {
    Button blackBtnTest = ButtonFactory.createButton("black");

    // set the size of the button list
    buttonsList = new Button[buttonLimit];

    // add a button to the list
    buttonsList[0] = blackBtnTest;
    System.out.println(buttonsList.length);

  }

}
