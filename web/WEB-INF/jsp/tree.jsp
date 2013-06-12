<%-- 
    Document   : tree
    Created on : 2013-06-10, 14:41:55
    Author     : sergi_000
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <c:forEach varStatus="status" var="featNode" items="${nodeArr}"> 
        ${featNode.htmlTable()}
        <hr/>
    </c:forEach>
</body>
</html>
