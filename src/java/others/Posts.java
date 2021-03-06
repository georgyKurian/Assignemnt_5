/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package others;

import Beans.DBUtils;
import Beans.Post;
import Beans.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 *
 * @author Georgi
 */
@Named
@ApplicationScoped
public class Posts {

    private List<Post> posts;
    private Post currentPost;

    /**
     *
     */
    public Posts() {
        currentPost = new Post(-1, -1, "", null, "");
        getPostsFromDB();
    }

    /**
     * 
     */
    private void getPostsFromDB() {
        try (Connection conn = DBUtils.getConnection()) {
            posts = new ArrayList<>();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM posts");
            while (rs.next()) {
                Post p = new Post(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getString("title"),
                        rs.getTimestamp("created_time"),
                        rs.getString("contents")
                );
                posts.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Posts.class.getName()).log(Level.SEVERE, null, ex);
            // This Fails Silently -- Sets Post List as Empty
            posts = new ArrayList<>();
        }
    }

    /**
     *
     * @return
     */
    public List<Post> getPosts() {
        return posts;
    }

    /**
     *
     * @return
     */
    public Post getCurrentPost() {
        return currentPost;
    }

    /**
     *
     * @param id
     * @return
     */
    public Post getPostById(int id) {
        for (Post p : posts) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    /**
     *
     * @param title
     * @return
     */
    public Post getPostByTitle(String title) {
        for (Post p : posts) {
            if (p.getTitle().equals(title)) {
                return p;
            }
        }
        return null;
    }

    /**
     *
     * @param post
     * @return
     */
    public String viewPost(Post post) {
        currentPost = post;
        return "viewPost";
    }

    /**
     *
     * @return
     */
    public String addPost() {
        currentPost = new Post(-1, -1, "", null, "");
        return "editPost";
    }

    /**
     *
     * @return
     */
    public String editPost() {
        return "editPost";
    }

    /**
     *
     * @return
     */
    public String cancelPost() {
        // currentPost can be corrupted -- reset it based on the DB
        int id = currentPost.getId();
        getPostsFromDB();
        currentPost = getPostById(id);
        return "viewPost";
    }

    /**
     *
     * @param user
     * @return
     */
    public String savePost(User user) {
        try (Connection conn = DBUtils.getConnection()) {
            if (currentPost.getId() >= 0) {
                String sql = "UPDATE posts SET title = ?, contents = ? WHERE id = ?";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, currentPost.getTitle());
                pstmt.setString(2, currentPost.getContents());
                pstmt.setInt(3, currentPost.getId());
                pstmt.executeUpdate();
            } else {
                String sql = "INSERT INTO posts (user_id, title, created_time, contents) VALUES (?,?,NOW(),?)";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, user.getId());
                pstmt.setString(2, currentPost.getTitle());
                pstmt.setString(3, currentPost.getContents());
                pstmt.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Posts.class.getName()).log(Level.SEVERE, null, ex);
        }
        getPostsFromDB();
        currentPost = getPostByTitle(currentPost.getTitle());
        return "viewPost";
    }

    /**
     *
     * @return
     */
    public String deletePost() {
        try (Connection conn = DBUtils.getConnection()) {
            String sql = "DELETE FROM posts WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, currentPost.getId());
            pstmt.execute();

        } catch (SQLException ex) {
            Logger.getLogger(Posts.class.getName()).log(Level.SEVERE, null, ex);
        }
        getPostsFromDB();
        currentPost = null;
        return "index";
    }

}
