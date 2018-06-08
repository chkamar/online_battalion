/**
 * Created by karma on 2018/5/16.
 */

function refreshImageHtml(){
    var url = '/image/admin/get/index';
    ajaxGet(url,function(res){
        console.log(res);
        if(res.status){
            $("#body").html(res.data);
            useUpload();
        }else{
            errorCallBack(res.msg);
        }
    },errorCallBack);

}

function imgHtml(res){
    var images = res.data;

    $("#body").empty();
    var htmlStr = '<div style="padding: 30px 50px"> <h2 style="padding: 30px">首页轮播图片</h2>';
    console.log(images);
    for (var i = 0; i < images.length; i++) {
        if(i%3==0){
            htmlStr += '<div class="layui-row">'
        }
        var image = images[i];
        htmlStr += '<div class="layui-col-md4"> <div class="layui-col-md12" align="center"> <img class="image" src="'
            +image.path+
            '"> <button id="'
            +image.id
            +'" type="button" class="layui-btn" onclick="deleImg(this)">删除图片</button> </div> </div>';

        if(i%3==2||i==images.length-1){
            htmlStr += '</div>';
        }

    }
    htmlStr += '</div> <div class="layui-upload" align="center"> <button type="button" class="layui-btn" id="indexImgUpload">上传图片</button> </div> <br>';
    console.log(htmlStr);
    //将html动态拼接到对应的div上面
    $('#body').html(htmlStr);
    //声明upload组件
    useUpload();
}

function imageManage(){
    //获取现有图片并更新页面
    var url = '/image/admin/get';
    ajaxGet(url,imgHtml,errorCallBack);
}