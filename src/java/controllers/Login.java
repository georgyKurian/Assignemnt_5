/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Beans.DBUtils;
import Beans.User;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import others.Users;

/**
 *
 * @author Georgi
 */
@Named
@SessionScoped
public class Login implements Serializable {

    private String username;
    private String password;
    private boolean isLoggedIn;
    private User currentuser;

    /**
     *
     */
    public Login() {
        username = null;
        password = null;
        isLoggedIn = false;
        currentuser = null;
    }

    /**
     *
     * @return
     */
    public String getUsername() {
        return username;
    }

    /**
     *
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     *
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *
     * @return
     */
    public boolean isIsLoggedIn() {
        return isLoggedIn;
    }

    /**
     *
     * @param isLoggedIn
     */
    public void setIsLoggedIn(boolean isLoggedIn) {
        this.isLoggedIn = isLoggedIn;
    }

    /**
     *
     * @return
     */
    public User getCurrentuser() {
        return currentuser;
    }

    /**
     *
     * @param currentuser
     */
    public void setCurrentuser(User currentuser) {
        this.currentuser = currentuser;
    }

    /**
     *
     * @return
     */
    public String login() {
        String passwordHash = DBUtils.hash(password);
        for (User u : Users.getInstance().getUsers()) {
            if (u.getUsername().equals(username) && u.getPasshash().equals(passwordHash)) {
                isLoggedIn = true;
                currentuser = u;
                return "index";
            }
        }
        return "index";
    }
}
