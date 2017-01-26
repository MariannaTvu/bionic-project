<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Delete Group</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <div class="form-group"><h3>Select groups to delete</h3></div>
    <form role="delete" action="group/delete" method="post">
            <table class="table table-striped">
                <thead>
                <tr>
                    <td></td>
                    <td><b>Name</b></td>
                </tr>
                </thead>
                <c:forEach items="${groups}" var="group">
                    <tr>
                        <td><input type="checkbox" name="toDelete[]" value="${group.id}" id="checkbox_${group.id}"/>
                        </td>
                        <td>${group.name}</td>
                    </tr>
                </c:forEach>
            </table>
            <button type="submit" id="delete_groups" class="btn btn-default navbar-btn">Delete Groups</button>
    </form>
</div>
</body>
</html>