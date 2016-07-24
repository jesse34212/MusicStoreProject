<jsp:include page="/includes/header.jsp" />
<jsp:include page="/includes/column_left_admin.jsp" />
<%@ taglib prefix="t1" uri="/WEB-INF/team1.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- begin middle column -->

<section id="admin">

<h1>Product</h1>    

<p><i><c:out value="${message}" /></i></p>
<p><i><c:out value="${numberMessage}" /></i></p>
<p><t1:ifEmptyMark field=""/> marks required fields</p>
<form action="productMaint" method="post">
    <table>
        <tr>
            <td><label>Code:</label></td>
            <td>
                <input type="text" name="productCode" maxlength="4" size="4" value="<c:out value='${productCode}'/>" required>
                <t1:ifEmptyMark field="${productCode}"/>
            </td>
        </tr>
        <tr>
            <td><label>Description:</label></td>
            <td>
                <input type="text" name="productDescription" size="55" value="<c:out value='${productDescription}'/>" required>
                <t1:ifEmptyMark field="${productDescription}"/>
            </td>
        </tr>
        <tr>
            <td><label>Price:</label></td>
            <td>
                <input type="text" name="productPrice" maxlength="6" size="6" value="<c:out value='${productPrice}'/>" required>
                <t1:ifEmptyMark field="${productPrice}"/>
            </td>
        </tr>
        </table>
        <table>
        <tr>
            <td>
                <c:if test="${pageContext.request.getParameter('action') == 'editProduct' or pageContext.request.getParameter('action') == 'updateProduct'}">
                    <input type="hidden" name="action" value="updateProduct">
                    <input type="submit" value="Update Product" style="margin-left: 7em;">
                </c:if>
                <c:if test="${pageContext.request.getParameter('action') == 'addProduct'}">
                    <input type="hidden" name="action" value="createProduct">
                    <input type="submit" value="Add Product" style="margin-left: 7em;">
                </c:if>
                </form>
            </td>
            <td>
                <form action="productMaint">
    <input type="hidden" name="action" value="displayProducts">
    <input type="submit" value="View Products"> 
</form>
            </td>
            
        </tr>
    </table>



    

</section>

<!-- end middle column -->

<jsp:include page="/includes/footer.jsp" />