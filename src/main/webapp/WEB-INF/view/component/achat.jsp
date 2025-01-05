<%@ page import="mg.atelier.model.Component" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Ajouter un Achat</title>
    <link rel="stylesheet" href="/assets/css/bootstrap.min.css">
    <style>
        body {
            padding-top: 56px;
        }
        .navbar {
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
        <a class="navbar-brand" href="#">Atelier</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="/index">Accueil</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/component/all">Composants</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/client/all">Clients</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/reparationType/all">Type de Reparation</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/reparation/all">Reparation</a>
                </li>
            </ul>
        </div>
    </nav>
    <div class="container">
        <h1>Ajouter un Achat</h1>
        <form action="/achat/insert" method="post">
            <input type="hidden" class="form-control" id="idComponent" name="idComponent" value="<%= request.getParameter("idComponent") %>" readonly>
            <div class="form-group">
                <label for="quantity">Quantité</label>
                <input type="number" class="form-control" id="quantity" name="quantity" required>
            </div>
            <div class="form-group">
                <label for="unitPrice">Prix Unitaire</label>
                <input type="number" step="0.01" class="form-control" id="unitPrice" name="unitPrice" required>
            </div>
            <div class="form-group">
                <label for="purchaseDate">Date d'achat</label>
                <input type="datetime-local" class="form-control" id="purchaseDate" name="purchaseDate" required>
            </div>
            <% 
                String error = (String) request.getAttribute("error");
                if (error != null && !error.isEmpty()) {
            %>
                <div class="alert alert-danger" role="alert">
                    <%= error %>
                </div>
            <% 
                } 
            %>
            <button type="submit" class="btn btn-dark">Ajouter</button>
        </form>
        <a href="/component/all" class="btn btn-dark">Retour</a>
    </div>
    <script src="/assets/js/jquery-3.5.1.slim.min.js"></script>
    <script src="/assets/js/popper.min.js"></script>
    <script src="/assets/js/bootstrap.min.js"></script>
</body>
</html>