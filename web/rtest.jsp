<%--
  Created by IntelliJ IDEA.
  User: 李佳骏
  Date: 2017/4/9
  Time: 11:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>Title</title>

    <script src="js/jquery-3.1.1.min.js"></script>
    <script src="js/jsencrypt.min.js"></script>

    <script type="text/javascript">

        $(document).ready(function () {
            $("#btn").click(function () {

                var encrypt = new JSEncrypt();
                alert($('#publicKey').val());
                encrypt.setPublicKey($('#publicKey').val());
                var encrypted = encrypt.encrypt($('#password').val());

                $.ajax({
                    type:"post",
                    url:"rsaValidate",
                    async:false,
                    data:{
                        username:$("#username").val(),
                        password:encrypted,
                        publicKey:$("#publicKey").val()
                    },
                    success:function (data, status) {
                        alert(JSON.stringify(data));
                    },
                    error:function () {
                        alert("what");
                    }
                });

            });
        });

    </script>

</head>
<body>

<form>

    <input type="text" name="username" id="username">
    <input type="text" name="password" id="password">
    <input type="hidden" id="publicKey" name="publicKey" value="<s:property value="publicKey"/>">
    <input type="button" value="登录" id="btn">

</form>

</body>
</html>
