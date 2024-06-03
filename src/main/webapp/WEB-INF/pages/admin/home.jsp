<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../../layouts/admin/header.jsp" />
<div class="page-header">
	<h3 class="page-title">
		<span class="page-title-icon bg-gradient-primary text-white me-2">
			<i class="mdi mdi-home"></i>
		</span> Admin Dashboard
	</h3>
</div>
<div class="row">
	<div class="col-md-4 stretch-card grid-margin">
		<div class="card bg-gradient-danger card-img-holder text-white">
			<div class="card-body">
				<img src="assets/images/dashboard/circle.svg"
					class="card-img-absolute" alt="circle-image">
				<h4 class="font-weight-normal mb-3">
					Ventes de la semaine <i class="mdi mdi-chart-line mdi-24px float-right"></i>
				</h4>
				<h2 class="mb-5"> 15,0000 TND</h2>
				<h6 class="card-text">60% par apport a la semaine passée</h6>
				<p class="text-danger">Pas implementé</p>
			</div>
		</div>
	</div>
	<div class="col-md-4 stretch-card grid-margin">
		<div class="card bg-gradient-info card-img-holder text-white">
			<div class="card-body">
				<img src="assets/images/dashboard/circle.svg"
					class="card-img-absolute" alt="circle-image">
				<h4 class="font-weight-normal mb-3">
					COmmandes de la semaines <i
						class="mdi mdi-bookmark-outline mdi-24px float-right"></i>
				</h4>
				<h2 class="mb-5">45,6334 TND</h2>
				<h6 class="card-text">10% par apport a la semaine passée</h6>
				<p class="text-danger">Pas implementé</p>
			</div>
		</div>
	</div>
	<div class="col-md-4 stretch-card grid-margin">
		<div class="card bg-gradient-success card-img-holder text-white">
			<div class="card-body">
				<img src="assets/images/dashboard/circle.svg"
					class="card-img-absolute" alt="circle-image">
				<h4 class="font-weight-normal mb-3">
					Visiteurs de la semaine <i class="mdi mdi-diamond mdi-24px float-right"></i>
				</h4>
				<h2 class="mb-5">95,5741</h2>
				<h6 class="card-text">5% par apport a la semaine passée</h6>
				<p class="text-danger">Pas implementé</p>
			</div>
		</div>
	</div>
