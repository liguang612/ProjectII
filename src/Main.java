import javax.swing.UIManager;

import Resources.Constants;
import View.Homepage;

public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        UIManager.put("Button.font", Constants.getFont(Constants.FontType.QUICKSAND_REGULAR));
        UIManager.put("Label.font", Constants.getFont(Constants.FontType.QUICKSAND_REGULAR));
        UIManager.put("TextField.font", Constants.getFont(Constants.FontType.QUICKSAND_REGULAR));

        new Homepage();
    }
}
