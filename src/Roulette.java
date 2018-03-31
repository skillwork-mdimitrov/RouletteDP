import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Roulette implements Subject, ActionListener {

  // ~~~ BUTTONS ~~~
  private Button[] buttonsList;
  private final int buttonLimit = 37; // including the 0

  // ~~~ USERS ~~~
  private User user;
  private NpcPlayer npc;

  // ~~~ States ~~~
  private GameState currentState;

  // ~~~ ROULETTE ~~~
  private int winningNumber = 20; // hardcoded, needs to be random
  private boolean youWon = false;
  private boolean npcWon = false;
  private boolean numberLocked = false;
  private List<Observer> observers;

  // ~~~ TESTING
  private boolean testing = false;



  Roulette() {
    buttonsList = new Button[buttonLimit]; // Instantiate array, set the size of the button list
    makeButtons();
    user = new User(); // Create the user playing
    npc = new NpcPlayer(); // Create the NPC
    // rouletteGUI = new RouletteGUI(); // Create the RouletteGUI (just to send notifications)
    observers = new ArrayList<Observer>();

    // Register observers
    register(user); // let the user observe
    register(npc); // let the npc observe

    // Set state
    currentState = new SelectNumberState();

    // Testing
    if(testing) {
      for (Button element : buttonsList) {
        System.out.println(element);
      }
    }

  }

  // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ SETTERS ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

  /* Increase bet */
  public void increaseUserBet(){
    user.getBetObject().increaseBet();
    notifyObservers();
  }

  /* Decrease bet */
  public void decreaseUserBet(){
    user.getBetObject().decreaseBet();
    notifyObservers();
  }

  /* Set winning number */
  public void setWinningNumber(int winningNumber) {
    this.winningNumber = winningNumber;
  }

  /* Lock the selected number */
  public void setNumberLocked(boolean numberLocked) {
    this.numberLocked = numberLocked;
  }

  // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ GETTERS ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

  /* Get buttons */
  public Button[] getButtonsList() {
    return buttonsList;
  }

  // Get the winning number
  public int getWinningNumber() {
    return winningNumber;
  }

  // Find if you win or not
  public boolean didYouWin() {
    return youWon;
  }

  // Find if the NPC won or not
  public boolean didNPCWon() {
    return npcWon;
  }

  // Get user object
  public User getUser() {
    return user;
  }

  // Get NPC object
  public NpcPlayer getNpc() {
    return npc;
  }

  // Get selected number lock status
  public boolean isNumberLocked() {
    return numberLocked;
  }

  // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

  // Make buttons
  public void makeButtons() {
    for(int i=0; i<buttonLimit; i++) {
      if(i==0) {
        buttonsList[0] = ButtonFactory.createButton("green", 0);
      }
      else if(i%2 == 0) {
        buttonsList[i] = ButtonFactory.createButton("black", i );
      }
      else {
        buttonsList[i] = ButtonFactory.createButton("red", i);
      }

      buttonsList[i].addListener(this);
    }
  }

  // Enable or disable all number buttons
  public void enableAllNumberButtons(boolean enable)
  {
    for (Button button : buttonsList) {
      JButton jButton = (JButton)button;
      jButton.setEnabled(enable);
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

  @Override
  public void unregister(Observer observer) {
    if(observer != null) {
      this.observers.remove(observer);
    }
  }

  @Override
  public void notifyObservers() {
    Iterator<Observer> it = observers.iterator();
    while (it.hasNext()) {
      Observer observer = it.next();
      observer.update(this);
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    Button buttonPressed = (Button)e.getSource();
    this.selectNumber(buttonPressed.getNumber());
  }

  // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ STATE HANDLERS ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
  public GameState getState(){ return currentState; }
  public void setState(GameState newState){
    currentState = newState;
  }

  public void selectNumber(int number){
    user.setBetNumber(number);

    // Randomly let the opponent bet here

    currentState.selectNumber(this);

    // Notify the observers
    notifyObservers();
  }

  public void placeBet(){

    currentState.placeBet(this);
    notifyObservers();
  }

  public void spinRoulette(){
    youWon = user.getBetObject().getBetNumber() == getWinningNumber();
    npcWon = npc.getBetObject().getBetNumber() == getWinningNumber();
    notifyObservers();

    currentState.spinRoulette(this);
    notifyObservers();
  }

  // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
}
