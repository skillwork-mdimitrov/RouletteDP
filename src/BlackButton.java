/*
Problems/Missing
* addActionListener();

 */

public class BlackButton implements Button {
  private final String colour = "black"; // won't change
  private int number;

  // Constructor
  BlackButton(int number) {
    setNumber(number);
  }

  @Override
  // Give the button a number (1-36)
  public void setNumber(int number) {
    this.number = number;
  }

  @Override
  public void addActionListener() {

  }

  /// GETTERS
  @Override
  // Get the colour of the button
  public String getColour() {
    return this.colour;
  }
  // Get the number of the button
  @Override
  public int getNumber() {
    return this.number;
  }

}
