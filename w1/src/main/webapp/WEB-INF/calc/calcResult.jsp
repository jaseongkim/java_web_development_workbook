
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>calcResult</title>
</head>
<body>
  <h1>num1 : ${ param.name1}</h1>
  <h1>num2 : ${ param.name2}</h1>
  <h1>sum : ${Integer.parseInt(param.name1) +Integer.parseInt(param.name2)}</h1>
</body>
</html>
