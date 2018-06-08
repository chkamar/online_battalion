<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>编辑新闻</title>
</head>
<body>

<#include "header.ftl">
<div class="layui-container">
<#if news??>
    <div>
        <form class="layui-form" action="javascript:;">
            <div class="layui-row">
                <div class="layui-col-md4">
                    <div class="layui-form-item">
                        <label class="layui-form-label">标题</label>
                        <div class="layui-input-block">
                            <input type="text" name="title" required lay-verify="required"
                                   value="${news.title!''}" autocomplete="off" class="layui-input">
                        </div>
                    </div>
                </div>
                <#--<div class="layui-col-md4">-->
                    <#--<div class="layui-form-item">-->
                        <#--<label class="layui-form-label">发布时间</label>-->
                        <#--<div class="layui-input-inline">-->
                        <#--&lt;#&ndash;<input type="text" name="deadline" id="deadline" lay-verify="date" placeholder="${battalion.deadline!'报名截止时间'}" autocomplete="off" class="layui-input">&ndash;&gt;-->
                            <#--<input type="text" name="time" id="time" lay-verify="date" value="${news.time!''}" autocomplete="off" class="layui-input">-->
                        <#--</div>-->
                    <#--</div>-->
                <#--</div>-->
                <div class="layui-col-md6" style="float: right">
                    <div class="layui-form-item">
                        <button type="button" class="layui-btn " id="imageUpload">上传图片</button>
                        <div class="layui-input-inline" style="width: 70%">
                            <input readonly type="text" name="imagePath" id="imgUrl" required lay-verify="required"
                                   value="${news.imagePath!''}" autocomplete="off" class="layui-input">
                        </div>
                    </div>
                </div>

            </div>

            <div class="layui-form-item layui-form-text">
                <label class="layui-form-label">新闻内容</label>
                <div class="layui-input-block">
                    <textarea id="demo" name="content" class="layui-textarea" lay-verify="content">
                        ${news.content!''}
                    </textarea>
                </div>
            </div>

            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button class="layui-btn" lay-submit lay-filter="newsForm">立即提交</button>
                </div>
            </div>
        </form>
    </div>
<#else>
    <h1 align="center">不存在该新闻，无法修改</h1>
</#if>
</div>
<script src="/static/js/layui/layui.all.js"></script>

<script>
//    laydate.render({
//        elem: '#time'
//    });
    var layedit = layui.layedit;
    var index = layedit.build('demo', {
        tool: ['left', 'center', 'right', '|', 'link', 'unlink', 'face', 'image']
        , height: 400
        , uploadImage: {
            url: '${request.contextPath}/upload/image'
        }
    });
    var upload = layui.upload;
    upload.render({
        elem: '#imageUpload'
        , url: '/image/admin/upload/content'
        , multiple:true
        , done: function (res) {
            if(res.status){
                $('#imgUrl').val(res.data);
            }else{
                alertMessage(res.msg);
            }
        }
        , error: function () {
            alertMessage("上传失败");
        }
    });
    var element = layui.element;
    var form = layui.form;
    //监听提交 TODO
    form.on('submit(newsForm)', function (data) {
        console.log(data.field);
        var news = JSON.stringify({
            'id': ${news.id!0},
            'title': data.field.title,
//            'time': data.field.time,
            'imagePath': data.field.imagePath,
            'content': layedit.getContent(index),
        });
        var url = '${request.contextPath}/news/admin/upsert';
        function successCallBack(result) {
            if (result.status == false) {
                alertMessage(result.msg);
            } else {
                layer.msg('提交成功', {
                    time: 2000, //2s后自动关闭
                });
                setTimeout(function(){
                    window.location.href = result.toUrl;
                },2000);

            }
        }
        ajaxPost(url,news,successCallBack,alertMessage);
        return false;
    });

</script>

</body>
</html>