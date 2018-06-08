/**
 * Created by karma on 2018/5/8.
 */

function refreshList(newsList){
    $("#newsList").empty();
    var htmlStr = '<ul>';
    console.log(newsList);
    for(var i=0;i<newsList.length;i++){
        var news  = newsList[i];
        htmlStr += '<li> <div class="layui-row" style="margin: 10px 0;border: 1px solid #f6f5f5">'
            +'<div class="layui-col-md2"> <a href="/news/details/'
            +news.id
            +'" target="_blank"> <img class="image" src="'
            +news.imagePath
            +'"> </a> </div> <div class="layui-col-md10"> <h3 class="news-title" style="margin-top: 10px">'
            +news.title
            +'<span style="float: right;padding-right: 10px" >'
            +news.time
            +'</span> </h3> <h3 class="news-title" style="margin-top: 10px">'
            +news.summary
            +'</h3> </div> </div> </li>';
    }
    htmlStr += '</ul>';
    console.log(htmlStr);

    //将html动态拼接到对应的div上面
    $('#newsList').html(htmlStr);
}

