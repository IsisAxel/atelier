<%@ page import="mg.atelier.model.ComponentType" %>
<%@ page import="mg.atelier.model.ComponentStock" %>
<%@ page import="mg.atelier.model.ComputerType" %>
<%@ page import="mg.atelier.model.Brand" %>
<%@ page import="mg.atelier.model.Clients" %>
<%@ page import="mg.atelier.model.Technicien" %>
<%@ page import="mg.atelier.model.Status" %>
<%@ page import="mg.atelier.model.ReparationType" %>
<%@ page import="mg.atelier.model.ComputerUsage" %>
<%@ page import="mg.atelier.model.ReparationTypePrice" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Ajouter une Reparation</title>
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
                    <a class="nav-link" href="/reparationType/all">Types de Reparation</a>
                </li>
                <li class="nav-item active">
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
        <h1>Ajouter une Reparation</h1>

        <form action="/reparation/insert" method="post">
            <h3>Informations sur l'ordinateur</h3>
            <div class="form-group">
                <label for="computerType">Type d'ordinateur</label>
                <select class="form-control" id="computerType" name="computerType.idComputerType">
                    <% 
                        List<ComputerType> computerTypes = (List<ComputerType>) application.getAttribute("computerTypes");
                        for (ComputerType type : computerTypes) {
                    %>
                        <option value="<%= type.getIdComputerType() %>"><%= type.getType() %></option>
                    <% 
                        } 
                    %>
                </select>
            </div>
            <div class="form-group">
                <label for="model">Modele</label>
                <input type="text" class="form-control" id="model" name="model" required>
            </div>
            <div class="form-group">
                <label for="client">Client</label>
                <select class="form-control" id="client" name="client.idClient">
                    <% 
                        List<Clients> clients = (List<Clients>) application.getAttribute("clients");
                        for (Clients client : clients) {
                    %>
                        <option value="<%= client.getIdClient() %>"><%= client.getName() %></option>
                    <% 
                        } 
                    %>
                </select>
            </div>
            <div class="form-group">
                <label for="client">Technicien</label>
                <select class="form-control" id="technicien" name="technicien">
                    <% 
                        List<Technicien> technicien = (List<Technicien>) application.getAttribute("techniciens");
                        for (Technicien tec : technicien) {
                    %>
                        <option value="<%= tec.getIdTechnicien() %>"><%= tec.getName() %></option>
                    <% 
                        } 
                    %>
                </select>
            </div>
            <div class="form-group">
                <label for="brand">Marque</label>
                <select class="form-control" id="brand" name="brand.idBrand">
                    <% 
                        List<Brand> brands = (List<Brand>) application.getAttribute("brands");
                        for (Brand brand : brands) {
                    %>
                        <option value="<%= brand.getIdBrand() %>"><%= brand.getName() %></option>
                    <% 
                        } 
                    %>
                </select>
            </div>
            <div class="form-group">
                <label for="computerUsage">Usage d'Ordinateur</label>
                <select class="form-control" id="computerUsage" name="computerUsage.idComputerUsage" required>
                    <% 
                        List<ComputerUsage> computerUsages = (List<ComputerUsage>) application.getAttribute("computerUsages");
                        for (ComputerUsage usage : computerUsages) {
                    %>
                        <option value="<%= usage.getIdComputerUsage() %>"><%= usage.getUsageName() %></option>
                    <% 
                        } 
                    %>
                </select>
            </div>
            <h3>Composants de l'ordinateur</h3>
            <div id="componentsContainer">
                <div class="form-group">
                    <label for="component">Composant</label>
                    <select class="form-control" name="components[0].component.idComponent">
                        <% 
                            List<ComponentStock> components = (List<ComponentStock>) application.getAttribute("components");
                            for (ComponentStock component : components) {
                        %>
                        <option value="<%= component.getIdComponent() %>"><%= component.getName() %> (<%= component.getComputerType().getType() %>)</option>
                        <% 
                            } 
                        %>
                    </select>
                    <label for="status">Statut</label>
                    <select class="form-control" name="components[0].status.idStatus">
                        <% 
                            List<Status> statuses = (List<Status>) application.getAttribute("statuses");
                            for (Status status : statuses) {
                        %>
                            <option value="<%= status.getIdStatus() %>"><%= status.getStatus() %></option>
                        <% 
                            } 
                        %>
                    </select>
                </div>
            </div>
            <button type="button" class="btn btn-secondary" onclick="addComponent()">Ajouter un composant</button>

            <h3>Informations sur la reparation</h3>
            <div id="reparationsContainer">
                <div class="form-group">
                    <label for="reparationType">Type de Reparation</label>
                    <select class="form-control" id="reparationType" name="reparations[0]">
                        <% 
                            List<ReparationTypePrice> reparationTypePrice = (List<ReparationTypePrice>) application.getAttribute("reparationTypes");
                            for (ReparationTypePrice type : reparationTypePrice) {
                        %>
                            <option value="<%= type.getReparationType().getIdReparationType() %>"><%= type.getReparationType().getType() %></option>
                        <% 
                            } 
                        %>
                    </select>
                </div>
            </div>
            <button type="button" class="btn btn-secondary" onclick="addReparation()">Ajouter une reparation</button>
            <div class="form-group">
                <label for="startDate">Date de debut</label>
                <input type="datetime-local" class="form-control" id="startDate" name="startDate" required>
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
        <a href="/reparation/all"><button class="btn btn-dark">Retour</button></a>
    </div>
    <script src="/assets/js/jquery-3.5.1.slim.min.js"></script>
    <script src="/assets/js/popper.min.js"></script>
    <script src="/assets/js/bootstrap.min.js"></script>
    <script>
        let componentIndex = 1;
        let reparationIndex = 1;

        function addComponent() {
            const container = document.getElementById('componentsContainer');
            const newComponent = document.createElement('div');
            newComponent.classList.add('form-group');
            newComponent.innerHTML = `
                <label for="component">Composant</label>
                <select class="form-control" name="components[`+ componentIndex +`].component.idComponent">
                    <% 
                        for (ComponentStock component : components) {
                    %>
                        <option value="<%= component.getIdComponent() %>"><%= component.getName() %> (<%= component.getComputerType().getType() %>)</option>
                    <% 
                        } 
                    %>
                </select>
                <label for="status">Statut</label>
                <select class="form-control" name="components[`+ componentIndex +`].status.idStatus">
                    <% 
                        for (Status status : statuses) {
                    %>
                        <option value="<%= status.getIdStatus() %>"><%= status.getStatus() %></option>
                    <% 
                        } 
                    %>
                </select>
            `;
            container.appendChild(newComponent);
            componentIndex++;
        }

        function addReparation() {
            const container = document.getElementById('reparationsContainer');
            const newReparation = document.createElement('div');
            newReparation.classList.add('form-group');
            newReparation.innerHTML = `
                <label for="reparationType">Type de Reparation</label>
                <select class="form-control" id="reparationType" name="reparations[`+ reparationIndex +`]">
                    <% 
                        for (ReparationTypePrice type : reparationTypePrice) {
                    %>
                        <option value="<%= type.getReparationType().getIdReparationType() %>"><%= type.getReparationType().getType() %></option>
                    <% 
                        } 
                    %>
                </select>
            `;
            container.appendChild(newReparation);
            reparationIndex++;
        }
    </script>
</body>
</html>