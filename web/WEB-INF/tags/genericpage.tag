<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@attribute name="left" fragment="true" %>
<%@attribute name="content" fragment="true" %>
<html>
  <body>
      <table>
          <tr>
              <td>
                  <div id="pageleft">
                      
                    <jsp:invoke fragment="left"/>
                    </div> 
              </td>
              <td>
                  <div id="pagecontent">
                    <jsp:invoke fragment="content"/>
                    </div> 
              </td>
          </tr>
      </table>
  </body>
</html>