<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>
<form action="myServlet">
    <label for="key">Key:</label>
    <input name="key" id="key" type="text"></br>
    <label for="value">Value</label>
    <input name="value" id="value" type="number" value="0"><br>
    <label for="mock">Mock</label>
    <input name="mock" id="mock" type="checkbox"><br>
    <label for="sync">Sync</label>
    <input name="sync" id="sync" type="checkbox"><br>
    <input type="submit">
</form>
</body>
</html>