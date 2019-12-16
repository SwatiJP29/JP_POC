<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<title>Execute Linux Commands</title>
<script type="text/javascript">
function executecmd(){
	window.location.href="${servername}/cmd/executeCommand";
}
</script>
</head>
<body>
Welcome!!

<input name="display" type="button" onclick="executecmd()" value="Click Here">
</body>
</html>