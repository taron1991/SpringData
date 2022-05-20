<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<table>
    <c:forEach var="namess" items="${names}">
        <tr>
            <th>${namess.name}</th>
            <th>${namess.color}</th>
        </tr>

    </c:forEach>

</table>
</body>
</html>
