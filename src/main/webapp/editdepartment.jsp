<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit employee</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<div class="panel">
    <h1>Edit employee</h1>
    <form action="departments" method="post">
        <div>
            <input type="hidden" name="id" value="${dep[0].id}">
            <label>Name</label>
            <input required type="text" name="name" value="${dep[0].name}">
        </div>
        <br/>
        <input type="submit" class="btn" name="btnedit" value="Edit department">
    </form>
</div>
</body>
</html>
