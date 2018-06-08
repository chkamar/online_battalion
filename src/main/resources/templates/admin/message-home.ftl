<!DOCTYPE html>
<html>
<head>
    <title>留言列表</title>
    <script src="/static/js/battalions/common.js"></script>
</head>
<body>
<#include "/admin/header.ftl"/>

<div id="message-page" class="layui-container">
    <div id="messages" class="layui-row">
        <div class="layui-col-md12">
            <table>
                <tbody>
                <tr style="background: #ebebeb;">
                    <td style="width: 20%">用户</td>
                    <td style="width: 50%">内容</td>
                    <td style="width: 20%">留言时间</td>
                    <td style="width: 10%">操作</td>
                </tr>

                <#list pageResponse.data as message>
                <tr>
                    <td style="width: 20%">
                    ${message.userName!'-'}
                    </td>
                    <td style="text-align:center;height:inherit;width: 50%">
                    ${message.content!'-'}
                    </td>
                    <td style="width: 20%">${message.time!'-'}</td>
                    <td style="width: 10%">
                        <a href="javaScript:;" class="delete">删除</a>
                    </td>
                    <input type="hidden" value="${message.id}"/>
                </tr>
                </#list>

                </tbody>
            </table>
        </div>
    </div>
    <div id="demo11" style="margin:0 auto;text-align:center;padding-top: 20px "></div>
</div>


<script>
    bindDelete();
    var url = '${request.contextPath}/message/admin/list';
    var totalCount = ${pageResponse.totalCount};
    if (totalCount > 0) {
//        $("#recruitment-page").append('<div id="demo11" style="margin:0 auto;text-align:center; "></div>');
        usePage(totalCount,${pageResponse.pageSize} , url, successCallBack);
    }

    //开启垂直导航及监听点击
    layui.use('element', function () {
        element = layui.element; //导航的hover效果、二级菜单等功能，需要依赖element模块

//        //监听导航点击
//        element.on('nav(demo)', function (elem) {
//            var category = elem.attr("title");
//            var pageRequest = JSON.stringify({
//                'currentPage': 1,
//                'pageSize': 10,
//                'data': category
//            });
//            ajaxPost(url, pageRequest, refreshPage, errorCallBack);
//        });
    });

    function refreshList(messages) {
        $("#messages").empty();
        var htmlStr = '<div class="layui-col-md12">'
                + '<table>'
                + '<tbody>'
                + '<tr style="background: #ebebeb;">'
                + '<td style="width: 20%">用户</td>'
                + '<td style="width: 50%">内容</td>'
                + '<td style="width: 20%">留言时间</td>'
                + '<td style="width: 10%">操作</td>'
                + '</tr>'
                + '</tr>';
        console.log(messages);
        for (var i = 0; i < messages.length; i++) {
            var message = messages[i];
            htmlStr += '<tr><td style="width: 20%">'
                    + message.userName
                    + '</td><td style= "text-align:center;height:inherit;width: 50%">'
                    + message.content
                    + '</td><td style="width: 20%">'
                    + message.time
                    + '</td><td style="width: 10%">'
                    + '<a href="javaScript:;" class="delete">删除</a></td>'
                    + '<input type="hidden" value="'
                    + message.id
                    + '"/></tr>';
        }
        htmlStr += '</tbody> </table> </div>';
        console.log(htmlStr);
        //将html动态拼接到对应的div上面
        $('#messages').html(htmlStr);
        bindDelete();
    }

    function bindDelete() {
        $(".delete").click(function () {
            //当id出现  1,234  这种加逗号的表示形式时，去除逗号，否则将无法从String("1,234")->Long(1234)
            var id = $(this).parent().next().val().replace(/,/g, "");
            console.log(id);
            var url = '/message/admin/delete/' + id;
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

</body>
</html>