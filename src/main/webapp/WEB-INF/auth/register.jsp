<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../layouts/header.jsp" />
<div class="checkout-section mt-150 mb-150">
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<div class="checkout-accordion-wrap">
					<div class="accordion" id="accordionExample">
						<div class="card single-accordion">
							<div class="card-header" id="headingOne">
								<h5 class="mb-0">Inscrivez-vous</h5>
							</div>

							<div id="collapseOne" class="collapse show"
								aria-labelledby="headingOne" data-parent="#accordionExample"
								style="">
								<div class="card-body">
									<div class="billing-address-form">
										<c:if test="${not empty sessionScope.emailExist}">
											<h5 class="text-danger">L'email existe deja dans le systeme!</h5>
										</c:if>
										<form action="register" method="post">
											<p>
												<input type="text" name="nom" class="form-control"
													value="${form.nom}" placeholder="Votre nom">
												<c:if test="${not empty errors.nom}">
													<small style="color: red;">${errors.nom}</small>
												</c:if>
											</p>
											<p>
												<input type="text" name="prenom" class="form-control"
													value="${form.prenom}" placeholder="Votre prenom">
												<c:if test="${not empty errors.prenom}">
													<small style="color: red;">${errors.prenom}</small>
												</c:if>
											</p>
											<p>
												<input type="text" name="phone" class="form-control"
													value="${form.phone}" placeholder="Votre telephone">
												<c:if test="${not empty errors.phone}">
													<small style="color: red;">${errors.phone}</small>
												</c:if>
											</p>
											<p>
												<input type="text" name="email" class="form-control"
													value="${form.email}" placeholder="Votre email">
												<c:if test="${not empty errors.email}">
													<small style="color: red;">${errors.email}</small>
												</c:if>
											</p>
											<p>
												<input type="password" name="password" class="form-control"
													placeholder="Votre mot de passe">
												<c:if test="${not empty errors.password}">
													<small style="color: red;">${errors.password}</small>
												</c:if>
											</p>
											<p>
												<input type="submit" value="Inscrire"
													class="btn btn-primary py-2 px-5">
											</p>
											<p>
												Vous avez deja un compte? <a href="login"><b>Connectez-vous!</b></a>
											</p>
										</form>
									</div>
								</div>
							</div>
						</div>
					</div>

				</div>
			</div>
		</div>
	</div>
</div>

<jsp:include page="../layouts/footer.jsp" />
</body>
</html>
