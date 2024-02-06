
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

    <form method="POST" action="/todo/register"  accept-charset="utf-8">
        <div>
            <input type="text" name="title" placeholder="제목을 입력하세요">
        </div>
        <div>
            <input type="date" name="dueDate">
        </div>
        <div>
            <button type="reset">RESET</button>
            <button type="submit">REGISTER</button>
        </div>
    </form>

</body>
</html>
