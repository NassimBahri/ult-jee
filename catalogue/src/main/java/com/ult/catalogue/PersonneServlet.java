package com.ult.catalogue;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name = "PersonneServlet", value = "/personnes")
public class PersonneServlet extends HttpServlet {

    private Connection connexion;
    private Statement requete;

    public PersonneServlet(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try{
            // Connexion à la base de données
            this.connexion = DriverManager.getConnection(Config.URL, Config.USER, Config.PASSWORD);
            // Création de la requête
            this.requete = connexion.createStatement();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // SQL
            String sql = "SELECT * FROM personnes";
            // Exécution de la requête
            ResultSet resultat = requete.executeQuery(sql);
            PrintWriter out = response.getWriter();
            response.setContentType("text/html");
            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset='utf8'>");
            out.println("<title>Mon application</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Liste des personnes</h1>");
            out.println("<table border='1'>");
            out.println("<tr><th>ID</th><th>NOM</th><th>PRENOM</th><th>NIVEAU</th></tr>");
            // Afficher les personnes
            while(resultat.next()){
                int id = resultat.getInt("id");
                String nom = resultat.getString("nom");
                String prenom = resultat.getString("prenom");
                String niveau = resultat.getString("niveau");
                out.println("<tr>");
                out.println("<td>" + id + "</td>");
                out.println("<td>" + nom + "</td>");
                out.println("<td>" + prenom + "</td>");
                out.println("<td>" + niveau + "</td>");
                out.println("</tr>");
            }
            out.println("</table>");
            out.println("</body>");
            out.println("</html>");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String niveau = request.getParameter("niveau");
        if(
                nom == null || nom.equals("") ||
                prenom == null || prenom.equals("") ||
                niveau == null || niveau.equals("")
        ){
            out.println("Veuillez remplit tous les champs!");
            return;
        }
        try {
            // SQL
            String sql = "INSERT INTO personnes(nom, prenom, niveau)" +
                    " VALUES ('" + nom + "', '" + prenom + "', '" + niveau + "')";
            int n = requete.executeUpdate(sql);
            if(n == 1){
                response.sendRedirect("personnes");
            }
            else{
                out.println("Erreur!!!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void destroy() {
        try {
            this.connexion.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
