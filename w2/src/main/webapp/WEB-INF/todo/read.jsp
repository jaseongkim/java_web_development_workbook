
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<div>
    <input type="text" name="tno" value="${todoDTO.tno}" readonly>
</div>
<div>
    <input type="text" name="title" value="${todoDTO.title}" readonly>
</div>
<div>
    <input type="date" name="dueDate" value="${todoDTO.dueDate}">
</div>
<div>
    <input type="checkbox" name="finished" ${todoDTO.finished ? "checked" : ""} readonly>
</div>
<div>
    <a href="/todo/modify?tno=${todoDTO.tno}">MODIFY/REMOVE</a>
    <a href="/todo/list">LIST</a>
</div>

</body>
</html>
