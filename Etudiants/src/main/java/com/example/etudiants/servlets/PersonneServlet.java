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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String niveau = request.getParameter("niveau");
        PrintWriter out = response.getWriter();
        if(
                nom != null && !nom.equals("")
                && prenom != null && !prenom.equals("")
                && niveau != null && !niveau.equals("")
        ){
            Personne personne = new Personne(nom, prenom, niveau);
            int r = personne.save(this.statement);
            if(r == 1){
                out.println("Bravo.");
            }
            else{
                out.println("Erreur!");
            }
        }
        else{
            out.println("Erreur! Veuillez remplir tous les champs.");
        }
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