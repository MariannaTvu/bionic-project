<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Wiki archive</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>

<body>
<div class="container">
    <h3><a href="/">Articles List</a></h3>

    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul id="groupList" class="nav navbar-nav">
                    <li>
                        <button type="button" id="add_article" class="btn btn-default navbar-btn">Add Article</button>
                    </li>
                    <li>
                        <button type="button" id="add_group" class="btn btn-default navbar-btn">Add Group</button>
                    </li>
                    <li>
                        <button type="button" id="delete_article" class="btn btn-default navbar-btn">Delete Article
                        </button>
                    </li>
                    <li>
                        <button type="button" id="delete_group" class="btn btn-default navbar-btn">Delete Group</button>
                    </li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                           aria-expanded="false">Usefulness <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <c:forEach items="${levels}" var="levels">
                                <li><a href="/level/${levels.value}">${levels.description}</a></li>
                            </c:forEach>
                        </ul>
                    </li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                           aria-expanded="false">Groups <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <c:forEach items="${groups}" var="group">
                                <li><a href="/group/${group.id}">${group.name}</a></li>
                            </c:forEach>
                        </ul>
                    </li>
                </ul>
                <form class="navbar-form navbar-left" role="search" action="/search" method="post">
                    <div class="form-group">
                        <input type="text" class="form-control" name="pattern" placeholder="Search">
                    </div>
                    <button type="submit" class="btn btn-default">Search</button>
                </form>
            </div><!-- /.navbar-collapse -->
        </div><!-- /.container-fluid -->
    </nav>

    <table class="table table-striped">
        <thead>
        <tr>
            <td></td>
            <td><b>Name</b></td>
            <td><b>Link</b></td>
            <td><b>Description</b></td>
            <td><b>Usefulness</b></td>
            <td><b>Group</b></td>
        </tr>
        </thead>
        <c:forEach items="${articles}" var="article">
            <tr>
                <td><input type="checkbox" name="toDelete[]" value="${article.id}" id="checkbox_${article.id}"/></td>
                <td>${article.name}</td>
                <td><a href="${article.link}">Source</a></td>
                <td>${article.description}</td>
                <td>${article.usefulnessLevel.description}</td>
                <td>${article.group.name}</td>
                <td><button class="btn btn-default" id="edit_article" value="${article.id}" name="articleId"
                            onclick="window.location.href='${article.id}'">Edit</button></td>
            </tr>
        </c:forEach>
    </table>
</div>
<script>
    $('.dropdown-toggle').dropdown();

    $('#add_article').click(function(){
        window.location.href='/article_add_page';
    });

    $('#add_group').click(function(){
        window.location.href='/group_add_page';
    });

    $('#delete_group').click(function(){
        window.location.href='/group_delete_page';
    });

    $('#delete_article').click(function(){
        var data = { 'toDelete[]' : []};
        $(":checked").each(function() {
            data['toDelete[]'].push($(this).val());
        });
        $.post("/article/delete", data, function(data, status) {
            window.location.reload();
        });
    });
</script>
</body>
</html>