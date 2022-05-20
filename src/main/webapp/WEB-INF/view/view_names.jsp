<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<table>
    <c:forEach var="car" items="${names}">
        <tr>
            <th>${car.name}</th>
            <th>${car.color}</th>
        </tr>

    </c:forEach>

</table>
</body>
</html>
