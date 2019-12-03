<%--
  Created by IntelliJ IDEA.
  User: 11578
  Date: 2019/12/2
  Time: 9:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    String basePath = request.getScheme()+"://"+request.getServerName()+":"
            +request.getServerPort()+request.getContextPath()+"/";
%>
<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath %>" />
    <title></title>
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css" />
    <link rel="stylesheet" href="css/oper.css" />
    <script src="js/jquery-3.4.1.min.js"></script>
</head>

<body>
    <header>
        <ul class="nav nav-tabs">
            <li role="presentation" class="active"><a href="#">全部文件</a></li>
        </ul>
    </header>
    <article>
        <form id="form1" method="post" action="servlet/fileall">
            <dl>
                <dt>修改文件信息</dt>
                <dd >
                    <input type="text" name="fileName" value="${file.fileName}" class="form-control" />
                    <input type="hidden" name="method" value="update" />
                    <input type="hidden" name="fileId" value="${file.fileId}">
                </dd>
                <dd class="btns">
                    <input type="submit" value="确 定" class="btn btn-primary input-sm" />
                    <input type="reset" value="重 置" class="btn btn-success input-sm" />
                </dd>
            </dl>
        </form>
    </article>

</body>
</html>
