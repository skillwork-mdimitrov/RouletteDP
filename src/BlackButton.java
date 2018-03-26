/*
Problems/Missing
* addActionListener();

 */

import javax.swing.*;
import java.awt.*;

public class BlackButton extends JButton implements Button {
  private final Color color = Color.BLACK; // won't change
  private int number;

  // Constructor
  BlackButton(int number)
  {
    setText(Integer.toString(number));
    setNumber(number);
    setBackground(color);
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
  public Color getColour() {
    return this.color;
  }
  // Get the number of the button
  @Override
  public int getNumber() {
    return this.number;
  }

}
