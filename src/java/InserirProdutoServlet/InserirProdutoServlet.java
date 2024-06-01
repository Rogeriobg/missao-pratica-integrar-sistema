/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package InserirProdutoServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/InserirProdutoServlet")
public class InserirProdutoServlet extends HttpServlet {

    private static final String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=loja;encrypt=true;trustServerCertificate=true";
    private static final String DB_USER = "loja";
    private static final String DB_PASSWORD = "loja";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String nome = request.getParameter("nome");
            int quantidade = Integer.parseInt(request.getParameter("quantidade"));
            double precoVenda = Double.parseDouble(request.getParameter("precoVenda"));

            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

                String sql = "INSERT INTO Produto (Nome, Quantidade, PrecoVenda) VALUES (?, ?, ?)";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, nome);
                pstmt.setInt(2, quantidade);
                pstmt.setDouble(3, precoVenda);
                pstmt.executeUpdate();

                conn.close();
                out.println("<p>Produto inserido com sucesso!</p>");
                out.println("<a href='ProdutoServlet'>Voltar para lista de produtos</a>");
            } catch (Exception e) {
                out.println("<p>Erro ao inserir produto: " + e.getMessage() + "</p>");
                e.printStackTrace(out);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Servlet para inserir um novo produto";
    }
}