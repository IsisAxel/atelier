<%@ page import="mg.atelier.model.Reparation" %>
<%@ page import="java.util.List" %>
<%@ page import="mg.atelier.model.ReparationType" %>
<%@ page import="mg.atelier.model.ComputerUsage" %>
<%@ page import="mg.atelier.model.ReparationTypePrice" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Liste des Reparations</title>
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
        <h1>Voici la liste des reparations</h1>
        <% 
            String success = (String) request.getAttribute("success");
            if (success != null && !success.isEmpty()) {
        %>
            <div class="alert alert-success" role="alert">
                <%= success %>
            </div>
        <% 
            } 
        %>
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
        <br>
        <form action="/reparation/recherche" method="POST">
        <div class="form-group">
            <label for="reparationType">Type de Reparation</label>
            <select class="form-control" id="reparationType" name="idReparationTypePrice">
                <option value=0>tous</option>
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
        <div class="form-group">
            <label for="ComputerUsage">Usage de l'ordinateur</label>
            <select class="form-control" id="computerUsage" name="idComputerUsage" required>
                <option value=0>tous</option>
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
        <button type="submit" class="btn btn-primary">Rechercher</button>
    </form>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Ordinateur</th>
                    <th>Client</th>
                    <th>Date de debut</th>
                    <th>Date de fin</th>
                    <th>Montant total</th>
                    <th>Action</th>
                    <th>Retour</th>
                </tr>
            </thead>
            <tbody id="reparationTableBody">
                <% 
                    List<Reparation> allReparations = (List<Reparation>) request.getAttribute("reparationFiltre");
                        if(allReparations==null){
                            allReparations=(List<Reparation>) application.getAttribute("reparations");
                        }
                    for (Reparation reparation : allReparations) {
                %>
                    <tr class="reparation-row">
                        <td><%= reparation.getIdReparation() %></td>
                        <td><%= reparation.getComputer().getModel() %></td>
                        <td><%= reparation.getComputer().getClient().getName() %></td>
                        <td><%= reparation.getStartDate() %></td>
                        <td><%= reparation.getEndDate() %></td>
                        <td><%= reparation.getTotalAmount() %></td>
                        <td>
                            <form action="/reparation/details/<%= reparation.getIdReparation() %>" method="get">
                                <input type="hidden" name="typeReturn" value="reparation">
                                <button type="submit" class="btn btn-primary">Voir les details</button>
                            </form>
                        </td>
                        <td>
                            <form action="/return/prepareInsert/<%= reparation.getIdReparation() %>" method="get">
                                <button type="submit" class="btn btn-primary">Ajouter retour</button>
                            </form>
                        </td>
                    </tr>
                <% 
                    } 
                %>
            </tbody>
        </table>
        <nav aria-label="Page navigation">
            <ul class="pagination" id="pagination">
                <!-- Pagination links will be generated by JavaScript -->
            </ul>
        </nav>
        <a href="/reparation/prepareInsert" class="btn bg-dark" style="color : white;">Ajouter une reparation</a>
    </div>
    <script src="/assets/js/jquery-3.5.1.slim.min.js"></script>
    <script src="/assets/js/popper.min.js"></script>
    <script src="/assets/js/bootstrap.min.js"></script>
    <script src="/assets/js/script.js"></script>
    <script>
        document.addEventListener('DOMContentLoaded', function() {
            const rowsPerPage = 20;
            const rows = document.querySelectorAll('.reparation-row');
            const pagination = document.getElementById('pagination');
            const totalPages = Math.ceil(rows.length / rowsPerPage);

            createPagination(rowsPerPage , rows , pagination,totalPages);
        });
    </script>
</body>
</html>