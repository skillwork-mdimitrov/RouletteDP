import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Roulette implements Subject {

  private Button[] buttonsList;
  private final int buttonLimit = 37; // including the 0
  private User user;
  private boolean testing = false;

  private List<Observer> observers;


  private int winningNumber = 20; // hardcoded, needs to be random
  private int selectedNumber;

  Roulette() {
    // ~~~ BUTTONS ~~~
    buttonsList = new Button[buttonLimit]; // set the size of the button list
    makeButtons();

    // ~~~ PLAYERS ~~~
    user = new User(); // Create the user playing

    // ~~~ OBSERVER ~~~
    observers = new ArrayList<Observer>();
    register(user); // add the user to the list of observers

    // ~~~ ROULETTE ~~~
    spinRoulette();

    // Create the NPC

    // Testing
    if(testing) {
      for (Button element : buttonsList) {
        System.out.println(element);
      }
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

  // ~~~ SETTERS ~~~

  // Setter player number
  public void setSelectedNumber(int selectedNumber) {
    this.selectedNumber = selectedNumber;
  }

  // Setter winning number
  public void setWinningNumber(int winningNumber) {
    this.winningNumber = winningNumber;
  }

  // ~~~ GETTERS ~~~

  // Get buttons
  public Button[] getButtonsList() {
    return buttonsList;
  }

  // Get the selected by the player number
  public int getSelectedNumber() {
    return selectedNumber;
  }

  // Get the winning number
  public int getWinningNumber() {
    return winningNumber;
  }

  public void spinRoulette() {
    if(getSelectedNumber() == getWinningNumber()) {
      // you won some credits
      notifyObservers();
    }
    else {
      // you lost some credits
      notifyObservers();
    }
  }

  @Override
  public void register(Observer observer) {
    if(observer != null) {
      this.observers.add(observer);
    }
    else {
      System.out.println("Null object passed");
    }
  }

  public void unregister(Observer observer) {
    if(observer != null) {
      this.observers.remove(observer);
    }
  }

  @Override
  // check this ...
  public void notifyObservers() {
    Iterator<Observer> it = observers.iterator();
    while (it.hasNext()) {
      System.out.println("WTF man");
      Observer observer = it.next();
      observer.update(this);
    }

//    // to implement
//    switch (results) {
//      case "win":
//
//      case "lose":
//        // blabla
//    }

  }



  // More Subject methods to come

}
