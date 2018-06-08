<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>编辑训练营信息</title>
    <script src="/static/js/laydate/laydate.js"></script>
</head>
<body>

<#include "header.ftl">
<div class="layui-container">
    <#if battalion??>
    <div>
        <form class="layui-form" action="javascript:;">
            <div class="layui-row">
                <div class="layui-col-md4">
                    <div class="layui-form-item">
                        <label class="layui-form-label">名称</label>
                        <div class="layui-input-block">
                            <input type="text" name="name" required lay-verify="required"
                                   value="${battalion.name!''}" autocomplete="off" class="layui-input">
                        </div>
                    </div>
                </div>
                <div class="layui-col-md4">
                    <div class="layui-form-item">
                        <label class="layui-form-label">费用</label>
                        <div class="layui-input-block">
                            <input type="text" name="price" required lay-verify="required"
                                   value="${battalion.price!'0'}" autocomplete="off" class="layui-input">
                        </div>
                    </div>
                </div>
                <div class="layui-col-md4">
                    <div class="layui-form-item">
                        <label class="layui-form-label">类别</label>
                        <div class="layui-input-inline">
                            <#--<input type="text" name="type" required lay-verify="required"-->
                                   <#--placeholder="${battalion.type!'请输入类别'}" autocomplete="off" class="layui-input">-->
                            <select name="typeId" lay-verify="required" lay-search="">
                                <option value="${battalion.typeId!''}">${battalion.type!'请选择类别'}</option>
                                <#list types as type>
                                    <option value="${type.typeId}">${type.typeName}</option>
                                </#list>
                            </select>
                        </div>
                    </div>
                </div>
            </div>

            <div class="layui-row">
                <div class="layui-col-md4">
                    <div class="layui-form-item">
                        <label class="layui-form-label">训练地点</label>
                        <div class="layui-input-block">
                            <input type="text" name="place" required lay-verify="required"
                                   value="${battalion.place!''}" autocomplete="off" class="layui-input">
                        </div>
                    </div>
                </div>
                <div class="layui-col-md4">
                <div class="layui-form-item">
                    <label class="layui-form-label">状态</label>
                    <div class="layui-input-block">
                        <#--<input type="text" name="status" required lay-verify="required"-->
                               <#--placeholder="${battalion.status!'请输入状态'}" autocomplete="off" class="layui-input">-->
                        <select name="status">
                            <option value="${battalion.status!''}">${battalion.status!'请输入状态'}</option>
                            <option value="报名中">报名中</option>
                            <option value="进行中">进行中</option>
                            <option value="已结束">已结束</option>
                        </select>
                    </div>
                </div>
            </div>

                <div class="layui-col-md4">
                    <div class="layui-form-item">
                        <label class="layui-form-label">推荐等级</label>
                        <div class="layui-input-inline">
                            <select name="rank">
                                <option value="${battalion.rank!''}">${battalion.rank!'请选择等级'}</option>
                                <option value="1">A</option>
                                <option value="2">B</option>
                                <option value="3">C</option>
                                <option value="4">D</option>
                                <option value="5">E</option>
                            </select>
                        </div>
                    </div>
                </div>

            </div>

            <div class="layui-row">

                <div class="layui-col-md6" >
                    <div class="layui-form-item" >
                        <div class="layui-inline" >
                            <label class="layui-form-label">时间范围</label>
                            <div class="layui-input-inline" >
                                <input type="text" name="timeStart" id="timeStart" lay-verify="date" value="${battalion.timeStart!''}" autocomplete="off" class="layui-input">
                            </div>
                            <div class="layui-form-mid">-</div>
                            <div class="layui-input-inline" >
                                <input type="text" name="timeEnd" id="timeEnd" lay-verify="date" value="${battalion.timeEnd!''}" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="layui-col-md6">
                    <div class="layui-form-item">
                    <#--<div class="layui-input-inline">-->
                    <#--<label class="layui-form-label">-->
                        <button type="button" class="layui-btn " id="imageUpload">上传图片</button>
                    <#--</label>-->
                        <div class="layui-input-inline" style="width: 70%">
                            <input readonly type="text" name="imagePath" id="imgUrl" required lay-verify="required"
                                   value="${battalion.imagePath!''}" autocomplete="off" class="layui-input">
                        </div>
                    <#--</div>-->
                    </div>
                </div>

            </div>

            <div class="layui-row">
                <div class="layui-col-md4">
                    <div class="layui-form-item">
                        <label class="layui-form-label">报名截止</label>
                        <div class="layui-input-inline">
                        <#--<input type="text" name="deadline" id="deadline" lay-verify="date" placeholder="${battalion.deadline!'报名截止时间'}" autocomplete="off" class="layui-input">-->
                            <input type="text" name="deadline" id="deadline" lay-verify="date" value="${battalion.deadline!''}" autocomplete="off" class="layui-input">
                        </div>
                    </div>
                </div>
            </div>

            <div class="layui-form-item layui-form-text">
                <label class="layui-form-label">描述</label>
                <div class="layui-input-block">
                    <textarea id="demo" name="description" class="layui-textarea" lay-verify="description">
                        ${battalion.description!''}
                    </textarea>
                </div>
            </div>

            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button class="layui-btn" lay-submit lay-filter="battalionForm">立即提交</button>
                </div>
            </div>
        </form>
    </div>
    <#else>
        <h1 align="center">不存在该训练营</h1>
    </#if>
</div>

<script src="/static/js/layui/layui.all.js"></script>

<script>
    laydate.render({
    elem: '#deadline'
    });
    laydate.render({
    elem: '#timeStart'
    });
    laydate.render({
    elem: '#timeEnd'
    });

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
    form.on('submit(battalionForm)', function (data) {
        console.log('submit');
        var battalion = JSON.stringify({
            'id': ${battalion.id!0},
            'name': data.field.name,
            'price': data.field.price,
            'typeId': data.field.typeId,
            'place': data.field.place,
            'rank': data.field.rank,
            'deadline': data.field.deadline,
            'timeStart': data.field.timeStart,
            'timeEnd': data.field.timeEnd,
            'imagePath': data.field.imagePath,
            'description': layedit.getContent(index),
            'status':data.field.status
        });
        var url = '${request.contextPath}/battalion/admin/upsert';
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
        ajaxPost(url,battalion,successCallBack,alertMessage);
        return false;
    });

</script>

</body>
</html>