package com.example.etudiants.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Personne {

    private int id;
    private String nom;
    private String prenom;
    private String niveaux;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNiveaux() {
        return niveaux;
    }

    public void setNiveaux(String niveaux) {
        this.niveaux = niveaux;
    }

    public Personne(int id, String nom, String prenom, String niveaux) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.niveaux = niveaux;
    }

    public Personne(String nom, String prenom, String niveaux) {
        this.nom = nom;
        this.prenom = prenom;
        this.niveaux = niveaux;
    }

    public Personne(){}

    public static ArrayList<Personne> getListePersonnes(Statement statement){
        String sql = "SELECT * FROM personnes";
        ArrayList<Personne> personnes;
        try {
            ResultSet resultat = statement.executeQuery(sql);
            personnes = new ArrayList<>();
            while(resultat.next()){
                int id = resultat.getInt("id");
                String nom = resultat.getString("nom");
                String prenom = resultat.getString("prenom");
                String niveau = resultat.getString("niveau");
                Personne p = new Personne(id, nom, prenom, niveau);
                personnes.add(p);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return personnes;
    }

    public int save(Statement statement){
        String sql  = "INSERT INTO personnes(nom, prenom, niveau) " +
                "VALUES ('"+this.nom+"', '"+this.prenom+"', '"+this.niveaux+"')";
        int resultat = 0;
        try{
            resultat = statement.executeUpdate(sql);
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return resultat;
    }
}
