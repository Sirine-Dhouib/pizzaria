<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../../layouts/admin/header.jsp" />
<div class="page-header">
    <h3 class="page-title">
        <span class="page-title-icon bg-gradient-primary text-white me-2">
            <i class="mdi mdi-cart"></i>
        </span> Mes Commandes
    </h3>
</div>

<div class="row">
    <div class="col-lg-12 grid-margin stretch-card">
        <div class="card">
            <div class="card-body">
                <div class="d-flex justify-content-between align-items-center mb-3">
                    <h4 class="card-title">Liste utilisateurs</h4>
                    <!-- <a href="admin-home?action=ajout" class="btn btn-gradient-primary">Ajouter un pizza</a> -->
                </div>
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>Nom</th>
                            <th>Prenom</th>
                            <th>Email</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:if test="${empty utilisateurs}">
                            <p>Aucune utilisateurs n'est disponible pour le moment.</p>
                        </c:if>
                        <c:if test="${not empty utilisateurs}">
                            <c:forEach var="utilisateur" items="${utilisateurs}">
                                <tr>
                                    <td class="py-1">${utilisateur.nom}</td>
                                    <td>${utilisateur.prenom}f</td>
                                    <td>${commande.email}</td>
                                    <td>
                                        <%-- <a href="commandes?mumeroCommande=${commande.num_commande}&action=view">
                                            <i class="mdi mdi-eye" style="font-size: 22px; margin-right: 5px;"></i>
                                        </a>
                                        <a href="commandes?mumeroCommande=${commande.num_commande}&action=view">
                                            <i class="mdi mdi-eye" style="font-size: 22px; margin-right: 5px;"></i>
                                        </a> --%>
                                        <c:choose>
										    <c:when test="${utilisateur.status == 0}">
										        <form class="forms-sample" action="utilisateurs" method="post">
										            <input type="hidden" name="action" value="bloquer">
										            <input type="hidden" name="idUser" value="${utilisateur.id}">
										            <button type="submit" class="btn btn-danger">Bloquer</button>
										        </form>
										    </c:when>
										    <c:when test="${utilisateur.status == 1}">
										        <form class="forms-sample" action=""utilisateurs"" method="post">
										            <input type="hidden" name="action" value="debloquer">
										            <input type="hidden" name="idUser" value="${utilisateur.id}">
										            <button type="submit" class="btn btn-secondary">Débloquer</button>
										        </form>
										    </c:when>
										</c:choose>

                                    </td>
                                </tr>
                            </c:forEach>
                        </c:if>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<jsp:include page="../../layouts/admin/footer.jsp" />
