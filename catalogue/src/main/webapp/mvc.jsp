<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 22‏/11‏/2023
  Time: 10:16 ص
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h1>Bonjour <%= request.getAttribute("prenom") %> <%= request.getAttribute("nom") %></h1>

</body>
</html>
