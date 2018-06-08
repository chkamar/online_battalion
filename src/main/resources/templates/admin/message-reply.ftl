<!DOCTYPE html>
<html>
<head>
    <title>留言回复</title>
    <script src="/static/js/battalions/common.js"></script>
</head>
<body>
<#include "/admin/header.ftl"/>



<div class="layui-container">
    <form class="layui-form" action="javascript:;">
        <div class="layui-form-item layui-form-text" align="center">
            <div class="layui-input-block">
                <textarea placeholder="请输入回复内容" id="content" name="content" class="layui-textarea" style="min-height: 200px"></textarea>
            </div>
        </div>
        <div class="layui-input-block" align="center">
            <button class="layui-btn" lay-submit="" lay-filter="demo1">回复</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </form>
</div>

<script>
    layui.use(['form'], function(){
        var form = layui.form
                ,layer = layui.layer

        //监听提交
        form.on('submit(demo1)', function(data){
            var content = data.field.content;
            var url = '${request.contextPath}/message/add';
            function successCallBack(result) {
                if (result.status == false) {
                    alertMessage(result.msg);
                } else {
                    layer.open({
                        type: 1
                        ,offset: 't'
                        ,id: 'layerDemot' //防止重复弹出
                        ,content: '<div style="padding: 20px 100px;">您的宝贵意见我们已经收到，谢谢！</div>'
                        ,btn: '关闭'
                        ,btnAlign: 'c' //按钮居中
                        ,shade: 0 //不显示遮罩
                        ,yes: function(){
                            layer.closeAll();
                        }
                    });
                    $("#content").val('');
//                        layer.msg('您的宝贵意见我们已经收到，谢谢！', {
//                            time: 20000, //20s后自动关闭
//                        });
                }
            }
            ajaxPost(url,content,successCallBack,errorCallBack);
            return false;
        });


    });
</script>

</body>
</html>