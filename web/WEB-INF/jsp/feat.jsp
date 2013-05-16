<%-- 
    Document   : feat
    Created on : 2013-05-13, 09:45:07
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
        <div>
            <font size='6'>
            ${feat.toHtmlString()}
            </font>
        </div>
        
        <div>
           
        </div>
            
        <div>
            
            <br/><a href="/Feats/feats">Powrót do listy</a>
            
        </div>
    </body>
</html>
