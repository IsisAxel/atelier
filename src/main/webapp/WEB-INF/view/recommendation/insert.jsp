<%@ page import="mg.atelier.model.ComponentType" %>
<%@ page import="mg.atelier.model.ComputerType" %>
<%@ page import="mg.atelier.model.ComputerUsage" %>
<%@ page import="mg.atelier.model.ComponentStock" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Ajouter un Composant</title>
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
                <li class="nav-item active">
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
                <li class="nav-item">
                    <a class="nav-link" href="/return/all">Retour</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/techniciens/all">Technicien</a>
                </li>
            </ul>
        </div>
    </nav>
    <div class="container">
        <h1>Ajouter une Recommendation</h1>

        <form action="/recommendation/insert" method="post">
            <div class="form-group">
                <label for="date">Date</label>
                <input type="datetime-local" class="form-control" id="date" name="date" required>
            </div>
           
            <div class="form-group">
                <label for="componentType">Composant</label>
                <select class="form-control" id="componentType" name="idComponent">
                    <% 
                        List<ComponentStock> components = (List<ComponentStock>) application.getAttribute("components");
                        for (ComponentStock component : components) {
                    %>
                        <option value="<%= component.getIdComponent() %>"><%= component.getName() %></option>
                    <% 
                        } 
                    %>
                </select>
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
            <button type="submit" class="btn btn-dark">Ajouter</button><br><br>
        </form>
        <a href="/component/all"><button class="btn btn-dark">Retour</button></a>
    </div>
    <script src="/assets/js/jquery-3.5.1.slim.min.js"></script>
    <script src="/assets/js/popper.min.js"></script>
    <script src="/assets/js/bootstrap.min.js"></script>
</body>
</html>