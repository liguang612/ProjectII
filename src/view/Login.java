package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Controller.AuthCtrl;
import Controller.Callback.UserCallback;
import Resources.Constants;
import Resources.Constants.FontType;
import View.Components.Button;
import View.Components.Center;
import View.Components.PasswordField;
import View.Components.RoundedPanel;
import View.Components.TextField;

public class Login extends JPanel {
    private Button signinButton;
    private RoundedPanel signInPanel;
    private PasswordField password;
    private TextField username;

    public Login(UserCallback callback) {
        super(new BorderLayout());

        JLabel label1 = new JLabel("Đăng nhập"), label2 = new JLabel("Chào mừng bạn quay trở lại"),
                label3 = new JLabel("Quên mật khẩu", JLabel.RIGHT);
        label1.setFont(Constants.getFont(FontType.QUICKSAND_BOLD).deriveFont(37f));

        label2.setFont(Constants.getFont(FontType.QUICKSAND_REGULAR).deriveFont(28f));

        label3.setCursor(new Cursor(Cursor.HAND_CURSOR));
        label3.setFont(Constants.getFont(FontType.QUICKSAND_BOLD).deriveFont(15f));
        label3.setMaximumSize(new Dimension(378, 20));
        label3.setMinimumSize(new Dimension(378, 20));
        label3.setPreferredSize(new Dimension(378, 20));

        password = new PasswordField("Mật khẩu");
        password.setBorderColor(Constants.gray02);
        password.setMaximumSize(new Dimension(378, 60));
        password.setMinimumSize(new Dimension(378, 60));
        password.setPreferredSize(new Dimension(378, 60));
        password.setRadius(8);

        signinButton = new Button("Đăng nhập");
        signinButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                login(callback);
            }
        });
        signinButton.setBackground(Constants.blue01);
        signinButton.setFont(Constants.getFont(FontType.QUICKSAND_BOLD));
        signinButton.setForeground(Color.WHITE);
        signinButton.setMaximumSize(new Dimension(378, 60));
        signinButton.setMinimumSize(new Dimension(378, 60));
        signinButton.setPreferredSize(new Dimension(378, 60));

        username = new TextField("Tên đăng nhập", 16);
        username.setBorderColor(Constants.gray02);
        username.setMaximumSize(new Dimension(378, 60));
        username.setMinimumSize(new Dimension(378, 60));
        username.setPreferredSize(new Dimension(378, 60));
        username.setRadius(8);

        signInPanel = new RoundedPanel(18, true);
        signInPanel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        signInPanel.setBackground(Color.WHITE);
        signInPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        signInPanel.setLayout(new BoxLayout(signInPanel, BoxLayout.Y_AXIS));
        signInPanel.setMaximumSize(new Dimension(578, 667));
        signInPanel.add(new Center(label1, BoxLayout.X_AXIS));
        signInPanel.add(Box.createVerticalStrut(16));
        signInPanel.add(new Center(label2, BoxLayout.X_AXIS));
        signInPanel.add(Box.createVerticalGlue());
        signInPanel.add(username);
        signInPanel.add(Box.createVerticalStrut(16));
        signInPanel.add(password);
        signInPanel.add(Box.createVerticalStrut(16));
        signInPanel.add(new Center(label3, BoxLayout.X_AXIS));
        signInPanel.add(Box.createVerticalStrut(16));
        signInPanel.add(new Center(signinButton, BoxLayout.X_AXIS));
        signInPanel.add(Box.createVerticalGlue());

        setBackground(Constants.neutral01);
        setLayout(new BorderLayout());

        add(Box.createHorizontalStrut(467), BorderLayout.WEST);
        add(Box.createHorizontalStrut(467), BorderLayout.EAST);
        add(Box.createVerticalStrut(153), BorderLayout.NORTH);
        add(Box.createVerticalStrut(153), BorderLayout.SOUTH);
        add(signInPanel, BorderLayout.CENTER);
    }

    public void login(UserCallback callback) {
        if (password.getPassword().toString().isEmpty() || username.getText().isEmpty()) {

            return;
        }

        int myUser = AuthCtrl.login(username.getText(), new String(password.getPassword()));

        callback.callbackUser(myUser);
    }

}