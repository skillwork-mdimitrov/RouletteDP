import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Roulette implements Subject {

  // ~~~ BUTTONS ~~~
  private Button[] buttonsList;
  private final int buttonLimit = 37; // including the 0
  // ~~~ USERS ~~~
  private User user;
  private NpcPlayer npc;
  // ~~~ ROULETTE ~~~
  private int winningNumber = 20; // hardcoded, needs to be random
  private int selectedNumber; // Player's number
  private int npcSelectedNumber; // NPC's number
  private boolean youWon = false;
  private boolean npcWon = false;
  private boolean numberLocked = false;
  private RouletteGUI rouletteGUI;
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

    // Testing
    if(testing) {
      for (Button element : buttonsList) {
        System.out.println(element);
      }
    }

  }

  // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ SETTERS ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

  /* Set player number */
  public void setSelectedNumber(int selectedNumber) {
    this.selectedNumber = selectedNumber;
  }

  /* Set NPC number */
  public void setNpcSelectedNumber(int npcSelectedNumber) {
    this.npcSelectedNumber = npcSelectedNumber;
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

  /* Get player number (the number he is betting on) */
  public int getSelectedNumber() {
    return selectedNumber;
  }

  /* Get the NPC number (what is he betting on) */
  public int getNpcSelectedNumber() {
    return npcSelectedNumber;
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

      buttonsList[i].addListener(new NumberButtonActionListener());
    }
  }

  // Play the game
  public void spinRoulette() {

    youWon = getSelectedNumber() == getWinningNumber();
    npcWon = getNpcSelectedNumber() == getWinningNumber();
    notifyObservers();
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
}

class NumberButtonActionListener implements ActionListener
{
  @Override
  public void actionPerformed(ActionEvent e)
  {
    Button buttonPressed = (Button)e.getSource();

    JOptionPane.showMessageDialog(null, "Clicked " + buttonPressed.getNumber() + "!");

//    JFrame newFrame = new JFrame();
//    newFrame.setSize(100, 100);
//    newFrame.setVisible(true);
  }
}