</div>
<div class="row">
	<div class="col-lg-12 grid-margin stretch-card">
		<div class="card">
			<div class="card-body">
				<div class="d-flex justify-content-between align-items-center mb-3">
					<h4 class="card-title">Liste des pizzas</h4>
					<a href="admin-home?action=ajout"" class="btn btn-gradient-primary">Ajouter
						un pizza</a>
				</div>
				<c:if test="${not empty pizzaAjoute}">
					<p class="text-success">Pizza ajoutée avec succes</p>
				</c:if>
				<c:if test="${not empty pizzaModifie}">
					<p class="text-success">Pizza modifiée avec succes</p>
				</c:if>
				
				
				<c:if test="${not empty messages}">
					<p class="text-success">messages</p>
				</c:if>

				<table class="table table-striped">
					<thead>
						<tr>
							<th>Nom</th>
							<th>Prix</th>
							<th>Categorie</th>
							<th>Actions</th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${empty pizzaList}">
							<p>Aucune pizza n'est disponible pour le moment.</p>
						</c:if>
						<c:if test="${not empty pizzaList}">
							<c:forEach var="pizza" items="${pizzaList}">
								<tr>
									<td class="py-1"><img src="images/${pizza.photo}"
										alt="image"> ${pizza.nom}</td>
									<td>${pizza.prix}TND</td>
									<td>${pizza.categorie}</td>
									<td><a href="admin-home?id=${pizza.id}&action=edit"><i
											class="mdi mdi-tooltip-edit"
											style="font-size: 18px; margin-right: 5px;"></i></a> <a
										href="admin-home?id=${pizza.id}&action=supprimer"><i
											class="mdi mdi-delete"
											style="font-size: 18px; margin-right: 5px;"></i></a></td>

								</tr>
							</c:forEach>
						</c:if>
					</tbody>
				</table>
			</div>
		</div>
	</div>



	<!-- Modal -->
	<c:if test="${actionPizza}">
		<script>
			document.body.classList.add('modal-open');
		</script>
		<div class="modal fade show bd-example-modal-lg" id="myModal"
			tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
			aria-hidden="true" style="display: block;">
			<div class="modal-dialog modal-lg " role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title">${nomActionPizza}</h4>
					</div>
					<div class="modal-body">
						<form class="forms-sample" action="admin-home" method="post"
							enctype="multipart/form-data">
							<div class="form-group">
								<label for="exampleInputUsername1">Nom</label> <input
									type="text" name="nom" value="${form.nom}" class="form-control"
									id="exampleInputUsername1" placeholder="nom">
								<c:if test="${not empty errors.nom}">
									<span class="text-danger">${errors.nom}</span>
								</c:if>
							</div>
							<div class="form-group">
								<label for="exampleInputEmail1">Categorie</label> <input
									type="text" name="categorie" value="${form.categorie}"
									class="form-control" id="exampleInputEmail1"
									placeholder="categorie">
								<c:if test="${not empty errors.categorie}">
									<span class="text-danger">${errors.categorie}</span>
								</c:if>
							</div>
							<div class="form-group">
								<label for="exampleInputPassword1">Prix</label> <input
									type="number" name="prix" value="${form.prix}"
									class="form-control" id="exampleInputPassword1"
									placeholder="prix">
								<c:if test="${not empty errors.prix}">
									<span class="text-danger">${errors.prix}</span>
								</c:if>
							</div>
							<div class="form-group">
								<label for="exampleInputConfirmPassword1">Description</label> <input
									type="text" name="description" value="${form.description}"
									class="form-control" id="exampleInputConfirmPassword1"
									placeholder="Description">
								<c:if test="${not empty errors.description}">
									<span class="text-danger">${errors.description}</span>
								</c:if>
							</div>

							<div class="form-group">
								<label for="exampleInputConfirmPassword1">Quantité</label> <input
									type="number" name="qte_disponible"
									value="${form.qte_disponible}" class="form-control" id=""
									placeholder="Password">
								<c:if test="${not empty errors.qte_disponible}">
									<span class="text-danger">${errors.qte_disponible}</span>
								</c:if>
							</div>
							<input type="hidden" name="idPizza" value="${form.id}"> 
							<input type="hidden" name="action" value="${nomActionPizza}">
							<div class="form-group">
								<label>Image</label> <input type="file" name="photo"
									class="file-upload-default">
								<div class="input-group col-xs-12">
									<input type="text" class="form-control file-upload-info"
										disabled="" placeholder="Choisir une image"> <span
										class="input-group-append">
										<button class="file-upload-browse btn btn-gradient-primary"
											type="button"
											onclick="document.querySelector('input[type=file]').click();">Choisir</button>
									</span>
								</div>

								<c:if test="${not empty errors.photo}">
									<span class="text-danger">${errors.photo}</span>
								</c:if>
							</div>
							<div class="modal-footer">
								<a href="admin-home?action=annuler" class="btn btn-secondary">Fermer</a>
								<button type="submit" class="btn btn-primary">Enregistrer
									les modifications</button>
							</div>
						</form>
					</div>

				</div>
			</div>
		</div>
	</c:if>

	<c:if test="${deletePizza}">
		<script>
			document.body.classList.add('modal-open');
		</script>
		<div class="modal fade show" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true"
			style="display: block;">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-body">Voulez-vous vraiment supprimer la pizza ${pizza.nom}</div>
					<div class="modal-footer">
						<form method="post" action="admin-home">
							<input type="hidden" name="action" value="${nomActionPizza}">
							<input type="hidden" name="idPizza" value="${pizza.id}">
							<a href="admin-home?action=annuler" >Annuler</a>
							<button type="submit" class="btn btn-danger">Confirmer
								la suppression</button>
						</form>
					</div>
				</div>
			</div>
		</div>

	</c:if>



</div>
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