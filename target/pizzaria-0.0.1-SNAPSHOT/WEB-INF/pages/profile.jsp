
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!-- Header and navbar -->

<jsp:include page="../layouts/header.jsp" />
<!-- end header -->
<div class="breadcrumb-section breadcrumb-bg">
	<div class="container">
		<div class="row">
			<div class="col-lg-8 offset-lg-2 text-center">
				<div class="breadcrumb-text">
					<p>Bienvenue sur votre profil, vous pouvez suivre l'etat de vos
						commandes</p>
					<h1>Cher client, ${userLogin.getNom()}</h1>
					<c:if test="${not empty sessionScope.commandePasser}">
                        <h6 class="text-success">La ${commandePasser} est passer avec succes</h6>
                        <c:remove var="commandePasser" scope="session" />
                    </c:if>
				</div>
			</div>
		</div>
	</div>
</div>
<br>
<br>
<div class="container">
	<div class="row">
		<div class="col-md-6 col-lg-6 offset-lg-1 offset-xl-0">
			<div class="card" style="border-radius: 10px;">
				<div class="card-header px-4 py-5">
					<h5 class="text-muted mb-0">Vos commandes en attentes</h5>
				</div>
				<c:if test="${empty commandesEnAttentes}">
					<p>Vous n'avez pas de commandes en attentes.</p>
				</c:if>
				<c:if test="${not empty commandesEnAttentes}">
					<c:forEach var="commande" items="${commandesEnAttentes}">
						<div class="card-body p-4">
							<div
								class="d-flex justify-content-between align-items-center mb-4">
								<p class="lead fw-normal mb-0" style="color: #a8729a;">${commande.num_commande}</p>
							</div>
							<div class="d-flex justify-content-between pt-2">
								<p class="fw-bold mb-0">Details de la commande</p>
								<p class="text-muted mb-0">
									<span class="fw-bold me-4">Total</span> ${commande.total} f
								</p>
							</div>

							<div class="d-flex justify-content-between pt-2">
								<p class="text-muted mb-0">Adresse de livraison :</p>
								<p class="text-muted mb-0">${commande.adresse}</p>
							</div>

							<div class="d-flex justify-content-between">
								<p class="text-muted mb-0">Date de commande :</p>
								<p class="text-muted mb-0">${commande.date_commande}</p>
							</div>
							<c:forEach var="pizzaCommande"
								items="${gestionCommande.getCommandesByOrderNumber(commande.num_commande)}">
								<c:set var="pizza"
									value="${gestionCommande.getPizzaById(pizzaCommande.value.pizzaId)}" />
								<div class="card shadow-0 border mb-4">
									<div class="card-body">
										<div class="row">
											<div class="col-md-2">
												<img src="images/${pizza.photo}" class="img-fluid"
													alt="Phone">
											</div>
											<div
												class="col-md-2 text-center d-flex justify-content-center align-items-center">
												<p class="text-muted mb-0">${pizza.nom}</p>
											</div>
											<div
												class="col-md-2 text-center d-flex justify-content-center align-items-center">
												<p class="text-muted mb-0 small">${pizza.categorie}</p>
											</div>
											<div
												class="col-md-2 text-center d-flex justify-content-center align-items-center">
												<p class="text-muted mb-0 small">Qte : x
													${pizzaCommande.value.quantite}</p>
											</div>
											<div
												class="col-md-2 text-center d-flex justify-content-center align-items-center">
												<p class="text-muted mb-0 small">${pizza.prix}f</p>
											</div>
										</div>
									</div>
								</div>
							</c:forEach>
						</div>
					</c:forEach>
				</c:if>
			</div>
		</div>
		<div class="col-md-6 col-lg-6 offset-lg-1 offset-xl-0">
			<div class="card">
				<div class="card-header px-4 py-5">
					<h5 class="text-muted mb-0">
						Votre historique, <span style="color: #a8729a;"> Cher
							client, ${userLogin.getNom()}</span>!
					</h5>
				</div>
				<c:if test="${empty historiques}">
					<p>Votre historique est vide.</p>
				</c:if>
				<c:if test="${not empty historiques}">
					<c:forEach var="commande" items="${historiques}">
						<div class="card-body p-4">
							<div
								class="d-flex justify-content-between align-items-center mb-4">
								<p class="lead fw-normal mb-0" style="color: #a8729a;">${commande.num_commande}</p>
							</div>
							<div class="d-flex justify-content-between pt-2">
								<p class="fw-bold mb-0">Details de la commande</p>
								<p class="text-muted mb-0">
									<span class="fw-bold me-4">Total</span> ${commande.total} f
								</p>
							</div>

							<div class="d-flex justify-content-between pt-2">
								<p class="text-muted mb-0">Adresse de livraison :</p>
								<p class="text-muted mb-0">${commande.adresse}</p>
							</div>

							<div class="d-flex justify-content-between">
								<p class="text-muted mb-0">Validé depuis :</p>
								<p class="text-muted mb-0">${commande.date_validation}</p>
							</div>
							<c:forEach var="pizzaCommande"
								items="${gestionCommande.getCommandesByOrderNumber(commande.num_commande)}">
								<c:set var="pizza"
									value="${gestionCommande.getPizzaById(pizzaCommande.value.pizzaId)}" />
								<div class="card shadow-0 border mb-4">
									<div class="card-body">
										<div class="row">
											<div class="col-md-2">
												<img src="images/${pizza.photo}" class="img-fluid"
													alt="Phone">
											</div>
											<div
												class="col-md-2 text-center d-flex justify-content-center align-items-center">
												<p class="text-muted mb-0">${pizza.nom}</p>
											</div>
											<div
												class="col-md-2 text-center d-flex justify-content-center align-items-center">
												<p class="text-muted mb-0 small">${pizza.categorie}</p>
											</div>
											<div
												class="col-md-2 text-center d-flex justify-content-center align-items-center">
												<p class="text-muted mb-0 small">Qte : x
													${pizzaCommande.value.quantite}</p>
											</div>
											<div
												class="col-md-2 text-center d-flex justify-content-center align-items-center">
												<p class="text-muted mb-0 small">${pizza.prix}f</p>
											</div>
										</div>
									</div>
								</div>
							</c:forEach>
						</div>
					</c:forEach>
				</c:if>
			</div>
		</div>
	</div>
</div>
<br>
<br>

<jsp:include page="../layouts/footer.jsp" />
