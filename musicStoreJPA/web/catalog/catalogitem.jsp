<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/includes/header.jsp" />
<jsp:include page="/includes/column_left_all.jsp" />

<!-- start the middle column -->

<section>
    <jsp:include page="/includes/product_table.jsp" />
    <p class="clear_both">
        <c:if test="${product.code == '8601'}">
        The debut album from 86 (the band), True Life Songs and Pictures rocks and
        twangs in equal measure. Filled with banjo, one-string bass, fiddle, and
        3-part harmonies, this semi-rock, semi-country, semi-bluegrass album covers
        a lot of ground. "How to Get There" is a rambling epic that unleashes a
        rapid-fire lyrical barrage while "Don't Close Your Eyes" and "Morning Sun"
        are more melancholy and bittersweet.
        </c:if>
        <c:if test="${product.code == 'jr01'}">
        The debut album from Joe Rut rambles from Byrds-esque folk pop of "Filter" to
        the country sounds of "Find My Way Marie" to psychedelic Brit-pop tunes like
        "A Place In All This." This well-crafted album is unique and cohesive, revealing
        its many layers on repeated listens.
        </c:if>
        <c:if test="${product.code == 'pf01'}">
        This 68-minute opus from San Francisco's Paddlefoot doesn't pull any
        punches. Songs like "64 Corvair, Part 2", "Whiskey Before Breakfast",
        and "The Murphy-Ryan Polkas" mix traditional American and Irish fiddle
        tunes with indie rock. The result is somewhere between The Pogues, Camper
        Van Beethoven, and Uncle Tupelo. The sincerity and quirkiness of other songs
        like "When I Was Nine" and "Tiny House" are more reminiscent of Jonathan Richman.
        </c:if>
        <c:if test="${product.code == 'pf02'}">
        The second CD from San Francisco's Paddlefoot finds the band maturing as it
        roams through much of the same musical terrain as the previous CD. While this
        album occasionally rocks, it also has its introspective and bittersweet moments.
        "Neon Lights" is a romantic tribute to neon lights, "Twist Away" is a heartfelt
        song of longing and loss, and "Let the Possums Play Possum" is a instrumental romp
        that's driven by dual fiddles. If you liked the first CD, you'll like this one too.
        </c:if>
    </p>
</section>
<jsp:include page="/includes/column_right_buttons.jsp" />
<jsp:include page="/includes/footer.jsp" />
