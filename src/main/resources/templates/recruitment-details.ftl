<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>招聘详情</title>
    <script src="/static/js/battalions/common.js"></script>

    <style type="text/css">
        p{
            padding: 20px;
        }
        span{
            margin: 20px;
        }
    </style>
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
<div class="layui-container" style="margin-top: 10px">
    <div class="recruitment-details" style="margin: 10px 160px;padding:10px;border: 1px solid #d3d3d4">
        <p>${recruitment.positionName}<hr class="layui-bg-blue"></p>
        <br>
        <div>
            <p>
                招聘类别：<span id="category">${recruitment.category}</span>
                工作性质：<span id="jobNature">${recruitment.jobNature}</span>
                薪资范围：<span id="salaryRange">${recruitment.salaryRange}</span>
            </p>
            <br>
            <p>
                工作地点：<span id="workingPlace">${recruitment.workingPlace}</span>
                发布时间：<span id="publishTime">${recruitment.publishTime}</span>
            </p>

        </div>
        <br>
        <br>
        <div>
            <p>工作职责:</p><br>
            <p>${recruitment.jobDuty}</p>
            <br>
            <br>
            <p>任职资格:</p><br>
            <p>${recruitment.qualifications}</p>
            <br>
        </div>
    </div>
</div>

<#include "/common/footer.ftl"/>
</body>
</html>