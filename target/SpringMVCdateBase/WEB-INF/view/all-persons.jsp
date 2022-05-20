<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>

<h1>------------------</h1>

<br>

<c:forEach var="student" items="${persons}">
    <tr>
        <th>${student.name}</th>
        <th>${student.age}</th>
        <th>${student.id}</th>
    </tr>

</c:forEach>

<br>

<h1>------------------</h1>

</body>
</html>
