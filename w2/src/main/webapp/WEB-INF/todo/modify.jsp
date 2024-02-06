<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>TodoModifyRemove</title>
</head>
<body>

    <form id="form1" method="post" action="/todo/modify">
        <div>
            <input type="text" name="tno" value="${todoDTO.tno}" readonly>
        </div>
        <div>
            <input type="text" name="title" value="${todoDTO.title}" >
        </div>
        <div>
            <input type="date" name="dueDate" value="${todoDTO.dueDate}">
        </div>
        <div>
            <input type="checkbox" name="finished" ${todoDTO.finished ? "checked" : ""}>
        </div>
        <div>
           <button type="submit">MODIFY</button>
        </div>
    </form>

    <form id="form2" method="post" action="/todo/remove">
        <input type="hidden" name="tno" value="${todoDTO.tno}" readonly>
        <button type="submit">REMOVE</button>
    </form>

</body>
</html>
