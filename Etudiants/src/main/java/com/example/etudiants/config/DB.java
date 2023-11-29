package com.example.etudiants.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {

    /**
     * URL de connexion à la base de données
     */
    private static final String URL = "jdbc:mysql://localhost:3306/app_java";
    /**
     * Nom d'utilisateur de la base de données
     */
    private static final String USER = "root";
    /**
     * Mot de passe de l'utilisateur de la base de données
     */
    private static final String PASSWORD = "nassim";


    /**
     * Méthode de connexion à la base de données
     * @return Objet de type Connection
     */
    public static Connection connexionDB(){
        Connection connexion = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connexion = DriverManager.getConnection(DB.URL, DB.USER, DB.PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return connexion;
    }


}
