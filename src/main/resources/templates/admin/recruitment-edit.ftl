<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>编辑招聘信息</title>
</head>
<body>

<#include "header.ftl">
<div class="layui-container">
<#if recruitment??>
    <div>
        <form class="layui-form" action="javascript:;">
            <div class="layui-row">
                <div class="layui-col-md4">
                    <div class="layui-form-item">
                        <label class="layui-form-label">职位名称</label>
                        <div class="layui-input-block">
                            <input type="text" name="positionName" required lay-verify="required"
                                   value="${recruitment.positionName!''}" autocomplete="off" class="layui-input">
                        </div>
                    </div>
                </div>
                <div class="layui-col-md4">
                    <div class="layui-form-item">
                        <label class="layui-form-label">职位类型</label>
                        <div class="layui-input-block">
                            <input type="text" name="positionType" required lay-verify="required"
                                   value="${recruitment.positionType!''}" autocomplete="off" class="layui-input">
                        </div>
                    </div>
                </div>
                <div class="layui-col-md4">
                    <div class="layui-form-item">
                        <label class="layui-form-label">薪资</label>
                        <div class="layui-input-block">
                            <input type="text" name="salaryRange" required lay-verify="required"
                                   value="${recruitment.salaryRange!''}" autocomplete="off" class="layui-input">
                        </div>
                    </div>
                </div>

            </div>
            <div class="layui-row">
                <div class="layui-col-md4">
                    <div class="layui-form-item">
                        <label class="layui-form-label">招聘类别</label>
                        <div class="layui-input-inline">
                            <select name="category">
                                <option value="${recruitment.category!''}">${recruitment.category!'请选择招聘类别'}</option>
                                <option value="校园招聘">校园招聘</option>
                                <option value="社会招聘">社会招聘</option>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="layui-col-md4">
                    <div class="layui-form-item">
                        <label class="layui-form-label">工作性质</label>
                        <div class="layui-input-inline">
                            <select name="jobNature">
                                <option value="${recruitment.jobNature!''}">${recruitment.jobNature!'请选择工作性质'}</option>
                                <option value="全职">全职</option>
                                <option value="实习">实习</option>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="layui-col-md4">
                    <div class="layui-form-item">
                        <label class="layui-form-label">工作地点</label>
                        <div class="layui-input-block">
                            <input type="text" name="workingPlace" required lay-verify="required"
                                   value="${recruitment.workingPlace!''}" autocomplete="off" class="layui-input">
                        </div>
                    </div>
                </div>
                <#--<div class="layui-col-md4">-->
                    <#--<div class="layui-form-item">-->
                        <#--<label class="layui-form-label">发布时间</label>-->
                        <#--<div class="layui-input-inline">-->
                        <#--&lt;#&ndash;<input type="text" name="deadline" id="deadline" lay-verify="date" placeholder="${battalion.deadline!'报名截止时间'}" autocomplete="off" class="layui-input">&ndash;&gt;-->
                            <#--<input type="text" name="publishTime" id="publishTime" lay-verify="date" value="${recruitment.publishTime!''}" autocomplete="off" class="layui-input">-->
                        <#--</div>-->
                    <#--</div>-->
                <#--</div>-->
            </div>

            <div class="layui-form-item layui-form-text">
                <label class="layui-form-label">工作职责</label>
                <div class="layui-input-block">
                    <textarea id="jobDuty" name="jobDuty" class="layui-textarea" lay-verify="jobDuty">
                        ${recruitment.jobDuty!''}
                    </textarea>
                </div>
            </div>

            <div class="layui-form-item layui-form-text">
                <label class="layui-form-label">任职资格</label>
                <div class="layui-input-block">
                    <textarea id="qualifications" name="qualifications" class="layui-textarea" lay-verify="qualifications">
                        ${recruitment.qualifications!''}
                    </textarea>
                </div>
            </div>

            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button class="layui-btn" lay-submit lay-filter="recruitmentForm">立即提交</button>
                </div>
            </div>
        </form>
    </div>
<#else>
    <h1 align="center">不存在该招聘信息，无法修改</h1>
</#if>
</div>
<script src="/static/js/layui/layui.all.js"></script>

<script>
//    laydate.render({
//        elem: '#publishTime'
//    });
    var layedit = layui.layedit;
    var jobDuty = layedit.build('jobDuty', {
        tool: ['left', 'center', 'right', '|']
        , height: 300
        , uploadImage: {
            url: '${request.contextPath}/upload/image'
        }
    });
    var qualifications = layedit.build('qualifications', {
        tool: ['left', 'center', 'right', '|']
        , height: 300
        , uploadImage: {
            url: '${request.contextPath}/upload/image'
        }
    });
    var element = layui.element;
    var form = layui.form;
    //监听提交 TODO
    form.on('submit(recruitmentForm)', function (data) {
        console.log(data.field);
        var recruitment = JSON.stringify({
            'id': ${recruitment.id!0},
            'positionName':data.field.positionName,
            'positionType':data.field.positionType,
            'workingPlace':data.field.workingPlace,
//            'publishTime':data.field.publishTime,
            'category':data.field.category,
            'jobNature':data.field.jobNature,
            'salaryRange':data.field.salaryRange,
            'jobDuty':layedit.getContent(jobDuty),
            'qualifications':layedit.getContent(qualifications)
        });
        var url = '${request.contextPath}/recruitment/admin/upsert';
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
        ajaxPost(url,recruitment,successCallBack,alertMessage);
        return false;
    });

</script>

</body>
</html>