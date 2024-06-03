<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- Le reste de vos imports --%>
<c:set var="listPanier" value="${sessionScope.listPanier}" />
<c:set var="user" value="${sessionScope.userLogin}" />
<c:set var="total" value="0" />


<jsp:include page="../layouts/header.jsp" />


	<!-- cart -->
	<div class="cart-section mt-150 mb-150">
		<div class="container">
		<c:if test="${empty listPanier}">
		    
		    <h2 style="color: red;">Votre panier est vide continuer vos achats</h2>
		    <a href="home" class="boxed-btn black">Retour a l'acceuil</a>
		</c:if>
		<c:if test="${not empty listPanier}">
			<div class="row">
				<div class="col-lg-8 col-md-12">
					<div class="cart-table-wrap">
						<table class="cart-table">
							<thead class="cart-table-head">
								<tr class="table-head-row">
									<th class="product-remove"></th>
									<th class="product-image">Image</th>
									<th class="product-name">Nom</th>
									<th class="product-price">Prix</th>
									<th class="product-quantity">Quatite</th>
									<th class="product-total">Total</th>
								</tr>
							</thead>
							<tbody>
							<c:forEach var="panier" items="${listPanier}" varStatus="loop">
								<tr class="table-body-row">
									<td class="product-remove"><a href="supprimer-panier?index=${loop.index}"><i class="far fa-window-close"></i></a></td>
									<td class="product-image"><img src="images/${panier.photo}" alt=""></td>
									<td class="product-name">${panier.nom}</td>
									<td class="product-price">${panier.prix} TND</td>
									<td class="product-quantity">
                        				<a style="text-decoration: none" href="quantite?action=0&index=${loop.index}" id="qtyminus">-</a>&nbsp
										<input type="number" value="${panier.quantite}" placeholder="0" readonly>
										<a style="text-decoration: none" href="quantite?action=1&index=${loop.index}" id="qtyplus">+</a>
									</td>
									<td class="product-total">${panier.prix * panier.quantite}</td>
                        			<c:set var="total" value="${total + panier.prix * panier.quantite}" />
								</tr>
							</c:forEach>
							</tbody>
						</table>
					
					</div>
				
				</div>

				<div class="col-lg-4">
					<div class="total-section">
					<form action="commander" method="post">
						<table class="total-table">
							<thead class="total-table-head">
								<tr class="table-total-row">
									<th>Total</th>
									<th>Price</th>
								</tr>
							</thead>
							<tbody>
								<tr class="total-data">
									<td><strong>Total Panier: </strong></td>
									<td>${total} TND</td>
								</tr>
								<tr class="total-data">
									<td><strong>Livraison: </strong></td>
									<td>Gratuite</td>
								</tr>
							</tbody>
						</table>
						<div class="form-group">
			                 <input type="text" name="adresse" class="form-control" placeholder="Renseignez l'adresse de livraison">
			                 <c:if test="${not empty errors.adresse}">
			                     <small style="color: red;">${errors.adresse}</small>
			                 </c:if>
			             </div>
			             <div class="form-group">
			                 <input type="text" name="phone" class="form-control" value="${user.phone}" placeholder="Votre Telephone">
			                 <c:if test="${not empty errors.phone}">
			                     <small style="color: red;">${errors.phone}</small>
			                 </c:if>
			             </div>
			             <input type="hidden" name="total" value="${total}" />
						<div class="cart-buttons">
							<c:if test="${empty userLogin}">
								<a href="login" class="boxed-btn black">ConnectezVOus pour commander</a>
							</c:if>
							<c:if test="${not empty userLogin}">
							    <button class="btn btn-primary" type="submit">Passer commander</button>
							</c:if>
							<a href="home" class="btn btn-info">Ajouter d'autres</a>
						</div>
					</form>
					</div>
				</div>
			</div>
		</c:if>
		</div>
	</div>
	<!-- end cart -->

<jsp:include page="../layouts/footer.jsp" />

