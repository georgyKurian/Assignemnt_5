/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import java.util.Date;

/**
 *
 * @author Georgi
 */
public class Post {

    private int id;
    private int user_id;
    private String title;
    private Date createdTime;
    private String contents;

    /**
     *
     * @param id
     * @param user_id
     * @param title
     * @param createdTime
     * @param contents
     */
    public Post(int id, int user_id, String title, Date createdTime, String contents) {
        this.id = id;
        this.user_id = user_id;
        this.title = title;
        this.createdTime = createdTime;
        this.contents = contents;
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
    public int getUser_id() {
        return user_id;
    }

    /**
     *
     * @param user_id
     */
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    /**
     *
     * @return
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @return
     */
    public Date getCreatedTime() {
        return createdTime;
    }

    /**
     *
     * @param createdTime
     */
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    /**
     *
     * @return
     */
    public String getContents() {
        return contents;
    }

    /**
     *
     * @param contents
     */
    public void setContents(String contents) {
        this.contents = contents;
    }

}
