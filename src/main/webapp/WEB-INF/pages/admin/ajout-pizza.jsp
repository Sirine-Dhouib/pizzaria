<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<jsp:include page="../../layouts/header.jsp"/>

<section class="ftco-appointment py-5">
    <div class="overlay"></div>
    <div class="container-wrap">
        <div class="row no-gutters d-md-flex align-items-center">
            <div class="col-md-6 d-flex align-self-stretch">
                <img alt="" src="${pageContext.request.contextPath}/images/pizza-4.jpg" width="100%">
            </div>

            <div class="col-md-6 appointment ftco-animate fadeInUp ftco-animated">
                <h3 class="mb-3">Ajout une pizza</h3>
                <c:if test="${not empty pizzaAjoute}">
                    <h3 class="error">Pizza ajoutée avec succes!</h3>
                </c:if>
                <form action="${pageContext.request.contextPath}/ajout-pizza" method="post" class="appointment-form" enctype="multipart/form-data">
                    <div class="d-md-flex">
                        <div class="form-group">
                            <input type="text" name="nom" class="form-control" value="${form.nom}" placeholder="Le nom">
                            <c:if test="${not empty errors.nom}">
                                <small style="color: red;">${errors.nom}</small>
                            </c:if>
                        </div>
                    </div>
                    <div class="d-me-flex">
                        <div class="form-group">
                            <input type="text" name="categorie" class="form-control" value="${form.categorie}" placeholder="la Categorie">
                            <c:if test="${not empty errors.categorie}">
                                <small style="color: red;">${errors.categorie}</small>
                            </c:if>
                        </div>
                    </div>
                    <div class="d-me-flex">
                        <div class="form-group">
                            <input type="number" name="prix" class="form-control" value="${form.prix}" placeholder="Le prix">
                            <c:if test="${not empty errors.prix}">
                                <small style="color: red;">${errors.prix}</small>
                            </c:if>
                        </div>
                    </div>
                    <div class="d-md-flex">
                        <div class="form-group">
                            <textarea name="description" class="form-control" placeholder="La description"></textarea>
                            <c:if test="${not empty errors.description}">
                                <small style="color: red;">${errors.description}</small>
                            </c:if>
                        </div>
                    </div>
                    <div class="d-me-flex">
                        <div class="form-group">
                            <input type="number" name="qte_disponible" class="form-control" value="${form.qte_disponible}" placeholder="La quantité">
                            <c:if test="${not empty errors.qte_disponible}">
                                <small style="color: red;">${errors.qte_disponible}</small>
                            </c:if>
                        </div>
                    </div>
                    <div class="d-me-flex">
                        <div class="form-group">
                            <input type="file" name="photo" class="form-control">
                            <c:if test="${not empty errors.photo}">
                                <small style="color: red;">${errors.photo}</small>
                            </c:if>
                        </div>
                    </div>

                    <div class="form-group">
                        <input type="submit" value="Ajouter" class="btn btn-primary py-2 px-5">
                    </div>
                </form>
            </div>
        </div>
    </div>
</section>

<jsp:include page="../../layouts/footer.jsp"/>
</body>
</html>
