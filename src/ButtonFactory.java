public class ButtonFactory {

  // not sure if public Button
  public static Button createButton(String colour) {
    switch(colour) {
      case "red":
        return null;
      case "black":
        // parameter is element.value
        return new BlackButton(10);
      case "green":
        return null;
      default:
        return null;
    }
  }
}
