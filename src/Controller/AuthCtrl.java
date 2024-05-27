package Controller;

import Model.Account;
import Server.AuthQuery;

public class AuthCtrl {
    public static Account login(String username, String password) {
        return AuthQuery.login(username, password);
    }

    public static int signUp(Account account, String username, String password) {
        return AuthQuery.signUp(account, username, password);
    }
}
