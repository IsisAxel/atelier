<%@ page import="mg.atelier.model.ComponentStock" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Ajouter un Type de Reparation</title>
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
                <li class="nav-item active">
                    <a class="nav-link" href="/reparationType/all">Type de Reparation</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/reparation/all">Reparation</a>
                </li>
            </ul>
        </div>
    </nav>
    <div class="container">
        <h1>Ajouter un Type de Reparation</h1>

        <form action="/reparationType/insert" method="post">
            <div class="form-group">
                <label for="type">Type</label>
                <input type="text" class="form-control" id="type" name="type" required>
            </div>
            <div class="form-group">
                <label for="componentType">Composant a remplacer</label>
                <select class="form-control" id="componentType" name="component">
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
            <div class="form-group">
                <label for="durationHours">Duree (heures)</label>
                <input type="number" class="form-control" id="durationHours" name="durationHours" min="0" required>
            </div>
            <div class="form-group">
                <label for="durationMinutes">Duree (minutes)</label>
                <input type="number" class="form-control" id="durationMinutes" name="durationMinutes" min="0" max="59" required>
            </div>
            <div class="form-group">
                <label for="price">Prix</label>
                <input type="number" step="0.01" class="form-control" id="price" name="price" required>
            </div>
            <div class="form-group">
                <label for="effectiveDate">Date d'effet</label>
                <input type="datetime-local" class="form-control" id="effectiveDate" name="effectiveDate" required>
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
        <a href="/reparationType/all"><button class="btn btn-dark">Retour</button></a>
    </div>
    <script src="/assets/js/jquery-3.5.1.slim.min.js"></script>
    <script src="/assets/js/popper.min.js"></script>
    <script src="/assets/js/bootstrap.min.js"></script>
</body>
</html>