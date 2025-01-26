<%@ page import="java.util.List" %>
<%@ page import="mg.atelier.model.TechnicienCommission" %>
<%@ page import="mg.atelier.model.Technicien" %>
<%@ page import="mg.atelier.model.Genre" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Techniciens</title>
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
                <li class="nav-item">
                    <a class="nav-link" href="/return/all">Retour</a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link" href="/techniciens/all">technicien</a>
                </li>
            </ul>
        </div>
    </nav>
    <div class="container">
        <h1>Voici la liste des Techniciens</h1>
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
        <form action="/techniciens/filter" method="POST" class="mb-4">
            <div class="form-group">
                <label for="startDate">Date de debut:</label>
                <input type="date" class="form-control" id="startDate" name="startDate">
            </div>
            <div class="form-group">
                <label for="endDate">Date de fin:</label>
                <input type="date" class="form-control" id="endDate" name="endDate">
            </div>
            <div class="form-group">
                <label for="reparateur">Reperateur:</label>
                <select class="form-control" id="reparateur" name="idReparateur">
                    <option value=0>tous</option>
                    <% 
                        List<Technicien> allTechniciens = (List<Technicien>) application.getAttribute("techniciens");
                        for (Technicien tech : allTechniciens) {
                    %>
                        <option value="<%= tech.getIdTechnicien() %>"><%= tech.getName() %></option>
                    <% 
                        } 
                    %>

                </select>
            </div>
            <div class="form-group">
                <label for="reparateur">Genre:</label>
                <select class="form-control" id="reparateur" name="idGenre">
                    <option value=0>tous</option>
                    <% 
                        List<Genre> allGenres = (List<Genre>) application.getAttribute("genre");
                        for (Genre g : allGenres) {
                    %>
                        <option value="<%= g.getIdGenre() %>"><%= g.getGenre() %></option>
                    <% 
                        } 
                    %>

                </select>
            </div>
            <button type="submit" class="btn btn-primary">Filtrer</button>
        </form>
    
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nom</th>
                    <th>Genre</th>
                    <th>Commission</th>
                    <th>Role</th>
                </tr>
            </thead>
            <tbody id="technicienTableBody">
                <% 
                    List<TechnicienCommission> techniciens = (List<TechnicienCommission>) request.getAttribute("techniciensCommission");
                    if (techniciens == null) {
                        techniciens = (List<TechnicienCommission>) application.getAttribute("techniciens");
                    }
                    for (TechnicienCommission technicien : techniciens) {
                %>
                    <tr class="technicien-row">
                        <td><%= technicien.getIdTechnicien() %></td>
                        <td><%= technicien.getName() %></td>
                        <td><%= technicien.getGenre().getGenre() %></td>
                        <td><%= technicien.getCommission() %></td>
                        <td><%= technicien.getRoleTechnicien().getName() %></td>
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
        <a href="/techniciens/filter/genre/all" class="btn bg-dark" style="color : white;">Commission par genre</a>
    </div>
    <script src="/assets/js/jquery-3.5.1.slim.min.js"></script>
    <script src="/assets/js/popper.min.js"></script>
    <script src="/assets/js/bootstrap.min.js"></script>
    <script src="/assets/js/script.js"></script>
    <script>
        document.addEventListener('DOMContentLoaded', function() {
            const rowsPerPage = 20;
            const rows = document.querySelectorAll('.technicien-row');
            const pagination = document.getElementById('pagination');
            const totalPages = Math.ceil(rows.length / rowsPerPage);

            createPagination(rowsPerPage, rows, pagination, totalPages);
        });
    </script>
</body>
</html>
