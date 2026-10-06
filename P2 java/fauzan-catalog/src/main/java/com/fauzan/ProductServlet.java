package com.fauzan;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/product")
public class ProductServlet extends HttpServlet {
    
    private ProductRepository productRepository;

    // PERBAIKAN 1: Menambahkan method init() untuk mengisi data ke repository agar tidak bernilai Null
    @Override
    public void init() {
        productRepository = new ProductRepository();
        productRepository.addProduct(new product("PRD-01", "Keyboard Mechanical", 450000.0));
        productRepository.addProduct(new product("PRD-02", "Mouse Wireless Silent", 175000.0));
        productRepository.addProduct(new product("PRD-03", "Monitor Gaming 24 Inch", 2100000.0));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        String idParam = request.getParameter("id");

        out.println("<html>");
        out.println("<head><title>Katalog Produk Web</title></head>");
        out.println("<body>");

        if (idParam != null && !idParam.trim().isEmpty()) {
            try {
                product p = productRepository.findById(idParam);
                
                out.println("<h2>=== DETAIL PRODUK ===</h2>");
                out.println("<p><a href='/product'>&larr; Kembali ke Daftar Katalog</a></p>");
                out.println("<table border='1' cellpadding='8'>");
                out.println("<tr><th>ID Produk</th><td>" + p.getId() + "</td></tr>");
                out.println("<tr><th>Nama Produk</th><td>" + p.getName() + "</td></tr>");
                out.println("<tr><th>Harga Satuan</th><td>Rp" + p.getPrice() + "</td></tr>");
                out.println("</table>");
                
            } catch (Exception e) { 
                response.setStatus(HttpServletResponse.SC_NOT_FOUND); 
                out.println("<h2 style='color: red;'>ERROR 404 - PRODUK TIDAK DITEMUKAN</h2>");
                out.println("<p style='color: red;'>Maaf, produk dengan ID '" + idParam + "' tidak ada di sistem.</p>");
                out.println("<p><a href='/product'>Kembali ke Daftar Katalog</a></p>");
            }
        } 
        else {
            List<product> products = productRepository.findAll();
            out.println("<h2>=== DAFTAR KATALOG PRODUK (WEB) ===</h2>");
            out.println("<table border='1' cellpadding='8'>");
            out.println("<tr><th>ID Produk</th><th>Nama Produk</th><th>Harga Satuan</th></tr>");
            
            for (product p : products) {
                out.println("<tr>");
                out.println("<td><a href='/product?id=" + p.getId() + "'>" + p.getId() + "</a></td>");
                out.println("<td>" + p.getName() + "</td>");
                out.println("<td>Rp" + p.getPrice() + "</td>");
                out.println("</tr>");
            }
            out.println("</table>");
        }

        out.println("</body>");
        out.println("</html>");
    }
}
