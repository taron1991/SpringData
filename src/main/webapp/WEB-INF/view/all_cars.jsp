<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>

<c:forEach var="cycle" items="${save}">

<tr>
    <th> ${cycle.name}</th>
    <th> ${cycle.color}</th>
    <th> ${cycle.price}</th>
</tr>

</c:forEach>

</body>
</html>
