<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/10/17
  Time: 9:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  String basePath = request.getScheme()+"://"+request.getServerName()+":"
          +request.getServerPort()+request.getContextPath()+"/";
%>
<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath %>" />
    <title>首页...</title>
    <link rel="stylesheet" href="../bootstrap/css/bootstrap.min.css" />
    <style>
      header {
        position: fixed;
        width: 100%;
        height: 50px;
        top: 0px;
        left: 0px;
        background-color: #1874CD;
      }
      aside {
        position: fixed;
        width: 160px;
        top: 50px;
        bottom: 3px;
        left: 0px;
        background-color: #7EC0EE;
      }
      aside ul {
        width: 96%;
        margin: 2px auto 0px;
        border-radius: 3px;
        background-color: #FFFAF0;
      }
      aside ul li a {
        text-align: center;
      }
      article {
        position: fixed;
        top: 50px;
        right: 0px;
        bottom: 3px;
        left: 160px;
      }
      footer {
        position: fixed;
        width: 100%;
        height: 3px;
        background-color: #1874CD;
        bottom: 0px;
        left: 0px;
      }
    </style>
    <script src="../js/jquery-3.4.1.min.js"></script>
    <script src="../js/index.js"></script>
  </head>
  <body>
    <header></header>
    <aside>
      <ul class="nav nav-pills nav-stacked">
        <li id="1" class="active"><a href="javascript:void(0);">个人中心</a></li>
        <li parentId="1"><a href="#">我的资料</a></li>
        <li parentId="1"><a href="#">修改密码</a></li>
        <li id="2" class="active"><a href="javascript:void(0);">资料管理</a></li>
        <li parentId="2"><a href="servlet/fileall" target="mainFrame">全部文件</a></li>
        <li parentId="2"><a href="#">图像</a></li>
        <li parentId="2"><a href="#">视频</a></li>
        <li parentId="2"><a href="#">合同</a></li>
        <li parentId="2"><a href="#">制度</a></li>
      </ul>
    </aside>
    <article>
      <iframe id="mainFrame" name="mainFrame" src="servlet/fileall" width="100%" height="100%" frameborder="0" />
    </article>
    <footer></footer>
  </body>
</html>
