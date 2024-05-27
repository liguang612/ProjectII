import java.awt.Color;

import javax.swing.UIManager;

import Resources.Constants;
import Resources.Constants.DialogType;
import Server.DBConnection;
import View.Dialog;
import View.Home;

public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        UIManager.put("Button.font", Constants.getFont(Constants.FontType.QUICKSAND_REGULAR));
        UIManager.put("Label.font", Constants.getFont(Constants.FontType.QUICKSAND_REGULAR));
        UIManager.put("Panel.background", Color.WHITE);
        UIManager.put("RadioButton.background", Color.WHITE);
        UIManager.put("RadioButton.font", Constants.getFont(Constants.FontType.QUICKSAND_REGULAR));
        UIManager.put("TextField.font", Constants.getFont(Constants.FontType.QUICKSAND_REGULAR));

        if (DBConnection.connect())
            new Home();
        else
            new Dialog("Không thể kết nối tới máy chủ, vui lòng thử lại (Mã lỗi: 403)", DialogType.ERROR, null);
    }
}
