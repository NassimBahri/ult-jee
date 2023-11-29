<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Salut <%= request.getAttribute("nom") %></h1>
${prenom}
<form action="demo" method="put">
  <input type="text" name="nom" value="test">
  <input type="submit" value="Envoyer">
</form>
</body>
</html>
