<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <script src="/static/js/battalions/image.js"></script>
</head>
<body>
<#include "/admin/header.ftl"/>
<div class="layui-container">

<#--<div style="padding: 30px 50px">-->
    <h2 style="padding: 30px">首页轮播图片</h2>
    <div class="layui-row">
    <#--<#assign i=0>-->
    <#--<ul>-->
    <#list images as image>
    <#--<li>-->
        <div class="layui-col-md3" style="height: 240px">
            <img style="width: 90%;height: 80%;padding: 3px;" src="${image.path}">
            <div style="margin: 0 auto;text-align:center">
                <button  id="${image.id}" type="button" class="layui-btn" onclick="deleImg(this)">删除图片</button>
            </div>
        </div>

    <#--</li>-->
    </#list>
    <#--</ul>-->
    </div>
    <div class="layui-upload" align="center">
        <button type="button" class="layui-btn" id="indexImgUpload">上传图片</button>
    </div>
    <br>

</div>
    <script>
        layui.use('element', function(){
            var element = layui.element;
            element.render('nav');
        });
        useUpload();
    </script>

<#--</div>-->


</body>
</html>


