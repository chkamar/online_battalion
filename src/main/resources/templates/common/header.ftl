<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/static/js/layui/css/layui.css">
    <link rel="stylesheet" href="/static/css/style.css">
    <script src="/static/js/layui/layui.js"></script>
    <#--<script src="/static/js/layui/layui.all.js"></script>-->
    <script src="/static/js/jquery-1.12.3.min.js"></script>
    <script src="/static/js/battalions/common.js"></script>
    <script src="/static/js/battalions/account.js"></script>
    <script src="/static/js/jquery.cookie.js"></script>
</head>
<body>

    <div class="layui-header">

        <ul class="layui-nav layui-bg-green" id="navs" lay-filter="demo" style="padding-left: 30%">
            <li class="layui-nav-item"><a href="/index">首页</a></li>
            <li class="layui-nav-item"><a href="/enterpriseInformation/details">公司简介</a></li>
            <li class="layui-nav-item"><a href="/battalion/home">训练营</a></li>
            <li class="layui-nav-item"><a href="/news/home">新闻中心</a></li>
            <li class="layui-nav-item"><a href="/message/edit">留言反馈</a></li>
            <li class="layui-nav-item"><a href="/recruitment/home">加入我们</a></li>
            <li class="layui-nav-item" style="float: right;">
                <a href="javascript:;">帐号操作</a>
                <dl class="layui-nav-child">
                    <dd><a href="/html/register.html">注册</a></dd>
                    <dd><a href="/html/login.html">登录</a></dd>
                    <dd><a href="javaScript:;" onclick="logout()">注销</a></dd>
                    <dd><a href="/html/updatePwd.html">修改密码</a></dd>
                </dl>
            </li>
            <#--<li class="layui-nav-item" style="float: right;"><a href="/register.html">注册</a></li>-->
            <#--<li class="layui-nav-item" style="float: right;"><a href="/login.html">登录</a></li>-->
            <#--<li class="layui-nav-item" style="float: right;"><a href="logout()">注销</a></li>-->
            <#--<li class="layui-nav-item" style="float: right;"><a href="#">修改密码</a></li>-->
        </ul>

        <script type="text/javascript">
            var url = location.href;
            var prefix = location.protocol+'//'+location.host;
            var status = 0;
            var paths = new Array('enterpriseInformation','battalion','news','message','recruitment');
            for(var i=0;i<5;i++){
                if(status==1){
                    $("#navs a").eq(i+1).removeClass('layui-this');
                    continue;
                }
                if(url.indexOf(paths[i])==prefix.length+1){
                    $("#navs a").eq(i+1).addClass('layui-this');
                    status = 1;
                }else{
                    $("#navs a").eq(i+1).removeClass('layui-this');
                }
            }
            if(status!=1){
                $("#navs a").eq(0).addClass('layui-this');
            }

            //全部使用轮播
            useCarousel();
            layui.use('element', function(){
                var element = layui.element;

            });


            //获取cookie，判断是否登录，动态显示登录状态 TODO


        </script>

    </div>

</body>
</html>