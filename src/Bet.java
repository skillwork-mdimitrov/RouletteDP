// Who handles if bet goes below 0?

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Bet implements Subject {
  private int betNumber = 0;
  private int bet = 0; // initially 0
  private boolean confirmedBet = false;
  private List<Observer> observers;

  Bet() {
    observers = new ArrayList<Observer>();
  }

  /// Manipulate Bet
  public void increaseBet() {
    // Possibly add if confirmed bet = false check
    if(!isConfirmedBet()) {
      bet += 500;
      notifyObservers();
    }
  }
  public void decreaseBet() {
    // Possibly add if confirmed bet = false check
    if(!isConfirmedBet()) {
      bet -= 500;
      notifyObservers();
    }
  }

  public int getBetNumber(){return betNumber;}
  public void setBetNumber(int newBetNumber){betNumber = newBetNumber;}

  public void setBet(int bet) {
    this.bet = bet;
  }

  // Finalize the bet
  public void confirmBet() {
    confirmedBet = true;
  }

  // Reset bet
  public void resetBet() {
    bet = 0;
  }

  // Retrieve how much is the bet
  public int getBet() {
    return bet;
  }

  public boolean isConfirmedBet() {
    return confirmedBet;
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
