<%-- 
    Document   : fulltree
    Created on : 2013-06-12, 12:45:42
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
        <table border="1">
            <c:forEach varStatus="tierIndex" var="tier" items="${tiers}"> 
                <tr>
                    <td>
                        Tier ${tier}
                    </td>
                    <c:forEach varStatus="nodeIndex" var="node" items="${nodeArr}">
                        <c:if test="${node.getTier()==tier}">
                            <td>
                                ${node.getFeatName()}
                            </td>
                        </c:if>
                    </c:forEach>
                </tr>        
            </c:forEach>
        </table>
    </body>
</html>
