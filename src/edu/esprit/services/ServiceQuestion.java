/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.esprit.services;

import edu.esprit.entities.Question;
import edu.esprit.entities.Reponse;
import edu.esprit.util.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author trabe
 */
public class ServiceQuestion {

    private Connection cnx;

    public ServiceQuestion() {
        cnx = DataSource.getInstance().getCnx();
    }

    
    public void insertOne(Question t) throws SQLException {
        String req = "INSERT INTO `question`(`contenu`,`created_at`) "
                + "VALUES (?,?)";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, t.getContenu());
        ps.setString(2, t.getCreated_at());
        ps.executeUpdate();
        System.out.println("question ajouté !");
    }

    public void insertOne1(Question t) throws SQLException {
        String req = "INSERT INTO `question`(`contenu`, `created_at`,`likes`,`user_id`) VALUES (?,?,?,?)";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, t.getContenu());
        ps.setString(2, t.getCreated_at());
        ps.setInt(3, t.getLikes());
        ps.setInt(4, t.getUser_id());
        ps.executeUpdate();
        System.out.println("question ajouté !");
    }

    
    public void updateQuestion(Question t, int id) throws SQLException {

        String req = "UPDATE question SET `created_at`= ?, `contenu`= ? WHERE id=" + id;
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, t.getCreated_at());
        ps.setString(2, t.getContenu());
        ps.executeUpdate();
        System.out.println("Question a été modifiée avec succés !");
    }

   
    public void deleteOne(int id) throws SQLException {
        String req = "DELETE FROM `question` WHERE `id`=?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, id);
        ps.executeUpdate();
        System.out.println("question supprimée !");

    }


    public List<Question> selectAll() throws SQLException {
        List<Question> temp = new ArrayList<>();

        String req = "SELECT * FROM `question` ORDER BY created_at DESC";
        Statement st = cnx.createStatement();

        ResultSet rs = st.executeQuery(req);

        while (rs.next()) {
            Question q = new Question();

            q.setId(rs.getInt("id"));
            q.setContenu(rs.getString("contenu"));
            q.setCreated_at(rs.getString("created_at"));
            q.setLikes(rs.getInt("likes"));
            q.setUser_id(rs.getInt("user_id"));

            temp.add(q);
        }

        return temp;

    }

   
    public void deleteOne(Question t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

   
    public void updateCount(int count, int questionId) {

        try {
            String req = "UPDATE question SET `likes`= ? WHERE id=" + questionId;
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, count);
            ps.executeUpdate();
            System.out.println("Likes a été modifiée avec succés !");
        } catch (SQLException ex) {
            Logger.getLogger(ServiceQuestion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  
    public String usernameById(int id) {
        String name = "Dead";

        try {
            String req = "SELECT nom FROM users WHERE id = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                name = rs.getString("nom");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceQuestion.class.getName()).log(Level.SEVERE, null, ex);
        }

        return name;

    }


    public int countReponses(int id) {
        int count = 0;

        try {
            String req = "SELECT COUNT(*) AS response_count FROM reponse WHERE question_id = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt("response_count");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceQuestion.class.getName()).log(Level.SEVERE, null, ex);
        }

        return count;

    }


    public List<Question> selectByLikes() throws SQLException {

        List<Question> temp = new ArrayList<>();

        String req = "SELECT * FROM question ORDER BY likes DESC LIMIT 3";
        Statement st = cnx.createStatement();

        ResultSet rs = st.executeQuery(req);

        while (rs.next()) {
            Question r = new Question();

            r.setId(rs.getInt("id"));
            r.setContenu(rs.getString("contenu"));
            r.setCreated_at(rs.getString("created_at"));
            r.setUser_id(rs.getInt("user_id"));
            r.setLikes(rs.getInt("likes"));

            temp.add(r);
        }

        return temp;

    }


    public Question selectById(int id) throws SQLException {

        String req = "SELECT * FROM question WHERE id = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        Question q = new Question();
        if (rs.next()) {
            q.setId(rs.getInt("id"));
            q.setContenu(rs.getString("contenu"));
            q.setCreated_at(rs.getString("created_at"));
            q.setLikes(rs.getInt("likes"));
            q.setUser_id(rs.getInt("user_id"));
        }
        return q;
    }

}
