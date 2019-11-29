<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/10/17
  Time: 19:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
    <link rel="stylesheet" href="css/list.css" />
    <script src="js/jquery-3.4.1.min.js"></script>
    <script>

        var percentComplete = 0; //上传文件的进度

        /**
         * 弹出文件选择框
         */
        function upload() {
            //获得文件域对象
            var fileField = $("#file")[0];
            //弹出选择文件窗口
            fileField.click();
        }

        /**
         * 上传文件
         * @param thisz
         */
        function uploadFile(thisz) {
            //获得文件的路径
            var filePath = thisz.value;
            //判断是否选择文件
            if(filePath) {
                //创建表单对象
                var formData = new FormData();
                //封装请求参数
                formData.append("method", "upload");
                formData.append("file",thisz.files[0]);
                //创建xhr对象
                var xhr = new XMLHttpRequest();
                //打开与服务器的连接
                xhr.open("post", "servlet/fileall");
                //注册状态改变事件
                xhr.onreadystatechange = function() {
                    //判断服务器是否成功响应
                    if(xhr.readyState==4&&xhr.status==200) {
                        //获得响应数据
                        var result = xhr.responseText;
                        //转换成JSON对象
                        var jsonData = eval("("+result+")");
                        //判断是否上传成功
                        if(jsonData.success) {
                            //设置进度
                            $("#progressBar").css("width", "100%");
                            $("#progressText").html("上传成功: "+"100%");
                            //获得数据列表数据的JSON数组
                            var list = jsonData.list;
                            //删除表头外的所有数据行
                            $("#form1 table tr:nth-child(n+2)").remove();
                            //遍历数组添加每一行数据
                            for(i in list) {
                                var fileData = list[i];
                                $("#form1 table").append('<tr><td><input type="checkbox" name="id" value="'+fileData.fileId+'"></td></tr>');
                            }
                        } else {
                            //设置进度
                            $("#progressText").html("上传失败: "+percentComplete+"%");
                        }
                        //显示进度条左上角关闭小图标
                        $("#progressClose").css("display", "inline-block");
                    }
                }
                //注册删除进度事件
                xhr.upload.onprogress = function(event) {
                    //判断文件是否存在数据， false 表示文件长度（event.total）为0
                    if (event.lengthComputable) {
                        //计算进度
                        percentComplete = parseInt(event.loaded/event.total*100);
                        //设置进度
                        $("#progressBar").css("width", percentComplete+"%");
                        $("#progressText").html("正在上传: "+percentComplete+"%");
                    }
                }
                //发送请求
                xhr.send(formData);
                //显示进度条
                $("#progressContainer").css("display", "block");
            }
        }

        /**
         * 关闭进度条
         */
        function closeProgress() {
            $("#progressContainer").css("display", "none");
            //还原进度条
            $("#progressBar").css("width", "1%");
            $("#progressText").html("正在上传: "+"0%");
            //清理文件域
            $("#file").val("");
        }
        
        function download() {
            
        }
        
        function update() {
            
        }
        
        function deleting() {
            //获得选择的行数，选中复选框的数量
            var count = $("#form1 input[name=id]:checked").length;
            //判断是否未选择1行
            if(count==0) {
                alert("请至少选择1行数据!");
                return;
            }
            //弹出选择对话框，让用户确定是否删除
            if(confirm("您是否要删除选择的数据?")) {
                //获得表单对象
                var form = $("#form1").get(0);
                //设置请求路径
                form.action = "servlet/fileall?method=deleting";
                //提交表单
                form.submit();
            }
        }
    </script>
</head>
<body>
<header>
    <ul class="nav nav-tabs">
        <li role="presentation" class="active"><a href="#">全部文件</a></li>
    </ul>
</header>
<article>
    <section class="search">
        <form method="post" action="servlet/fileall" class="form-inline">
            <section class="form-group">
                <input type="text" name="sfileName" placeholder="按文件名搜索!" class="form-control" />
                <input type="submit" value="搜索" class="btn btn-warning" />
            </section>
        </form>
    </section>
    <form id="form1" method="post" action="">
        <table class="table table-bordered table-hover">
            <tr>
                <th><input type="checkbox" /></th>
                <th>序号</th>
                <th>文件名</th>
                <th>类型</th>
                <th>大小</th>
                <th>操作时间</th>
            </tr>
            <c:forEach items="${list}" var="file" varStatus="st">
                <tr>
                    <td><input type="checkbox" name="id" value="${file.fileId}"></td>
                    <td>${st.count}</td>
                    <td>${file.fileName}</td>
                    <td>${file.fileType}</td>
                    <td>${file.fileSize}</td>
                    <td><fmt:formatDate value="${file.operTime}" pattern="yyyy年MM月dd日 HH:mm:ss" /></td>
                </tr>
            </c:forEach>
            <c:if test="${fn:length(list)==0}">
                <tr>
                    <td colspan="6" align="center">未查找到数据!</td>
                </tr>
            </c:if>
        </table>
        <input type="file" id="file" name="file" style="visibility:hidden;height:1px;" onchange="uploadFile(this);" />
    </form>
    <section class="row btns">
        <section class="col-xs-12 col-sm-4">
            <input type="button" value="上传文件" class="btn btn-primary" onclick="upload();" />
            <input type="button" value="下载文件" class="btn btn-info" onclick="download();" />
            <input type="button" value="修改文件" class="btn btn-success" onclick="update();" />
            <input type="button" value="删除文件" class="btn btn-danger" onclick="deleting();" />
        </section>
        <section class="col-xs-12 col-sm-4">共-3-行 共-1-页 当前第-1-页</section>
        <section class="col-xs-12 col-sm-4">
            <ul class="pager">
                <li><a href="#">首&emsp;页</a></li>
                <li><a href="#">上一页</a></li>
                <li><a href="#">下一页</a></li>
                <li><a href="#">尾&emsp;页</a></li>
            </ul>
        </section>
    </section>
</article>
<div id="progressContainer" style="position:fixed;top:0px;right:0px;bottom:0px;left:0px;background-color:rgba(0,0,0,0.3);display:none;">
    <div style="position:relative;width:400px;margin:300px auto;padding: 30px 16px 10px;border-radius:3px;background-color:#e0e0e0;">
        <div class="progress">
            <div id="progressBar" class="progress-bar progress-bar-info progress-bar-striped" role="progressbar"
                 aria-valuenow="20" aria-valuemin="0" aria-valuemax="100" style="width:1%">
            </div>
            <span id="progressText" style="position:absolute;top:30px;left:160px;color:#3c3c3c;">正在上传: 0%</span>
            <span id="progressClose" style="position:absolute;top:0px;right:0px;padding:0px 5px;cursor:pointer;display:none;
                  border-bottom:solid 1px #cccccc;border-left:solid 1px #cccccc;border-bottom-left-radius:3px;" onclick="closeProgress()">x</span>
        </div>
    </div>
</div>
</body>
</html>
