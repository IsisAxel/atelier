<%@ page import="mg.atelier.model.ReparationDetail" %>
<%@ page import="mg.atelier.model.Reparation" %>
<%@ page import="java.util.List" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Details de la Reparation</title>
    <link rel="stylesheet" href="/assets/css/bootstrap.min.css">
    <style>
        body {
            padding-top: 56px;
        }
        .navbar {
            margin-bottom: 20px;
        }
        .info-section {
            margin-bottom: 20px;
        }
        .info-section h3 {
            margin-bottom: 15px;
        }
        .info-section p {
            margin-bottom: 5px;
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
        <% 
            Reparation rep = (Reparation) request.getAttribute("reparation");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        %>
        <h1 class="mt-4">Details de la Reparation</h1>
        <div class="info-section">
            <h3>Reparation</h3>
            <table class="table table-bordered">
                <tbody>
                    <tr>
                        <th>ID</th>
                        <td><%= rep.getIdReparation() %></td>
                    </tr>
                    <tr>
                        <th>Ordinateur</th>
                        <td><%= rep.getComputer().getBrand().getName() %> <%= rep.getComputer().getModel() %></td>
                    </tr>
                    <tr>
                        <th>Usage de l'ordinateur</th>
                        <td><%= rep.getComputer().getComputerUsage().getUsageName() %></td>
                    </tr>
                    <tr>
                        <th>Date de debut</th>
                        <td><%= rep.getStartDate().format(formatter) %></td>
                    </tr>
                    <tr>
                        <th>Date de fin</th>
                        <td><%= rep.getEndDate() != null ? rep.getEndDate().format(formatter) : "N/A" %></td>
                    </tr>
                    <tr>
                        <th>Montant total</th>
                        <td><%= rep.getTotalAmount() %></td>
                    </tr>
                </tbody>
            </table>
        </div>

        <div class="info-section">
            <h3>Details de la Reparation</h3>
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Type de Reparation</th>
                        <th>Composant remplace</th>
                        <th>Prix</th>
                    </tr>
                </thead>
                <tbody>
                    <% 
                        List<ReparationDetail> reparationDetails = (List<ReparationDetail>) request.getAttribute("reparationDetails");
                        for (ReparationDetail reparationDetail : reparationDetails) {
                    %>
                        <tr>
                            <td><%= reparationDetail.getIdReparationDetail() %></td>
                            <td><%= reparationDetail.getReparationType().getType() %></td>
                            <td><%= reparationDetail.getReparationType().getComponent() != null ? reparationDetail.getReparationType().getComponent().getName() : "N/A" %></td>
                            <td><%= reparationDetail.getPrice() %></td>
                        </tr>
                    <% 
                        } 
                    %>
                </tbody>
            </table>
        </div>
        <%
            if(request.getParameter("typeReturn") != null && request.getParameter("typeReturn").equals("return")){
                %>
                    <a href="/return/all" class="btn btn-dark">Retour</a>
                <%
            } else {
                %>
                    <a href="/reparation/all" class="btn btn-dark">Retour</a>
                <%
            }
        %>
        
    </div>
    <script src="/assets/js/jquery-3.5.1.slim.min.js"></script>
    <script src="/assets/js/popper.min.js"></script>
    <script src="/assets/js/bootstrap.min.js"></script>
</body>
</html>