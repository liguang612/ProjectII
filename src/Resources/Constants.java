package Resources;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.io.File;

public class Constants {
    public static final String fontPath = System.getProperty("user.dir") + "/src/Resources/Fonts/";
    public static final String imagePath = System.getProperty("user.dir") + "/src/Resources/Images/";

    public static final Color blue01 = new Color(0x4A3AFF);

    public static final Color gray01 = new Color(0xE7E7E7);

    public enum FontType {
        QUICKSAND_BOLD, QUICKSAND_REGULAR, ROBOTO_REGULAR
    }

    public static final Font getFont(FontType fontType) {
        try {
            String fontFile = "";

            switch (fontType) {
                case QUICKSAND_BOLD:
                    fontFile = "Quicksand-Bold.ttf";
                    break;
                case QUICKSAND_REGULAR:
                    fontFile = "Quicksand-Regular.ttf";
                    break;
                case ROBOTO_REGULAR:
                    fontFile = "Roboto-Regular.ttf";
                    break;
                default:
                    break;
            }

            Font f = Font.createFont(Font.TRUETYPE_FONT, new File(fontPath + fontFile)).deriveFont(Font.PLAIN)
                    .deriveFont(16f);
            GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(f);

            return f;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new Font("Segoe UI", Font.PLAIN, 16);
    }
}
