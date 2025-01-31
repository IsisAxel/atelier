<%@ page import="mg.atelier.model.ComponentStock" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Accueil</title>
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
        <h1>Voici la liste des composants</h1>
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
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nom</th>
                    <th>Type de Composant</th>
                    <th>Type d'Ordinateur</th>
                    <th>Numero de Serie</th>
                    <th>Stock</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody id="componentTableBody">
                <% 
                    List<ComponentStock> allComponents = (List<ComponentStock>) application.getAttribute("components");
                    for (ComponentStock component : allComponents) {
                %>
                    <tr class="component-row">
                        <td><%= component.getIdComponent() %></td>
                        <td><%= component.getName() %></td>
                        <td><%= component.getComponentType().getType() %></td>
                        <td><%= component.getComputerType().getType() %></td>
                        <td><%= component.getSerialNumber() %></td>
                        <td><%= component.getStock() %></td>
                        <td>
                            <form action="/achat/prepareInsert" method="get">
                                <input type="hidden" name="idComponent" value="<%= component.getIdComponent() %>">
                                <button type="submit" class="btn btn-primary">Acheter</button>
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
        <a href="/component/prepareInsert" class="btn bg-dark" style="color : white;">Ajouter un composant</a>
    </div>
    <script src="/assets/js/jquery-3.5.1.slim.min.js"></script>
    <script src="/assets/js/popper.min.js"></script>
    <script src="/assets/js/bootstrap.min.js"></script>
    <script src="/assets/js/script.js"></script>
    <script>
        document.addEventListener('DOMContentLoaded', function() {
            const rowsPerPage = 20;
            const rows = document.querySelectorAll('.component-row');
            const pagination = document.getElementById('pagination');
            const totalPages = Math.ceil(rows.length / rowsPerPage);

            createPagination(rowsPerPage , rows , pagination,totalPages);
        });
    </script>
</body>
</html>