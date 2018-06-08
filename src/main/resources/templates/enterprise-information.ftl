<!DOCTYPE html>
<html>
<head>
    <title>企业简介</title>
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

<div class="layui-container" id="enterprise-info" style="margin-top: 30px">
    <p style="text-indent: 2em; line-height: 1.75em;">
        ${enterpriseInformation.name}（官方电话：<strong>${enterpriseInformation.officalTelephone}</strong>
            ，hr电话：<strong>${enterpriseInformation.hrTelephone}</strong>
            ,企业地址：<strong>${enterpriseInformation.officalTelephone}</strong>）
        <br>

    </p>
    <p style="text-indent: 2em; line-height: 1.75em;">
        ${enterpriseInformation.summary}
    </p>
</div>
<#include "/common/footer.ftl"/>
</body>
</html>