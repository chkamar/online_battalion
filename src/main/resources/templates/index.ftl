<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>小怪兽首页</title>

    <link rel="stylesheet" href="/static/css/style.css">
</head>
<body>
<#include "/common/header.ftl"/>
    <#if paths??>
        <div class="layui-carousel" id="index" style="margin-top: 10px">
            <div carousel-item>
                <#list paths as path>
                    <<img src="${path}">
                </#list>
            </div>
        </div>

    </#if>

    <div  class="layui-container" style="margin-top: 10px">
        <div class="hot-news">
            <h3>
                <hr class="layui-bg-green">
                <label style="color: #668B8B;;font-size: 30px">热点新闻</label>
                <a href="/news/home" style="float: right;color: #8FBC8F">更多</a>
            </h3>
            <div class="layui-row" style="margin-top: 10px">
                <div class="layui-col-xs4">
                    <div class="layui-col-md12" style="padding-top: 3px">
                        <a href="/news/details/${news[0].id}" style="width: 95%">
                            <img class="image" src="${news[0].imagePath}">
                            <span id="top1Title" style="margin:10px auto;display: block;text-align: center">${news[0].title}</span>
                        </a>
                    </div>
                </div>
                <div class="layui-col-xs8">
                    <ul>
                        <#list 1..3 as index>
                            <#if (news?size>index)>
                            <li>
                                <a href="/news/details/${news[index].id}">
                                    <div class="layui-col-md12" style="padding-top: 3px">
                                        <div class="layui-col-md2">
                                            <img class="image" src="${news[index].imagePath}">
                                        </div>
                                        <div class="layui-col-md10">
                                            <h3 id="news-title">
                                                ${news[index].title}
                                                <#--<span id="news-date" style="float: right" >${news[index].time}</span>-->
                                            </h3>
                                            <p id="news-summary" style="padding-top: 20px">${news[index].time}</p>
                                            <#--<p id="news-summary">${news[index].summary}</p>-->
                                        </div>
                                    </div>
                                </a>
                            </li>
                            <#if index!=3><hr class="layui-bg-gray"></#if>
                            </#if>
                        </#list>

                    </ul>

                </div>
            </div>
        </div>


        <h3>
            <hr class="layui-bg-green">
            <label style="color: #668B8B;font-size: 30px">训练营推荐</label>
            <a href="/battalion/home" style="float: right;color: #8FBC8F">更多</a>
        </h3>
        <div id="battalion-page">


            <div class="layui-row" style="margin-top: 10px">

            <ul>
            <#list battalions as battalion>
                <li>

                    <div class="layui-col-md4" style="height: 280px">
                        <div style="margin: 5px 5px;padding-bottom: 20px;padding-top: 5px;
                                    padding-left: 5px;padding-right: 5px;border: 1px solid #f6f5f5">
                            <div class="layui-col-md12">
                                <a href="/battalion/details/${battalion.id}">
                                    <span style="font-size: large">${battalion.name}</span>
                                </a>
                                <span class="count">${battalion.enrollment}
                                    人报名</span>

                            </div>
                            <a href="/battalion/details/${battalion.id}">
                                <img class="image" src="${battalion.imagePath}">
                            </a>
                            <div class="layui-col-md12">
                                        <span class="date">${battalion.timeStart}
                                            到${battalion.timeEnd}</span>
                                <span class="deadline">截止报名 ${battalion.deadline}</span>
                            </div>
                        </div>
                    </div>

                </li>
            </ul>
            </#list>
            </div>
        </div>


    </div>

    <#include "/common/footer.ftl"/>
</body>
</html>
