/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dynamics.pidev.services;

import com.dynamics.pidev.entites.Salle;
import com.dynamics.pidev.entites.abonnementSalle;
import java.sql.SQLException;
import java.util.ArrayList;
//import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author FGH
 */
public interface IService<T> {
    
    void insertOne(T t) throws SQLException;
    
    void updateOne(T t, int id) throws SQLException;
    
    //void deleteOne(T t) throws SQLException;
    void deleteOne(int id) throws SQLException;
    
    List<T> selectAll() throws SQLException;
    
    List<String> selectAllNames() throws SQLException;
    
    //ArrayList<T> searchByName(String nom_salle);
    ArrayList<T> search1(String duree_abonnement);
    List<Salle> sortSalle(List<Salle> salles);
    List<abonnementSalle> sortAbonnement(List<abonnementSalle> abonnements);
}
