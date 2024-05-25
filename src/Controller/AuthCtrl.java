package Controller;

import Server.DBQuery;

public class AuthCtrl {
    public static int login(String username, String password) {
        return DBQuery.login(username, password);
    }
}
