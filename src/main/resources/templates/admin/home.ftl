<!DOCTYPE html>
<html>
<head>
    <title>后台管理主页</title>
</head>

<body>
<#include "/admin/header.ftl">

<div align="center" style="padding: 200px">
    <h1>欢迎来到网上训练营服务平台后台管理系统！</h1>
</div>

    <script>
        layui.use('element', function(){
            var element = layui.element;
            element.render('nav');
        });
    </script>

</body>
</html>