package com.ult.catalogue;

import java.io.*;
import java.sql.*;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/example";
        try {
            Connection connexion = DriverManager.getConnection(url, "root", "nassim");
            Statement req = connexion.createStatement();
            ResultSet result = req.executeQuery("select * from book");
            while(result.next()) {
                out.println("Auteur : " + result.getString("author") + "<br>");
                out.println("Titre : " + result.getString("title") + "<br>");
                out.println("<p>-----------------------</p>");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        out.println("</body></html>");
    }

    public void destroy() {
    }
}