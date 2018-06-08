<!DOCTYPE html>
<html>
<head>
    <title>训练营</title>
    <#--<script src="/static/js/layui/layui.js"></script>-->
    <script src="/static/js/battalions/common.js"></script>
    <script src="/static/js/battalions/battalion-home.js"></script>
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
    <div class="layui-row" style="margin-top: 10px">

        <div class="layui-col-xs2">
            <div class="battalion-left">
                <input id="typeId" type="hidden" value="0"/>
                <ul class="layui-nav layui-nav-tree layui-inline" lay-filter="demo"
                    style="margin-right: 10px;width: 120px">
                    <li class="layui-nav-item layui-this"><a href="javascript:;" id="0">不限</a></li>
                <#list types as type>
                <#--<c:forEach var="type" items="${types}">-->
                    <li class="layui-nav-item"><a href="javascript:;" id="${type.typeId}">${type.typeName}</a></li>
                <#--</c:forEach>-->
                </#list>
                </ul>
            </div>
        </div>

        <div class="layui-col-xs10">
            <div class="layui-col-md12">
                <div class="layui-col-md4">
                    <input type="text" id="name" name="title" required lay-verify="required"
                           placeholder="请输入训练营名称" autocomplete="off" class="layui-input">
                </div>
                <i onclick="search('name')" class="layui-icon" style="font-size: 35px; color: #668B8B;">&#xe615;</i>
            </div>

            <div class="layui-col-md12">

            <#if histories??>
            <p style="padding: 10px 0;color: #668B8B" id="histories">搜索历史
                <#list histories as history>
                    <p style="display: inline-flex;padding-bottom: 10px">
                        <a href="javascript:;" class="history"><span
                                style="padding-left: 10px;color: #666666;">${history}</span></a>
                        <input class="historyContent" value="${history}" type="hidden"/>
                    <#--<input class="historyId" value="${history.id}" type="hidden"/>-->
                        <a href="javascript:;" class="historyDel">
                            <i class="layui-icon" style="color: firebrick">&#x1006;</i>
                        </a>;
                    </p>
                </#list>
                <p id="hisInsert" type="hidden"/>
            </#if>
                </p>
            </div>

            <hr class="layui-bg-gray">
            <p style="color: #668B8B">排序方式</p>
            <div class="layui-col-md12">
                <p style="padding: 10px 60px;color: #668B8B">
                    <a href="javascript:;"><input type="radio" name="sort" value="null" title="null" checked="checked"></a>不限
                </p>
                <p style="padding: 10px 60px;color: #668B8B">
                    <span style="padding-right: 10px">报名人数:</span>
                    <a href="javascript:;"><input type="radio" name="sort" value="desc" title="enrollment"></a>多&emsp;
                    <a href="javascript:;"><input type="radio" name="sort" value="asc" title="enrollment"></a>少&emsp;
                </p>
                <p style="padding: 10px 60px;color: #668B8B">
                    <span style="padding-right: 10px">开始时间:</span>
                    <a href="javascript:;"><input type="radio" name="sort" value="asc" title="time_start"></a>最早
                    <a href="javascript:;"><input type="radio" name="sort" value="desc" title="time_start"></a>最晚
                </p>
                <p style="padding: 10px 60px;color: #668B8B">
                    <span style="padding-right: 10px">结束时间:</span>
                    <a href="javascript:;"><input type="radio" name="sort" value="asc" title="time_end"></a>最早
                    <a href="javascript:;"><input type="radio" name="sort" value="desc" title="time_end"></a>最晚
                </p>
                <p style="padding: 10px 60px;color: #668B8B">
                    <span style="padding-right: 10px">报名截止:</span>
                    <a href="javascript:;"><input type="radio" name="sort" value="asc" title="deadline"></a>最早
                    <a href="javascript:;"><input type="radio" name="sort" value="desc" title="deadline"></a>最晚
                </p>
            </div>

            <hr class="layui-bg-gray">

            <div id="battalion-page">
                <div id="battalions" class="layui-row" style="margin-top: 10px">

                    <ul>
                    <#list pageResponse.data as battalion>
                        <li>

                            <div class="layui-col-md4">
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
                    </#list>
                    </ul>
                </div>
                <div id="demo11" style="margin:0 auto;text-align:center; "></div>
            </div>

        </div>

    </div>
</div>

<script>
    var url = '${request.contextPath}/battalion/list';
    var totalCount = ${pageResponse.totalCount};
    if (totalCount > 0) {
//        $("#battalion").append('<div id="demo11" style="margin:0 auto;text-align:center; "></div>');
        usePage(totalCount,${pageResponse.pageSize} , url, successCallBack);
    }
</script>

<#include "/common/footer.ftl"/>
</body>
</html>