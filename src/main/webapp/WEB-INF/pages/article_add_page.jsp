<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>New Article</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <cf:form method="POST" action="save_article" modelAttribute="article" enctype="multipart/form-data">
        <h3>New article</h3>
        <cf:hidden path="id"/>
        <cf:select enctype="multipart/form-data" class="selectpicker form-control form-group" name="group" path="group">
            <c:forEach items="${groups}" var="group">
                <cf:option value="${group.id}">${group.name}</cf:option>
            </c:forEach>
        </cf:select>
        <cf:errors path="name"></cf:errors>
        <cf:input class="form-control form-group" type="text" name="name" placeholder="Name" path="name"/>
        <cf:errors path="link"></cf:errors>
        <cf:input class="form-control form-group" type="text" name="link" placeholder="Short link" path="link"/>
        <cf:input class="form-control form-group" type="text" name="description" placeholder="Short description"
                  path="description"/>
        <cf:select enctype="multipart/form-data" class="selectpicker form-control form-group" name="usefulnessLevel"
                   path="usefulnessLevel">
            <c:forEach items="${levels}" var="levels">
                <option value="${levels.value}">${levels.description}</option>
            </c:forEach>
        </cf:select>
        <input type="submit" class="btn btn-primary" value="Add">
        <button type="submit" id="go_back" class="btn btn-default navbar-btn"><a href="/"> Go back</a></button>
    </cf:form>
</div>

<script>
    $('.selectpicker').selectpicker();
</script>
</body>
</html>