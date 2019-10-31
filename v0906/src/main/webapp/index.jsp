<%@ page session="false" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%
    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy");
    request.setAttribute("year", sdf.format(new java.util.Date()));
    request.setAttribute("tomcatUrl", "https://tomcat.apache.org/");
    request.setAttribute("tomcatDocUrl", "/docs/");
    request.setAttribute("tomcatExamplesUrl", "/examples/");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <title><%=request.getServletContext().getServerInfo() %>
    </title>
    <link href="favicon.ico" rel="icon" type="image/x-icon"/>
    <link href="favicon.ico" rel="shortcut icon" type="image/x-icon"/>
    <link href="tomcat.css" rel="stylesheet" type="text/css"/>
</head>

<body>
<center>
    <h1>
        这是 tomcat 集群中的一员,端口信息为:8084-8045-8049
    </h1>
</center>
</body>

</html>

