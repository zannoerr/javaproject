package com.fauzan;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/categories")
public class CategoryServlet extends HttpServlet {

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        List<Category> categories = new ArrayList<>();

        categories.add(new Category(1, "Laptop"));
        categories.add(new Category(2, "Smartphone"));
        categories.add(new Category(3, "Aksesoris"));

        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Categories</title>");
        out.println("</head>");
        out.println("<body>");

        out.println("<h1>Daftar Kategori</h1>");
        out.println("<ul>");

        for (Category category : categories) {
            out.println("<li>" 
                    + category.getId() 
                    + " - " 
                    + category.getName() 
                    + "</li>");
        }

        out.println("</ul>");

        out.println("</body>");
        out.println("</html>");
    }
}