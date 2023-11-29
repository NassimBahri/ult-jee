package com.example.etudiants.servlets;

import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.example.etudiants.config.DB;
import com.example.etudiants.models.Personne;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "PersonneServlet", value = "/")
public class PersonneServlet extends HttpServlet {

    /**
     * Attribut qui représente la connexion à la base de données
     */
    private Connection connexion;
    /**
     * Attribut qui représente les requêtes
     */
    private Statement statement;

    /**
     * Initialization de notre servlet
     */
    public void init() {
        this.connexion = DB.connexionDB();
        try {
            this.statement = this.connexion.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Personne> personnes = Personne.getListePersonnes(this.statement);
        request.setAttribute("personnes", personnes);
        RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * Détruire la servlet / Libérer les ressources
     */
    public void destroy() {
        try {
            this.connexion.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}