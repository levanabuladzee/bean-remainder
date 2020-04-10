import view.RemainderView;
import controller.RemainderController;

public class Main {
    public static void main(String[] args) {
        RemainderView view = new RemainderView();
        RemainderController controller = new RemainderController();
        view.getMenu();

        while (true) {
            controller.getInput();
        }
    }
}
