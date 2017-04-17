<%--
  Created by IntelliJ IDEA.
  User: 李佳骏
  Date: 2017/4/9
  Time: 16:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>Title</title>

    <script src="js/jquery-3.1.1.min.js"></script>
    <script src="js/jsencrypt.min.js"></script>
    <script src="js/RSAEncrypt.js"></script>

    <script type="text/javascript">


        $(document).ready(function () {
            // 获取public key
           $.ajax({
               url:"reqPublicKey",
               success:function (data) {
                   $("#publicKey").val(data.publicKey);
               }
           });
           // button listener
            $("#login-form").on("submit",function () {
                initRSAEncrypt($("#publicKey").val().replace("\n",""));
                $("input[name='password']").val(encodePart($("input[name='password']").val()));
            });
        });
    </script>
</head>
<body>

<form id="login-form" method="post" action="test.action">

    <input type="text" name="username">
    <input type="password" name="password">
    <input type="submit" value="登录">
    <input type="hidden" id="publicKey">

</form>

</body>
</html>
