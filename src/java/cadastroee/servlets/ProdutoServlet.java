package cadastroee.servlets;




import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ProdutoServlet")
public class ProdutoServlet extends HttpServlet {

    private static final String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=loja;encrypt=true;trustServerCertificate=true";
    private static final String DB_USER = "loja";
    private static final String DB_PASSWORD = "loja";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
               out.println("<html>");
            out.println("<head>");
            out.println("<title>Produtos</title>");
            out.println("<!-- Bootstrap CSS -->");
            out.println("<link rel='stylesheet' href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css\">");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='container'>");
            out.println("<h1 class='text-center'>Listagem de Produtos</h1>");
            out.println("<a href='novoProduto.jsp' class='btn btn-primary mb-3'>Novo Produto</a>");
            out.println("<table class='table table-striped'>");
            out.println("<thead class='table-dark'>");
            out.println("<tr>");
            out.println("<th>ID</th>");
            out.println("<th>Produto</th>");
            out.println("<th>Preço Venda</th>");
            out.println("<th>Quantidade</th>");
            out.println("<th>Opções</th>");
            out.println("</tr>");
            out.println("</thead>");
            out.println("<tbody>");

            try {
                out.println("<p>Iniciando conexão com o banco de dados...</p>");
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                out.println("<p>Conexão estabelecida com sucesso!</p>");
                
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT ProdutoID, Nome, PrecoVenda, Quantidade FROM Produto");

                while (rs.next()) {
                    int id = rs.getInt("ProdutoID");
                    String nome = rs.getString("Nome");
                    float precoVenda = rs.getFloat("PrecoVenda");
                    int quantidade = rs.getInt("Quantidade");
                    out.println("<tr>");
                    out.println("<td>" + id + "</td>");
                    out.println("<td>" + nome + "</td>");
                    out.println("<td>R$" + precoVenda + "</td>");
                    out.println("<td>" + quantidade + " unidades</td>");
                    out.println("<td>");
                    out.println("<a href='editarProduto.jsp?id=" + id + "'><button class='btn btn-primary  btn-sm'>Alterar</button></a>");
                    out.println("<a href='ExcluirProdutoServlet?id=" + id + "'><button class='btn btn-danger  btn-sm'>Excluir</button></a>");
                    out.println("</td>");
                    out.println("</tr>");
                }

                conn.close();
                out.println("<p>Conexão fechada com sucesso!</p>");
            } catch (Exception e) {
                out.println("<p>Erro ao acessar o banco de dados: " + e.getMessage() + "</p>");
                e.printStackTrace(out);
            }

            out.println("</table>");
            out.println("<script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js\" integrity=\"sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4\" crossorigin=\"anonymous\"></script>");
            out.println("</body>");
            out.println("</html>");
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
        return "Servlet para listar produtos";
    }
}