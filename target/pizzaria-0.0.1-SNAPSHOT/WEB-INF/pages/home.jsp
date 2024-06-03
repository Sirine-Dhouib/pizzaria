<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!-- Header and navbar -->

<jsp:include page="../layouts/header.jsp" />
<!-- end header -->

<!-- home page slider -->
<c:if test="${not empty pizzaList}">
	<div class="homepage-slider">
		<!-- single home slider -->
		<c:forEach items="${pizzaList}" var="pizza">
			<div class="single-homepage-slider ">
				<div class="container">
					<div class="row">
						<div class="col-md-6 col-lg-6 offset-lg-1 offset-xl-0">
							<div class="hero-text">
								<div class="hero-text-tablecell">
									<p class="subtitle">${pizza.categorie}</p>
									<h1>${pizza.nom}</h1>
									<p class="subtitle">${pizza.prix}Fcfa</p>
									<div class="hero-btns">
										<a href="panier?id=${pizza.id}" class="boxed-btn">Ajout au panier</a>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-6 col-lg-6 offset-lg-1 offset-xl-0">
							<div class="img-fluid" alt="" style="margin-top: 130px;">
								<img src="images/${pizza.photo}" class="img-fluid" alt="">
							</div>
						</div>
					</div>
				</div>
			</div>
		</c:forEach>
		<!-- single home slider -->

	</div>
</c:if>
<c:if test="${empty pizzaList}">
	<p>Aucune pizza n'est disponible pour le moment.</p>
</c:if>
<!-- end home page slider -->
<!-- end features list section -->

<!-- product section -->

<div class="product-section mt-150 mb-150">
	<div class="container">
		<div class="row">
			<div class="col-lg-8 offset-lg-2 text-center">
				<div class="section-title">
					<h3>
					    <span class="orange-text">SunuPizza</span> - Carte des Pizzas
					</h3>
					<p>Découvrez nos délicieuses pizzas artisanales chez SunuPizza. Bon appétit !</p>

				</div>
			</div>
		</div>

		<div class="row">
		<c:if test="${not empty pizzaList}">
			<c:forEach items="${pizzaList}" var="pizza">
			<div class="col-lg-4 col-md-6 text-center">
				<div class="single-product-item">
					<div class="product-image">
						<a href="single-product.html"><img
							src="images/${pizza.photo}" alt=""></a>
					</div>
					<h3>${pizza.nom}</h3>
					<p class="product-price">
						${pizza.prix} F
					</p>
					<a href="panier?id=${pizza.id}" class="cart-btn"><i
						class="fas fa-shopping-cart"></i> Ajouter au panier</a>
				</div>
			</div>
			
			</c:forEach>
			</c:if>
		<c:if test="${empty pizzaList}">
			<p>Aucune pizza n'est disponible pour le moment.</p>
		</c:if>
		</div>
	</div>
</div>
<!-- end product section -->

<jsp:include page="../layouts/footer.jsp" />

