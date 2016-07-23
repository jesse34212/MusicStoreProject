<jsp:include page="/includes/header.jsp" />
<jsp:include page="/includes/column_left_admin.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- begin middle column -->

<section id="admin">

<h1>Products</h1>
<div>
<table class="none">
    <tr>
        <td>Code</td>
        <td>Description</td>
        <td>Price</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
    </tr>
    <c:forEach var="product" items="${requestScope.products}">
    <tr>
        <td><c:out value="${product.code}" /></td>
        <td><c:out value="${product.description}" /></td>
        <td class="right">$<c:out value="${product.price}" /></td>
        <td><a href="productMaint?action=editProduct&amp;productCode=${product.code}">Edit</a></td>
        <td><a href="productMaint?action=deleteProduct&amp;productCode=${product.code}">Delete</a></td>
    </tr>
    </c:forEach>
</table>
</div>
    <div class="vspacer"></div>
<form action="productMaint" method="post">
    <input type="hidden" name="action" value="addProduct">
    <input type="submit" value="Add Product">
</form>
</section>    
<!-- end middle column -->

<jsp:include page="/includes/footer.jsp" />