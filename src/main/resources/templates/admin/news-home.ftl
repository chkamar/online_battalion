<!DOCTYPE html>
<head>
    <title>新闻中心</title>
    <script src="/static/js/battalions/common.js"></script>
<#--<script src="/static/js/battalions/news-home.js"></script>-->
</head>
<body>
<#include "header.ftl"/>

<div class="layui-container">

<#--<div id="newsList">-->
    <div id="newsList" style="margin-top: 10px">
        <ul>
        <#list pageResponse.data as news>
            <li>

                <div class="layui-row" style="margin: 10px 0;border: 1px solid #f6f5f5">

                    <div class="layui-col-md2">
                        <a href="/news/details/${news.id}" target="_blank">
                            <img class="image" src="${news.imagePath}">
                        </a>
                    </div>

                    <div class="layui-col-md10" style="margin:5px auto">
                        <h3 class="news-title" style="margin-top: 10px">${news.title}
                            <a href="/news/admin/edit/${news.id}" style="float: right;padding-right: 10px"
                               target="_blank">修改</a>
                        </h3>
                    <#--<p id="news-summary">${news.summary}</p>-->
                        <h3 class="news-title" style="margin-top: 10px">${news.time}
                            <a href="javaScript:;" class="delete" style="float: right;padding-right: 10px">删除</a>
                            <input type="hidden" value="${news.id}"/>
                        </h3>
                    </div>

                </div>

            </li>
        </#list>
        </ul>
    </div>
    <div id="demo11" style="margin:20px auto;text-align:center; "></div>
<#--</div>-->

</div>

<script>
    bindDelete();
    var url = '${request.contextPath}/news/list';
    var totalCount = ${pageResponse.totalCount};

    if (totalCount > 0) {
        usePage(totalCount,${pageResponse.pageSize} , url, successCallBack);
    }

    function refreshList(newsList) {
        $("#newsList").empty();
        var htmlStr = '<ul>';
        console.log(newsList);
        for (var i = 0; i < newsList.length; i++) {
            var news = newsList[i];
            htmlStr += '<li> <div class="layui-row" style="margin: 10px 0;border: 1px solid #f6f5f5">'
                    + '<div class="layui-col-md2"> <a href="/news/details/'
                    + news.id
                    + '"> <img class="image" src="'
                    + news.imagePath
                    + '"> </a> </div> <div class="layui-col-md10" style="margin:5px auto"> <h3 id="news-title" style="margin-top: 10px">'
                    + news.title
                    + '<a href="/news/admin/edit/'
                    + news.id
                    + '" style="float: right;padding-right: 10px"target="_blank">修改</a> </h3> <h3 id="news-title" style="margin-top: 10px">'
                    + news.time
                    + 'href="javaScript:;" class="delete" style="float: right;padding-right: 10px">删除</a> '
                    + '<input type="hidden" value="'
                    + news.id
                    + '"/></h3> </div> </div> </li>';
        }
        htmlStr += '</ul>';
        console.log(htmlStr);

        //将html动态拼接到对应的div上面
        $('#newsList').html(htmlStr);
    }

    function bindDelete() {
        $(".delete").click(function () {
            //当id出现  1,234  这种加逗号的表示形式时，去除逗号，否则将无法从String("1,234")->Long(1234)
            var id = $(this).next().val().replace(/,/g, "");
            console.log(id);
            var url = '/news/admin/delete/' + id;
            var $this = $(this);
            ajaxGet(url, function (result) {
                if (result.status) {
                    $this.parent().parent().parent().parent().remove();
                } else {
                    errorCallBack();
                }
            }, errorCallBack);
        });
    }

</script>
</body>
</html>
