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

import Model.Account;
import Resources.Callback;
import Resources.Constants;
import Resources.Tools;
import View.Homepage;
import View.Login;
import View.SignUp;
import View.Exam.AddExam;
import View.Exam.ManageExam;

public class Header extends JPanel {
    private Button manageExam, home, login, signup;
    private int isSelected = 0;
    private JLabel account;
    private JPanel leftPanel, rightPanel;
    private Account user;

    public Header(JFrame parentFrame, GridBagConstraints gbc) {
        super(new BorderLayout());

        Callback.createExamCallback = () -> {
            parentFrame.getContentPane().remove(1);
            parentFrame.getContentPane().add(new AddExam(), gbc);

            parentFrame.revalidate();
            parentFrame.repaint();
        };

        Callback.manageExamCallback = () -> {
            parentFrame.getContentPane().remove(1);
            parentFrame.getContentPane().add(new ManageExam(), gbc);

            parentFrame.revalidate();
            parentFrame.repaint();
        };

        Callback.userCallback = (user) -> {
            this.user = user;

            isSelected = 0;
            changeUI();

            account = new JLabel(user.getName(),
                    Tools.resize(user.getImage(), (int) (getHeight() * 0.6), (int) (getHeight() * 0.6)), JLabel.CENTER);
            rightPanel.remove(login);
            rightPanel.remove(signup);
            if (user.getRole() == 1)
                rightPanel.add(manageExam);
            rightPanel.add(account);
            rightPanel.revalidate();
            rightPanel.repaint();

            parentFrame.getContentPane().remove(1);
            parentFrame.getContentPane().add(new Homepage(), gbc);
            parentFrame.revalidate();
            parentFrame.repaint();
        };

        home = new Button("Trang chủ");
        home.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                parentFrame.getContentPane().remove(1);
                parentFrame.getContentPane().add(new Homepage(), gbc);

                isSelected = 0;
                changeUI();

                parentFrame.revalidate();
                parentFrame.repaint();
            }
        });
        home.setBackground(Color.WHITE);

        login = new Button("Đăng nhập");
        login.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                parentFrame.getContentPane().remove(1);
                parentFrame.getContentPane().add(new Login(), gbc);

                isSelected = -1;
                changeUI();

                parentFrame.revalidate();
                parentFrame.repaint();
            }
        });
        login.setMargin(new Insets(12, 24, 12, 24));

        manageExam = new Button("Quản lý đề thi");
        manageExam.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                parentFrame.getContentPane().remove(1);
                parentFrame.getContentPane().add(new ManageExam(), gbc);

                isSelected = 1;
                changeUI();

                parentFrame.revalidate();
                parentFrame.repaint();
            }
        });
        manageExam.setBackground(Color.WHITE);

        signup = new Button("Đăng ký");
        signup.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                parentFrame.getContentPane().remove(1);
                parentFrame.getContentPane().add(new SignUp(), gbc);

                isSelected = -1;
                changeUI();

                parentFrame.revalidate();
                parentFrame.repaint();
            }
        });
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
            manageExam.setBorder(null);
        } else if (isSelected == 1) {
            home.setBorder(null);
            manageExam.setBorder(BorderFactory.createMatteBorder(0, 0, 4, 0, Constants.blue01));
        } else {
            home.setBorder(null);
        }

        revalidate();
        repaint();
    }
}
