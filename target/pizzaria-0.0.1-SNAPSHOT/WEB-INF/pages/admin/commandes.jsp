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
                    <h4 class="card-title">Liste des commandes en attentes</h4>
                    <!-- <a href="admin-home?action=ajout" class="btn btn-gradient-primary">Ajouter un pizza</a> -->
                </div>
                <c:if test="${not empty commandeValide}">
                    <p class="text-success">Commande validée avec succès</p>
                </c:if>
                <c:if test="${not empty commandeAnnule}">
                    <p class="text-success">Commande annulée avec succès</p>
                </c:if>
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>Numero Commande</th>
                            <th>Total</th>
                            <th>Date</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:if test="${empty commandeEnAttentes}">
                            <p>Aucune pizza n'est disponible pour le moment.</p>
                        </c:if>
                        <c:if test="${not empty commandeEnAttentes}">
                            <c:forEach var="commande" items="${commandeEnAttentes}">
                                <tr>
                                    <td class="py-1">${commande.num_commande}</td>
                                    <td>${commande.total}f</td>
                                    <td>${commande.date_commande}</td>
                                    <td>
                                        <a href="commandes?mumeroCommande=${commande.num_commande}&action=view">
                                            <i class="mdi mdi-eye" style="font-size: 22px; margin-right: 5px;"></i>
                                        </a>
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

<div class="col-lg-12 grid-margin stretch-card">
    <div class="card">
        <div class="card-body">
            <div class="d-flex justify-content-between align-items-center mb-3">
                <h4 class="card-title">Historiques Commandes</h4>
                <!-- <a href="admin-home?action=ajout" class="btn btn-gradient-primary">Ajouter un pizza</a> -->
            </div>
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>Numero Commande</th>
                        <th>Total</th>
                        <th>Validé le </th>
                    </tr>
                </thead>
                <tbody>
                    <c:if test="${empty historiques}">
                        <p>Pas d'historiques pour le moment.</p>
                    </c:if>
                    <c:if test="${not empty historiques}">
                        <c:forEach var="commande" items="${historiques}">
                            <tr>
                                <td class="py-1">${commande.num_commande}</td>
                                <td>${commande.total}f</td>
                                <td>${commande.date_validation}</td>
                            </tr>
                        </c:forEach>
                    </c:if>
                </tbody>
            </table>
        </div>
    </div>
</div>

<!-- Modal -->
<c:if test="${modalCommande}">
    <script>
        document.body.classList.add('modal-open');
    </script>
    <div class="modal fade show bd-example-modal-lg" id="myModal" tabindex="-1" role="dialog"
        aria-labelledby="myModalLabel" aria-hidden="true" style="display: block;">
        <div class="modal-dialog modal-lg " role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title font-weight-bold">COMMANDE : ${commande.num_commande}</h4>
                    <a type="button" href="commandes?action=quitter" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </a>
                </div>
                <div class="modal-body">
                    <h5>Détails du contenu de la commande :</h5>
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th>Pizza</th>
                                <th>Prix</th>
                                <th>Quantité</th>
                                <th>Total</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:if test="${empty pizzaCommandes}">
                                <p>Aucune commandes n'est disponible pour le moment.</p>
                            </c:if>
                            <c:if test="${not empty pizzaCommandes}">
                                <c:forEach var="pizzaCommande" items="${gestionCommande.getCommandesByOrderNumber(commande.num_commande)}">
                                    <c:set var="pizza" value="${gestionCommande.getPizzaById(pizzaCommande.value.pizzaId)}" />
                                    <tr>
                                        <td class="py-1"><img src="images/${pizza.photo}" alt="image"> ${pizza.nom}</td>
                                        <td>${pizza.prix}f</td>
                                        <td>${pizzaCommande.value.quantite}</td>
                                        <td>${pizza.prix * pizzaCommande.value.quantite}</td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                        </tbody>
                    </table>
                    <div class="modal-footer">
					    <form class="forms-sample" action="commandes" method="post">
						    <input type="hidden" name="action" value="valider"> <!-- Pour valider la commande -->
						    <input type="hidden" name="numeroCommande" value="${commande.num_commande}">
						    <button type="submit" class="btn btn-danger">Valider</button>
						</form>
						
						<form class="forms-sample" action="commandes" method="post" >
						    <input type="hidden" name="action" value="annuler"> <!-- Pour annuler la commande -->
						    <input type="hidden" name="numeroCommande" value="${commande.num_commande}">
						    <button type="submit" class="btn btn-secondary">Annuler</button>
						</form>

					</div>
                </div>
            </div>
        </div>
    </div>
</c:if>
<style>
/* Styles pour flouter tout sauf le modal */
body.modal-open::before {
    content: "";
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background: rgba(0, 0, 0, 0.6); /* Arrière-plan sombre */
    z-index: 1049;
    /* Assurez-vous que la superposition est derrière le modal */
}

.modal {
    z-index: 1050;
    /* Assurez-vous que le modal est au-dessus de la superposition */
}
</style>
<jsp:include page="../../layouts/admin/footer.jsp" />
