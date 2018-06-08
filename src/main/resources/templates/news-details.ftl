<!DOCTYPE html>
<head>
    <title>${news.title}</title>
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
        <div id="news-whole">
            <h1 align="center" style="font-size:16px; padding-top:20px;">${news.title}</h1>
            <div style="text-align:center; padding:10px 0 40px; color:#999 ">
                发布时间：${news.time}&nbsp;&nbsp;浏览次数：${news.readCount}
            </div>
            <div class="content" style="padding-top: 20px">
                <p>${news.content}</p>
            </div>
        </div>

    </div>

    <#include "/common/footer.ftl"/>
</body>
</html>