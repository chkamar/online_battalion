/**
 * Created by karma on 2018/5/6.
 */

function bindDele(){
    $(".deleteBattalion").click(function(){
        //当id出现  1,234  这种加逗号的表示形式时，去除逗号，否则将无法从String("1,234")->Long(1234)
        var id = $(this).parent().next().val().replace(/,/g, "");
        var url = '/battalion/admin/delete/'+id;

        var $this = $(this);
        ajaxGet(url,function(result){
            if(result.status){
                $this.parent().parent().remove();
            }
        },errorCallBack);
        //}
        //if (result.status == false) {
        //    alertMessage(result.msg);
        //} else {
        //    //刷新数据显示


        //$(this).parent().parent().empty();

    });
}

function bindHis() {
    $(".history").click(function () {
        $("#name").val($(this).next().val());
        search('history');
    });

    //TODO 完善
    $(".historyDel").click(function () {
        var content = $(this).prev().val();
        console.log(content);
        var url = '/searchHistory/delete/' + content;
        ajaxGet(url, null, errorCallBack);
        $(this).parent().empty();
        $(this).parent().remove();
        if ($(this).parent().parent().children().length == 1) {
            //此时历史搜索记录全部被删除
            $(this).parent().text('无');
        }
    });

    bindDele();
}

window.onload = function () {
    bindHis();

    //为排序方式单选框绑定点击事件
    $('input:radio[name="sort"]').click(function () {
        search('sort');
    });
    //开启垂直导航及监听点击
    layui.use('element', function () {
        element = layui.element; //导航的hover效果、二级菜单等功能，需要依赖element模块

        //监听导航点击
        element.on('nav(demo)', function (elem) {
            var typeId = elem.attr("id");
            //为隐藏域赋值，方便其他情况读取
            $("#typeId").attr('value', typeId);
            search('typeId');
        });
    });
}

function refreshList(battalions) {
    $("#battalions").empty();
    var htmlStr = '';
    console.log(battalions);
    for (var i = 0; i < battalions.length; i++) {
        if (i % 3 == 0) {
            htmlStr += '<div class="layui-row">'
        }
        var battalion = battalions[i];
        htmlStr += '<div class="layui-col-md4">'
            + '<div style="margin: 5px 5px;padding-bottom: 20px;padding-top: 5px;'
            + 'padding-left: 5px;padding-right: 5px;border: 1px solid #f6f5f5">'
            + '<div class="layui-col-md12"> <a href="/battalion/details/'
            + battalion.id
            + '"> <span style="font-size: large">'
            + battalion.name
            + '</span> </a> <span class="count">'
            + battalion.enrollment
            + '人报名</span> </div> <a href="/battalion/details/'
            + battalion.id
            + '"> <img class="image" src="'
            + battalion.imagePath
            + '"> </a> <div class="layui-col-md12"> <span class="date">'
            + battalion.timeStart
            + '到'
            + battalion.timeEnd
            + '</span> <span class="deadline">截止报名 '
            + battalion.deadline
            + '</span> </div> </div></div>';

        if (i % 3 == 2 || i == battalions.length - 1) {
            htmlStr += '</div>';
        }

    }

    console.log(htmlStr);
    //将html动态拼接到对应的div上面
    $('#battalions').html(htmlStr);
}

//function refreshPage(result) {
//    refreshList(result.data.data);
//
//    $("#demo11").empty();
//    if (result.data.totalCount > 0) {
//        //$("#battalion").append('<div id="demo11" style="margin:0 auto;text-align:center; "></div>');
//        //重新初始化分页数据
//        laypage.render({
//            elem: 'demo11'
//            , count: result.data.totalCount
//            , limit: result.data.pageSize
//            , jump: function (obj, first) {
//                //首次不执行
//                if (!first) {
//                    var pageRequest = JSON.stringify({
//                        'currentPage': obj.curr,
//                        'pageSize': obj.limit,
//                    });
//                    var url = 'list';//在此处暂时无法使用${request.contextPath}
//                    ajaxPost(url, pageRequest, successCallBack, errorCallBack);
//                }
//            }
//        });
//    } else {
//        //$("#battalion").append('<h2 style="text-align: center">暂无数据</h2>');
//    }
//
//
//}
var searchConditions = null;

function getSearchConditions() {
    var typeId = $("#typeId").val();
    var name = $("#name").val();
    var sort = $('input:radio[name="sort"]:checked').attr("title");
    var sc = $('input:radio[name="sort"]:checked').attr("value");

    searchConditions = {
        'typeId': typeId,
        'name': name,
        'sort': sort,
        'sc': sc
    }

}

function search(from) {
    if (from == 'name') {
        if ($("#name").val() == '') {
            return;
        }
        upsertHis($("#name").val());
    }
    getSearchConditions();
    var url = 'list';
    var pageRequest = JSON.stringify({
        'currentPage': 1,
        'pageSize': 6,
        'data': searchConditions
    });
    ajaxPost(url, pageRequest, refreshPage, errorCallBack);
}

function upsertHis(content) {
    if (loginStatus()) {
        ajaxGet('/searchHistory/upsert/' + content, refreshHis, errorCallBack);
    } else {
        refreshHis();
    }
}

function refreshHis() {
    $('<p style="display: inline-flex;padding-bottom: 10px"> <a href="javascript:;" class="history"><span style="padding-left: 10px;color: #666666;">'
        + $("#name").val()
        + '</span></a> <input class="historyContent" value="'
        + $("#name").val()
        + '" type="hidden"/><a href="javascript:;" class="historyDel"> <i class="layui-icon" style="color: firebrick">&#x1006;</i> </a>; </p>')
        .insertBefore($("#hisInsert"));
    //.append();
    bindHis();
}

function deleteById(){

}