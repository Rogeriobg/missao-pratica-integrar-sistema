<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <title>Novo Produto</title>
</head>
<body class='container'>
    <h1>Novo Produto</h1>
    <form class='form' action="InserirProdutoServlet" method="POST">
        <div class='mb3'>
        <label class='form-label' for="nome">Nome:</label><br>
        <input class='form-control' type="text" id="nome" name="nome"><br>
        </div>
        <div class='mb3'>
        <label class='form-label' for="quantidade">Quantidade:</label><br>
        <input class='form-control' type="number" id="quantidade" name="quantidade"><br>
            </div>
        <div class='mb3'>
        <label class='form-label' for="precoVenda">Preço de Venda:</label><br>
        <input class='form-control' type="number" step="0.01" id="precoVenda" name="precoVenda"><br><br>
            </div>
        <input class='btn btn-primary' type="submit" value="Adicionar Produtos">
        
    </form>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
</body>
</html>
