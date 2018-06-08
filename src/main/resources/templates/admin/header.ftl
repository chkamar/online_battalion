<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <#--<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">-->

    <link rel="stylesheet" href="/static/js/layui/css/layui.css">
    <link rel="stylesheet" href="/static/css/style.css">
    <script src="/static/js/layui/layui.js"></script>
    <#--<script src="/static/js/layui/layui.all.js"></script>-->
    <script src="/static/js/jquery-1.12.3.min.js"></script>
    <script src="/static/js/battalions/common.js"></script>
    <#--<script src="/static/js/battalions/image.js"></script>-->
    <script src="/static/js/battalions/admin.js"></script>
    <script src="/static/js/battalions/account.js"></script>
    <script src="/static/js/jquery.cookie.js"></script>

    <style type="text/css">
        .logo{
            position: absolute;
            left: 30px;
            top: 0;
            width: 300px;
            height: 100%;
            line-height: 60px;
            text-align: center;
            color: #009688;
            font-size: 16px;
        }

        .li{
            padding-right:20px;
        }
        .ul{
            left:30%;
        }
    </style>
</head>
<body>
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="logo">网上训练营服务平台管理系统</div>
        <!-- 头部区域（可配合layui已有的水平导航） -->
        <ul class="layui-nav layui-layout-left ul">
            <li class="layui-nav-item">
                <a class="" href="javascript:;">基本信息</a>
                <dl class="layui-nav-child">
                <#--<dd><a href="javascript:;" onclick="refreshImageHtml()">首页图片管理</a></dd>-->
                    <dd><a href="/image/admin/manage">首页图片管理</a></dd>
                    <dd><a href="/enterpriseInformation/admin/edit">企业信息管理</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item">
                <a href="javascript:;">训练营</a>
                <dl class="layui-nav-child">
                    <dd><a href="/battalion/admin/edit/0">添加</a></dd>
                    <dd><a href="/battalion/admin/home">其他操作</a></dd>
                </dl>
            </li>
            <#--<li class="layui-nav-item"><a href="">训练营员</a></li>-->
            <li class="layui-nav-item"><a href="/message/admin/home">留言反馈</a></li>
            <li class="layui-nav-item">
                <a href="javascript:;">新闻</a>
                <dl class="layui-nav-child">
                    <dd><a href="/news/admin/edit/0">添加</a></dd>
                    <dd><a href="/news/admin/home">其他操作</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item">
                <a href="javascript:;">招聘信息</a>
                <dl class="layui-nav-child">
                    <dd><a href="/recruitment/admin/edit/0">添加</a></dd>
                    <dd><a href="/recruitment/admin/home">其他操作</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item" style="float: right;">
                <a href="javascript:;">帐号操作</a>
                <dl class="layui-nav-child">
                    <dd><a href="/html/admin/login.html">登录</a></dd>
                    <dd><a href="javaScript:;" onclick="logout()">注销</a></dd>
                    <dd><a href="/html/admin/updatePwd.html">修改密码</a></dd>
                </dl>
            </li>
        </ul>
    </div>

</div>
<script>
    layui.use(['element'], function(){
        var element = layui.element;
    });
</script>
</body>
</html>
