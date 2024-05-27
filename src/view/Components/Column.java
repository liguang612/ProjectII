package View.Components;

import java.awt.*;
import javax.swing.*;

public class Column extends JPanel {
    public Column(int gap, Component... children) {
        super();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        for (int i = 0; i < children.length - 1; i++) {
            add(children[i]);
            add(Box.createVerticalStrut(gap));
        }
        add(children[children.length - 1]);
    }
}