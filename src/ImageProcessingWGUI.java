import controller.IPControllerGUIImpl;
import model.IPModelImpl;
import view.IPViewGUIImpl;

public class ImageProcessingWGUI {
  public static void main(String[] args) {
    IPModelImpl m1 = new IPModelImpl();
    IPViewGUIImpl v1 = new IPViewGUIImpl(m1);
    IPControllerGUIImpl c1 = new IPControllerGUIImpl(m1, v1);
    c1.startIPGUI();
  }
}
