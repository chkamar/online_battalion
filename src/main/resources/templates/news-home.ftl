<!DOCTYPE html>
<head>
    <title>新闻中心</title>
    <script src="/static/js/battalions/common.js"></script>
    <script src="/static/js/battalions/news-home.js"></script>
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
            <div id="newsList" style="margin-top: 10px">
            <ul>
            <#list pageResponse.data as news>
                <li>
                    <#--<div class="layui-row" style="margin: 10px 0;border: 1px solid #f6f5f5">-->

                        <#--<div class="layui-col-md2">-->
                            <#--&lt;#&ndash;<div class="layui-col-xs2">&ndash;&gt;-->
                            <#--<a href="/news/details/${news.id}" target="_blank">-->
                                <#--<img class="image" src="${news.imagePath}">-->
                            <#--</a>-->
                        <#--</div>-->

                        <#--<div class="layui-col-md10" style="margin:5px auto">-->
                            <#--&lt;#&ndash;<div class="layui-col-xs10">&ndash;&gt;-->
                            <#--<h3 class="news-title" style="margin-top: 10px">${news.title}-->
                                <#--<span style="float: right;padding-right: 10px"-->
                                   <#-->${news.time}</span>-->
                            <#--</h3>-->
                            <#--<h3 class="news-title" style="margin-top: 10px">-->
                                <#--${news.summary}-->
                            <#--</h3>-->
                        <#--</div>-->
                    <#--</div>-->
                        <div class="layui-row" style="margin: 10px 0;border: 1px solid #f6f5f5">

                            <div class="layui-col-md2">
                                <a href="/news/details/${news.id}" target="_blank">
                                    <img class="image" src="${news.imagePath}">
                                </a>
                            </div>

                            <div class="layui-col-md10" style="margin:5px auto">
                                <h3 class="news-title" style="margin-top: 10px">${news.title}
                                    <#--<a href="/news/admin/edit/${news.id}" style="float: right;padding-right: 10px"-->
                                       <#--target="_blank">修改</a>-->
                                </h3>
                            <#--<p id="news-summary">${news.summary}</p>-->
                                <h3 class="news-title" style="margin-top: 10px">${news.time}
                                    <#--<a href="javaScript:;" class="delete" style="float: right;padding-right: 10px">删除</a>-->
                                    <#--<input type="hidden" value="${news.id}"/>-->
                                </h3>
                            </div>

                        </div>
                </li>
            </#list>
            </ul>
            </div>
            <div id="demo11" style="margin:50px auto;text-align:center; "></div>
    </div>

<script>
    var url = '${request.contextPath}/news/list';
    var totalCount = ${pageResponse.totalCount};

    if (totalCount > 0) {
        usePage(totalCount,${pageResponse.pageSize} , url, successCallBack);
    }
</script>
<#--<#include "/common/footer.ftl"/>-->
</body>
</html>
