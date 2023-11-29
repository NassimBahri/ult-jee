<%@page contentType="text/html; utf-8" language="java"  %>
<%@page isErrorPage="true" %>
<!doctype html>
<html lang="en" data-bs-theme="auto">
<head>
    <meta charset="utf-8">
    <title>Mon application de test</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <link rel="stylesheet" href="/css/style.css">


</head>
<body>

<%@ include file="includes/menu.jsp"%>

<main class="container">
    <h1>Page 2</h1>
    <h2><%= nom %></h2>
</main>

</body>
</html>
