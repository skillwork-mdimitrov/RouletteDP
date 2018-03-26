import javax.swing.*;

public class ButtonFactory {

  // not sure if public Button
  public static Button createButton(String colour, int number) {
    switch(colour) {
      case "black":
        return new BlackButton(number);
      case "red":
        return new RedButton(number);
      case "green":
        return new GreenButton(0);
      default:
        return null;
    }
  }
}
