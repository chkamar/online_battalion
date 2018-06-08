<!DOCTYPE html>
<html>
<head>
    <title>留言反馈</title>
    <script src="/static/js/battalions/common.js"></script>
</head>
<body>
    <#include "/common/header.ftl"/>

    <#if paths??>
    <div class="layui-carousel" id="index" style="margin-top: 10px;">
        <div carousel-item>
            <#list paths as path>
                <img src="${path}">
            </#list>
        </div>
    </div>

    </#if>

    <div class="layui-container">
        <form class="layui-form" action="javascript:;">
            <div class="layui-form-item layui-form-text" align="center">
                <#--<label class="layui-form-label">留言反馈</label>-->
                <div class="layui-input-block">
                    <textarea placeholder="请输入反馈内容" id="content" name="content" class="layui-textarea" style="min-height: 200px"></textarea>
                </div>
            </div>
            <div class="layui-input-block" align="center">
                <button class="layui-btn" lay-submit="" lay-filter="demo1">立即提交</button>
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
                if(content.length>100){
                    alertMessage('字数不得大于100');
                    return;
                }
                if(!loginStatus()){
                    alertMessage('未登录');
                    return;
                }
                var url = '${request.contextPath}/message/user/add';
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

    <#include "/common/footer.ftl"/>
</body>
</html>