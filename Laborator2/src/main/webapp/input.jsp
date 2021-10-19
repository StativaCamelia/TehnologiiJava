<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>

<html>

<body>

<form action="record" method="get">
    Select a Category:&nbsp;
    <select name="category">
        <c:set var="varCookie" value="${cookieAttribute}" />
        <option value="none">none</option>
        <%request.getParameter("cookieAttribute");%>
        <c:forEach items="${listCategory}" var="category">
            <option ${category.name == varCookie ? "selected":""} value="${category.name}">${category.name}</option>
        </c:forEach>
    </select>
    <br>
    Value:&nbsp;
    <input type="text" id="value" name="value"><br>
    Key:&nbsp;
    <input type="text" id="key" name="key"><br>
    <br/>
    <input type="submit" value="Submit" />
</form>
<br/>
</body>
</html>
