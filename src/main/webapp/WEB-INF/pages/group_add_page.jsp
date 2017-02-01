<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<html>
    <head>
        <title>New Group</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
    </head>
    <body>
        <div class="container">
            <form role="form" enctype="multipart/form-data" class="form-horizontal" action="/group/add" method="post">
                <div class="form-group"><h3>New Group</h3></div>
                <div class="form-group"><input type="text" class="form-control" name="name" placeholder="Name"></div>
                <div class="form-group"><input type="submit" class="btn btn-primary" value="Add">
                    <button id="go_back" class="btn btn-default navbar-btn"><a href="/"> Go back</a></button>
                </div>

            </form>

        </div>
    </body>
</html>