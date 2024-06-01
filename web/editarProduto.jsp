<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.sql.*" %>
<%
    String id = request.getParameter("id");
    String nome = "";
    double precoVenda = 0.0;
    int quantidade = 0;

    try {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=loja;encrypt=true;trustServerCertificate=true", "loja", "loja");
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT Nome, PrecoVenda, Quantidade FROM Produto WHERE ProdutoID = " + id);

        if (rs.next()) {
            nome = rs.getString("Nome");
            precoVenda = rs.getDouble("PrecoVenda");
            quantidade = rs.getInt("Quantidade");
        }
        
        conn.close();
    } catch (Exception e) {
        out.println("<p>Erro ao acessar o banco de dados: " + e.getMessage() + "</p>");
        e.printStackTrace(new java.io.PrintWriter(out));
    }
%>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <title>Editar Produto</title>
</head>
<body class='container'>
    <h1>Dados do Produto</h1>
    <form class='form' action="SalvarProdutoServlet" method="post">
        <input  type="hidden" name="id" value="<%= id %>">
        <div class='mb-3'>
        <p class='form-label'>Nome: <input class='form-control' type="text" name="nome" value="<%= nome %>"></p>
        </div>
         <div class='mb-3'>
        <p class='form-label'>Preço Venda: <input class='form-control' type="text" name="precoVenda" value="<%= precoVenda %>"></p>
         </div>
        <div class='mb-3'>
        <p class='form-label'>Quantidade: <input  class='form-control' type="text" name="quantidade" value="<%= quantidade %>"></p>
       </div>
     <div class='mb-3'>
       <p class='form-label'><input class='btn  btn-primary ' type="submit" value="Alterar Produtos"></p>
  </div>
    </form>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
</body>
</html>