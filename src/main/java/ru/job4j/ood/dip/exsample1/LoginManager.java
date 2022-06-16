package ru.job4j.ood.dip.exsample1;

public class LoginManager {

    SimpleLogin simpleLogin = new SimpleLogin();

    public void login(User user) {
        simpleLogin.authenticate(user);
    }
}
