<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>TodoList</h1>

<h1>${appName}</h1>
<h1>${loginInfo}</h1>
<h1>${loginInfo.mname}</h1>

<ul>
    <c:forEach items="${list}" var="dto">
        <li>
            <span><a href="/todo/read?tno=${dto.tno}">${dto.tno}</a></span>
            <span>${dto.title}</span>
            <span>${dto.dueDate}</span>
            <span>${dto.finished ? "DONE" : "YET"}</span>
        </li>
    </c:forEach>
</ul>

<form  action="/logout" method="post">
    <button type="submit">LOGOUT</button>
</form>

</body>
</html>
