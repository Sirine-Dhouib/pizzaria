<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
								<h5 class="mb-0">
									Connectez-vous
								</h5>
							</div>

							<div id="collapseOne" class="collapse show"
								aria-labelledby="headingOne" data-parent="#accordionExample"
								style="">
								<div class="card-body">
									<div class="billing-address-form">
									<c:if test="${not empty sessionScope.loginError}">
										<h3 style="color: red;">mot de pass ou email incorrect!</h3>
										<c:remove var="loginError" scope="session" />
									</c:if>
									<c:if test="${bloque}">
										<h3 style="color: red;">Votre Compte est bloqué veuillez
											contacter l'administrateur</h3>
										<c:remove var="bloque" scope="session" />
									</c:if>
									<c:if test="${not empty compteCree}">
										<h3 class="error">Votre compte est cree avec succes,
											connectez-vous!</h3>
									</c:if>
									<c:if test="${not empty messages}">
										<h3 style="color: red;">${messages}</h3>
									</c:if>
										<form action="login" method="post" >
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
												<input type="submit" value="Connecter" class="btn btn-primary py-2 px-5">
											</p>
											<p>
												Vous avez pas de compte? <a
													href="register"><b>Inscrivez-vous!</b></a>
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