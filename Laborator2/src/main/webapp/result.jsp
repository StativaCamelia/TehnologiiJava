<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%

    Cookie category = new Cookie("category",
            request.getParameter("category"));

    category.setMaxAge(60*60*10);

    response.addCookie(category);
%>
<html>
<head>
    <title>Title</title>
</head>
<body>

<table>
    <%--@elvariable id="recordList" type="java.util.List"--%>
    <c:forEach items="${recordList}" var="record">
        <tr>
            <td>Record Key: </td>
            <td>${record.key}</td>
        </tr>

        <tr>
            <td>Record Name:</td>
            <td>${record.value}</td>
        </tr>
        <tr>
            <td>Record Category: </td>
            <td>${record.category.name}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
