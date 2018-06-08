<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>编辑企业信息</title>
</head>
<body>

<#include "/admin/header.ftl">
<div class="layui-container">

<form class="layui-form" action="javascript:;">
    <div class="layui-row">
        <div class="layui-col-md6">
            <div class="layui-form-item">
                <label class="layui-form-label">企业名称</label>
                <div class="layui-input-block">
                    <input type="text" name="name" required lay-verify="required"
                           value="${enterpriseInformation.name!'请输入企业名称'}" autocomplete="off" class="layui-input">
                </div>
            </div>
        </div>
        <div class="layui-col-md6">
            <div class="layui-form-item">
                <label class="layui-form-label">企业地址</label>
                <div class="layui-input-block">
                    <input type="text" name="address" required lay-verify="required"
                           value="${enterpriseInformation.address!'请输入企业地址'}" autocomplete="off" class="layui-input">
                </div>
            </div>
        </div>
    </div>
    <div class="layui-row">
        <div class="layui-col-md6">
            <div class="layui-form-item">
                <label class="layui-form-label">官方电话</label>
                <div class="layui-input-block">
                    <input type="text" name="officalTelephone" required lay-verify="required"
                           value="${enterpriseInformation.officalTelephone!'请输入官方电话'}" autocomplete="off" class="layui-input">
                </div>
            </div>
        </div>
        <div class="layui-col-md6">
            <div class="layui-form-item">
                <label class="layui-form-label">HR电话</label>
                <div class="layui-input-block">
                    <input type="text" name="hrTelephone" required lay-verify="required"
                           value="${enterpriseInformation.hrTelephone!'请输入HR电话'}" autocomplete="off" class="layui-input">
                </div>
            </div>
        </div>
    </div>

    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">企业简介</label>
        <div class="layui-input-block">
                <textarea id="summary" name="summary" class="layui-textarea">
                    ${enterpriseInformation.summary!'请输入企业简介'}
                </textarea>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
        </div>
    </div>
</form>
</div>
<script src="/static/js/layui/layui.all.js"></script>
<script>
    var element = layui.element;
    var layedit = layui.layedit;
    var summary = layedit.build('summary', {
        tool: ['left', 'center', 'right', '|', 'link', 'unlink', 'face', 'image']
        , height: 500
        , uploadImage: {
        url: '${request.contextPath}/upload/image'
        }
    });
    var form = layui.form;

    //监听提交
    form.on('submit(formDemo)', function (data) {
        var enterpriseInformation = JSON.stringify({
            'name': data.field.name,
            'summary': layedit.getContent(summary),
            'address': data.field.address,
            'officalTelephone': data.field.officalTelephone,
            'hrTelephone': data.field.hrTelephone
        });
        var url = '${request.contextPath}/enterpriseInformation/admin/upsert';
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
        ajaxPost(url,enterpriseInformation,successCallBack,errorCallBack);
        return false;
    });
    <#--layui.use('element', function(){-->
        <#--var element = layui.element;-->
        <#--//重新渲染导航栏，否则hover和二级菜单效果消失-->
        <#--element.render('nav');-->
    <#--});-->

    <#--layui.use('layedit', function () {-->
        <#--var layedit = layui.layedit;-->
        <#--var summary = layedit.build('summary', {-->
            <#--tool: ['left', 'center', 'right', '|', 'link', 'unlink', 'face', 'image']-->
            <#--, height: 500-->
            <#--, uploadImage: {-->
                <#--url: '${request.contextPath}/upload/image'-->
            <#--}-->
        <#--});-->
    <#--});-->

    //Demo
    <#--layui.use('form', function () {-->
        <#--var form = layui.form;-->

        <#--//监听提交-->
        <#--form.on('submit(formDemo)', function (data) {-->
            <#--var enterpriseInformation = JSON.stringify({-->
                <#--'name': data.field.name,-->
                <#--'summary': layedit.getContent(summary),-->
                <#--'address': data.field.address,-->
                <#--'officalTelephone': data.field.officalTelephone,-->
                <#--'hrTelephone': data.field.hrTelephone-->
            <#--});-->
            <#--var url = '${request.contextPath}/enterpriseInformation/admin/upsert';-->
            <#--function successCallBack(result) {-->
                <#--if (result.status == false) {-->
                    <#--alertMessage(result.msg);-->
                <#--} else {-->
                    <#--layer.msg('提交成功', {-->
                        <#--time: 2000, //2s后自动关闭-->
                    <#--});-->
                    <#--setTimeout(function(){-->
                        <#--window.location.href = result.toUrl;-->
                    <#--},2000);-->

                <#--}-->
            <#--}-->
            <#--ajaxPost(url,enterpriseInformation,successCallBack,errorCallBack);-->
            <#--return false;-->
        <#--});-->
    <#--});-->



</script>

</body>
</html>