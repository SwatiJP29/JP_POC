<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<title>Execute Linux Commands</title>
</head>
<body>

<c:out value="${output}" />

<input type="button" onclick="window.history.go(-1)" value="Back"/>
</body>
</html>