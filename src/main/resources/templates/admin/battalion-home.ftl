<!DOCTYPE html>
<html>
<head>
    <title>训练营</title>
<#--<script src="/static/js/layui/layui.js"></script>-->
    <script src="/static/js/battalions/common.js"></script>
    <script src="/static/js/battalions/battalion-home.js"></script>
    <#--<script src="/static/js/battalions/account.js"></script>-->

</head>
<body>
<#include "header.ftl"/>
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
            </p>
            <hr class="layui-bg-gray">
            </#if>

            </div>

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
                <div id="battalions" class="layui-row">
                        <div class="layui-col-md12">
                            <table>
                                <tbody>
                                <tr class="recruitment-title">
                                    <td class="positionName">名称</td>
                                    <td class="positionOther">类型</td>
                                    <td class="positionOther">状态</td>
                                    <td class="positionOther">操作</td>
                                    <td class="positionOther">报名信息</td>
                                </tr>

                                <#list pageResponse.data as battalion>
                                <tr>
                                    <td class="positionName">
                                        <a href="${request.contextPath}/battalion/details/${battalion.id}" target="_Blank">${battalion.name!'-'}</a>
                                    </td>
                                    <td class="positionOther">${battalion.type!'-'}</td>
                                    <td class="positionOther">${battalion.status!'-'}</td>
                                    <td class="positionOther"><a style="padding-right: 5px" href="/battalion/admin/edit/${battalion.id}">修改</a>
                                        <a href="javaScript:;" class="deleteBattalion">删除</a></td>
                                    <input type="hidden" value="${battalion.id}"/>
                                    <td class="positionOther"><a href="/battalionUserRelation/admin/details/${battalion.id}">查看</a></td>
                                </tr>
                                </#list>

                                </tbody>
                            </table>
                        </div>
                    </div>
                <div id="demo11" style="margin:0 auto;text-align:center; "></div>
            </div>

        </div>

    </div>
</div>

<script>
    var url = '${request.contextPath}/battalion/admin/list';
    var totalCount = ${pageResponse.totalCount};
    if(totalCount>0){
//        $("#recruitment-page").append('<div id="demo11" style="margin:0 auto;text-align:center; "></div>');
        usePage(totalCount,${pageResponse.pageSize},url,successCallBack);
    }

    //开启垂直导航及监听点击
    layui.use('element', function () {
        element = layui.element; //导航的hover效果、二级菜单等功能，需要依赖element模块

        //监听导航点击
        element.on('nav(demo)', function (elem) {
            var category = elem.attr("title");
            var pageRequest = JSON.stringify({
                'currentPage': 1,
                'pageSize': 10,
                'data': category
            });
            ajaxPost(url, pageRequest, refreshPage, errorCallBack);
        });
    });

    function refreshList(battalions) {
        $("#battalions").empty();
        var htmlStr = '<div class="layui-col-md12"> <table> <tbody> <tr class="recruitment-title">'
                +'<td class="positionName">名称</td>'
                +'<td class="positionOther">类型</td>'
                +'<td class="positionOther">状态</td>'
                +'<td class="positionOther">操作</td>'
                +'<td class="positionOther">报名信息</td> </tr>';
        console.log(battalions);
        for (var i = 0; i < battalions.length; i++) {
            var battalion = battalions[i];
            htmlStr += '<tr> <td class="positionName"> <a href="/battalion/details/'
                    +battalion.id
                    +'">'
                    +battalion.name
                    +'</a> </td> <td class="positionOther">'
                    +battalion.type
                    +'</td> <td class="positionOther">'
                    +battalion.status
                    +'</td> <td class="positionOther"><a style="padding-right: 5px" href="/battalion/admin/edit/'
                    +battalion.id
                    +'">修改</a> <a href="javaScript:;" class="deleteBattalion">删除</a>'
                    +'</td><input type="hidden" value="'
                    +battalion.id
                    +'"/> <td class="positionOther"><a href="/battalionUserRelation/admin/details/'
                    +battalion.id
                    +'">查看</a>'
                    +'</td> </tr>';
        }
        htmlStr += '</tbody> </table> </div>';
        console.log(htmlStr);
        //将html动态拼接到对应的div上面
        $('#battalions').html(htmlStr);
        bindDele();
    }

</script>

<#--<#include "footer.ftl"/>-->
</body>
</html>