<%-- 
    Document   : list
    Created on : 2013-05-13, 09:09:30
    Author     : sergi_000
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html ng-app>
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
        </style>
        <link href='http://fonts.googleapis.com/css?family=Metamorphous&subset=latin,latin-ext' rel='stylesheet' type='text/css'>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="Content-Style-Type" content="text/css">
        <title>Feat List</title>
    </head>
    <body ng-controller="ListCtrl">
        <table>
            <tr>
                <td>
        Filtr: <input type="text" ng-model="input"/>        
                </td>
                <td>
                    <form action="/Feats/feats/reload" method="GET">
                        <input type="submit" value="Odśwież listę atutów"/>
                    </form>
                </td>
            </tr>
        </table>
        <table id="basicTable" width="100%">
            <tr>
                <td>
                    <table id="listTable" border="1px">
                        <c:forEach varStatus="status" var="feat" items="${feats}"> 
                            <tr ng-show="contains('${feat.name}', input)">
                                <td style="padding : 10px; margin: 10px">
                                    <a href="/Feats/feats/${feat.id}">${feat.name}</a><br/>            
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </td>
                <td width="80%"  align="top">
                    <div id="featInfo">
                            ${feat.toHtmlString()}
                    </div>
                    <hr/>
                    <div ng-hide="angular.equals('${feat.name}','')">
                    Legenda:
                    <ul>                        
                        <li>
                            <font color="blue">Wymagania na niebiesko to wymagane atuty</font><br/>
                        </li>
                        <li>
                            <font color="purple">Wymagania na fioletowo to wymogi fabularne</font>
                        </li>
                    </ul>
                    </div>
                    <form ng-hide='true' action="/Feats/feats/${feat.id}" method="POST">
                        Data for ${feat.id}<br/>
                        Name: <input type='text' name="featData" value="${feat.name}"/><br/>
                        Opis:<textarea rows="5" cols="20" name="featData">${feat.description}</textarea><br/>
                        <input type="submit"/>
                    </form>
                </td>
            </tr>
        </table>
    </body>
</html>
