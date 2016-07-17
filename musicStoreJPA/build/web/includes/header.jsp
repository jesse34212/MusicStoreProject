<%@page contentType="text/html" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<!-- Website template by freewebsitetemplates.com -->
<html>
<head>
    <meta charset="UTF-8">
    <title>Good Music Website Template</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/styles/style.css'/>">
    <!--[if IE 7]><link rel="stylesheet" type="text/css" href=""<c:url value='/styles/ie7.css'/>"><![endif]-->
</head>
<body>
    <div id="header">
        <div>
            <div>
                <ul>
                    <li <c:if test="${pageContext.request.servletPath == '/index.jsp'}">class="current"</c:if>>
                         <a href="<c:url value='/' />">Home</a>
                    </li>
                    <li <c:if test="${fn:contains(pageContext.request.servletPath,'/catalog')}">class="current"</c:if>>
                        <a href="<c:url value='/catalog' />">Catalog</a>
                    </li>
                </ul>
                <a href="<c:url value='/' />" id="logo"><img src="<c:url value='/images/logo.png' />" alt="Image"></a>
                <ul>
                    <li <c:if test="${fn:contains(pageContext.request.servletPath,'/admin') || pageContext.request.servletPath == '/login.jsp'}">class="current"</c:if>>
                        <a href="<c:url value='/admin'/>">Admin<c:if test="${not empty AdminController.userName}"> - ${AdminController.userName}</c:if></a>
                    </li>
                    <li <c:if test="${fn:contains(pageContext.request.servletPath,'/cart')}">class="current"</c:if>>
                        <a href="<c:url value='/order/showCart'/>">Cart</a>
                    </li>
		</ul>
            </div>
	</div>
        <div>
            <ul>
                <li>
                    <a href="<c:url value='/email' />">Join Email List</a>
                </li>
                <li>
                    <a href="<c:url value='/customer_service' />">Customer Service</a>
                </li>
                <li>
                    <a href="<c:url value='/user/deleteCookies' />"><span>Logout</span></a>
                </li>
            </ul>
        </div>
    </div>
    <div id="body">
        <div>
            <div>
                <div id="article">