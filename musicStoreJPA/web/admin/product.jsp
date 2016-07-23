<jsp:include page="/includes/header.jsp" />
<jsp:include page="/includes/column_left_admin.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- begin middle column -->

<section id="admin">

<h1>Product</h1>    

<p><i><c:out value="${message}" /></i></p>
<p><i><c:out value="${numberMessage}" /></i></p>
<form action="productMaint" method="post">
    <table>
        <tr>
            <td><label>Code:</label></td>
            <td><input type="text" name="productCode" maxlength="4" size="4" value="<c:out value='${productCode}'/>"></td>
        </tr>
        <tr>
            <td><label>Description:</label></td>
            <td><input type="text" name="productDescription" size="55" value="<c:out value='${productDescription}'/>"></td>
        </tr>
        <tr>
            <td><label>Price:</label></td>
            <td><input type="text" name="productPrice" maxlength="6" size="6" value="<c:out value='${productPrice}'/>"></td>
        </tr>
        </table>
        <table>
        <tr>
            <td>
                <input type="hidden" name="action" value="updateProduct">
                <input type="submit" value="Update Product" style="margin-left: 6em;">
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