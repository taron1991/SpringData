<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>

<c:forEach var="cars" items="${allcars}">

    <tr>
        <th> ${cars.name}</th>
        <th> ${cars.color}</th>
        <th> ${cars.price}</th>
    </tr>

</c:forEach>

</body>
</html>
