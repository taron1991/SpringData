<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML>
<html>

<body>

<h2> Hello Write your car! </h2>

<br>
<br>



<form:form action="/car/results" modelAttribute="save" method="get">
    Name: <form:input path="name"/>
    <br>
    Color: <form:input path="color"/>
    <br>
    Price: <form:input path="price"/>
    <br>
    <p><input type="submit" value="OK"></p>

</form:form>

</body>

</html>
