<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>招聘管理中心</title>
    <script src="/static/js/battalions/common.js"></script>
</head>
<body>
<#include "header.ftl"/>
<div class="layui-container" style="margin-top: 10px">
<div class="layui-row" style="margin-top: 10px">

    <div class="layui-col-xs2">
        <div class="battalion-left">
            <#--<input id="typeId" type="hidden" value="0"/>-->
            <ul class="layui-nav layui-nav-tree layui-inline" lay-filter="demo"
                style="margin-right: 10px;width: 120px">
                <li class="layui-nav-item layui-this"><a href="javascript:;" id="0">不限</a></li>
                <li class="layui-nav-item"><a href="javascript:;" title="校园招聘">校园招聘</a></li>
                <li class="layui-nav-item"><a href="javascript:;" title="社会招聘">社会招聘</a></li>
            </ul>
        </div>
    </div>

    <div class="layui-col-xs10">

        <div id="recruitment-page">
            <div id="recruitments" class="layui-row">
                <div class="layui-col-md12">
                            <table>
                                <tbody>
                                    <tr class="recruitments-title">
                                        <td class="positionName">职位名称</td>
                                        <#--<td class="positionOther">职位类型</td>-->
                                        <td class="positionOther">工作性质</td>
                                        <td class="positionOther">工作地点</td>
                                        <td class="positionOther">发布时间</td>
                                        <td class="positionOther">操作</td>
                                    </tr>

                                    <#list pageResponse.data as recruitment>
                                        <tr>
                                            <td class="positionName">
                                                <a href="${request.contextPath}/recruitment/details/${recruitment.id}" target="_blank">
                                                    ${recruitment.positionName}
                                                </a>
                                            </td>
                                            <#--<td class="positionOther">${recruitment.positionType}</td>-->
                                            <td class="positionOther">${recruitment.jobNature}</td>
                                            <td class="positionOther">${recruitment.workingPlace}</td>
                                            <td class="positionOther">${recruitment.publishTime}</td>
                                            <td class="positionOther">
                                                <a style="padding-right: 5px" href="/recruitment/admin/edit/${recruitment.id}">修改</a>
                                                <a href="javaScript:;" class="delete">删除</a>
                                            </td>
                                            <input type="hidden" value="${recruitment.id}"/>
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
    bindDelete();

    var url = '${request.contextPath}/recruitment/list';
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

    function refreshList(recruitments) {
        $("#recruitments").empty();
        var htmlStr = '<div class="layui-col-md12"> <table> <tbody> <tr class="recruitments-title"> ' +
                '<td class="positionName">职位名称</td> ' +
//                '<td class="positionOther">职位类型</td> ' +
                '<td class="positionOther">工作性质</td> <td class="positionOther">工作地点</td> ' +
                '<td class="positionOther">发布时间</td> <td class="positionOther">操作</td></tr>';
        console.log(recruitments);
        for (var i = 0; i < recruitments.length; i++) {
            var recruitment = recruitments[i];
            htmlStr += '<tr> <td class="positionName"> <a href="/recruitment/details/'
                    +recruitment.id
                    +'" target="_blank">'
                    +recruitment.positionName
                    +'</a> </td> ' +
//                    '<td class="positionOther">'
//                    +recruitment.positionType
//                    +'</td> ' +
                    '<td class="positionOther">'
                    +recruitment.jobNature
                    +'</td> <td class="positionOther">'
                    +recruitment.workingPlace
                    +'</td> <td class="positionOther">'
                    +recruitment.publishTime
                    +'</td> <td class="positionOther"> <a style="padding-right: 5px" href="/recruitment/admin/edit/'
                    +recruitment.id
                    +'">修改</a> <a href="javaScript:;" class="delete">删除</a> </td> <input type="hidden" value="'
                    +recruitment.id
                    +'"/></tr>';

        }
        htmlStr += '</tbody> </table> </div>';
        console.log(htmlStr);
        //将html动态拼接到对应的div上面
        $('#recruitments').html(htmlStr);
        bindDelete();
    }

    function bindDelete() {
        $(".delete").click(function () {
            //当id出现  1,234  这种加逗号的表示形式时，去除逗号，否则将无法从String("1,234")->Long(1234)
            var id = $(this).parent().next().val().replace(/,/g, "");
            console.log(id);
            var url = '/recruitment/admin/delete/' + id;
            var $this = $(this);
            ajaxGet(url, function (result) {
                if (result.status) {
                    $this.parent().parent().remove();
                } else {
                    errorCallBack();
                }
            }, errorCallBack);
        });
    }


</script>
<#--<#include "/common/footer.ftl"/>-->
</body>
</html>