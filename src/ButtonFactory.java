public class ButtonFactory {

  // not sure if public Button
  public static Button createButton(String colour) {
    switch(colour) {
      case "black":
        // parameter is element.value
        return new BlackButton(10);
      case "red":
        return new RedButton(15);
      case "green":
        return new GreenButton(0);
      default:
        return null;
    }
  }
}
