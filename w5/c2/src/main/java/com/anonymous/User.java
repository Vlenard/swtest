package com.anonymous;

/**
 * Gyakorló feladat - User osztály
 */
public class User {
    private String userName;
    private String password;
    private int id;
    private int loginCount;
    private boolean isLoggedIn;

    /**
     * Default konstruktor
     */
    public User() {
        this.userName = null;
        this.password = null;
        this.id = 0;
        this.loginCount = 0;
        this.isLoggedIn = false;
    }

    public User(String userName, String password, int id) {
        this.userName = userName;
        this.password = password;
        this.id = id;
        this.loginCount = 0;
        this.isLoggedIn = false;
    }

    public String getUserName() {
        return userName;
    }

    public String getUsername() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUsername(String username) {
        this.userName = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLoginCount() {
        return loginCount;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public boolean getIsLoggedIn() {
        return isLoggedIn;
    }

    /**
     * Bejelentkezés: isLoggedIn állapotot true-ra állítja,
     * és megnöveli a loginCount mező értékét eggyel.
     */
    public void login() {
        this.isLoggedIn = true;
        this.loginCount++;
    }

    /**
     * Kijelentkezés: isLoggedIn állapotot false-ra állítja.
     */
    public void logout() {
        this.isLoggedIn = false;
    }

    /**
     * Piros szín a slide-on: nem került implementálásra, UnsupportedOperationException-t dob.
     */
    public boolean updatePwd(String newPwd, boolean isLoggedIn) {
        throw new UnsupportedOperationException("updatePwd nem került implementálásra");
    }

    public boolean updatePwd(String newPwd) {
        return updatePwd(newPwd, this.isLoggedIn);
    }
}
