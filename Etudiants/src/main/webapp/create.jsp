<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf8">
    <title>Ajouter une personne</title>
</head>
<body>
    <h1>Ajouter une personne</h1>

    <%
    if(request.getAttribute("message") != null){
        out.println(request.getAttribute("message"));
    }
    %>

    <form action="/Etudiants_war_exploded/" method="post" name="ajouter">
        <p>
            <label for="nom">Nom</label>
            <input type="text" id="nom" name="nom">
        </p>
        <p>
            <label for="prenom">Prénom</label>
            <input type="text" id="prenom" name="prenom">
        </p>
        <p>
            <label for="niveau">Niveau</label>
            <input type="text" id="niveau" name="niveau">
        </p>
        <p>
            <button type="reset">Vider le formulaire</button>
            <button type="submit" id="save">Enregistrer</button>
        </p>
    </form>
</body>
</html>
