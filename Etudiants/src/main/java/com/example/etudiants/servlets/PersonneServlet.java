package com.example.etudiants.servlets;

import java.io.*;
import java.sql.*;
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
            int r = personne.save(this.connexion);
            if(r == 1){
                response.sendRedirect("/Etudiants_war_exploded/?ajoutok");
            }
            else{
                out.println("Erreur!");
            }
        }
        else{
            String message = "<div style='color:red;'>Erreur! Veuillez remplir tous les champs.</div>";
            RequestDispatcher dispatcher = request.getRequestDispatcher("create.jsp");
            request.setAttribute("message", message);
            dispatcher.include(request, response);
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