package com.ult.catalogue;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(value = "/mvc", name = "MVC")
public class MVCServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nom = "Bahri";
        String prenom = "Nassim";
        request.setAttribute("nom", nom);
        request.setAttribute("prenom", prenom);
        RequestDispatcher dispatcher = request.getRequestDispatcher("mvc.jsp");
        dispatcher.forward(request, response);
    }
}
