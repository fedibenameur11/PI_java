/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.services;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author wassim
 */
public interface Services<T> {
    public void ajouter(T t) throws SQLException;
    public void modifier(T t);
    public void delete(int id);
    public List<T> getAll();
    public T getOneById(int id);
    
    void insertOne(T t) throws SQLException;
    
    void updateOne(T t, int id) throws SQLException;
    
    //void deleteOne(T t) throws SQLException;
    void deleteOne(int id) throws SQLException;
    
    List<T> selectAll() throws SQLException;
    
    List<String> selectAllNames() throws SQLException;
}
