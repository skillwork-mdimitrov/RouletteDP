/*
Potentially missing
* Extend JButton

 */

public interface Button {
  // Number is referring to physical number of the button (1-36)

  // Setters
  public void setNumber(int number);

  // Getters
  public String getColour();
  public int getNumber();

  public void addActionListener();
}
