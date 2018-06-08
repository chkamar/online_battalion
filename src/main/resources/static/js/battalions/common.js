/**
 * Created by karma on 2018/5/4.
 */
var laypage,layer = layui.layer,element;

function useCarousel(){
    layui.use('carousel', function () {
        var carousel = layui.carousel;
        //建造实例
        carousel.render({
            elem: '#index'
            , width: '100%' //设置容器宽度
            , height: '300px'
            , arrow: 'none' //始终显示箭头

            //,anim: 'updown' //切换动画方式
        });
    });
}
function usePage(totalCount,pageSize,url,successCallBack){
    layui.use(['laypage', 'layer'], function() {
        laypage = layui.laypage;
        layer = layui.layer;

        //自定义每页条数的选择项
        laypage.render({
            elem: 'demo11'
            ,count: totalCount
            ,limit: pageSize
            ,jump: function(obj, first){
                //首次不执行
                if(!first){
                    var pageRequest = JSON.stringify({
                        'currentPage': obj.curr,
                        'pageSize': obj.limit,
                    });
                    //var url = 'list';//或'${request.contextPath}/battalion/list'
                    ajaxPost(url,pageRequest,successCallBack,errorCallBack);
                }
            }
        });

    });
}

function ajaxGet(url,successCallBack,errorCallBack){
    $.ajax({
        url:url,
        type:'GET',
        success:function(result){
            if(successCallBack!=null){
                successCallBack(result)
            }
        },
        error:function(message){
            if(errorCallBack!=null){
                errorCallBack(message)
            }
        }
    });
}

function ajaxPost(url,data,successCallBack,errorCallBack){
    $.ajax({
        url:url,
        data:data,
        type:'POST',
        dataType:'json',
        contentType: 'application/json;charset=utf-8',
        success:function(result){
            if(successCallBack!=null){
                successCallBack(result)
            }
        },
        error:function(message){
            if(errorCallBack!=null){
                errorCallBack(message)
            }
        }
    });
}

function successCallBack(result) {
    if (result.status == false) {
        errorCallBack(result.msg);
    } else {
        //刷新数据显示
        refreshList(result.data.data);
    }
}

function alertMessage(message){
    if(!message){
        message = '发生错误';
    }
    alert(message);
    //layer.open({
    //    type: 1
    //    ,offset: 't'
    //    ,id: 'layerDemot' //防止重复弹出
    //    ,content: '<div style="padding: 20px 100px;">'+ message +'</div>'
    //    ,btn: '关闭'
    //    ,btnAlign: 'c' //按钮居中
    //    ,shade: 0 //不显示遮罩
    //    ,yes: function(){
    //        layer.closeAll();
    //    }
    //});
}


function errorCallBack(){
    alertMessage('操作失败');
}

function refreshPage(result) {
    refreshList(result.data.data);

    $("#demo11").empty();
    if (result.data.totalCount > 0) {
        //$("#battalion").append('<div id="demo11" style="margin:0 auto;text-align:center; "></div>');
        //重新初始化分页数据
        laypage.render({
            elem: 'demo11'
            , count: result.data.totalCount
            , limit: result.data.pageSize
            , jump: function (obj, first) {
                //首次不执行
                if (!first) {
                    var pageRequest = JSON.stringify({
                        'currentPage': obj.curr,
                        'pageSize': obj.limit,
                    });
                    var url = 'list';//在此处暂时无法使用${request.contextPath}
                    ajaxPost(url, pageRequest, successCallBack, errorCallBack);
                }
            }
        });
    } else {
        //$("#battalion").append('<h2 style="text-align: center">暂无数据</h2>');
    }


}