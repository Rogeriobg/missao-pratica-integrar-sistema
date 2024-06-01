package cadastroee.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/SalvarProdutoServlet")
public class SalvarProdutoServlet extends HttpServlet {

    private static final String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=loja;encrypt=true;trustServerCertificate=true";
    private static final String DB_USER = "loja";
    private static final String DB_PASSWORD = "loja";
    private static final Logger LOGGER = Logger.getLogger(SalvarProdutoServlet.class.getName());

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idString = request.getParameter("id");
        String nome = request.getParameter("nome");
        String precoVendaString = request.getParameter("precoVenda");
        String quantidadeString = request.getParameter("quantidade");

        LOGGER.log(Level.INFO, "Received parameters: id={0}, nome={1}, precoVenda={2}, quantidade={3}", 
                   new Object[]{idString, nome, precoVendaString, quantidadeString});

        if (idString != null && !idString.isEmpty() &&
            nome != null && !nome.isEmpty() &&
            precoVendaString != null && !precoVendaString.isEmpty() &&
            quantidadeString != null && !quantidadeString.isEmpty()) {

            try {
                int id = Integer.parseInt(idString);
                float precoVenda = Float.parseFloat(precoVendaString);
                int quantidade = Integer.parseInt(quantidadeString);

                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {

                    String sql = "UPDATE Produto SET Nome = ?, PrecoVenda = ?, Quantidade = ? WHERE ProdutoID = ?";
                    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                        pstmt.setString(1, nome);
                        pstmt.setFloat(2, precoVenda);
                        pstmt.setInt(3, quantidade);
                        pstmt.setInt(4, id);

                        int rowsAffected = pstmt.executeUpdate();
                        LOGGER.log(Level.INFO, "Rows affected: {0}", rowsAffected);
                    }
                }
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, "Error updating product", e);
                throw new ServletException("Erro ao atualizar o produto", e);
            }
        } else {
            LOGGER.log(Level.WARNING, "Missing or invalid parameters");
            throw new ServletException("Parâmetros ausentes ou inválidos");
        }

        response.sendRedirect("ProdutoServlet");
    }
}