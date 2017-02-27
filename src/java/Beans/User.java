/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

/**
 *
 * @author Georgi
 */
public class User {

    private int id;
    private String username;
    private String passhash;

    /**
     *
     * @param id
     * @param username
     * @param passhash
     */
    public User(int id, String username, String passhash) {
        this.id = id;
        this.username = username;
        this.passhash = passhash;
    }

    /**
     *
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
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
    public String getPasshash() {
        return passhash;
    }

    /**
     *
     * @param passhash
     */
    public void setPasshash(String passhash) {
        this.passhash = passhash;
    }

}
