<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %> 

    <sql:setDataSource
        var="myDS"
        driver="com.mysql.jdbc.Driver"
        url="jdbc:mysql://localhost:3306/music_jpa"
        user="root" password="sesame"
    />
     
    <sql:query var="listProducts"   dataSource="${myDS}">
        SELECT * FROM product;
    </sql:query>

</div>
                <div id="sidebar">
                    <aside id="sidebarB">
                        <h1>New Release</h1>
                            <c:forEach var="product" items="${listProducts.rows}" varStatus="loop">
                                <c:if test="${loop.last}">        
                                    <img src="<c:url value='/images/${product.code}_cover.jpg' />" width="80" alt="${product.code} cover"><br />
                                    <a href="<c:url value='/catalog/product/${product.code}' />">${product.description}</a> 
                                </c:if>
                            </c:forEach>
                        <p class="news_item">A refreshing mix of rock, country, and bluegrass 
                            that will have you stomping your foot and crying in your beer 
                            in no time.</p>
                    </aside>
                </div>
            </div>
        </div>
    </div>
    <div id="footer">
        <div>
            <div>
                <a href="http://freewebsitetemplates.com/go/twitter/" target="_blank" id="twitter">Twitter</a>
                <a href="http://freewebsitetemplates.com/go/facebook/" target="_blank" id="facebook">Facebook</a>
                <a href="http://freewebsitetemplates.com/go/googleplus/" target="_blank" id="googleplus">Google&#43;</a>
            </div>
            <p>
                &copy; Copyright 2016. All rights reserved
            </p>
        </div>
    </div>
</body>
</html>