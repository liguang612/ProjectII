package View.Components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Resources.Constants;
import Resources.Tools;
import View.Login;

public class Header extends JPanel {
    private Button account, home, login, signup;
    private int isSelected = 0;
    private JPanel leftPanel, rightPanel;

    public Header(JFrame parentFrame, GridBagConstraints gbc) {
        super(new BorderLayout());

        account = new Button("Tài khoản");

        home = new Button("Trang chủ");
        home.setBackground(Color.WHITE);

        login = new Button("Đăng nhập");
        login.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                parentFrame.getContentPane().remove(1);
                parentFrame.getContentPane().add(new Login(), gbc);

                parentFrame.revalidate();
                parentFrame.repaint();
            }
        });
        login.setMargin(new Insets(12, 24, 12, 24));

        signup = new Button("Đăng ký");
        signup.setBackground(Constants.blue01);
        signup.setForeground(Color.WHITE);
        signup.setMargin(new Insets(12, 24, 12, 24));

        changeUI();

        leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftPanel.setBackground(Color.WHITE);
        leftPanel.add(new JLabel(Tools.resize(new ImageIcon(Constants.imagePath + "logo.png"), 80, 288)));

        rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 0));
        rightPanel.setBackground(Color.WHITE);
        rightPanel.add(home);
        rightPanel.add(login);
        rightPanel.add(signup);

        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(25, 121, 25, 121));
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.EAST);
    }

    void changeUI() {
        if (isSelected == 0) {
            home.setBorder(BorderFactory.createMatteBorder(0, 0, 4, 0, Constants.blue01));
        }
    }
}
