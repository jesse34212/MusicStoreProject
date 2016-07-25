<jsp:include page="/includes/header.jsp" />
<jsp:include page="/includes/column_left_admin.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- begin middle column -->

<section id="admin">

<h1>Are you sure you want to delete this product?</h1>

<table>
    <tr>
        <td><b>Code:</b></td>
        <td>${productCode}</td>
    </tr>
    <tr>
        <td><b>Description:</b></td>
        <td>${productDescription}</td>
    </tr>
    <tr>
        <td><b>Price:</b></td>
        <td>$<fmt:formatNumber pattern="##.00" value="${product.price}" /></td>
    </tr>
</table>

<table>
    <tr>
        <td>
            <form action="productMaint" method="post">
                <input type="hidden" name="productCode" value="<c:out value='${productCode}'/>">
                <input type="hidden" name="action" value="confirmDelete">
                <input type="submit" value="Yes">
            </form>
        </td>
        <td>
            <form action="productMaint" method="post">
                <input type="hidden" name="action" value="displayProducts">
                <input type="submit" value="No">
            </form>
        </td>
    </tr>
</table>
        
        
</section>
<!-- end middle column -->

<jsp:include page="/includes/footer.jsp" />