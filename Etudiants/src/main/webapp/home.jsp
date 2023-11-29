<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.etudiants.models.Personne" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Gestion des personnes</title>
</head>
<body>
    <h1>Liste des personnes</h1>
    <h3>Nombre de personnes : <%= ((ArrayList<Personne>)request.getAttribute("personnes")).size() %></h3>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>NOM</th>
            <th>PRENOM</th>
            <th>NIVEAU</th>
        </tr>
        <%
            for(int i = 0;
                i < ((ArrayList<Personne>)request.getAttribute("personnes")).size();
                i++
                ){
                out.println("<tr>");
                out.println("<td>" + ((ArrayList<Personne>)request.getAttribute("personnes")).get(i).getId() + "</td>");
                out.println("<td>" + ((ArrayList<Personne>)request.getAttribute("personnes")).get(i).getNom() + "</td>");
                out.println("<td>" + ((ArrayList<Personne>)request.getAttribute("personnes")).get(i).getPrenom() + "</td>");
                out.println("<td>" + ((ArrayList<Personne>)request.getAttribute("personnes")).get(i).getNiveaux() + "</td>");
                out.println("</tr>");
            }
        %>

    </table>


</body>
</html>