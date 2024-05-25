package Model;

import java.util.Date;

import javax.swing.ImageIcon;

public class Account {
    private int id;
    private int role;
    private String name;
    private Date dob;
    private int phoneNumber;
    private String email;
    private String school;
    private String Class;
    private ImageIcon image;

    public Account() {
    };

    public Account(int id, int role, String name, Date dob, int phoneNumber, String email, String school, String Class,
            ImageIcon image) {
        this.id = id;
        this.role = role;
        this.name = name;
        this.dob = dob;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.school = school;
        this.Class = Class;
        this.image = image;
    }
}
