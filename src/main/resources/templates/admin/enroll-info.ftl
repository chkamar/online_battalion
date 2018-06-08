<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>报名信息</title>
    <script src="/static/js/battalions/common.js"></script>
</head>
<body>
<#include "header.ftl"/>
<div class="layui-container" style="margin-top: 10px">
    <div class="layui-row" style="margin-top: 10px">

        <#--<div class="layui-col-xs2">-->
            <#--<p style="color: #009E94">报名人数：${pageResponse.totalCount}</p>-->
        <#--</div>-->

        <#--<div class="layui-col-xs10">-->

            <div id="enrollment-page">
                <div id="enrollments" class="layui-row">
                    <div class="layui-col-md12">
                        <table>
                            <tbody>
                            <tr class="recruitments-title">
                                <td class="positionName">用户</td>
                                <td class="positionOther">时间</td>
                                <td class="positionOther">状态</td>
                                <td class="positionOther">操作</td>
                            </tr>

                            <#list pageResponse.data as enrollment>
                            <tr>
                                <td class="positionName">${enrollment.username}</td>
                                <td class="positionOther">${enrollment.time}</td>
                                <td class="positionOther">
                                    <#if enrollment.status==1>
                                        已确认
                                    <#else>
                                        未确认
                                    </#if>
                                </td>
                                <td class="positionOther">
                                    <#if enrollment.status==1>
                                        已确认
                                    <#else>
                                        <a style="padding-right: 5px"
                                           href="javaScript:;" class="confirm">确认</a>
                                    </#if>
                                    <a href="javaScript:;" class="delete">删除</a>
                                </td>
                                <input type="hidden" value="${enrollment.id}"/>
                            </tr>
                            </#list>

                            </tbody>
                        </table>
                    </div>
                </div>
                <div id="demo11" style="margin:0 auto;text-align:center; "></div>
            </div>

        <#--</div>-->

    </div>
</div>
<script>
    bind();

    var url = '${request.contextPath}/battalionUserRelation/admin/list';
    var totalCount = ${pageResponse.totalCount};
    if (totalCount > 0) {
        usePage(totalCount,${pageResponse.pageSize} , url, successCallBack);
    }

    function refreshList(enrollments) {
        $("#enrollments").empty();
        var htmlStr = '<div class="layui-col-md12"> <table> <tbody>'
                + '<tr class="recruitments-title">'
                + '<td class="positionName">用户</td>'
                + '<td class="positionOther">时间</td>'
                + '<td class="positionOther">状态</td>'
                + '<td class="positionOther">操作</td>'
                + '</tr>';
        console.log(enrollments);
        for (var i = 0; i < enrollments.length; i++) {
            var enrollment = enrollments[i];
            htmlStr += '<tr>'
                    + '<td class="positionName">' + enrollment.username + '</td> '
                    + '<td class="positionOther">'
            enrollment.time + '</td>'
            + '<td class="positionOther">';
            if (enrollment.status == 1) {
                htmlStr += '已确认';
            } else {
                htmlStr += '未确认'
            }
            htmlStr += '</ td> <td class="positionOther">';
            if (enrollment.status == 1) {
                htmlStr += '已确认';
            } else {
                htmlStr += '< a style = "padding-right: 5px" href="javaScript:;" class="confirm"> 确认 < / a >';
            }
            htmlStr += '<a href="javaScript:;" class="delete" >删除</a></td><input type ="hidden"value = "'
            +enrollment.id+'" / > < / tr >';
        }
        htmlStr += '</tbody> </table> </div>';
        console.log(htmlStr);
        //将html动态拼接到对应的div上面
        $('#enrollments').html(htmlStr);
    }



    function bind() {
        $(".confirm").click(function () {
            //当id出现  1,234  这种加逗号的表示形式时，去除逗号，否则将无法从String("1,234")->Long(1234)
            var id = $(this).parent().next().val().replace(/,/g, "");
            console.log(id);
            var url = '/battalionUserRelation/admin/confirm/' + id;
            var $this = $(this);
            ajaxGet(url, function (result) {
                if (result.status) {
                    $this.html('已确认');
                    $this.parent().prev().html('已确认');
                } else {
                    errorCallBack();
                }
            }, errorCallBack);
        });

        $(".delete").click(function () {
            //当id出现  1,234  这种加逗号的表示形式时，去除逗号，否则将无法从String("1,234")->Long(1234)
            var id = $(this).parent().next().val().replace(/,/g, "");
            console.log(id);
            var url = '/battalionUserRelation/admin/delete/' + id;
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