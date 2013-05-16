<%-- 
    Document   : sheets
    Created on : 2013-05-16, 19:26:05
    Author     : sergi_000
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
        <head>
        <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.0.6/angular.min.js"></script>
        <script src="ftp://Sergio:Qu0v4d1s1@famalis.no-ip.biz/FTP/resources/ListCtrl.js"></script>
        <style type="text/css">
            #listTable{
                
            }
            
            #featInfo {
                font-size: 36px;
            }
            td {
                vertical-align: top;
            }
            body {
                font-family: 'Metamorphous';
                background-image: url("ftp://Sergio:Qu0v4d1s1@famalis.no-ip.biz/FTP/resources/feats_bg1.jpg");
            }
            hr {
                color: red;
            }
            a {
                color:black;
                text-decoration: none;
            }
            table {
                font-size: 30px;
            }
            #sheetsTableHead {
                font-weight: bold;
                background-color: lightblue;
                
            }
        </style>
        <link href='http://fonts.googleapis.com/css?family=Metamorphous&subset=latin,latin-ext' rel='stylesheet' type='text/css'>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="Content-Style-Type" content="text/css">
        <title>Sheets List</title>
    </head>
    <body>
        <table border="1px" width="100%">
            <tr id="sheetsTableHead">
                <td>
                    Imię
                </td>
                <td>
                    Gracz
                </td>
                <td>
                    Atuty
                </td>
                <td>
                    Opis
                </td>
                <td>
                    Notatki
                </td>
            </tr>
    <c:forEach varStatus="status" var="sheet" items="${sheets}"> 
        <tr>
            <td>
                ${sheet.name}
            </td>
            <td>
                ${sheet.playerName}
            </td>
            <td>
                ${sheet.getFeatHtmlString()}
            </td>
            <td>
                ${sheet.description}
            </td>
            <td>
                ${sheet.notes}
            </td>
        </tr>
        
    
    </c:forEach>
        </table>
    </body>
</html>
